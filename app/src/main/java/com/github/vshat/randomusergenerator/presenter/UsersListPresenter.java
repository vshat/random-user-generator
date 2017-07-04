package com.github.vshat.randomusergenerator.presenter;


import android.os.Bundle;

import com.github.vshat.randomusergenerator.model.Model;
import com.github.vshat.randomusergenerator.model.ModelImpl;
import com.github.vshat.randomusergenerator.model.data.ApiResponseDTO;
import com.github.vshat.randomusergenerator.model.data.UserDTO;
import com.github.vshat.randomusergenerator.presenter.mappers.UserBriefInfoListMapper;
import com.github.vshat.randomusergenerator.presenter.mappers.UserDetailInfoMapper;
import com.github.vshat.randomusergenerator.presenter.vo.UserBriefInfo;
import com.github.vshat.randomusergenerator.presenter.vo.UserDetailInfo;
import com.github.vshat.randomusergenerator.view.fragments.UsersListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.observers.DisposableObserver;

public class UsersListPresenter {

    private final static int USERS_COUNT = 10;
    private static final String BUNDLE_USER_DTOS_KEY = "bundle_user_dtos_key";
    private static final String BUNDLE_IS_IN_CRITICAL_ERROR_STATE = "bundle_is_in_critical_error_state";
    private boolean isInCriticalErrorState = false;

    private List<UserDTO> userDTOs;

    private Model model = new ModelImpl();

    private UsersListView view;
    private Disposable disposable = Disposables.empty();

    public UsersListPresenter(UsersListView view) {
        this.view = view;
    }

    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            userDTOs = (List<UserDTO>) savedInstanceState.getSerializable(BUNDLE_USER_DTOS_KEY);
            isInCriticalErrorState = savedInstanceState.getBoolean(BUNDLE_IS_IN_CRITICAL_ERROR_STATE);
        }

    }

    public void onResume(boolean isOnline) {
        if(!isInCriticalErrorState) {
            if (!isUserDTOsEmpty()) {
                processUserDTOs(userDTOs);
            } else {
                if (isOnline) {
                    loadUsers();
                } else {
                    view.showOfflineError();
                }
            }
        }
    }

    private boolean isUserDTOsEmpty() {
        return userDTOs == null || userDTOs.isEmpty();
    }

    public void onSaveInstanceState(Bundle outState) {
        if (!isUserDTOsEmpty()) {
            outState.putSerializable(BUNDLE_USER_DTOS_KEY, new ArrayList<>(userDTOs));
        }
        outState.putBoolean(BUNDLE_IS_IN_CRITICAL_ERROR_STATE, isInCriticalErrorState);
    }

    public void onStop() {
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public void onUserSelected(int position) {
        UserDTO selectedUserDTO = userDTOs.get(position);
        UserDetailInfo userDetailInfo = UserDetailInfoMapper.map(selectedUserDTO);
        view.startUserDetailFragment(userDetailInfo);
    }

    public void onSettingsClick() {
        view.openNetworkSettings();
    }

    public void onExitClick() {
        view.exit();
    }

    private void loadUsers() {

        if (!disposable.isDisposed()) {
            disposable.dispose();
        }

        view.showLoading();

        disposable = model.getUsersList(USERS_COUNT)
                .subscribeWith(new DisposableObserver<ApiResponseDTO>() {
                    @Override
                    public void onNext(@NonNull ApiResponseDTO apiResponseDTO) {
                        if (apiResponseDTO != null) {
                            String apiError = apiResponseDTO.getError();
                            if (apiError == null) {
                                processUserDTOs(apiResponseDTO.getUserDTOs());
                            } else {
                                isInCriticalErrorState = true;
                                view.showApiError(apiError);
                            }
                        } else {
                            isInCriticalErrorState = true;
                            view.showUnknownApiResponse();
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.hideLoading();
                        view.showAppError(e.getMessage());
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        view.hideLoading();
                    }
                });
    }

    private void processUserDTOs(List<UserDTO> userDTOs) {
        this.userDTOs = userDTOs;
        sortUserDTOs(this.userDTOs);
        List<UserBriefInfo> usersBriefInfo = UserBriefInfoListMapper.map(this.userDTOs);
        view.showData(usersBriefInfo);
    }

    private void sortUserDTOs(List<UserDTO> userDTOs) {
        Collections.sort(userDTOs, (u1, u2) -> {
            int c;
            c = u1.getNameDTO().getFirst().compareTo(u2.getNameDTO().getFirst());
            if (c == 0) {
                c = u1.getNameDTO().getLast().compareTo(u2.getNameDTO().getLast());
            }
            return c;
        });
    }

}
