package org.pursuit.restaurantgrades.Views;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
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
import java.util.Arrays;

import android.widget.TextView;

import org.pursuit.restaurantgrades.DataStorage;
import org.pursuit.restaurantgrades.Models.Restaurant;
import org.pursuit.restaurantgrades.R;
import org.pursuit.restaurantgrades.network.ApiClient;
import org.pursuit.restaurantgrades.network.DataRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;



public class SearchByNameFragment extends Fragment {
    private List<String> cuisineList = new ArrayList<>();
    private List<String> restaurantList = new ArrayList<>();
    private List<String> zipCodes = new ArrayList<>();
    private List<Restaurant> allRestaurantsList = new ArrayList<>();
    private CompositeDisposable compositeDisposable;


    private List<Restaurant> restaurantQueryList = new ArrayList<>();

    private Button listResultButton, mapResultButton;
    private TextView listCounterTextView;
    private TextInputLayout restaurantNameTextView, zipCodeTextView;
    private Spinner boroughsSpinner, neighborhoodsSpinner, cuisinesSpinner;
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

        listResultButton = view.findViewById(R.id.list_result_button);
        mapResultButton = view.findViewById(R.id.map_result_button);

        restaurantNameTextView = view.findViewById(R.id.text_input_restaurant_name);
        zipCodeTextView = view.findViewById(R.id.text_input_zip_code);
        listCounterTextView = view.findViewById(R.id.list_counter);

        boroughsSpinner = view.findViewById(R.id.search_name_boroughs_spinner);
        neighborhoodsSpinner = view.findViewById(R.id.search_name_neighborhoods_spinner);
        cuisinesSpinner = view.findViewById(R.id.search_name_cuisine_spinner);

        clearVariable();


        fillCuisineSpinner();

        String[] Boroughs = getResources().getStringArray(R.array.Boroughs);
        String[] Brooklyn = getResources().getStringArray(R.array.Brooklyn);
        String[] The_Bronx = getResources().getStringArray(R.array.The_Bronx);
        String[] Queens = getResources().getStringArray(R.array.Queens);
        String[] Manhattan = getResources().getStringArray(R.array.Manhattan);
        String[] Staten_Island = getResources().getStringArray(R.array.Staten_Island);
        String[] Cuisines = getResources().getStringArray(R.array.Cuisines);

