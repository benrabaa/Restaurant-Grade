package org.pursuit.restaurantgrades.network;

import org.pursuit.restaurantgrades.Models.NeighborhoodResponse;
import org.pursuit.restaurantgrades.Models.RestaurantResponse;

import retrofit2.Call;

public class DataRepository {
    private ApiClient apiClient;

    public DataRepository(ApiClient apiClient){
        this.apiClient=apiClient;
    }
    public Call<RestaurantResponse> getRestaurant(){
        return apiClient.getRestaurant();

    }
    public Call<NeighborhoodResponse> getNeighborhood(){
        return apiClient.getNeighborhood();

    }
}
