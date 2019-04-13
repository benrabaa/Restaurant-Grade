package org.pursuit.restaurantgrades.Views;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.pursuit.restaurantgrades.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
    private Button resturantNameZipButton,boroughButton,slideGradeButton,aboutDeveloperButton;
    private OnFragmentInteractionListener mListener;
    public static final String ARG_PARAM="arrayList";
    private ImageView splashBackgroundImageView;
    private Animation backgroundAnimation;
    private LinearLayout logo_linearLayout1,logo_linearLayout2,menus,restaurant_grading_system_lout,find_restaurant_lout,about_developer_lout;
    private Animation fromcenterToTopLayoutAnimation;

    private List<String> restaurantResponseList=new ArrayList<>();

    public static SearchFragment getInstance(){

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
//        resturantNameZipButton=view.findViewById(R.id.restaurant_name_zip_button);
//        boroughButton=view.findViewById(R.id.Borough_button);
//        slideGradeButton=view.findViewById(R.id.slide_grade_button);
//        aboutDeveloperButton=view.findViewById(R.id.about_developer_button);


        splashBackgroundImageView=view.findViewById(R.id.splash_background_search_page);
        logo_linearLayout1=view.findViewById(R.id.logo_linearLayout1);
        logo_linearLayout2=view.findViewById(R.id.logo_linearLayout2);
        menus=view.findViewById(R.id.menus);


        restaurant_grading_system_lout=view.findViewById(R.id.restaurant_grading_system_lout);
        find_restaurant_lout=view.findViewById(R.id.find_restaurant_lout);
        about_developer_lout=view.findViewById(R.id.about_developer_lout);


        fromcenterToTopLayoutAnimation=AnimationUtils.loadAnimation(getContext(),R.anim.fromcenter_to_top);


        splashBackgroundImageView.animate().translationY(-1900).setDuration(800).setStartDelay(300);
        logo_linearLayout1.animate().translationY(140).alpha(0).setDuration(800).setStartDelay(300);
        logo_linearLayout2.startAnimation(fromcenterToTopLayoutAnimation);
        menus.startAnimation(fromcenterToTopLayoutAnimation);




        restaurant_grading_system_lout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.openSlideGradesFramgment();

            }
        });

        find_restaurant_lout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.openSearchByNameFragment();

            }
        });

        about_developer_lout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.openAboutMeFragment();

            }
        });





//        aboutDeveloperButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mListener.openAboutMeFragment();
//            }
//        });
//
//        slideGradeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mListener.openSlideGradesFramgment();
//            }
//        });
//
//
//        resturantNameZipButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mListener.openSearchByNameFragment();
//            }
//        });
    }



}
