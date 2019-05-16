package org.pursuit.restaurantgrades.Controler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import org.pursuit.restaurantgrades.Models.Restaurant;
import org.pursuit.restaurantgrades.R;
import org.pursuit.restaurantgrades.Views.OnFragmentInteractionListener;
import org.pursuit.restaurantgrades.Views.RestaurantRecyclerViewFragment;
import org.pursuit.restaurantgrades.Views.viewHolder.RestaurantViewHolder;

import java.util.ArrayList;
import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantViewHolder> {

    private List<Restaurant>restaurantList=new ArrayList<>();
    Context context;

    private OnFragmentInteractionListener mListener;

    public RestaurantAdapter(List<Restaurant>restaurantList, OnFragmentInteractionListener mListener, Context context){
        this.restaurantList=restaurantList;
        this.mListener=mListener;
        this.context=context;

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
        restaurantViewHolder.itemView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
        restaurantViewHolder.onBind(restaurant,mListener);
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }
}
