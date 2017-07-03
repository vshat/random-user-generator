package com.github.vshat.randomusergenerator.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.vshat.randomusergenerator.R;
import com.github.vshat.randomusergenerator.presenter.vo.UserDetailInfo;
import com.github.vshat.randomusergenerator.util.PicassoUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserDetailActivity extends AppCompatActivity {

    @BindView(R.id.imageview_userdetail_avatar) ImageView avatarImageView;
    @BindView(R.id.textview_userdetail_title) TextView titleTextView;
    @BindView(R.id.textview_userdetail_subtitle) TextView subtitleTextView;
    @BindView(R.id.textview_userdetail_phone_cell) TextView phoneCellTextView;
    @BindView(R.id.textview_userdetail_phone_home) TextView phoneHomeTextView;
    @BindView(R.id.textview_userdetail_location) TextView locationTextView;
    @BindView(R.id.textview_userdetail_about_user) TextView aboutUserTextView;
    @BindView(R.id.textview_userdetail_date_of_birth) TextView dateOfBirthTextView;
    @BindView(R.id.textview_userdetail_registred) TextView registeredTextView;
    @BindView(R.id.textview_userdetail_id_name) TextView idNameTextView;
    @BindView(R.id.textview_userdetail_id_value) TextView idValueTextView;
    @BindView(R.id.textview_userdetail_username) TextView usernameTextView;
    @BindView(R.id.textview_userdetail_password) TextView passwordTextView;
    @BindView(R.id.textview_userdetail_salt) TextView saltTextView;
    @BindView(R.id.textview_userdetail_md5) TextView md5TextView;
    @BindView(R.id.textview_userdetail_sha1) TextView sha1TextView;
    @BindView(R.id.textview_userdetail_sha256) TextView sha256TextView;

    private static final String EXTRA_USER_DETAIL_INFO = "extra_user_detail_info";


    public static void start(Context context, UserDetailInfo userDetailInfo) {
        Intent starter = new Intent(context, UserDetailActivity.class);
        starter.putExtra(EXTRA_USER_DETAIL_INFO, userDetailInfo);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        ButterKnife.bind(this);

        setupToolbar();

        UserDetailInfo userDetailInfo = getIntent().getParcelableExtra(EXTRA_USER_DETAIL_INFO);
        displayData(userDetailInfo);

    }

    private void displayData(UserDetailInfo userDetailInfo) {
        titleTextView.setText(userDetailInfo.getFullName());
        subtitleTextView.setText(userDetailInfo.getEmail());
        phoneCellTextView.setText("8-800-555-35-35");
        phoneHomeTextView.setText("762-10-20");
        locationTextView.setText("Ukraine, Odessa, Rabina 15");
        aboutUserTextView.setText("About " + userDetailInfo.getFirstName());
        dateOfBirthTextView.setText("11.05.1997 14:20");
        registeredTextView.setText("25.03.2014 00-00");
        idNameTextView.setText("ID");
        idValueTextView.setText("53826451");
        usernameTextView.setText("vshat");
        passwordTextView.setText("lolKek11");
        saltTextView.setText("PogChamp");
        md5TextView.setText("fc49fb87e0a3f8d319c38348e60fe2e6");
        sha1TextView.setText("58cd166fe955a09c57c9e34bf8a0de8a8bac7f33");
        sha256TextView.setText("5fb87a18e7163ba83f9751afb5f4729ce31355795b59978b2c9128b7736ee7ac");

        PicassoUtils.loadImage(this, userDetailInfo.getAvatarUrl(), avatarImageView);
    }


    private void setupToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" ");
    }

}
