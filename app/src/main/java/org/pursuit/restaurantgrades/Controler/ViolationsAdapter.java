package org.pursuit.restaurantgrades.Controler;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.pursuit.restaurantgrades.Views.ViolationsFragment;

import java.util.List;

public class ViolationsAdapter extends FragmentPagerAdapter {
    private List<ViolationsFragment> fragmentList;

    public ViolationsAdapter(FragmentManager fm,List<ViolationsFragment> fragmentList) {
        super(fm);
        this.fragmentList=fragmentList;
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void setFragmentList(List<ViolationsFragment> fragmentList) {
        this.fragmentList = fragmentList;
        notifyDataSetChanged();

    }
}
