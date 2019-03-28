package org.pursuit.restaurantgrades.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import org.pursuit.restaurantgrades.Models.Restaurant;
import org.pursuit.restaurantgrades.R;
//import org.pursuit.restaurantgrades.Models.NeighborhoodResponse;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {
    private List<String> neighborhoodResponseList=new ArrayList<>();
    private List<List<String>> restaurantList =new ArrayList<>();
    private Spinner neighborhoodsSpinner;
    private Spinner boroughsSpinner;


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
    public void openDetailsFragment(List<Restaurant> restaurantList) {

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,DetailsFragment.getInstance(restaurantList))
                .addToBackStack(null)
                .commit();
//        DetailsFragment detailsFragment=DetailsFragment.getInstance(restaurantList);
//        detailsFragment.getFragmentManager().beginTransaction().replace(R.id.fragment_container,detailsFragment).commit();

    }
}
