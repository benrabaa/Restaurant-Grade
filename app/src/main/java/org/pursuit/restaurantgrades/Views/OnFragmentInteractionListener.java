package org.pursuit.restaurantgrades.Views;

import org.pursuit.restaurantgrades.Models.Restaurant;

import java.util.List;

public interface OnFragmentInteractionListener {
    void openSearchByNameFragment();
    void openSearchFragment();
    void openDetailsFragment(List<Restaurant> restaurantList);
}
