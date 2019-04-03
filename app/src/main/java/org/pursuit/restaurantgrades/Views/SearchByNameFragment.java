package org.pursuit.restaurantgrades.Views;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;

import org.pursuit.restaurantgrades.Models.Restaurant;
import org.pursuit.restaurantgrades.Models.RestaurantResponse;
import org.pursuit.restaurantgrades.R;
import org.pursuit.restaurantgrades.network.ApiClient;
import org.pursuit.restaurantgrades.network.DataRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchByNameFragment extends Fragment {
    private List<String> cuisineList = new ArrayList<>();
    private List<String> restaurantList = new ArrayList<>();

    private List<Restaurant> restaurantQueryList = new ArrayList<>();

    private Button listResultButton, mapResultButton;
    private TextView listCounterTextView;
    private TextInputLayout restaurantNameTextView, zipCodeTextView;
    private Spinner boroughsSpinner,neighborhoodsSpinner,cuisinesSpinner;
    private DataRepository dataRepositoryQuery = new DataRepository(new ApiClient());


    private OnFragmentInteractionListener mListener;

    private static final String ARG_PARAM1 = "param";

    public static SearchByNameFragment newInstance(List<List<String>> restaurantList) {
        SearchByNameFragment searchByNameFragment = new SearchByNameFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_PARAM1, (ArrayList) restaurantList);
        searchByNameFragment.setArguments(args);
        return searchByNameFragment;
    }

    public SearchByNameFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            restaurantList = getArguments().getStringArrayList(ARG_PARAM1);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_by_name, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("ben", "onCreateView: " + "ggg");

        listResultButton = view.findViewById(R.id.list_result_button);
        mapResultButton = view.findViewById(R.id.map_result_button);
        restaurantNameTextView = view.findViewById(R.id.text_input_restaurant_name);
        zipCodeTextView = view.findViewById(R.id.text_input_zip_code);
        listCounterTextView = view.findViewById(R.id.list_counter);
        mapResultButton=view.findViewById(R.id.map_result_button);

        boroughsSpinner = view.findViewById(R.id.search_name_boroughs_spinner);
        neighborhoodsSpinner = view.findViewById(R.id.search_name_neighborhoods_spinner);
        cuisinesSpinner = view.findViewById(R.id.search_name_cuisine_spinner);



        String[] Boroughs = getResources().getStringArray(R.array.Boroughs);
        String[] Brooklyn = getResources().getStringArray(R.array.Brooklyn);
        String[] The_Bronx = getResources().getStringArray(R.array.The_Bronx);
        String[] Queens = getResources().getStringArray(R.array.Queens);
        String[] Manhattan = getResources().getStringArray(R.array.Manhattan);
        String[] Staten_Island = getResources().getStringArray(R.array.Staten_Island);



        Disposable restaurantsListByName =

                dataRepositoryQuery.getCuisine()
                        .flatMapIterable(restaurantList -> restaurantList)
                        .distinct(restaurant -> restaurant.getCuisine_description())
                        .observeOn(AndroidSchedulers.mainThread())
                        .toList()
                        .subscribe(restaurantList -> {
                            cuisineList.add("Please select a cuisine");
                            for (Restaurant r: restaurantList) {
                                System.out.println(r.getCuisine_description());
                                cuisineList.add(r.getCuisine_description());
                                System.out.println(cuisineList.size());
                            }
                            ArrayAdapter<String> cuisineAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, cuisineList);
                            cuisineAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            cuisinesSpinner.setAdapter(cuisineAdapter);

                                }, throwable -> throwable.printStackTrace()
                        );






        ArrayAdapter<String> boroughsAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, Boroughs);
        boroughsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boroughsSpinner.setAdapter(boroughsAdapter);


        boroughsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                networkConnection();

                Object value = parent.getItemAtPosition(position);
                switch (position) {
                    case 1:
                        ArrayAdapter<String> brooklynAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, Brooklyn);
                        brooklynAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        neighborhoodsSpinner.setAdapter(brooklynAdapter);
                        break;

                    case 2:
                        ArrayAdapter<String> The_BronxAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, The_Bronx);
                        The_BronxAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        neighborhoodsSpinner.setAdapter(The_BronxAdapter);
                        break;

                    case 3:
                        ArrayAdapter<String> QueensAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, Queens);
                        QueensAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        neighborhoodsSpinner.setAdapter(QueensAdapter);
                        break;
                    case 4:
                        ArrayAdapter<String> ManhattanAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, Manhattan);
                        ManhattanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        neighborhoodsSpinner.setAdapter(ManhattanAdapter);
                        break;
                    case 5:
                        ArrayAdapter<String> Staten_IslandAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, Staten_Island);
                        Staten_IslandAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        neighborhoodsSpinner.setAdapter(Staten_IslandAdapter);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                networkConnection();


            }
        });


        restaurantNameTextView.getEditText().addTextChangedListener(restaurantEditText);
        zipCodeTextView.getEditText().addTextChangedListener(restaurantEditText);

        listResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(restaurantQueryList.size()==1){
                     mListener.openDetailsFragment(restaurantQueryList.get(0));
                }else{
                    mListener.openRestaurantRecyclerViewFragment(restaurantQueryList);
                }
