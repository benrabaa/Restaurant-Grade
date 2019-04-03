package org.pursuit.restaurantgrades.Views;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Spinner;

import org.pursuit.restaurantgrades.Models.Restaurant;
import org.pursuit.restaurantgrades.R;
//import org.pursuit.restaurantgrades.Models.NeighborhoodResponse;

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

//        neighborhoodsSpinner =findViewById(R.id.Neighborhoods_spinner);
//        boroughsSpinner=findViewById(R.id.boroughs);







       // ArrayAdapter<String> adapter=new ArrayAdapter<String>(
         //       this,android.R.layout.simple_spinner_item,neighborhoodResponseList);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //neighborhoodsSpinner.setAdapter(new ArrayAdapter<test>(this, android.R.layout.simple_spinner_item
          //      , test.values()));

        //neighborhoodsSpinner.setAdapter(adapter);
//        boroughsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String[] brooklynNeighborhoods = getResources().getStringArray(R.array.Brooklyn);
//
//               // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, R.id.text, brooklynNeighborhoods);
//
////                spinner.setAdapter(adapter);
////                neighborhoodsSpinner.setAdapter(new ArrayAdapter<test>(this, android.R.layout.simple_spinner_item
////                        ,brooklynNeighborhoods));
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, SearchFragment.getInstance())
                .addToBackStack(null)
                .commit();





    }

    @Override
    public void openSearchByNameFragment() {
//        SearchByNameFragment searchByNameFragment= SearchByNameFragment.getInstance();
//        searchByNameFragment.getFragmentManager().beginTransaction().replace(R.id.fragment_container,searchByNameFragment).commit();


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, SearchByNameFragment.newInstance(restaurantList))
                .addToBackStack(null)
                .commit();
//
//FragmentManager fragmentManager=getSupportFragmentManager().re
//                detailsFragment.setArguments(bundle);
//        FragmentManager fragmentManager=getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.fragment_container,detailsFragment).commit();
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
//
        Uri geoCoordinates=Uri.parse("geo:?z=15$q="+location);
        Intent mapIntent=new Intent(Intent.ACTION_VIEW,geoCoordinates);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);

//
//        Uri parseAddress=Uri.parse("google.navigation:q="+eventAddress);
//        Intent navIntent=new Intent(Intent.ACTION_VIEW,parseAddress);
//        navIntent.setPackage("com.google.android.apps.maps");
//        return navIntent



//        Uri gmmIntentUri = Uri.parse("http://maps.google.co.in/maps?q="+location);
//
//// Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
//        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//// Make the Intent explicit by setting the Google Maps package
//        mapIntent.setPackage("com.google.android.apps.maps");
//
//// Attempt to start an activity that can handle the Intent
//        startActivity(mapIntent);
    }

    @Override
    public void openRestaurantsListGoogleMap(List<Restaurant> restaurantList) {
        Intent intent= new Intent(this,RestaurantsMapActivity.class);
        intent.putParcelableArrayListExtra(ARG_PARAM1, (ArrayList<? extends Parcelable>) restaurantList);
        this.startActivity(intent);
    }
}
