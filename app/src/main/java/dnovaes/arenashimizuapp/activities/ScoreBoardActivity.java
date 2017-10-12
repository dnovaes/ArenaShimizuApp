package dnovaes.arenashimizuapp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_layout);

        Button bt = (Button)findViewById(R.id.bt_update);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                //ScoreBoardActivity.this.recreate();
            }
        });

        TableLayout tableSb = (TableLayout) findViewById(R.id.scoreboardLayout);

        for(int rowIndex = 0; rowIndex<tableSb.getChildCount()-1; rowIndex++){

            TableRow row = (TableRow) tableSb.getChildAt(rowIndex);

            for (int i = 1; i < row.getChildCount()-1; i++) {

                View v = row.getChildAt(i);
                if (v instanceof EditText){
                    //validate your EditText here
                    v.setOnClickListener(new View.OnClickListener(){

                        @Override
                        public void onClick(View view) {
                            EditText editText = (EditText)view;
                            Integer score = Integer.valueOf(editText.getText().toString());
                            score++;
                            editText.setText(score.toString(), TextView.BufferType.EDITABLE);

                            TableRow row = (TableRow) view.getParent();
                            if(row.getId() == R.id.border_row3){
                                EditText et = (EditText)findViewById(R.id.total_guest);
                                Integer guestScore = Integer.valueOf(et.getText().toString())+1;
                                et.setText(guestScore.toString(), TextView.BufferType.EDITABLE);
                            }else if(row.getId() == R.id.border_row4){
                                EditText et = (EditText)findViewById(R.id.total_home);
                                Integer homeScore = Integer.valueOf(et.getText().toString())+1;
                                et.setText(homeScore.toString(), TextView.BufferType.EDITABLE);
                            }
                        }
                    });

                    v.setOnLongClickListener(new View.OnLongClickListener(){

                        @Override
                        public boolean onLongClick(View view) {
                            EditText editText = (EditText)view;
                            Integer previousScore = Integer.valueOf(editText.getText().toString());
                            Integer score = Integer.valueOf(0);
                            editText.setText(score.toString(), TextView.BufferType.EDITABLE);
                            //Toast.makeText(ScoreBoardActivity.this, "LONG PRESS!", Toast.LENGTH_SHORT).show();

                            TableRow row = (TableRow) view.getParent();
                            if(row.getId() == R.id.border_row3){
                                EditText et = (EditText)findViewById(R.id.total_guest);
                                Integer guestScore = Integer.valueOf(et.getText().toString())-previousScore;
                                et.setText(guestScore.toString(), TextView.BufferType.EDITABLE);
                            }else if(row.getId() == R.id.border_row4){
                                EditText et = (EditText)findViewById(R.id.total_home);
                                Integer homeScore = Integer.valueOf(et.getText().toString())-previousScore;
                                et.setText(homeScore.toString(), TextView.BufferType.EDITABLE);
                            }
                            return true;
                        }
                    });
                }
            }
        }


    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        // Do something in response to button
    }
}
