package com.example.game.deltatask3v2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDataService {

    @GET("forces")
    Call<List<Forces>> getAllForces();
    @GET("forces/{id}")
    Call<Spec_Forces> getspec_force(@Path("id") String id);
    @GET("crimes-at-location")       //date=2017-02&lat=52.643950&lng=-1.143042
    Call<List<CrimesLocAll>> getcrimesforloc(@Query(value = "date",encoded = true) String date, @Query(value = "lat",encoded = true) String lat, @Query(value = "lng",encoded = true) String lng);
    @GET("crimes-no-location")
    Call<List<CrimesLocAll>> getcrimenolocation(@Query(value = "category",encoded = true) String category,@Query(value = "force",encoded = true) String force,@Query(value = "date",encoded = true) String date);
}