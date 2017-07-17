package com.example.dnovaes.arenashimizuapp.adapters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.graphics.Color;

import com.example.dnovaes.arenashimizuapp.fragments.IntroFragment;

/**
 * Created by dnovaes on 7/16/17.
 */

public class IntroAdapter extends FragmentPagerAdapter {

    public IntroAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return IntroFragment.newInstance(Color.parseColor("#03A9F4"), position); //blue
            default:
                return IntroFragment.newInstance(Color.parseColor("#4CAF50"), position); //green
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
