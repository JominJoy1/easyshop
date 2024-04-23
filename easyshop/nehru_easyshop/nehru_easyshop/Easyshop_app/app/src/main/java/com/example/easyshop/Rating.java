package com.example.easyshop;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easyshop.JsonReq;
import com.example.easyshop.JsonResponse;
import com.example.easyshop.R;

import org.json.JSONObject;

public class Rating extends AppCompatActivity implements JsonResponse {
    EditText t1;
    RatingBar ratingBar;
    Button getRating;
    SharedPreferences sh;
    Float rated;
    String rat,review,rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        getRating = (Button) findViewById(R.id.getRating);
        ratingBar = (RatingBar) findViewById(R.id.rating);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        JsonReq JR=new JsonReq();
//        JR.json_response=(JsonResponse) user_rate.this;
//        String q = "/user_view_review?user_id="+sh.getString("user_id","");
//        q=q.replace(" ", "%20");
//        JR.execute(q);
        getRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float rating =  ratingBar.getRating();

                JsonReq JR=new JsonReq();
                JR.json_response=(JsonResponse) Rating.this;
                String q = "/user_rate?rating="+rating+"&userid="+sh.getString("user_id","")+"&productid="+sh.getString("idpro","") ;
                JR.execute(q);
                Log.d("pearl",q);
            }
        });
    }
    @Override
    public void response(JSONObject jo) {
        try {
            String status = jo.getString("status");
            Log.d("pearl", status);

            if (status.equalsIgnoreCase("success")) {

                Toast.makeText(getApplicationContext(), "Rated Succesfull", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),Userhome.class));

            }
        } catch (Exception e) {
            // TODO: handle exception

            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    public void onBackPressed()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),Userhome.class);
        startActivity(b);
    }
}