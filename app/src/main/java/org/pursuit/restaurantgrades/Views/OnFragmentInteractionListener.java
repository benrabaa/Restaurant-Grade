package org.pursuit.restaurantgrades.Views;

import org.pursuit.restaurantgrades.Models.Restaurant;

import java.util.ArrayList;
import java.util.List;

public interface OnFragmentInteractionListener {
    void openSearchByNameFragment();
    void openSearchFragment();
    void openDetailsFragment(Restaurant restaurant);
    void openRestaurantRecyclerViewFragment(List<Restaurant> restaurantList);
    void openRestaurantGoogleMap(String location);
    void openRestaurantsListGoogleMap(List<Restaurant> restaurantList);
}
