package com.github.vshat.randomusergenerator.view;


import com.github.vshat.randomusergenerator.model.data.User;

import java.util.List;

public interface MvpView {
    void showData(List<User> list);

    void showError(String error);

    void showEmptyList();
}
