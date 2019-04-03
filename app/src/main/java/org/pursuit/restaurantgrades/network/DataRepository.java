package org.pursuit.restaurantgrades.network;

import org.pursuit.restaurantgrades.Models.NeighborhoodResponse;
import org.pursuit.restaurantgrades.Models.Restaurant;
import org.pursuit.restaurantgrades.Models.RestaurantQueryResponse;
import org.pursuit.restaurantgrades.Models.RestaurantResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;

public class DataRepository {
    private ApiClient apiClient;

    public DataRepository(ApiClient apiClient){
        this.apiClient=apiClient;
    }

    public Call<RestaurantResponse> getRestaurant(){
        return apiClient.getRestaurant();

    }
    public Observable<List<Restaurant>> getRestaurantDataQuery(String restaurantName){
        return apiClient.getRestaurantQueryList(restaurantName);

    }
    public Call<List<Restaurant>> getRestaurantByName2(String restaurantName,String boroughs){
        return apiClient.getRestaurantByName2(restaurantName,boroughs);

    }
    public Observable<List<Restaurant>> getRestaurantByName(String restaurantName,String boroughs){
        return apiClient.getRestaurantByName(restaurantName,boroughs);

    }

    public Observable<List<Restaurant>> getRestaurantZipCodeDataQuery(String zipCode){
        return apiClient.getRestaurantZipCodeQueryList(zipCode);

    }

    public Call<NeighborhoodResponse> getNeighborhood(){
        return apiClient.getNeighborhood();

    }

    public Observable<List<Restaurant>> getCuisine(){
        return apiClient.getCuisine();

    }
}
