package org.pursuit.restaurantgrades.Views;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import org.pursuit.restaurantgrades.DataStorage;
import org.pursuit.restaurantgrades.Models.Restaurant;
import org.pursuit.restaurantgrades.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {
    private static final String ARG_PARAM1 = "RestaurantsList";

    private List<String> neighborhoodResponseList = new ArrayList<>();
    private List<List<String>> restaurantList = new ArrayList<>();
    private DataStorage dataStorage;
    private Spinner neighborhoodsSpinner;
    private Spinner boroughsSpinner;
    private BottomNavigationView bottomNav;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataStorage = DataStorage.getInstance();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, SearchFragment.getInstance())
                .addToBackStack(null)
                .commit();
        bottomNav = findViewById(R.id.bottom_navigation);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);



    }



    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            openSearchFragment();
                            //selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_favorites:
                            openFavoritesFragment();
                           // selectedFragment = new FavoritesFragment();
                            break;
                        case R.id.nav_search:
                            //selectedFragment = new SearchFragment();
                            openSearchByNameFragment();
                            break;
                    }
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                            selectedFragment).commit();

                    return true;
                }
            };

    @Override
    public void openSearchByNameFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, SearchByNameFragment.newInstance(restaurantList))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void openSearchFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, SearchFragment.getInstance())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void openFavoritesFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, FavoritesFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void openSlideGradesFramgment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, GradesFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void openDetailsFragment(Restaurant restaurant) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, DetailsFragment.newInstance(restaurant))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void openRestaurantRecyclerViewFragment(List<Restaurant> restaurantList) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, RestaurantRecyclerViewFragment.getInstance(restaurantList))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void openRestaurantGoogleMap(String location) {
        Uri gmmIntentUri = Uri.parse("geo:?z=10&q="+location);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);

    }

    @Override
    public void openRestaurantsListGoogleMap(List<Restaurant> restaurantList) {

        Intent intent = new Intent(this, RestaurantsMapActivity.class);
        intent.putParcelableArrayListExtra(ARG_PARAM1, (ArrayList<? extends Parcelable>) restaurantList);
        this.startActivity(intent);
    }
}