//                if (!restaurantNameTextView.getEditText().getText().toString().equals("")) {
//                    mListener.openRestaurantRecyclerViewFragment(restaurantQueryList);
//
//                   // mListener.openDetailsFragment(restaurantQueryList.get(0));
//                } else {
//                    mListener.openRestaurantRecyclerViewFragment(restaurantQueryList);
//                }
//                if(!restaurantNameTextView.getEditText().getText().toString().equals("")) {
//                    Log.d("ben", "restaurantNameTextView: "+"res name");
//                    String selectedBoroughs = boroughsSpinner.getSelectedItem().toString();
//                    dataRepositoryQuery.getRestaurantDataQuery(restaurantNameTextView.getEditText().getText().toString().toUpperCase(), selectedBoroughs.toUpperCase())
//                            .enqueue(new Callback<List<Restaurant>>() {
//                                @Override
//                                public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
//                                    restaurantQueryList = response.body();
//                                    if (restaurantQueryList.size()>0){
//                                        Log.d("test", "onResponse: " + response.body().size());
//                                        listCounterTextView.setText(restaurantQueryList.size() + " " + restaurantQueryList.get(0).getGrade());
//
//                                    }
//                                    mListener.openDetailsFragment(restaurantQueryList);
//                                }
//
//                                @Override
//                                public void onFailure(Call<List<Restaurant>> call, Throwable t) {
//                                    Log.d("test", "onFailure: " + t.toString());
//
//                                }
//                            });
//                }else {
//
//                    Log.d("ben", "zip code: "+"res zip code");
//
////                     String str="";
////                    listCounterTextView.setText(str);
//
//
//                    String restaurantZipCode= zipCodeTextView.getEditText().getText().toString();
//                    Log.d("ben", "restaurantZipCode: "+restaurantNameTextView.getEditText().getText()+"zip"+zipCodeTextView.getEditText().getText());
//                    Disposable restaurantByZipCodes=
//                            dataRepositoryQuery.getRestaurantZipCodeDataQuery(restaurantZipCode)
//                                   // .flatMapIterable(restaurantList->restaurantList)
//                                    .flatMapIterable(restaurantList ->restaurantList  )
//                                    .distinct(restaurant -> restaurant.getName())
//                                    .observeOn(AndroidSchedulers.mainThread())
//                                    .toList()
//                                    .subscribe(restaurantZipCodes->{
//                                        restaurantQueryList.addAll(restaurantZipCodes);
//                                       // str=String.valueOf(restaurantQueryList.size());
//                                        mListener.openRestaurantRecyclerViewFragment(restaurantQueryList);
//                                                Log.d("checking", "onClick: "+ restaurantQueryList.size());
//                                        for (Restaurant r: restaurantQueryList)
//                                        {
//                                            Log.d("tot", "azs"+r.getName());
//                                            listCounterTextView.setText(listCounterTextView.getText()+r.getName());
//                                        }
//                                    },throwable-> throwable.printStackTrace()
//                                    );
//                }
            }
        });

        mapResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.openRestaurantsListGoogleMap(restaurantQueryList);
            }
        });

    }

    public void networkConnection() {
        restaurantQueryList.clear();

        if (!restaurantNameTextView.getEditText().getText().toString().equals("")) {
            Log.d("ben", "restaurantNameTextView: " + restaurantNameTextView.getEditText().getText().toString());
            String selectedBoroughs = boroughsSpinner.getSelectedItem().toString();
            Disposable restaurantsListByName =

                    dataRepositoryQuery.getRestaurantByName(restaurantNameTextView.getEditText().getText().toString().toUpperCase().trim(), selectedBoroughs.toUpperCase())
                            .flatMapIterable(restaurantList -> restaurantList)
                            .distinct(restaurant -> restaurant.getCamis())
                            .observeOn(AndroidSchedulers.mainThread())
                            .toList()
                            .subscribe(restaurantListByName -> {
                                        restaurantQueryList.addAll(restaurantListByName);
                                        listCounterTextView.setText(restaurantQueryList.size() + " restaurant(s) match your criteria. ");
                                    }, throwable -> throwable.printStackTrace()
                            );

//            dataRepositoryQuery.getRestaurantByName(restaurantNameTextView.getEditText().getText().toString().toUpperCase().trim(), selectedBoroughs.toUpperCase())
//                    .enqueue(new Callback<List<Restaurant>>() {
//                        @Override
//                        public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
//                            restaurantQueryList.addAll(response.body());
//                            Log.d("name", "onResponse:name"+restaurantQueryList.size());
//                            if( restaurantQueryList.size()>0){
//                                listCounterTextView.setText( 1+ " restaurant(s) match your criteria. ");
//
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<List<Restaurant>> call, Throwable t) {
//                            Log.d("test", "onFailure: " + t.toString());
//
//                        }
//                    });
        } else {
            String restaurantZipCode = zipCodeTextView.getEditText().getText().toString();
            Log.d("ben", "restaurantZipCode: " + restaurantNameTextView.getEditText().getText() + "zip" + zipCodeTextView.getEditText().getText());
            Disposable restaurantByZipCodes =
                    dataRepositoryQuery.getRestaurantZipCodeDataQuery(restaurantZipCode)
                            // .flatMapIterable(restaurantList->restaurantList)
                            .flatMapIterable(restaurantList -> restaurantList)
                            .filter(restaurant -> filterMethod(restaurant))
                            .distinct(restaurant -> restaurant.getCamis())
                            .observeOn(AndroidSchedulers.mainThread())
                            .toList()
                            .subscribe(restaurantZipCodes -> {

                                restaurantQueryList.addAll(restaurantZipCodes);
                                Log.d("zipcode", "networkConnection: "+restaurantQueryList.size());

                                listCounterTextView.setText(restaurantQueryList.size() + " restaurant(s) match your criteria. ");
                                    }, throwable -> throwable.printStackTrace()
                            );
        }
    }


    private boolean filterMethod(Restaurant restaurant) {
        if (restaurant.getGrade() != null )  //|| (restaurant.getGrade()=="Not Yet Graded")
            return true;
        return false;
    }

    private TextWatcher restaurantEditText = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            networkConnection();
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        restaurantQueryList = new ArrayList<>();
        restaurantQueryList.clear();


    }
}
