package org.pursuit.restaurantgrades.network;

import org.pursuit.restaurantgrades.Models.NeighborhoodResponse;
import org.pursuit.restaurantgrades.Models.RestaurantResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {
    private static Retrofit retrofitInstance;
    private String baseUrl;
    private RestaurantDataApi restaurantDataApi;
    private NeighborhoodApi neighborhoodApi;

    public ApiClient(){
        initApis();

    }

    private   Retrofit getRetrofitInstance(String baseUrl){

          return new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


    }

    private void initApis(){
    restaurantDataApi=getRetrofitInstance("https://data.cityofnewyork.us").create(RestaurantDataApi.class);
    neighborhoodApi =getRetrofitInstance("https://data.cityofnewyork.us").create(NeighborhoodApi.class);


    }

    public Call<RestaurantResponse> getRestaurant(){
        return restaurantDataApi.getResturentData();

    }
    public Call<NeighborhoodResponse> getNeighborhood(){
        return neighborhoodApi.getNeighborhood();
    }
}
