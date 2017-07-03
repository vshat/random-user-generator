package com.github.vshat.randomusergenerator.view.fragments;


import com.github.vshat.randomusergenerator.model.data.UserDTO;
import com.github.vshat.randomusergenerator.presenter.vo.UserBriefInfo;
import com.github.vshat.randomusergenerator.presenter.vo.UserDetailInfo;

import java.util.List;

public interface UsersListView {
    void showData(List<UserBriefInfo> list);

    void showError(String error);

    void startUserDetailFragment(UserDetailInfo userDetailInfo);
}
