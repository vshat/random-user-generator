package com.github.vshat.randomusergenerator.view;


import com.github.vshat.randomusergenerator.presenter.vo.UserDetailInfo;

public interface ActivityCallback extends SupportActionBarActivity {
    void startUserDetailFragment(UserDetailInfo userDetailInfo);
}
