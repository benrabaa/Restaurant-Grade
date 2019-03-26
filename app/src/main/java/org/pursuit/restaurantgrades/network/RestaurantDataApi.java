package org.pursuit.restaurantgrades.network;

import org.pursuit.restaurantgrades.Models.RestaurantResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestaurantDataApi {
    @GET("/api/views/gra9-xbjk/rows.json?accessType=DOWNLOAD")
    Call<RestaurantResponse> getResturentData();
}
