package com.github.vshat.randomusergenerator.view;


import android.support.v4.app.DialogFragment;

import com.github.vshat.randomusergenerator.presenter.vo.UserDetailInfo;

public interface ActivityCallback extends SupportActionBarActivity {
    void startUserDetailFragment(UserDetailInfo userDetailInfo);
    void showDialogFragment(DialogFragment dialogFragment);
}
