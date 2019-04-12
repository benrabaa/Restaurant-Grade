package org.pursuit.restaurantgrades.network;

import org.pursuit.restaurantgrades.Models.NeighborhoodResponse;
import org.pursuit.restaurantgrades.Models.Restaurant;
import org.pursuit.restaurantgrades.Models.RestaurantResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
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
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
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


    public Observable<List<Restaurant>> getRestaurantQueryList(String restaurantName){
        return restaurantDataApi.getRestaurantDataQuery(restaurantName,"inspection_date DESC","5")
                        .subscribeOn(Schedulers.io());

    }
    public Observable<List<Restaurant>> getRestaurantByBoro(String boroughs){
        return restaurantDataApi.getRestaurantByBoro(boroughs,"1000")
                .subscribeOn(Schedulers.computation());

    }
    public Call<List<Restaurant>> getRestaurantByBoro2(String boroughs){
        return restaurantDataApi.getRestaurantByBoro2(boroughs,"1000");
    }
    public Observable<List<Restaurant>> getRestaurantByName(String restaurantName,String boroughs){
        return restaurantDataApi.getRestaurantByName(restaurantName,boroughs,"inspection_date DESC")
                .subscribeOn(Schedulers.io());
    }

    public Observable<List<Restaurant>> getRestaurantZipCodeQueryList(String zipCode){
        return restaurantDataApi.getRestaurantZipCodeDataQuery(zipCode,"inspection_date DESC")
                .subscribeOn(Schedulers.io());
    }

    public Observable<List<Restaurant>> getAllData(){
        return restaurantDataApi.getAllData("BROOKLYN","inspection_date DESC","1000000")
                .subscribeOn(Schedulers.io());

    }

    //https://data.cityofnewyork.us/resource/9w7m-hzhe.json?$where=zipcode%20in(%2211372%22,%2211370%22)&$order=inspection_date%20DESC

    public Observable<List<Restaurant>> getRestaurantByAll(String boroughs,String restaurantName, String whereClause,String cuisine){

//        StringBuilder zipCodeStringsBuilder = new StringBuilder();
//        for (int i = 0; i < zipCodes.size(); i++) {
//            String zipCode = zipCodes.get(i);
//
//            zipCodeStringsBuilder.append("%22");
//            zipCodeStringsBuilder.append(zipCode);
//            zipCodeStringsBuilder.append("%22");
//
//            if (i < zipCodes.size() - 1) {
//                zipCodeStringsBuilder.append(",");
//            }
//        }
//
//        String whereClause = "zipcode%20in(" + zipCodeStringsBuilder.toString() + ")";

        return restaurantDataApi.getRestaurantByAll(boroughs,restaurantName,whereClause,cuisine,"inspection_date DESC","10000")
                .subscribeOn(Schedulers.io());
    }


//        public Observable<List<Restaurant>> getRestaurantByAll(Map<String,String> hashmap){
//        return restaurantDataApi.getRestaurantByAll(hashmap)
//                .subscribeOn(Schedulers.io());
//    }
}
