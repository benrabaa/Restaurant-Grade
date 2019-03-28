package org.pursuit.restaurantgrades.Views;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.pursuit.restaurantgrades.Models.Restaurant;
import org.pursuit.restaurantgrades.Models.RestaurantResponse;
import org.pursuit.restaurantgrades.R;
import org.pursuit.restaurantgrades.network.ApiClient;
import org.pursuit.restaurantgrades.network.DataRepository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchByNameFragment extends Fragment {
    private ArrayList<String> restaurantList=new ArrayList<>();
    private List<Restaurant> restaurantQueryList;
    private Button listResultButton,mapResultButton;
    private TextView listCounterTextView;
    private TextInputLayout restaurantNameTextView,zipCodeTextView;
    private Spinner boroughsSpinner;

    private OnFragmentInteractionListener mListener;

    private static final String ARG_PARAM1="param";

    public static SearchByNameFragment newInstance(List<List<String>> restaurantList) {
        SearchByNameFragment searchByNameFragment = new SearchByNameFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_PARAM1,(ArrayList)restaurantList);
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
            restaurantList =getArguments().getStringArrayList(ARG_PARAM1);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_by_name, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listResultButton=view.findViewById(R.id.list_result_button);
        mapResultButton=view.findViewById(R.id.map_result_button);
        boroughsSpinner=view.findViewById(R.id.search_name_boroughs_spinner);
        restaurantNameTextView=view.findViewById(R.id.text_input_restaurant_name);
        zipCodeTextView=view.findViewById(R.id.text_input_zip_code);
        listCounterTextView=view.findViewById(R.id.list_counter);
        String[] brooklynNeighborhoods = getResources().getStringArray(R.array.Boroughs);


        ArrayAdapter<String> adapter= new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,brooklynNeighborhoods);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boroughsSpinner.setAdapter(adapter);




        listResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String selectedBoroughs = boroughsSpinner.getSelectedItem().toString();
                DataRepository dataRepositoryQuery=new DataRepository(new ApiClient());
                Log.d("name", "onClick: "+restaurantNameTextView.getEditText().getText().toString().toUpperCase()+selectedBoroughs.toUpperCase());
                dataRepositoryQuery.getRestaurantDataQuery(restaurantNameTextView.getEditText().getText().toString().toUpperCase(), selectedBoroughs.toUpperCase())
                        .enqueue(new Callback<List<Restaurant>>() {
                    @Override
                    public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                        restaurantQueryList=response.body();
                        Log.d("test", "onResponse: "+response.body().size());
                        listCounterTextView.setText(restaurantQueryList.size()+" "+restaurantQueryList.get(0).getGrade());
                        mListener.openDetailsFragment(restaurantQueryList);
                    }

                    @Override
                    public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                        Log.d("test", "onFailure: "+t.toString());

                    }
                });
            }
        });
    }
}
