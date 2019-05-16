package org.pursuit.restaurantgrades.Views;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

import org.pursuit.restaurantgrades.Animation.DepthPageTransformer;
import org.pursuit.restaurantgrades.Controler.ViolationsAdapter;
import org.pursuit.restaurantgrades.DataStorage;
import org.pursuit.restaurantgrades.Models.Restaurant;
import org.pursuit.restaurantgrades.R;
import org.pursuit.restaurantgrades.network.ApiClient;
import org.pursuit.restaurantgrades.network.DataRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {
    private static final String TAG = "jimenez";
    private OnFragmentInteractionListener mListener;
    private Restaurant restaurant;
    private List<ViolationsFragment> violationsFragmentList=new ArrayList<>();
    private TextView restaurantName, restaurantAddress, restaurantPhone, gradeDate, criticalFlag, violationDescription, inspectionDate;
    private ImageView gradeImageView, mapBackgroundImageView,callImageView;
    private ViewPager viewPager;
    private String location;
    private DataRepository dataRepositoryQuery = new DataRepository(new ApiClient());
    private List<Restaurant> restaurantQueryList = new ArrayList<>();
    private ViolationsAdapter violationsAdapter;


    private static final String ARG_PARAM1 = "restaurant";
    private static final String ARG_PARAM2 = "fragmentRestaurantList";


    public static DetailsFragment newInstance(Restaurant restaurant) {
        DetailsFragment detailsFragmentInstance = new DetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, restaurant);
        detailsFragmentInstance.setArguments(args);
        return detailsFragmentInstance;
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
        violationsAdapter = new ViolationsAdapter(getChildFragmentManager(),violationsFragmentList );
//       violationsAdapter = new ViolationsAdapter(getFragmentManager(), violationsFragmentList);
//
//        violationsAdapter.setFragmentList(violationsFragmentList);
//        viewPager.setAdapter(violationsAdapter);


        //  violationsFragmentList.clear();
//
//        int indexT = restaurant.getInspection_date().indexOf('T');
//        violationsAdapter = new ViolationsAdapter(getFragmentManager(), violationsFragmentList);
//        //viewPager.setAdapter(violationsAdapter);
//        Disposable restaurantsListByName =
//                dataRepositoryQuery.getRestaurantDataQuery(restaurant.getDba())
//                        .subscribeOn(Schedulers.io())
//                        .flatMapIterable(restaurants -> restaurants)
//                        .map(restaurant -> ViolationsFragment.newInstance(restaurant.getInspection_date().substring(0, indexT), restaurant.getCritical_flag(), restaurant.getViolation_description()))
//                        .toList()
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(restaurantFrags -> violationsAdapter.setFragmentList(restaurantFrags),
//                                new Consumer<Throwable>() {
//                                    @Override
//                                    public void accept(Throwable throwable) throws Exception {
//                                        throwable.printStackTrace();
//                                    }
//                                });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        restaurantName = view.findViewById(R.id.restaurant_name_value);
        restaurantAddress = view.findViewById(R.id.restaurant_address_value);
        restaurantPhone = view.findViewById(R.id.restaurant_phone_value);
        gradeDate = view.findViewById(R.id.grade_date_value);
        gradeImageView = view.findViewById(R.id.grade);
        mapBackgroundImageView = view.findViewById(R.id.map_background);
        viewPager = view.findViewById(R.id.violations_viewPager);
        callImageView=view.findViewById(R.id.call_image_view);

        restaurantName.setText(restaurant.getName());
        location = restaurant.getBuilding() + " " + restaurant.getStreet() + " " + restaurant.getBoro() + ", NY " + restaurant.getZipcode();
        restaurantAddress.setText(location);

        restaurantPhone.setText(restaurant.getPhone());

        if (restaurant.getGrade().equals("Not Yet Graded")) {
            gradeDate.setText("-");

        } else {
            int indexT = restaurant.getGrade_date().indexOf('T');
            gradeDate.setText(restaurant.getGrade_date().substring(0, indexT));
        }
        int drawable = R.drawable.gradependinggrade;
        switch (restaurant.getGrade()) {
            case "A":
                drawable = R.drawable.agrade;
                break;
            case "B":
                drawable = R.drawable.bgrade;
                break;
            case "C":
                drawable = R.drawable.cgarde;
                break;
            case "Z":
            case "N":
                drawable = R.drawable.gradependinggrade;
                break;
        }
        Glide.with(this)
                .load(drawable)
                .into(gradeImageView);


        restaurantAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.openRestaurantGoogleMap(location);

            }
        });

        callImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + restaurant.getPhone()));
                startActivity(intent);
            }
        });



        mapBackgroundImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.openRestaurantGoogleMap(location);

            }
        });

        int indexT = restaurant.getGrade_date().indexOf('T');
        restaurantQueryList = DataStorage.getInstance().getdetailsRestaurantList();
        Log.d("ben", "get details RestaurantList: "+restaurantQueryList.size());

        int arrayLength=5;
        if(restaurantQueryList.size()<5){
            arrayLength=restaurantQueryList.size();
        }
        for (int i = 0; i <arrayLength ; i++) {
            violationsFragmentList.add(ViolationsFragment.getInstance(restaurantQueryList.get(i).getInspection_date().substring(0, indexT), restaurantQueryList.get(i).getCritical_flag(), restaurantQueryList.get(i).getViolation_description()));
        }
        Log.d("ben", "violationsFragmentList: "+violationsFragmentList.size());
        //violationsFragmentList.add(ViolationsFragment.getInstance(fragmentRestaurant.getInspection_date().substring(0, indexT), fragmentRestaurant.getCritical_flag(), fragmentRestaurant.getViolation_description())))

        violationsAdapter.setFragmentList(violationsFragmentList);
        viewPager.setAdapter(violationsAdapter);
        viewPagerSetup();

        //viewPagerSetup();
