package org.pursuit.restaurantgrades.Views;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.pursuit.restaurantgrades.Controler.SlideAdapter;
import org.pursuit.restaurantgrades.Models.Restaurant;
import org.pursuit.restaurantgrades.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class GradesFragment extends Fragment {
    private SlideGradeFragment slideGradeFragment;
    private List<SlideGradeFragment> slideGradeFragmentList=new ArrayList<>();
    private SlideAdapter slideAdapter;
    private ViewPager viewPager;
    private TextView[] slidesDots;
    private LinearLayout linearLayout;
    private Button nextButton;
    private Button backButton;
    private int currentPosition;

    private int[] slideImage={R.drawable.agrade_small,R.drawable.bgrade_small,R.drawable.cgrade_small,R.drawable.badgrade1};
    private String[] slideHeads={"A Grade","B Grade","C Grade",""};
    private String[] slideDescription={"A letter grade of 'A' indicates that a restaurant received 13 or fewer health code violations in their last inspection. Any recorded violations are likely to be technicalities or non-critical infractions, such as \"Non-food contact surface improperly constructed;\" \"Lighting inadequate;\" or \"Canned food product observed severely dented.\"",
            "A letter grade of 'B' indicates 14 to 27 recorded violations at the restaurant. The majority of NYC restaurants currently fall into this category. At this level some of the violations are noted as 'Critical' by the Department of Health—common Critical violations include \"Cold food held above 41°F (smoked fish above 38°F) except during necessary preparation;\" and \"Sanitized equipment or utensil...improperly used or stored.\""
            ,"A letter grade of 'C' indicates more than 28 health code violations. This is when things start to get pretty gross—and possibly dangerous. For instance, sanitation violations at this level sound like \"Evidence of mice or live mice present in facility's food and/or non-food areas;\" \"Hand washing facility not provided in or near food preparation area and toilet room;\" At the moment, about 200 of the 473 inspected Manhattan restaurants on the DOH website fit into the 'C' category."
            ,"Someone customers caught this interesting way of disguising a “B” \"C\" sanitation posting. a restaurant have to posted it but the business found a way to make it less noticeable. (Note how they worked hard to match the color and font. If they worked equally hard as cleanliness, this might not be necessary)."
    };


    public GradesFragment() {
        // Required empty public constructor
    }
    public static GradesFragment newInstance() {
        GradesFragment gradesFragment = new GradesFragment();
        Bundle args = new Bundle();
        gradesFragment.setArguments(args);
        return gradesFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grades, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager=view.findViewById(R.id.slide_grade_viewPager);
        linearLayout=view.findViewById(R.id.linearLayout);
        nextButton=view.findViewById(R.id.next_button);
        backButton=view.findViewById(R.id.back_button);

        slideAdapter=new SlideAdapter(getChildFragmentManager());


        for (int i = 0; i <slideImage.length ; i++) {
            slideGradeFragmentList.add(SlideGradeFragment.getInstance(slideImage[i], slideHeads[i], slideDescription[i]));
        }
        slideAdapter.setSlideGradeFragmentList(slideGradeFragmentList);
        viewPager.setAdapter(slideAdapter);
        addDotsIndicator(0);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(currentPosition+1);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(currentPosition-1);
            }
        });


        ViewPager.OnPageChangeListener viewListener=new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                addDotsIndicator(i);
                currentPosition=i;
                if(i==0){
                    nextButton.setEnabled(true);
                    backButton.setEnabled(false);
                    backButton.setVisibility(View.INVISIBLE);
                    nextButton.setText("Next");
                    backButton.setText("");
                }else if(i==slidesDots.length-1){

                    backButton.setEnabled(true);
                    nextButton.setEnabled(true);
                    nextButton.setVisibility(View.VISIBLE);
                    backButton.setText("Back");
                    nextButton.setText("Finish");
                }else{
                    nextButton.setEnabled(true);
                    backButton.setEnabled(true);
                    backButton.setVisibility(View.VISIBLE);
                    nextButton.setText("Next");
                    backButton.setText("Back");

                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        };

        viewPager.addOnPageChangeListener(viewListener);

    }


    public void addDotsIndicator(int position){

        slidesDots=new TextView[4];
        linearLayout.removeAllViews();
        for (int i = 0; i <slidesDots.length ; i++) {
            slidesDots[i]=new TextView(getContext());
            slidesDots[i].setText(Html.fromHtml("&#8226;"));
            slidesDots[i].setTextSize(35);
            slidesDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
            linearLayout.addView(slidesDots[i]);
        }
        if(slidesDots.length>0){
            slidesDots[position].setTextColor(getResources().getColor(R.color.white));
        }
    }



}
