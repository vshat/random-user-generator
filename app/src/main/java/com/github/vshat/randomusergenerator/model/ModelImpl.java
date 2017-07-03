package com.github.vshat.randomusergenerator.model;


import com.github.vshat.randomusergenerator.model.api.ApiInterface;
import com.github.vshat.randomusergenerator.model.api.ApiModule;
import com.github.vshat.randomusergenerator.model.data.ApiResponseDTO;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ModelImpl implements Model {

    private ApiInterface apiInterface = ApiModule.getApiInterface();

    @Override
    public Observable<ApiResponseDTO> getUsersList(int count) {
        return apiInterface.getRandomUsers(count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
