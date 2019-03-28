package org.pursuit.restaurantgrades.network;

import org.pursuit.restaurantgrades.Models.NeighborhoodResponse;
import org.pursuit.restaurantgrades.Models.Restaurant;
import org.pursuit.restaurantgrades.Models.RestaurantQueryResponse;
import org.pursuit.restaurantgrades.Models.RestaurantResponse;

import java.util.List;

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

    private Retrofit getRetrofitInstance(String baseUrl){

          return new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


    }

    private void initApis(){
    restaurantDataApi=getRetrofitInstance("https://data.cityofnewyork.us").create(RestaurantDataApi.class);
    neighborhoodApi =getRetrofitInstance("https://data.cityofnewyork.us").create(NeighborhoodApi.class);
       // https://data.cityofnewyork.us/resource/9w7m-hzhe.json?dba=CLAUDIO%27S&$order=inspection_date%20DESC&$limit=10

    }

    public Call<RestaurantResponse> getRestaurant(){
        return restaurantDataApi.getResturentData();

    }
    public Call<NeighborhoodResponse> getNeighborhood(){
        return neighborhoodApi.getNeighborhood();
    }
    public Call<List<Restaurant>> getRestaurantQueryList(String restaurantName,String boroughs){
        return restaurantDataApi.getRestaurantDataQuery(restaurantName,boroughs,"inspection_date DESC","5");
    }
}
