package com.example.covid_19_tracker;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {


    @GET("countries")
    Call<List<country>>  getcountries() ;



}