        ArrayAdapter<String> CuisinesAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, Cuisines);
        CuisinesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cuisinesSpinner.setAdapter(CuisinesAdapter);

        ArrayAdapter<String> boroughsAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, Boroughs);
        boroughsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boroughsSpinner.setAdapter(boroughsAdapter);

        boroughsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
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
                networkConnection();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        neighborhoodsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                networkConnection();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cuisinesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                networkConnection();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        restaurantNameTextView.getEditText().addTextChangedListener(restaurantEditText);
        zipCodeTextView.getEditText().addTextChangedListener(restaurantEditText);

        listResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (restaurantQueryList.size() == 1) {
                   // mListener.openDetailsFragment(allRestaurantsList.get(0));
                    mListener.openRestaurantRecyclerViewFragment(restaurantQueryList);

                } else {

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
                SearchByNameFragment.this.onClick(v);
            }
        });

    }


    private void fillCuisineSpinner() {
////        Disposable restaurantsListByName =
////                dataRepositoryQuery.getCuisine()
////                        .flatMapIterable(restaurantList -> restaurantList)
////                        .distinct(restaurant -> restaurant.getCuisine_description())
////                        .observeOn(AndroidSchedulers.mainThread())
////                        .toList()
////                        .subscribe(restaurantList -> {
////                                    cuisineList.add("Please select a cuisine");
////                                    for (Restaurant restaurant : restaurantList) {
////                                        cuisineList.add(restaurant.getCuisine_description());
////                                        System.out.println(restaurant.getCuisine_description());
////                                       // Log.d("cuicine", "fillCuisineSpinner: "+restaurant.getCuisine_description);
////
////                                    }
////                                    java.util.Collections.sort(cuisineList);
//
//
//                            ArrayAdapter<String> cuisineAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, cuisineList);
//                                    cuisineAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                                    cuisinesSpinner.setAdapter(cuisineAdapter);
//
//                                }, throwable -> throwable.printStackTrace()
//                        );
    }

    public void networkConnection() {
        clearVariable();
        String cuisine=cuisinesSpinner.getSelectedItem().toString();
        String restaurantName = restaurantNameTextView.getEditText().getText().toString().toUpperCase().trim();
        String borough = boroughsSpinner.getSelectedItem().toString().toUpperCase();
        //       if(cuisinesSpinner != null && cuisinesSpinner.getSelectedItem() !=null ) {
        if (cuisinesSpinner.getSelectedItemPosition() >= 1) {
            cuisine = cuisinesSpinner.getSelectedItem().toString();
        }

        String zipcode = zipCodeTextView.getEditText().getText().toString();
        String whereClause = "";

        if (zipcode.isEmpty()) {
            if (neighborhoodsSpinner.getSelectedItemPosition() >= 1) {
                String neighborhood = neighborhoodsSpinner.getSelectedItem().toString();
                String str = neighborhood.replace(' ', '_');
                int id = this.getResources().getIdentifier(str, "array", this.getContext().getPackageName());
                zipCodes = Arrays.asList(getContext().getResources().getStringArray(id));
//                for (String s:zipCodes) {
//                    System.out.println(s);
//                }
            }
            // use list of zipcodes from borough/neighborhood
            // also pass the neighborhood and selected borough
            // zipCodes = Arrays.asList(getContext().getResources().getStringArray(R.array.Astoria));
        } else {
            zipCodes = Collections.singletonList(zipcode);
            // ignore neighborhood and borough...set to null or whatever
        }

        if (zipCodes != null) {
            StringBuilder zipCodeStringsBuilder = new StringBuilder();
            for (int i = 0; i < zipCodes.size(); i++) {
                String zipCode = zipCodes.get(i);
                zipCodeStringsBuilder.append("%22");
                zipCodeStringsBuilder.append(zipCode);
                zipCodeStringsBuilder.append("%22");

                if (i < zipCodes.size() - 1) {
                    zipCodeStringsBuilder.append(",");
                }
            }
            whereClause = "zipcode%20in(" + zipCodeStringsBuilder.toString() + ")";
        }

        if (restaurantName.isEmpty()) {
            restaurantName = null;
        }
        if (borough.equals("PLEASE SELECT A BOROUGH")) {
            borough = null;
        }
//        if (zipcode.isEmpty()) {
//            zipcode = null;
//        }
        if (whereClause.equals("")) {
            whereClause = null;
        }
      if (cuisine.equals("Please select a Cuisine")) {
            cuisine=null;
        }


        if (borough == null && restaurantName == null && whereClause == null) {
            clearVariable();
        } else {


//            Disposable getAllData =
//                    dataRepositoryQuery.getAllData()
//                            .doOnNext(list -> System.out.println("JROD 1: " + list.size()))
//                            .flatMapIterable(restaurantList -> restaurantList)
//                            .distinct(restaurant -> restaurant.getCamis())
//                            .filter(restaurant -> filterMethod(restaurant))
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .toList()
//                            .doOnSuccess(list -> System.out.println("JROD 2: " + list.size()))
//                            .subscribe(
//                                    restaurantsList -> {
//                                        allRestaurantsList = restaurantsList;
//                                        listCounterTextView.setText(allRestaurantsList.size() + "kkk restaurant(s) match your criteria. ");
//                                    },
//                                    throwable -> throwable.printStackTrace()
//                            );


            Log.d("boro", "networkConnection: " + borough + " " + restaurantName + "" + whereClause + " " + cuisine);
            Disposable restaurantsListByName =
                    dataRepositoryQuery.getRestaurantByAll(borough, restaurantName, whereClause, cuisine)
                            .doOnNext(list -> {
                                System.out.println("JROD 1: " + list.size());
                                allRestaurantsList=list;
                            })
                            .flatMapIterable(restaurantList -> restaurantList)
                            .distinct(restaurant -> restaurant.getCamis())
                            .filter(restaurant -> filterMethod(restaurant))
                            .observeOn(AndroidSchedulers.mainThread())
                            .toList()
                            .doOnSuccess(list -> System.out.println("JROD 2: " + list.size()))
                            .subscribe(
                                    restaurantList -> {
                                        DataStorage.getInstance().setViewHolderRestaurantList(allRestaurantsList);
                                        restaurantQueryList = restaurantList;
                                        listCounterTextView.setText(restaurantList.size() + " restaurant(s) match your criteria. ");
                                    },
                                    throwable -> throwable.printStackTrace()
                            );
        }


//// code is working
//        if (!restaurantNameTextView.getEditText().getText().toString().equals("")) {
//            Log.d("ben", "restaurantNameTextView: " + restaurantNameTextView.getEditText().getText().toString());
//            String selectedBoroughs = boroughsSpinner.getSelectedItem().toString();
//            Disposable restaurantsListByName =
//                    dataRepositoryQuery.getRestaurantByName(restaurantNameTextView.getEditText().getText().toString().toUpperCase().trim(), selectedBoroughs.toUpperCase())
//                            .flatMapIterable(restaurantList -> restaurantList)
//                            .distinct(restaurant -> restaurant.getCamis())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .toList()
//                            .subscribe(restaurantListByName -> {
//                                        restaurantQueryList.addAll(restaurantListByName);
//                                        listCounterTextView.setText(restaurantQueryList.size() + " restaurant(s) match your criteria. ");
//                                    }, throwable -> throwable.printStackTrace()
//                            );
//     } else {
//            String restaurantZipCode = zipCodeTextView.getEditText().getText().toString();
//            Log.d("ben", "restaurantZipCode: " + restaurantNameTextView.getEditText().getText() + "zip" + zipCodeTextView.getEditText().getText());
//            Disposable restaurantByZipCodes =
//                    dataRepositoryQuery.getRestaurantZipCodeDataQuery(restaurantZipCode)
//                            // .flatMapIterable(restaurantList->restaurantList)
//                            .flatMapIterable(restaurantList -> restaurantList)
//                            .filter(restaurant -> filterMethod(restaurant))
//                            .distinct(restaurant -> restaurant.getCamis())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .toList()
//                            .subscribe(restaurantZipCodes -> {
//
//                                restaurantQueryList.addAll(restaurantZipCodes);
//                                Log.d("zipcode", "networkConnection: "+restaurantQueryList.size());
//
//                                listCounterTextView.setText(restaurantQueryList.size() + " restaurant(s) match your criteria. ");
//                                    }, throwable -> throwable.printStackTrace()
//                            );
//        }
    }

    private void clearVariable() {
        restaurantQueryList.clear();
        zipCodes = null;
    }


    private boolean filterMethod(Restaurant restaurant) {
        if (restaurant.getGrade() != null)
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
    }

    @Override
    public void onStart() {
        super.onStart();
        clearVariable();
        listCounterTextView.setText(restaurantQueryList.size() + " restaurant(s) match your criteria. ");
        //cuisineList.clear();
        boroughsSpinner.setSelection(0);
        neighborhoodsSpinner.setSelection(0);
        cuisinesSpinner.setSelection(0);

    }

    private void onClick(View v) {
        mListener.openRestaurantsListGoogleMap(restaurantQueryList);
    }
}
