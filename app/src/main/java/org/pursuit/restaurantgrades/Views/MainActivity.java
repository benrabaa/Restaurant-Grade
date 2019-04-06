package org.pursuit.restaurantgrades.Views;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import org.pursuit.restaurantgrades.Models.Restaurant;
import org.pursuit.restaurantgrades.R;

import java.util.ArrayList;
import java.util.List;

public  class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {
    private List<String> neighborhoodResponseList=new ArrayList<>();
    private List<List<String>> restaurantList =new ArrayList<>();
    private Spinner neighborhoodsSpinner;
    private Spinner boroughsSpinner;
    private static final String ARG_PARAM1="RestaurantsList";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, SearchFragment.getInstance())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void openSearchByNameFragment() {
       getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, SearchByNameFragment.newInstance(restaurantList))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void openSearchFragment() {
        SearchFragment searchFragment=SearchFragment.getInstance();
        searchFragment.getFragmentManager().beginTransaction().replace(R.id.fragment_container,searchFragment).commit();
    }


    @Override
    public void openDetailsFragment(Restaurant restaurant) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,DetailsFragment.getInstance(restaurant))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void openRestaurantRecyclerViewFragment(List<Restaurant> restaurantList) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,RestaurantRecyclerViewFragment.getInstance(restaurantList))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void openRestaurantGoogleMap(String location) {
        Uri geoCoordinates=Uri.parse("geo:?z=15$q="+location);
        Intent mapIntent=new Intent(Intent.ACTION_VIEW,geoCoordinates);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);

    }

    @Override
    public void openRestaurantsListGoogleMap(List<Restaurant> restaurantList) {
        Intent intent= new Intent(this,RestaurantsMapActivity.class);
        intent.putParcelableArrayListExtra(ARG_PARAM1, (ArrayList<? extends Parcelable>) restaurantList);
        this.startActivity(intent);
    }
}
