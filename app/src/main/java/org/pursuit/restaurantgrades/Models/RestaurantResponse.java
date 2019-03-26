package org.pursuit.restaurantgrades.Models;

import java.util.List;

public class RestaurantResponse {

    private List<List<String>> data;

    public List<List<String>> getData() {
        return data;
    }

    public void setData(List<List<String>> data) {
        this.data = data;
    }
}
