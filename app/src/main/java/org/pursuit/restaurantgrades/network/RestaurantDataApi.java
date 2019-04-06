package org.pursuit.restaurantgrades.network;

import org.pursuit.restaurantgrades.Models.Restaurant;
import org.pursuit.restaurantgrades.Models.RestaurantQueryResponse;
import org.pursuit.restaurantgrades.Models.RestaurantResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.annotations.Nullable;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

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
    Observable<List<Restaurant>> getRestaurantByBoro(
             @Query("boro") String boroughs,
            @Query("$limit") String limit

    );
 @GET("/resource/9w7m-hzhe.json?")
 Call<List<Restaurant>> getRestaurantByBoro2(
         @Nullable @Query("boro") String boroughs,
         @Query("$limit") String limit

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
            @Query("$order") String order);


    @GET("/resource/9w7m-hzhe.json?")
    Observable<List<Restaurant>> getCuisine();


 @GET("/resource/9w7m-hzhe.json?$$app_token=eIJ8Ir9ntP6e4NtZUPqUIQJuC")
 Observable<List<Restaurant>> getRestaurantByAll(
         @Query("boro") String boroughs,
         @Query("dba")String restaurantName,
         @Query(encoded = true, value = "$where") String where,
         @Query("cuisine_description") String cuisine,
         @Query("$order") String order,
         @Query("$limit") String limit

 );

}
