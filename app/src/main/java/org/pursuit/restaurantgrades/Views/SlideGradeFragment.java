package org.pursuit.restaurantgrades.Views;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.pursuit.restaurantgrades.Controler.ViolationsAdapter;
import org.pursuit.restaurantgrades.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SlideGradeFragment extends Fragment {
    private static final String ARG_PARAM1="gradeImage";
    private static final String ARG_PARAM2="gradeHeader";
    private static final String ARG_PARAM3="grade Description";

    private ImageView gradeImageView;
    private TextView gradeHeaderTextView;
    private TextView gradeDescriptionTextView;

    private int gradeImageValue;
    private String gradeHeaderValue;
    private String gradeDescriptionValue;




    public SlideGradeFragment() {
        // Required empty public constructor
    }

    public static SlideGradeFragment getInstance(int gradeImgae ,String gradeHeader  ,String gradeDescripation){
        SlideGradeFragment slideGradeFragment=new SlideGradeFragment();
        Bundle args=new Bundle();
        args.putInt(ARG_PARAM1, gradeImgae);
        args.putString(ARG_PARAM2, gradeHeader);
        args.putString(ARG_PARAM3, gradeDescripation);
        slideGradeFragment.setArguments(args);

        return slideGradeFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gradeImageValue = getArguments().getInt(ARG_PARAM1);
        gradeHeaderValue=getArguments().getString(ARG_PARAM2);
        gradeDescriptionValue=getArguments().getString(ARG_PARAM3);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_slide_grade, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gradeImageView=view.findViewById(R.id.grade_imageView);
        gradeHeaderTextView=view.findViewById(R.id.grade_header_textView);
        gradeDescriptionTextView=view.findViewById(R.id.grade_description_textView);


        gradeImageView.setImageResource(gradeImageValue);
        gradeHeaderTextView.setText(gradeHeaderValue);
        gradeDescriptionTextView.setText(gradeDescriptionValue);
    }


}
