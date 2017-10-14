package dnovaes.arenashimizuapp.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import dnovaes.arenashimizuapp.R;
import dnovaes.arenashimizuapp.adapters.IntroAdapter;


/**
 * Created by dnovaes on 7/16/17.
 */

public class ScoreBoardActivity extends AppCompatActivity{

    private IntroAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_layout);

        final DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        EditText ballCounter = (EditText)findViewById(R.id.ball_counter);
        ballCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText et = (EditText)view;
                Integer score = Integer.valueOf(et.getText().toString());
                if(score < 3){
                    score++;
                }else{
                    score = 0;
                }
                et.setText(score.toString());
            }
        });
        ballCounter.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                EditText editText = (EditText) view;
                Integer score = Integer.valueOf(0);
                editText.setText(score.toString(), TextView.BufferType.EDITABLE);
                return true;
            }
        });

        EditText strikeCounter = (EditText)findViewById(R.id.strike_counter);
        strikeCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText et = (EditText)view;
                Integer score = Integer.valueOf(et.getText().toString());
                if(score < 2){
                    score++;
                }else{
                    score = 0;
                }
                et.setText(score.toString());
            }
        });
        strikeCounter.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                EditText editText = (EditText) view;
                Integer score = Integer.valueOf(0);
                editText.setText(score.toString(), TextView.BufferType.EDITABLE);
                return true;
            }
        });

        EditText outCounter = (EditText)findViewById(R.id.out_counter);
        outCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            EditText et = (EditText)view;
            Integer score = Integer.valueOf(et.getText().toString());
            if(score < 2){
                score++;
            }else{
                score = 0;
            }
            et.setText(score.toString());
            }
        });
        outCounter.setOnLongClickListener(new View.OnLongClickListener() {
              @Override
              public boolean onLongClick(View view) {
                  EditText editText = (EditText) view;
                  Integer score = Integer.valueOf(0);
                  editText.setText(score.toString(), TextView.BufferType.EDITABLE);
                  return true;
              }
        });

        Button bt_update = (Button)findViewById(R.id.bt_update);
        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            postData();
            }
        });

        Button bt_resetSB = (Button)findViewById(R.id.bt_resetScoreboard);
        bt_resetSB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setMessage("Tem certeza?").setPositiveButton("Sim", dialogClickListener)
                    .setNegativeButton("N\u00E3o", dialogClickListener).show();
            }
        });

        Button bt_resetCounting = (Button)findViewById(R.id.bt_resetCounting);
        bt_resetCounting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TableRow row1 = (TableRow)findViewById(R.id.border_row1);

                for (int i = 1; i < row1.getChildCount(); i++) {
                    View v = row1.getChildAt(i);
                    if (v instanceof EditText){
                        EditText editText = (EditText)v;
                        editText.setText("0");
                    }
                }
            }
        });

        TableLayout tableSb = (TableLayout) findViewById(R.id.scoreboardLayout);

        for(int rowIndex = 3; rowIndex<tableSb.getChildCount()-1; rowIndex++){

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

    public void postData() {
        // Create a new HttpClient and Post Header
        //String url = "http://192.168.25.240:3000/scoreBoard/update";
        String url = "http://bobito-cc.umbler.net/scoreBoard/update";

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);


        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    // Display the first 500 characters of the response string.
                    Toast.makeText(getApplicationContext(), "Placar Atualizado !", Toast.LENGTH_LONG).show();
                    //Toast.makeText(getApplicationContext(), "Placar Atualizado !: "+response.toString(), Toast.LENGTH_LONG).show();
                }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Erro inesperado...", Toast.LENGTH_LONG).show();
                //Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                //error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String > getParams(){
                Map<String, String> params = new HashMap<String, String>();

                EditText ballET = (EditText)findViewById(R.id.ball_counter);
                params.put("b", ballET.getText().toString());

                EditText strikeET = (EditText)findViewById(R.id.strike_counter);
                params.put("s", strikeET.getText().toString());

                EditText outET = (EditText)findViewById(R.id.out_counter);
                params.put("o", outET.getText().toString());

                TableLayout tableSb = (TableLayout) findViewById(R.id.scoreboardLayout);

                for(int rowIndex = 3; rowIndex<tableSb.getChildCount()-1; rowIndex++) {
                    //rowINdex : 3 => GUEST / 4 => HOME
                    TableRow row = (TableRow) tableSb.getChildAt(rowIndex);

                    for (int i = 1; i < row.getChildCount() - 1; i++) {

                        View v = row.getChildAt(i);

                        if (v instanceof EditText) {
                            EditText editText = (EditText)v;
                            if(rowIndex == 3){ // guest row
                                params.put("g"+i, editText.getText().toString());
                            }else if(rowIndex == 4){ //home row
                                params.put("h"+i, editText.getText().toString());
                            }
                        }
                    }
                }
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        Toast.makeText(getApplicationContext(), "Requisiç\u00E3o enviada", Toast.LENGTH_LONG).show();
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        // Do something in response to button
    }
}
