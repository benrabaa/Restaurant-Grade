package org.pursuit.restaurantgrades.Views.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.pursuit.restaurantgrades.Controler.RestaurantAdapter;
import org.pursuit.restaurantgrades.Controler.ViolationsAdapter;
import org.pursuit.restaurantgrades.DataStorage;
import org.pursuit.restaurantgrades.Models.Restaurant;
import org.pursuit.restaurantgrades.R;
import org.pursuit.restaurantgrades.Views.BlankFragment;
import org.pursuit.restaurantgrades.Views.OnFragmentInteractionListener;
import org.pursuit.restaurantgrades.Views.ViolationsFragment;
import org.pursuit.restaurantgrades.network.ApiClient;
import org.pursuit.restaurantgrades.network.DataRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;


public class RestaurantViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "jimenez";
    private TextView restaurantNameTextView;
    private TextView restaurantAddressTextView;
    private TextView cuisine_descriptionTextView;
    private ImageView gradeImageView;
    private OnFragmentInteractionListener mListener;
    private List<ViolationsFragment> violationsFragmentList;
    private List<Restaurant> viewHolderRestaurantList = new ArrayList<>();
    private List<Restaurant> violationsRestaurantList;


    private String restaurantName = "";


    private DataRepository dataRepositoryQuery = new DataRepository(new ApiClient());


    public RestaurantViewHolder(@NonNull View itemView) {
        super(itemView);

        restaurantNameTextView = itemView.findViewById(R.id.restaurant_name);
        restaurantAddressTextView = itemView.findViewById(R.id.restaurant_address);
        cuisine_descriptionTextView = itemView.findViewById(R.id.cuisine_description);
        gradeImageView = itemView.findViewById(R.id.grade);


    }

    public void onBind(final Restaurant showInRestaurant, OnFragmentInteractionListener mListener) {
        restaurantName = showInRestaurant.getName();
        restaurantNameTextView.setText(showInRestaurant.getName());
        restaurantAddressTextView.setText(new StringBuilder().append(showInRestaurant.getBuilding()).append(" ").append(showInRestaurant.getStreet()).append(" ").append(showInRestaurant.getBoro()).append(" ").append(showInRestaurant.getZipcode()).toString());
        cuisine_descriptionTextView.setText(new StringBuilder().append(showInRestaurant.getCuisine_description()).append(" Cuisine").toString());

        int drawable = R.drawable.gradependinggrade;

        if (showInRestaurant.getGrade() != null) {
            switch (showInRestaurant.getGrade()) {
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
                    drawable = R.drawable.gradependinggrade;
                    break;
            }

        }

        Glide.with(itemView.getContext())
                .load(drawable)
                .into(gradeImageView);

        int indexT = showInRestaurant.getInspection_date().indexOf('T');


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolderRestaurantList = DataStorage.getInstance().getviewHolderRestaurantList();

                Log.d("ben", "onClick:view holder before rx " + viewHolderRestaurantList.size());
                Log.d(TAG, "onClick: " + showInRestaurant.getDba());

                Observable<List<Restaurant>> restaurants = Observable.fromArray(viewHolderRestaurantList);
                Disposable disposable = restaurants
                        .subscribeOn(Schedulers.io())
                        .doOnNext(restaurantList1 -> Log.d(TAG, "onClick: " + restaurantList1.size()))
                        .flatMapIterable(restaurantList -> restaurantList)
                        .filter(restaurant -> filterMethod(restaurant))
                        .toList()
                        .subscribe(restaurantFrags -> {
                                    violationsRestaurantList = restaurantFrags;
                                    DataStorage.getInstance().setdetailsRestaurantList(violationsRestaurantList);
                                    Log.d("ben", "onClick:view holder after rx " + violationsRestaurantList.size());

                                    mListener.openDetailsFragment(showInRestaurant);
                                },
                                throwable -> Log.d(TAG, "onClickinOnB: " + throwable));


//                Observable<List<Restaurant>> restaurants = Observable.fromArray(restaurantList);
//                Disposable disposable=restaurants
//                        .subscribeOn(Schedulers.io())
//                        .doOnNext(restaurantList1 -> Log.d(TAG, "onClick: "+restaurantList1.size()))
//                        .flatMapIterable(restaurantList -> restaurantList)
//                        .filter(restaurant -> {
//                            if (restaurant.getDba().equals(showInRestaurant.getDba())){
//                                return  true;
//                            }
//                            return false; })
//                        .map(fragmentRestaurant -> ViolationsFragment.getInstance(fragmentRestaurant.getInspection_date().substring(0, indexT), fragmentRestaurant.getCritical_flag(), fragmentRestaurant.getViolation_description()))
//                        .toList()
//                        .subscribe(restaurantFrags->{
//                                    violationsFragmentList = restaurantFrags;
//                                    DataStorage.getInstance().setViolationsFragments(violationsFragmentList);
//                                    mListener.openDetailsFragment(showInRestaurant);
//                                    Log.d(TAG, "restaurantFrags: "+restaurantFrags.size()+showInRestaurant.getDba());
//                                },
//                                throwable ->Log.d(TAG, "onClick: "+throwable));
                // yesterday method
// Disposable disposable = dataRepositoryQuery.getRestaurantDataQuery(sprestaurant.getDba())
//                        .subscribeOn(Schedulers.io())
//                        .flatMapIterable(restaurants -> restaurants)
//                        .map(fragmentRestaurant -> ViolationsFragment.getInstance(fragmentRestaurant.getInspection_date().substring(0, indexT), fragmentRestaurant.getCritical_flag(), fragmentRestaurant.getViolation_description()))
//                        .toList()
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(restaurantFrags -> {
//                                    Log.d(TAG, "onBind: ");
//                                    violationsFragmentList = restaurantFrags;
//                                    DataStorage.getInstance().setViolationsFragments(violationsFragmentList);
//                                    mListener.openDetailsFragment(sprestaurant);
//
//                                },
////                                violationsFragmentList.addAll(restaurantFrags),
//                                throwable -> Log.d(TAG, "onError: " + throwable));
//            }
            }

            private boolean filterMethod(Restaurant restaurant) {
                if (restaurant.getDba() != null && showInRestaurant.getDba() != null) {

                    return restaurant.getDba().equals(showInRestaurant.getDba());
                } else {
                    Log.d("ben", "filterMethod: "+restaurant.getDba()+" show"+showInRestaurant.getDba());
                    return false;
                }
            }
        });
    }
}
