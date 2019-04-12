package org.pursuit.restaurantgrades.Views;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.pursuit.restaurantgrades.Models.Restaurant;
import org.pursuit.restaurantgrades.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViolationsFragment extends Fragment {
    private static final String ARG_PARAM1="criticalFlag";
    private static final String ARG_PARAM2="inspectionDate";
    private static final String ARG_PARAM3="violationDescription";

    private String criticalFlag;
    private String inspectionDate;
    private String violationDescription;



    private TextView criticalFlagTextView,violationDescriptiontextView,inspectionDateTextview;


    public ViolationsFragment() {
        // Required empty public constructor
    }
    public static ViolationsFragment getInstance(String inspectionDate,String criticalFlag  ,String violationDescription){
        ViolationsFragment violationsFragment=new ViolationsFragment();
        Bundle args=new Bundle();
        args.putString(ARG_PARAM1, criticalFlag);
        args.putString(ARG_PARAM2, inspectionDate);
        args.putString(ARG_PARAM3, violationDescription);
        violationsFragment.setArguments(args);

        return violationsFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            criticalFlag=getArguments().getString(ARG_PARAM1);
            inspectionDate=getArguments().getString(ARG_PARAM2);
            violationDescription=getArguments().getString(ARG_PARAM3);


        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_violations, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        criticalFlagTextView=view.findViewById(R.id.critical_flag_value);
        inspectionDateTextview=view.findViewById(R.id.inspection_date_value);
        violationDescriptiontextView=view.findViewById(R.id.violation_description_value);
        if (criticalFlag.equals("Critical")){
            criticalFlagTextView.setTextColor(getResources().getColor(R.color.colorAccent));
            criticalFlagTextView.setText(criticalFlag);
            violationDescriptiontextView.setTextColor(getResources().getColor(R.color.colorAccent));
            violationDescriptiontextView.setText(violationDescription);

        }
        criticalFlagTextView.setText(criticalFlag);
        violationDescriptiontextView.setText(violationDescription);
        inspectionDateTextview.setText(inspectionDate);
        violationDescriptiontextView.setText(violationDescription);
    }

}
