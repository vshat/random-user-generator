package com.github.vshat.randomusergenerator.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.github.vshat.randomusergenerator.R;
import com.github.vshat.randomusergenerator.presenter.vo.UserDetailInfo;
import com.github.vshat.randomusergenerator.view.fragments.UserDetailFragment;
import com.github.vshat.randomusergenerator.view.fragments.UsersListFragment;


// TODO: fix back top-left button in all fragments

// TODO: fix data loss when back pressed

public class MainActivity extends AppCompatActivity implements ActivityCallback {

    private static String TAG = "TAG";

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        Fragment fragment = fragmentManager.findFragmentByTag(TAG);
        if (fragment == null) {
            replaceFragment(new UsersListFragment(), false);
        }
    }

    private void replaceFragment(Fragment fragment, boolean addBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.framelayout_main, fragment, TAG);
        if (addBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    @Override
    public ActionBar setupToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        return supportActionBar;

    }


    @Override
    public void startUserDetailFragment(UserDetailInfo userDetailInfo) {
        replaceFragment(UserDetailFragment.newInstance(userDetailInfo), true);
    }
}
