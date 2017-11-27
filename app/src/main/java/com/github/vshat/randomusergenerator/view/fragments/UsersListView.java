package com.github.vshat.randomusergenerator.view.fragments;

import com.github.vshat.randomusergenerator.presenter.vo.UserBriefInfo;
import com.github.vshat.randomusergenerator.presenter.vo.UserDetailInfo;

import java.util.List;

public interface UsersListView {
    void showData(List<UserBriefInfo> list);

    void startUserDetailFragment(UserDetailInfo userDetailInfo);

    void showLoading();

    void hideLoading();

    void showOfflineError();

    void openNetworkSettings();

    void exit();

    void showApiError(String apiError);

    void showAppError(String appError);

    void showUnknownApiResponse();
}
