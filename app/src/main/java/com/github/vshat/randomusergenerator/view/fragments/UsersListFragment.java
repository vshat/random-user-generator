package com.github.vshat.randomusergenerator.view.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.github.vshat.randomusergenerator.R;
import com.github.vshat.randomusergenerator.presenter.UsersListPresenter;
import com.github.vshat.randomusergenerator.presenter.vo.UserBriefInfo;
import com.github.vshat.randomusergenerator.presenter.vo.UserDetailInfo;
import com.github.vshat.randomusergenerator.util.NetworkUtils;
import com.github.vshat.randomusergenerator.view.ActivityCallback;
import com.github.vshat.randomusergenerator.view.adapters.UsersAdapter;
import com.github.vshat.randomusergenerator.view.fragments.dialogs.ErrorDialogFragment;
import com.github.vshat.randomusergenerator.view.fragments.dialogs.NetworkErrorDialogFragment;
import com.github.vshat.randomusergenerator.view.fragments.dialogs.NetworkErrorDialogListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class UsersListFragment extends Fragment implements UsersListView,
        NetworkErrorDialogListener {

    @BindView(R.id.toolbar_userslist) Toolbar toolbar;
    @BindView(R.id.recyclerview_userslist) RecyclerView recyclerView;
    @BindView(R.id.progressbar_userlist) ProgressBar progressBar;

    private UsersAdapter usersAdapter;
    private UsersListPresenter presenter;
    private ActivityCallback activityCallback;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userslist, container, false);

        unbinder = ButterKnife.bind(this, view);

        setupRecyclerView();

        activityCallback.setupToolbar(toolbar);

        setupPresenter(savedInstanceState);

        return view;
    }

    @Override
    public void showData(List<UserBriefInfo> list) {
        usersAdapter.setUsersList(list);
    }


    @Override
    public void startUserDetailFragment(UserDetailInfo userDetailInfo) {
        activityCallback.startUserDetailFragment(userDetailInfo);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showOfflineError() {
        NetworkErrorDialogFragment dialogFragment = NetworkErrorDialogFragment.newInstance(this);
        activityCallback.showDialogFragment(dialogFragment);
    }

    @Override
    public void openNetworkSettings() {
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        getActivity().startActivity(intent);
    }

    @Override
    public void exit() {
        getActivity().finish();
    }

    @Override
    public void showApiError(String apiError) {
        showError(getString(R.string.userslist_apierror_title), apiError);
    }

    @Override
    public void showAppError(String appError) {
        showError(getString(R.string.userslist_apperror_title), appError);
    }

    @Override
    public void showUnknownApiResponse() {
        showApiError(getString(R.string.userslist_apierror_unknown_message));

    }

    private void showError(String title, String error) {
        DialogFragment dialog = ErrorDialogFragment.newInstance(this, title, error);

        activityCallback.showDialogFragment(dialog);
    }

    private void setupRecyclerView() {
        usersAdapter = new UsersAdapter(getContext());
        usersAdapter.setOnItemClickListener((view, pos) -> presenter.onUserSelected(pos));

        recyclerView.setAdapter(usersAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.onStop();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            activityCallback = (ActivityCallback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement ActivityCallback");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        boolean isOnline = NetworkUtils.isOnline(getContext());
        presenter.onResume(isOnline);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setupPresenter(Bundle savedInstanceState) {
        if (presenter == null) {
            presenter = new UsersListPresenter(this);
        }

        presenter.onCreate(savedInstanceState);
    }


    @Override
    public void onExitClick() {
        presenter.onExitClick();

    }

    @Override
    public void onSettingsClick() {
        presenter.onSettingsClick();
    }
}
