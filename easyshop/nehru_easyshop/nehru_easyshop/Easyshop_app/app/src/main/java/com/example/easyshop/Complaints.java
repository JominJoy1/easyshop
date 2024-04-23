package com.example.easyshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Complaints extends AppCompatActivity implements JsonResponse{


    EditText e1;
    Button b1;

    String complaint;

    SharedPreferences sh;

    String[] complaints,reply,date,value;
    ListView l1;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l1=(ListView) findViewById(R.id.list);

        e1=(EditText) findViewById(R.id.com);
        b1=(Button) findViewById(R.id.send);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                complaint=e1.getText().toString();

                JsonReq JR = new JsonReq();
                JR.json_response = (JsonResponse) Complaints.this;
                String q = "/complaintsapi?complaint=" + complaint + "&user_id=" +sh.getString("user_id","") ;
                q = q.replace(" ", "%20");
                JR.execute(q);


            }
        });
        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Complaints.this;
        String q = "/complaintreply?user_id=" +sh.getString("user_id","") ;
        q = q.replace(" ", "%20");
        JR.execute(q);
    }

    @Override
    public void response(JSONObject jo) throws JSONException {
        {
            // TODO Auto-generated method stub

            try {

                String method = jo.getString("method");
                if (method.equalsIgnoreCase("com")) {
                    String status = jo.getString("status");
                    Log.d("pearl", status);
//				Toast.makeText(getApplicationContext(),status, Toast.LENGTH_SHORT).show();
                    if (status.equalsIgnoreCase("success")) {
                        Toast.makeText(getApplicationContext(), "send", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),Complaints.class));

                    }

                }

                if (method.equalsIgnoreCase("reply")) {

                    String status = jo.getString("status");
                    Log.d("pearl", status);


                    if (status.equalsIgnoreCase("success")) {
                        JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                        complaints = new String[ja1.length()];
                        reply = new String[ja1.length()];
                        date = new String[ja1.length()];
                        value = new String[ja1.length()];

                        for (int i = 0; i < ja1.length(); i++) {
                            complaints[i] = ja1.getJSONObject(i).getString("complaint");
                            reply[i] = ja1.getJSONObject(i).getString("reply");
                            date[i] = ja1.getJSONObject(i).getString("date_time");
                            value[i] = "complaints : " + complaints[i] +"  " + "reply :" + reply[i] + "\ndate :" + date[i];


                        }
//                        ArrayAdapter<String> ar=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_gallery_item,value);
//                        l1.setAdapter(ar);
                        Customcomplaint ci = new Customcomplaint(Complaints.this, complaints, reply, date);
//
                        l1.setAdapter(ci);
                        Toast.makeText(getApplicationContext(),"send",Toast.LENGTH_LONG).show();




                        }

                }
            } catch (Exception e) {
                // TODO: handle exception

                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            }


        }
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
    public void onBackPressed(){
        startActivity(new Intent(getApplicationContext(),Userhome.class));
    }
}

