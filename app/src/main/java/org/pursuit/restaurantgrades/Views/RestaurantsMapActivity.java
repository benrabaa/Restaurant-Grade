package org.pursuit.restaurantgrades.Views;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.pursuit.restaurantgrades.Models.Restaurant;
import org.pursuit.restaurantgrades.R;

import java.io.IOException;
import java.util.List;

public class RestaurantsMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final String ARG_PARAM1="RestaurantsList";
    private String location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setMyLocationButtonEnabled(true);

        Geocoder coder = new Geocoder(getApplicationContext());
        Intent intent=getIntent();
        List<Restaurant>restaurantsList=intent.getParcelableArrayListExtra(ARG_PARAM1);

        for (Restaurant restaurant:restaurantsList) {
            location=restaurant.getBuilding()+" "+restaurant.getStreet()+" "+restaurant.getBoro()+", NY "+restaurant.getZipcode();

            List<Address> address;
            LatLng p1 = null;
            try {
                // May throw an IOException
                address = coder.getFromLocationName(location, 5);
                if (address != null) {
                    Address location = address.get(0);
                    p1 = new LatLng(location.getLatitude(), location.getLongitude());
                    //mMap.addMarker(new MarkerOptions().position(p1).title("Marker in My address - Museum").icon(BitmapDescriptorFactory.fromResource(R.mipmap.test2)));
                    mMap.addMarker(new MarkerOptions().position(p1).title(restaurant.getDba()));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p1, 13));
                   // mMap.moveCamera(CameraUpdateFactory.newLatLng(p1));


                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }


        }

    }
}
