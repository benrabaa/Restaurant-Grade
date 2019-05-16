package org.pursuit.restaurantgrades.Views;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import org.pursuit.restaurantgrades.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutMeFragment extends Fragment {
    private ImageButton linkedinButton;
    private ImageButton githubButton;
    private OnFragmentInteractionListener mListener;


    public AboutMeFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance(){

        AboutMeFragment aboutMeFragment=new AboutMeFragment();


        return aboutMeFragment;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_me, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        linkedinButton=view.findViewById(R.id.linkedin_imageButton);
        githubButton=view.findViewById(R.id.github_imageButton2);

        linkedinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.linkedin.com/in/m-benrabaa/";
                Intent webIntent = new Intent(Intent.ACTION_VIEW);
                webIntent.setData(Uri.parse(url));
                startActivity(webIntent);            }
        });
        githubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://github.com/benrabaa";
                Intent webIntent = new Intent(Intent.ACTION_VIEW);
                webIntent.setData(Uri.parse(url));
                startActivity(webIntent);            }
        });
    }
}
