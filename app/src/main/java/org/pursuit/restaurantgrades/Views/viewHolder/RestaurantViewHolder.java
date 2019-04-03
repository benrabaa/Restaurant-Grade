package org.pursuit.restaurantgrades.Views.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.pursuit.restaurantgrades.Controler.RestaurantAdapter;
import org.pursuit.restaurantgrades.Models.Restaurant;
import org.pursuit.restaurantgrades.R;
import org.pursuit.restaurantgrades.Views.BlankFragment;
import org.pursuit.restaurantgrades.Views.OnFragmentInteractionListener;


public class RestaurantViewHolder extends RecyclerView.ViewHolder {
    private TextView restaurantNameTextView;
    private TextView restaurantAddressTextView;
    private TextView cuisine_descriptionTextView;
    private ImageView gradeImageView;
    private OnFragmentInteractionListener mListener;


    public RestaurantViewHolder(@NonNull View itemView) {
        super(itemView);

        restaurantNameTextView=itemView.findViewById(R.id.restaurant_name);
        restaurantAddressTextView=itemView.findViewById(R.id.restaurant_address);
        cuisine_descriptionTextView=itemView.findViewById(R.id.cuisine_description);
        gradeImageView=itemView.findViewById(R.id.grade);
    }
    public void onBind(Restaurant restaurant,OnFragmentInteractionListener mListener){
        restaurantNameTextView.setText(restaurant.getName());
        restaurantAddressTextView.setText(restaurant.getBuilding()+" "+restaurant.getStreet()+" "+restaurant.getBoro()+" "+restaurant.getZipcode());
        cuisine_descriptionTextView.setText(restaurant.getCuisine_description()+" Cuisine");

        int drawable=R.drawable.gradependinggrade;


        if (restaurant.getGrade()!=null){
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
                    drawable=R.drawable.gradependinggrade;
                    break;
            }

        }

        Glide.with(itemView.getContext())
                .load(drawable)
                .into(gradeImageView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.openDetailsFragment(restaurant);
            }
        });

    }
}
