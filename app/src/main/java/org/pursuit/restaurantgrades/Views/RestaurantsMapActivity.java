package org.pursuit.restaurantgrades.Views;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.pursuit.restaurantgrades.DataStorage;
import org.pursuit.restaurantgrades.Models.Restaurant;
import org.pursuit.restaurantgrades.R;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RestaurantsMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final String ARG_PARAM1 = "RestaurantsList";
    private String location;
    Geocoder coder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MapsInitializer.initialize(this);

        setContentView(R.layout.activity_restaurants_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        coder = new Geocoder(getApplicationContext());
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setMyLocationButtonEnabled(true);


        Intent intent = getIntent();
        List<Restaurant> restaurantsList = intent.getParcelableArrayListExtra(ARG_PARAM1);


        ////////

        Observable<Restaurant> restaurants = Observable.fromIterable(restaurantsList);
        Disposable disposable = restaurants
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(restaurantFrags -> mapFilter(coder, restaurantFrags),
                        throwable -> Log.d("bennyben", "onClickinOnB: " + throwable),
                        () -> mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(getLatLng(restaurantsList.get(restaurantsList.size() - 1)), 13)));


        ////////


//                for (Restaurant restaurant:restaurantsList) {
//            location=restaurant.getBuilding()+" "+restaurant.getStreet()+" "+restaurant.getBoro()+", NY "+restaurant.getZipcode();
//
//            List<Address> address;
//            LatLng p1 = null;
//            try {
//                // May throw an IOException
//                address = coder.getFromLocationName(location, 5);
//                if (address != null) {
//                    Address location = address.get(0);
//                    p1 = new LatLng(location.getLatitude(), location.getLongitude());
//                    //mMap.addMarker(new MarkerOptions().position(p1).title("Marker in My address - Museum").icon(BitmapDescriptorFactory.fromResource(R.mipmap.test2)));
//                    mMap.addMarker(new MarkerOptions().position(p1).title(restaurant.getDba()).zIndex(40));
//                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p1, 13));
//                    // mMap.moveCamera(CameraUpdateFactory.newLatLng(p1));
//
//
//                }
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }

    }

    private LatLng getLatLng(Restaurant restaurant) {
        String restaurantAddress = restaurant.getBuilding() + " " + restaurant.getStreet() + " " + restaurant.getBoro() + ", NY " + restaurant.getZipcode();

        List<Address> address;
        LatLng p1 = null;
        try {
            // May throw an IOException
            address = coder.getFromLocationName(restaurantAddress, 5);
            if (address != null) {
                Address location = address.get(0);
                p1 = new LatLng(location.getLatitude(), location.getLongitude());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return p1;
    }

    private void mapFilter(Geocoder coder, Restaurant restaurant) {

        String restaurantAddress = restaurant.getBuilding() + " " + restaurant.getStreet() + " " + restaurant.getBoro() + ", NY " + restaurant.getZipcode();

        List<Address> address;
        LatLng p1 = null;
        try {
            // May throw an IOException
            address = coder.getFromLocationName(restaurantAddress, 5);
            if (address != null) {
                Address location = address.get(0);
                p1 = new LatLng(location.getLatitude(), location.getLongitude());
                //mMap.addMarker(new MarkerOptions().position(p1).title("Marker in My address - Museum").icon(BitmapDescriptorFactory.fromResource(R.mipmap.test2)));
                mMap.addMarker(new MarkerOptions().position(p1).title(restaurant.getDba()).zIndex(40));

                // mMap.moveCamera(CameraUpdateFactory.newLatLng(p1));

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }


//        ///
//
//
//        for (Restaurant restaurant:restaurantsList) {
//            location=restaurant.getBuilding()+" "+restaurant.getStreet()+" "+restaurant.getBoro()+", NY "+restaurant.getZipcode();
//
//            List<Address> address;
//            LatLng p1 = null;
//            try {
//                // May throw an IOException
//                address = coder.getFromLocationName(location, 5);
//                if (address != null) {
//                    Address location = address.get(0);
//                    p1 = new LatLng(location.getLatitude(), location.getLongitude());
//                    //mMap.addMarker(new MarkerOptions().position(p1).title("Marker in My address - Museum").icon(BitmapDescriptorFactory.fromResource(R.mipmap.test2)));
//                    mMap.addMarker(new MarkerOptions().position(p1).title(restaurant.getDba()).zIndex(40));
//                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p1, 13));
//                    // mMap.moveCamera(CameraUpdateFactory.newLatLng(p1));
//
//
//                }
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//        ///

    }
}
