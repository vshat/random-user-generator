package com.github.vshat.randomusergenerator.presenter;


import com.github.vshat.randomusergenerator.model.Model;
import com.github.vshat.randomusergenerator.model.ModelImpl;
import com.github.vshat.randomusergenerator.model.data.ApiResponseDTO;
import com.github.vshat.randomusergenerator.model.data.UserDTO;
import com.github.vshat.randomusergenerator.presenter.mappers.UserBriefInfoMapper;
import com.github.vshat.randomusergenerator.presenter.vo.UserBriefInfo;
import com.github.vshat.randomusergenerator.view.UsersListView;

import java.util.Collections;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.observers.DisposableObserver;

public class UsersListPresenter {
    private final static int USERS_COUNT = 10;

    private List<UserDTO> userDTOs;

    private Model model = new ModelImpl();

    private UsersListView view;
    private Disposable disposable = Disposables.empty();

    public UsersListPresenter(UsersListView view) {
        this.view = view;
        loadUsers();
    }

    private void loadUsers() {

        if (!disposable.isDisposed()) {
            disposable.dispose();
        }

        disposable = model.getUsersList(USERS_COUNT)
                .subscribeWith(new DisposableObserver<ApiResponseDTO>() {
                    @Override
                    public void onNext(@NonNull ApiResponseDTO apiResponseDTO) {
                        if (apiResponseDTO != null) {
                            if (apiResponseDTO.getError() == null) {
                                processUserDTOs(apiResponseDTO.getUserDTOs());
                            }
                        } else {
                            view.showError("Null response");
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.showError(e.getMessage());
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void processUserDTOs(List<UserDTO> userDTOs) {
        this.userDTOs = userDTOs;
        sortUserDTOs(this.userDTOs);
        List<UserBriefInfo> usersBriefInfo = UserBriefInfoMapper.map(this.userDTOs);
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

    public void onStop() {
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }


}