//       violationsAdapter.setFragmentList(violationsFragmentList);
//        viewPager.setAdapter(violationsAdapter);

//        int indexT= restaurant.getInspection_date().indexOf('T');
//        violationsAdapter=new ViolationsAdapter(getFragmentManager(),violationsFragmentList);
//        viewPager.setAdapter(violationsAdapter);
//        Disposable restaurantsListByName =
//
//                dataRepositoryQuery.getRestaurantDataQuery(restaurant.getDba())
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(restaurantListByName -> {
//                                    restaurantQueryList=restaurantListByName;
//                            for (Restaurant R: restaurantQueryList) {
//                                violationsFragmentList.add(ViolationsFragment.newInstance(R.getInspection_date().substring(0,indexT), R.getCritical_flag(),R.getViolation_description()));
//                            }
//                            violationsAdapter.setFragmentList(violationsFragmentList);
//
//                                    //viewPagerSetup();
//                                }, throwable -> throwable.printStackTrace()
//                        );


//        for (Restaurant R: restaurantQueryList) {
//            violationsFragmentList.add(ViolationsFragment.newInstance(R.getInspection_date().substring(0,indexT), R.getCritical_flag(),R.getViolation_description()));
//        }
        //viewPagerSetup();

        //  ViolationsAdapter violationsAdapter=new ViolationsAdapter(getFragmentManager(),violationsFragmentList);

        ////viewpager for violations

    }

    private void viewPagerSetup() {

       // violationsAdapter.setFragmentList(violationsFragmentList);
        //viewPager.setAdapter(new ViolationsAdapter(getSupportFragmentManager(), violationsFragmentList));
        viewPager.setOffscreenPageLimit(3);
        viewPager.setClipToPadding(false);
       // viewPager.setPadding(10,0,5,0);
        viewPager.setPageTransformer(true, new DepthPageTransformer() );
        viewPager.setPageMargin(10);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("size", "on Resume: " + violationsFragmentList.size());


//        int indexT = restaurant.getGrade_date().indexOf('T');
//        restaurantQueryList = DataStorage.getInstance().getdetailsRestaurantList();
//
//        for (int i = 0; i <5 ; i++) {
//            violationsFragmentList.add(ViolationsFragment.getInstance(restaurantQueryList.get(i).getInspection_date().substring(0, indexT), restaurantQueryList.get(i).getCritical_flag(), restaurantQueryList.get(i).getViolation_description()));
//        }
//        Log.d("ben", "violationsFragmentList: "+violationsFragmentList.size());
//        //violationsFragmentList.add(ViolationsFragment.getInstance(fragmentRestaurant.getInspection_date().substring(0, indexT), fragmentRestaurant.getCritical_flag(), fragmentRestaurant.getViolation_description())))
//
//        violationsAdapter.setFragmentList(violationsFragmentList);
//        viewPager.setAdapter(violationsAdapter);
//        viewPager.setOffscreenPageLimit(4);



        //violationsAdapter.setFragmentList(violationsFragmentList);
        //restaurantQueryList.clear();
        //violationsFragmentList.clear();
    }
}
