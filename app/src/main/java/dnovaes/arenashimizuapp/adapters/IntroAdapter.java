package dnovaes.arenashimizuapp.adapters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dnovaes.arenashimizuapp.fragments.IntroFragment;

/**
 * Created by dnovaes on 7/16/17.
 */

public class IntroAdapter extends FragmentPagerAdapter {

    public final List<Fragment> listFrag = new ArrayList<Fragment>();

    public IntroAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        return listFrag.get(position);
    }

    @Override
    public int getCount() {
        return 2;
    }

    public void addFragment(Fragment f){
        listFrag.add(f);
    }
}
