package org.pursuit.restaurantgrades.Views;


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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    private List<List<String>>restaurantList=new ArrayList<>();
    private Button listResultButton,mapResultButton;
    private TextView listCounterTextView;
    private TextInputLayout restaurantNameTextView,zipCodeTextView;

    private static final String ARG_PARAM1="param";

    public static SearchByNameFragment newInstance() {
        SearchByNameFragment searchByNameFragment = new SearchByNameFragment();
        Bundle args = new Bundle();
       // args.putStringArrayList(ARG_PARAM1, (ArrayList<String>)restaurantList);
        searchByNameFragment.setArguments(args);
        return searchByNameFragment;
    }
    public SearchByNameFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataRepository dataRepository=new DataRepository(new ApiClient());
        dataRepository.getRestaurant().enqueue(new Callback<RestaurantResponse>() {
            @Override
            public void onResponse(Call<RestaurantResponse> call, Response<RestaurantResponse> response) {
                restaurantList=response.body().getData();
                Log.d("test", "onResponse: "+restaurantList.get(0).get(8)+restaurantList.get(0).size()+restaurantList.size());
                Toast.makeText(getActivity(), "This is my Toast message!",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<RestaurantResponse> call, Throwable t) {
                Log.d("test", "onFailure: "+t.toString());

            }
        });
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

        restaurantNameTextView=view.findViewById(R.id.text_input_restaurant_name);
        zipCodeTextView=view.findViewById(R.id.text_input_zip_code);
        listCounterTextView=view.findViewById(R.id.list_counter);

        listResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //listCounterTextView.setText(restaurantList.get(0).size());

                // for (List<String> s : restaurantList.get(0)) {

                    //if (restaurantList.get(1).get(8).contains(restaurantNameTextView.getEditText().getText())) {

                   // }
               // }
            }
        });
    }
}
