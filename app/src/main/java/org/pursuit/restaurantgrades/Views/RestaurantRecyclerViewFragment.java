package org.pursuit.restaurantgrades.Views;


import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.pursuit.restaurantgrades.Controler.RestaurantAdapter;
import org.pursuit.restaurantgrades.Models.Restaurant;
import org.pursuit.restaurantgrades.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantRecyclerViewFragment extends Fragment {
    private static final String LIST_PARAM="ListParam";
    private ArrayList<Restaurant> restaurantList=new ArrayList<>();
    private HashMap<String,Restaurant>restaurantHashMap=new HashMap<>();
    private OnFragmentInteractionListener mListener;

    private RecyclerView recyclerView;
    private RestaurantAdapter restaurantAdapter;


    public RestaurantRecyclerViewFragment() {
        // Required empty public constructor
    }


    public static RestaurantRecyclerViewFragment getInstance(List<Restaurant> restaurantList) {
        RestaurantRecyclerViewFragment restaurantRecyclerViewFragment = new RestaurantRecyclerViewFragment();
        Bundle args = new Bundle();
        args.putSerializable(LIST_PARAM, (Serializable) restaurantList);
        restaurantRecyclerViewFragment.setArguments(args);
        return restaurantRecyclerViewFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            restaurantList = (ArrayList<Restaurant>) getArguments().getSerializable(LIST_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant_recycler_view, container, false);
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        for (Restaurant restaurant: restaurantList) {
            if(restaurant.getGrade()!=null){
                //restaurantHashMap.put(restaurant.getCamis(),restaurant);
                System.out.println(restaurant.getDba());
                System.out.println(restaurant.getGrade());
                System.out.println(restaurant.getInspection_date());
            }
        }

//        for (String s: restaurantHashMap.keySet()){
//            String key =s;
//            String value = restaurantHashMap.get(key).toString();
//            System.out.println(key + " " + value);
//        }

       // System.out.println(restaurantHashMap.get("50008504").getInspection_date());

        Log.d("adapter", "list: "+restaurantList.size());

        recyclerView = view.findViewById(R.id.recyclerView);
        restaurantAdapter = new RestaurantAdapter(restaurantList,mListener);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        restaurantAdapter.setAdapterRestaurantList(restaurantList);
        recyclerView.setAdapter(restaurantAdapter);


    }
}
