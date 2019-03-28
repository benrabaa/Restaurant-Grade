package org.pursuit.restaurantgrades.Models;

import java.util.List;

public class RestaurantQueryResponse {
    private List<Restaurant> restaurantsList;

    public List<Restaurant> getRestaurantsList() {
        return restaurantsList;
    }

    public void setRestaurantsList(List<Restaurant> restaurantsList) {
        this.restaurantsList = restaurantsList;
    }
}
