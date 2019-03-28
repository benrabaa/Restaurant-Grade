package org.pursuit.restaurantgrades.network;

import org.pursuit.restaurantgrades.Models.NeighborhoodResponse;
import org.pursuit.restaurantgrades.Models.Restaurant;
import org.pursuit.restaurantgrades.Models.RestaurantQueryResponse;
import org.pursuit.restaurantgrades.Models.RestaurantResponse;

import java.util.List;

import retrofit2.Call;

public class DataRepository {
    private ApiClient apiClient;

    public DataRepository(ApiClient apiClient){
        this.apiClient=apiClient;
    }

    public Call<RestaurantResponse> getRestaurant(){
        return apiClient.getRestaurant();

    }
    public Call<List<Restaurant>> getRestaurantDataQuery(String restaurantName,String boroughs){
        return apiClient.getRestaurantQueryList(restaurantName,boroughs);

    }
    public Call<NeighborhoodResponse> getNeighborhood(){
        return apiClient.getNeighborhood();

    }
}
