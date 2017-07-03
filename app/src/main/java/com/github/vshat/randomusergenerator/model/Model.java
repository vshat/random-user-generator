package com.github.vshat.randomusergenerator.model;


import com.github.vshat.randomusergenerator.model.data.ApiResponseDTO;

import io.reactivex.Observable;

public interface Model {
    public Observable<ApiResponseDTO> getUsersList(int count);
}
