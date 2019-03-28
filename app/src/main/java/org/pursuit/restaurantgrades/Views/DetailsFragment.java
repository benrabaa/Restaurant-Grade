package org.pursuit.restaurantgrades.Views;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.pursuit.restaurantgrades.Models.Restaurant;
import org.pursuit.restaurantgrades.R;

import java.io.Serializable;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private List<Restaurant>restaurantList;
    private TextView restaurantName,restaurantAddress,restaurantPhone,gradeDate,criticalFlag,violationDescription,inspectionDate;
    private ImageView grade;

    private static final String ARG_PARAM1 = "restaurantList";

    public static DetailsFragment getInstance(List<Restaurant> restaurantList) {
        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, (Serializable) restaurantList);
        detailsFragment.setArguments(args);
        return detailsFragment;
    }

    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
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

        restaurantList= (List<Restaurant>) getArguments().getSerializable(ARG_PARAM1);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        restaurantName=view.findViewById(R.id.restaurant_name);
        restaurantAddress=view.findViewById(R.id.restaurant_address);
        restaurantPhone=view.findViewById(R.id.restaurant_phone);
        gradeDate=view.findViewById(R.id.grade_date);
        criticalFlag=view.findViewById(R.id.critical_flag);
        grade=view.findViewById(R.id.grade);

        restaurantName.setText(restaurantList.get(0).getName());
        restaurantAddress.setText(restaurantList.get(0).getBuilding()+restaurantList.get(0).getStreet()+restaurantList.get(0).getBoro()+restaurantList.get(0).getZipcode());
        restaurantPhone.setText(restaurantList.get(0).getPhone());
        gradeDate.setText(restaurantList.get(0).getGrade_date());


        int drawable=R.drawable.gradependinggrade;
        switch (restaurantList.get(0).getGrade()){
            case "A":
                 drawable=R.drawable.agrade;
                 break;
            case "B":
                drawable=R.drawable.bgrade;
                break;
            case "C":
                drawable=R.drawable.cgrade;
                break;
            case "Z":
                drawable=R.drawable.gradependinggrade;
                break;
        }
        Glide.with(this)
                .load(drawable)
                .into(grade);
    }
}
