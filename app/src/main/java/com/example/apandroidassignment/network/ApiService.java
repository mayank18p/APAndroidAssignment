package com.example.apandroidassignment.network;

import com.example.apandroidassignment.model.ApiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("v2/content/misc/media-coverages")
    Call<List<ApiResponse>> getImages(@Query("limit") int page);
}
