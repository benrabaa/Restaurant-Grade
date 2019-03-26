package org.pursuit.restaurantgrades.Views;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.pursuit.restaurantgrades.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
    private Button resturantNameZipButton,boroughButton;
    private OnFragmentInteractionListener mListener;
    public static final String ARG_PARAM="arrayList";
    private List<String> restaurantResponseList=new ArrayList<>();

    public static SearchFragment getInstance(){

//        restaurantResponseList=restaurantResponseList;
        SearchFragment searchFragment=new SearchFragment();
        Bundle args=new Bundle();
      // args.putStringArrayList(ARG_PARAM, (ArrayList<String>) restaurantResponseList);
//        args.putString("param2",param);
//
        searchFragment.setArguments(args);
        return searchFragment;
    }


    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
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
        resturantNameZipButton=view.findViewById(R.id.restaurant_name_zip_button);
        boroughButton=view.findViewById(R.id.Borough_button);


        resturantNameZipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.openSearchByNameFragment();
            }
        });
    }



}
