package dnovaes.arenashimizuapp.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import dnovaes.arenashimizuapp.R;

import dnovaes.arenashimizuapp.adapters.IntroAdapter;
import dnovaes.arenashimizuapp.fragments.IntroFragment;
import dnovaes.arenashimizuapp.interfaces.IntroPageTransformer;


/**
 * Created by dnovaes on 7/16/17.
 */

public class IntroActivity extends AppCompatActivity{

    private ViewPager mViewPager;
    private IntroAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.intro_layout);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        //Set an adapter on the viewPager. = FragmentPagerAdapter
        adapter = new IntroAdapter(getSupportFragmentManager());
        adapter.addFragment(IntroFragment.newInstance(Color.parseColor("#03A9F4"), 0)); //blue
        adapter.addFragment(IntroFragment.newInstance(Color.parseColor("#4CAF50"), 1)); //green
        mViewPager.setAdapter(adapter);

        //Set a PageTransformer
        mViewPager.setPageTransformer(false, new IntroPageTransformer());
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        // Do something in response to button
    }

    public void navigateToFragment(int mPage){
        mViewPager.setCurrentItem(mPage);
        //Toast.makeText(this, "Page number: "+mViewPager.getCurrentItem(), Toast.LENGTH_LONG).show();
    }
}
