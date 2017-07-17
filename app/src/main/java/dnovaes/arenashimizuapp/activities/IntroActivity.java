package dnovaes.arenashimizuapp.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import dnovaes.arenashimizuapp.R;

import dnovaes.arenashimizuapp.adapters.IntroAdapter;
import dnovaes.arenashimizuapp.interfaces.IntroPageTransformer;


/**
 * Created by dnovaes on 7/16/17.
 */

public class IntroActivity extends AppCompatActivity{

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.intro_layout);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        //Set an adapter on the viewPager. = FragmentPagerAdapter
        mViewPager.setAdapter(new IntroAdapter(getSupportFragmentManager()));
        //Set a PageTransformer
        mViewPager.setPageTransformer(false, new IntroPageTransformer());
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        // Do something in response to button
    }
}
