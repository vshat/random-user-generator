package com.github.vshat.randomusergenerator.presenter;


import com.github.vshat.randomusergenerator.model.Model;
import com.github.vshat.randomusergenerator.model.ModelImpl;
import com.github.vshat.randomusergenerator.model.data.ApiResponseDTO;
import com.github.vshat.randomusergenerator.presenter.mappers.UserBriefInfoMapper;
import com.github.vshat.randomusergenerator.presenter.vo.UserBriefInfo;
import com.github.vshat.randomusergenerator.view.UsersListView;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.observers.DisposableObserver;

public class UsersListPresenter {
    private final static int USERS_COUNT = 10;

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
                                List<UserBriefInfo> users = UserBriefInfoMapper.map(apiResponseDTO.getUserDTOs());
                                view.showData(users);
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

    public void onStop() {
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
