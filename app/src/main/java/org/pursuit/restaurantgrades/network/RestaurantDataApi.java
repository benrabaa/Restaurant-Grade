package org.pursuit.restaurantgrades.network;

import org.pursuit.restaurantgrades.Models.Restaurant;
import org.pursuit.restaurantgrades.Models.RestaurantQueryResponse;
import org.pursuit.restaurantgrades.Models.RestaurantResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestaurantDataApi {
    @GET("/api/views/gra9-xbjk/rows.json?accessType=DOWNLOAD")
    Call<RestaurantResponse> getResturentData();


   // https://data.cityofnewyork.us/resource/9w7m-hzhe.json?dba=RUBIROSA PIZZA %26 RISTORANTE
    @GET("/resource/9w7m-hzhe.json?")
    Observable<List<Restaurant>> getRestaurantDataQuery(
            //@Header("Authorization") String apiKey,
            @Query("dba")String restaurantName,
          //  @Query("boro") String boroughs,
            @Query("$order") String order,
            @Query("$limit") String limit
    );

    @GET("/resource/9w7m-hzhe.json?")
    Call<List<Restaurant>> getRestaurantByName2(
            @Query("dba")String restaurantName,
            @Query("boro") String boroughs,
            @Query("$order") String order
    );

    @GET("/resource/9w7m-hzhe.json?")
    Observable<List<Restaurant>> getRestaurantByName(
            @Query("dba")String restaurantName,
            @Query("boro") String boroughs,
            @Query("$order") String order
    );

    @GET("/resource/9w7m-hzhe.json?")
    Observable<List<Restaurant>> getRestaurantZipCodeDataQuery(
            @Query("zipcode")String zipCode,
            @Query("$order") String order
    );


    @GET("/resource/9w7m-hzhe.json?")
    Observable<List<Restaurant>> getCuisine();

}
