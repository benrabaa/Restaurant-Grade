package org.pursuit.restaurantgrades.Controler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.pursuit.restaurantgrades.Models.Restaurant;
import org.pursuit.restaurantgrades.R;
import org.pursuit.restaurantgrades.Views.OnFragmentInteractionListener;
import org.pursuit.restaurantgrades.Views.viewHolder.RestaurantViewHolder;

import java.util.ArrayList;
import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantViewHolder> {

    private List<Restaurant>restaurantList=new ArrayList<>();
    private OnFragmentInteractionListener mListener;

    public RestaurantAdapter(List<Restaurant>restaurantList,OnFragmentInteractionListener mListener){
        this.restaurantList=restaurantList;
        this.mListener=mListener;

    }
    public void setAdapterRestaurantList(List<Restaurant>restaurantList){
        this.restaurantList=restaurantList;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View childViewHolder= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view,viewGroup,false);
        return new RestaurantViewHolder(childViewHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder restaurantViewHolder, int position) {
        Restaurant restaurant=restaurantList.get(position);
        restaurantViewHolder.onBind(restaurant,mListener);
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }
}
