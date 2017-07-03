package com.github.vshat.randomusergenerator.model.api;


import com.github.vshat.randomusergenerator.model.data.ApiResponseDTO;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("?nat=US")
    Observable<ApiResponseDTO> getRandomUsers(@Query("results") int count);
}
