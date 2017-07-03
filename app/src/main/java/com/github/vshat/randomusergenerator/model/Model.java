package com.github.vshat.randomusergenerator.model;


import com.github.vshat.randomusergenerator.model.data.ApiResponse;

import io.reactivex.Observable;

public interface Model {
    public Observable<ApiResponse> getUsersList(int count);
}
