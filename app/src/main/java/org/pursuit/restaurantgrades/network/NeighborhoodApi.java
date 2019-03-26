package org.pursuit.restaurantgrades.network;

import org.pursuit.restaurantgrades.Models.NeighborhoodResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NeighborhoodApi {
    @GET("/api/views/xyye-rtrs/rows.json?accessType=DOWNLOAD")
    Call<NeighborhoodResponse> getNeighborhood();
}
