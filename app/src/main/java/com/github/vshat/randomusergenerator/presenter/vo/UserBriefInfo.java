package com.github.vshat.randomusergenerator.presenter.vo;


public class UserBriefInfo {
    private String name;
    private String avatarUrl;

    public UserBriefInfo(String name, String avatarUrl) {
        this.name = name;
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }


    public String getAvatarUrl() {
        return avatarUrl;
    }
}
