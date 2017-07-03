package com.github.vshat.randomusergenerator.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.github.vshat.randomusergenerator.R;
import com.github.vshat.randomusergenerator.model.data.User;
import com.github.vshat.randomusergenerator.presenter.UsersListPresenter;
import com.github.vshat.randomusergenerator.view.adapters.UsersAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements UsersAdapter.OnItemClickListener, MvpView {

    @BindView(R.id.toolbar_main) Toolbar toolbar;
    @BindView(R.id.recyclerview_main) RecyclerView recyclerView;
    private UsersAdapter usersAdapter;
    private UsersListPresenter presenter;

    private static List<String> createStringList() {
        List<String> result = new ArrayList<>(10);

        for (int i = 0; i < 10; i++) {
            result.add(i, "User #" + i);
        }

        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setupToolbar();

        setupRecyclerView();

        presenter = new UsersListPresenter(this);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
    }

    private void setupRecyclerView() {
        usersAdapter = new UsersAdapter();
        usersAdapter.setOnItemClickListener(this);

        recyclerView.setAdapter(usersAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onItemClick(View itemView, int position) {
        UserDetailActivity.start(this, ((TextView)itemView.findViewById(R.id.textview_user_name)).getText().toString());
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.onStop();
        }
    }


    void showSnackbar(String text) {
        Snackbar.make(toolbar, text, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showData(List<User> list) {
        usersAdapter.setUsersList(list);
    }

    @Override
    public void showError(String error) {
        showSnackbar(error);
    }

    @Override
    public void showEmptyList() {
        showSnackbar("Empty list");

    }
}
