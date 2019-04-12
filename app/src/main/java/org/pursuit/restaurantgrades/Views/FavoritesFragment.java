package org.pursuit.restaurantgrades.Views;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.pursuit.restaurantgrades.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment {
    private ImageView underConstructionImageView;


    public FavoritesFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance(){
        FavoritesFragment favoritesFragment=new FavoritesFragment();


        return favoritesFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        underConstructionImageView=view.findViewById(R.id.under_construction_image_view);

        Glide.with(this)
                .load(R.drawable.under_construction)
                .into(underConstructionImageView);
    }
}
