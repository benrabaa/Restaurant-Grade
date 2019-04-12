package org.pursuit.restaurantgrades;

import org.pursuit.restaurantgrades.Models.Restaurant;
import org.pursuit.restaurantgrades.Views.ViolationsFragment;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Predicate;

public final class DataStorage {

   // Observable<List<Restaurant>> restaurants = Observable.fromArray();

    private static DataStorage instance;

    private DataStorage() {
//        restaurants.flatMapIterable(restaurantList -> restaurantList)
//                .filter(restaurant -> {
//                    if (restaurant.getGrade() == ) {
//
//                    }
//                    return false;
//                })
//                .subscribe()
    }

    private List<ViolationsFragment> violationsFragments;
    private List<Restaurant>viewHolderRestaurantList;
    private List<Restaurant>detailsRestaurantList;


    public static void setInstance(DataStorage instance) {
        DataStorage.instance = instance;
    }


    public List<Restaurant> getviewHolderRestaurantList() {
        return viewHolderRestaurantList;
    }
    public void setViewHolderRestaurantList(List<Restaurant> viewHolderRestaurantList) {
        this.viewHolderRestaurantList = viewHolderRestaurantList;
    }
    public List<Restaurant> getdetailsRestaurantList() {
        return detailsRestaurantList;
    }
    public void setdetailsRestaurantList(List<Restaurant> detailsRestaurantList) {
        this.detailsRestaurantList = detailsRestaurantList;
    }

    public static DataStorage getInstance() {
        if (instance == null) {
            instance = new DataStorage();
        }
        return instance;
    }
}
