package org.pursuit.restaurantgrades.Controler;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import org.pursuit.restaurantgrades.R;
import org.pursuit.restaurantgrades.Views.SlideGradeFragment;

import java.util.ArrayList;
import java.util.List;

public class SlideAdapter extends FragmentPagerAdapter {
    private List<SlideGradeFragment>slideGradeFragmentList=new ArrayList<>();

    public void setSlideGradeFragmentList(List<SlideGradeFragment> slideGradeFragmentList){
        this.slideGradeFragmentList=slideGradeFragmentList;
        notifyDataSetChanged();
    }

    public SlideAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return slideGradeFragmentList.get(i);
    }

    @Override
    public int getCount() {
        return slideGradeFragmentList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return super.isViewFromObject(view, object);
        //return view==object;

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }
}
