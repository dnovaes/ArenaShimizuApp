package dnovaes.arenashimizuapp.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import dnovaes.arenashimizuapp.R;
import dnovaes.arenashimizuapp.adapters.IntroAdapter;
import dnovaes.arenashimizuapp.fragments.IntroFragment;
import dnovaes.arenashimizuapp.interfaces.IntroPageTransformer;


/**
 * Created by dnovaes on 7/16/17.
 */

public class ScoreBoardActivity extends AppCompatActivity{

    private IntroAdapter adapter;
    private GestureDetector mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_layout);

        //Set an adapter on the viewPager. = FragmentPagerAdapter

        final EditText h1 = (EditText) findViewById(R.id.h1);

        h1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                    handler.postDelayed(mLongPressed, 1000);
                if (event.getAction() == MotionEvent.ACTION_UP ) {
                    //Toast.makeText(ScoreBoardActivity.this, "você clicou em ", Toast.LENGTH_SHORT).show();
                    Integer score = Integer.valueOf(h1.getText().toString());
                    score++;
                    h1.setText(score.toString(), TextView.BufferType.EDITABLE);
                    handler.removeCallbacks(mLongPressed);
                    return true;
                }
                return false;
            }
        });
    }

    final Handler handler = new Handler();
    Runnable mLongPressed = new Runnable() {
        public void run() {
            Toast.makeText(ScoreBoardActivity.this, "LONG PRESS!", Toast.LENGTH_SHORT).show();
        }
    };

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        // Do something in response to button
    }
}
