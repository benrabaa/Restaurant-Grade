package org.pursuit.restaurantgrades.network;

import org.pursuit.restaurantgrades.Models.NeighborhoodResponse;
import org.pursuit.restaurantgrades.Models.Restaurant;
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
    public Observable<List<Restaurant>> getRestaurantByBoro(String boroughs){
        return apiClient.getRestaurantByBoro(boroughs);

    }
    public Call<List<Restaurant>> getRestaurantByBoro2(String boroughs){
        return apiClient.getRestaurantByBoro2(boroughs);

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



    public Observable<List<Restaurant>> getAllData(){
        return apiClient.getAllData();

    }

    public Observable<List<Restaurant>> getRestaurantByAll(String boroughs,String restaurantName,String whereClause, String cuisine){

        return apiClient.getRestaurantByAll(boroughs,restaurantName, whereClause,cuisine);
    }
//    public Observable<List<Restaurant>> getRestaurantByAll(Map<String, String> hashMap){
//        return apiClient.getRestaurantByAll(hashMap);
//    }
}
