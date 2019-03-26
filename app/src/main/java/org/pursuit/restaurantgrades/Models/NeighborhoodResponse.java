package org.pursuit.restaurantgrades.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NeighborhoodResponse {
    private List<List<String>> data;

    public List<List<String>> getNeighborhoodList() {
        return data;
    }

    public void setData(List<List<String>> data) {
        this.data = data;
    }
}
