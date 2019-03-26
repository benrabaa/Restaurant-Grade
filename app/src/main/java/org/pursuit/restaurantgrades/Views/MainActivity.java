package org.pursuit.restaurantgrades.Views;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.pursuit.restaurantgrades.Models.NeighborhoodResponse;
import org.pursuit.restaurantgrades.Models.RestaurantResponse;
import org.pursuit.restaurantgrades.R;
import org.pursuit.restaurantgrades.network.DataRepository;
//import org.pursuit.restaurantgrades.Models.NeighborhoodResponse;
import org.pursuit.restaurantgrades.network.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {
    private List<String> neighborhoodResponseList=new ArrayList<>();
    private Spinner neighborhoodsSpinner;
    private Spinner boroughsSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        neighborhoodsSpinner =findViewById(R.id.Neighborhoods_spinner);
//        boroughsSpinner=findViewById(R.id.boroughs);
        DataRepository dataRepository=new DataRepository(new ApiClient());
        dataRepository.getNeighborhood().enqueue(new Callback<NeighborhoodResponse>() {
            @Override
            public void onResponse(Call<NeighborhoodResponse> call, Response<NeighborhoodResponse> response) {
                for(List<String> n:response.body().getNeighborhoodList()){
                    neighborhoodResponseList.add(n.get(10));
                }
            }

            @Override
            public void onFailure(Call<NeighborhoodResponse> call, Throwable t) {

            }
        });


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
//        SearchByNameFragment searchByNameFragment= SearchByNameFragment.newInstance();
//        searchByNameFragment.getFragmentManager().beginTransaction().replace(R.id.fragment_container,searchByNameFragment).commit();


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, SearchByNameFragment.newInstance())
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
}
