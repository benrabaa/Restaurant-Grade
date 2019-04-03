package org.pursuit.restaurantgrades.Views;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.pursuit.restaurantgrades.Controler.ViolationsAdapter;
import org.pursuit.restaurantgrades.Models.Restaurant;
import org.pursuit.restaurantgrades.R;
import org.pursuit.restaurantgrades.network.ApiClient;
import org.pursuit.restaurantgrades.network.DataRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private Restaurant restaurant;
    private List<ViolationsFragment> violationsFragmentList=new ArrayList<>();
    private TextView restaurantName,restaurantAddress,restaurantPhone,gradeDate,criticalFlag,violationDescription,inspectionDate;
    private ImageView grade,mapBackground;
    private ViewPager viewPager;
    private String location;
    private DataRepository dataRepositoryQuery = new DataRepository(new ApiClient());
    private List<Restaurant> restaurantQueryList=new ArrayList<>();



    private static DetailsFragment  detailsFragmentInstance;

    private static final String ARG_PARAM1 = "restaurant";

    public static DetailsFragment getInstance(Restaurant restaurant) {
        if (detailsFragmentInstance ==null) {
            detailsFragmentInstance = new DetailsFragment();
            Bundle args = new Bundle();
            args.putParcelable(ARG_PARAM1,  restaurant);
            detailsFragmentInstance.setArguments(args);
            return detailsFragmentInstance;
        }else{
            Bundle args = new Bundle();
            args.putParcelable(ARG_PARAM1,  restaurant);
            detailsFragmentInstance.setArguments(args);
            return detailsFragmentInstance;
        }
    }

    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
        restaurant = getArguments().getParcelable(ARG_PARAM1);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        restaurantName=view.findViewById(R.id.restaurant_name_value);
        restaurantAddress=view.findViewById(R.id.restaurant_address_value);
        restaurantPhone=view.findViewById(R.id.restaurant_phone_value);
        gradeDate=view.findViewById(R.id.grade_date_value);
        grade=view.findViewById(R.id.grade);
        mapBackground=view.findViewById(R.id.map_background);
        viewPager=view.findViewById(R.id.violations_viewPager);

        restaurantName.setText(restaurant.getName());
         location=restaurant.getBuilding()+" "+restaurant.getStreet()+" "+restaurant.getBoro()+", NY "+restaurant.getZipcode();
        restaurantAddress.setText(location);

        restaurantPhone.setText(restaurant.getPhone());
        Log.d("T", "onViewCreated: "+restaurant.getGrade_date());

        if(restaurant.getGrade().equals("Not Yet Graded")){
            gradeDate.setText("-");

        }else {
            int indexT= restaurant.getGrade_date().indexOf('T');
            gradeDate.setText(restaurant.getGrade_date().substring(0,indexT));
        }
        int drawable=R.drawable.gradependinggrade;
        switch (restaurant.getGrade()){
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
            case "N":
                drawable=R.drawable.gradependinggrade;
                break;
        }
        Glide.with(this)
                .load(drawable)
                .into(grade);


        restaurantAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.openRestaurantGoogleMap(location);

            }
        });

        mapBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.openRestaurantGoogleMap(location);

            }
        });

        ////viewpager for violations

        int indexT= restaurant.getInspection_date().indexOf('T');

        Disposable restaurantsListByName =

                dataRepositoryQuery.getRestaurantDataQuery(restaurant.getDba())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(restaurantListByName -> {
                                    restaurantQueryList.addAll(restaurantListByName);
                                    Log.d("all", "getInspection_date().indexOf('T'): " +restaurantQueryList.size());
                            for (Restaurant R: restaurantQueryList) {
                                Log.d("fragmentcount", "onViewCreated: "+restaurantQueryList.size());
                                violationsFragmentList.add(ViolationsFragment.getInstance(R.getInspection_date().substring(0,indexT), R.getCritical_flag(),R.getViolation_description()));
                            }
                            viewPagerSetup();

                            ViolationsAdapter violationsAdapter=new ViolationsAdapter(getFragmentManager(),violationsFragmentList);

                                }, throwable -> throwable.printStackTrace()
                        );



        for (Restaurant R: restaurantQueryList) {
            Log.d("fragmentcount", "onViewCreated: "+restaurantQueryList.size());
            violationsFragmentList.add(ViolationsFragment.getInstance(R.getInspection_date().substring(0,indexT), R.getCritical_flag(),R.getViolation_description()));
        }
        viewPagerSetup();

        ViolationsAdapter violationsAdapter=new ViolationsAdapter(getFragmentManager(),violationsFragmentList);

        ////viewpager for violations



    }

    private void viewPagerSetup() {
        viewPager.setAdapter(new ViolationsAdapter(getFragmentManager(),violationsFragmentList));
        //viewPager.setAdapter(new ViolationsAdapter(getSupportFragmentManager(), violationsFragmentList));
//        viewPager.setOffscreenPageLimit(3);
//        viewPager.setClipToPadding(false);
//        viewPager.setPadding(200,0,200,0);
//        //viewPager.setPageTransformer(true, new DepthPageTransformer() );
//        viewPager.setPageMargin(200);
    }

    @Override
    public void onResume() {
        super.onResume();
        restaurantQueryList.clear();
        violationsFragmentList.clear();
    }
}
