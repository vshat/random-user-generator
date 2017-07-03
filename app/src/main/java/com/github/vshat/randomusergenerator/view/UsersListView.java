package com.github.vshat.randomusergenerator.view;


import com.github.vshat.randomusergenerator.model.data.UserDTO;
import com.github.vshat.randomusergenerator.presenter.vo.UserBriefInfo;

import java.util.List;

public interface UsersListView {
    void showData(List<UserBriefInfo> list);

    void showError(String error);

    void showEmptyList();
}
