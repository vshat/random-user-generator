package com.github.vshat.randomusergenerator.presenter;


import com.github.vshat.randomusergenerator.model.Model;
import com.github.vshat.randomusergenerator.model.ModelImpl;
import com.github.vshat.randomusergenerator.model.data.ApiResponse;
import com.github.vshat.randomusergenerator.view.MvpView;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.observers.DisposableObserver;

public class UsersListPresenter {
    private final static int USERS_COUNT = 10;

    private Model model = new ModelImpl();

    private MvpView view;
    private Disposable disposable = Disposables.empty();

    public UsersListPresenter(MvpView view) {
        this.view = view;
        loadUsers();
    }

    private void loadUsers() {

        if (!disposable.isDisposed()) {
            disposable.dispose();
        }

        disposable = model.getUsersList(USERS_COUNT)
                .subscribeWith(new DisposableObserver<ApiResponse>() {
                    @Override
                    public void onNext(@NonNull ApiResponse apiResponse) {
                        if (apiResponse != null) {
                            if (apiResponse.getError() == null) {
                                view.showData(apiResponse.getUsers());
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
