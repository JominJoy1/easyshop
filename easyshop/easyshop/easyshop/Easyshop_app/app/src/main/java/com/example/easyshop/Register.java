package com.example.easyshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class Register extends AppCompatActivity implements JsonResponse{


    EditText e1,e2,e3,e4,e5,e6,e7,e8,e9,e10;

    Button b1;

    String fname,lname,hname,landmark,place,email,pincode,phone,uname,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        e1=(EditText) findViewById(R.id.fname);
        e2=(EditText) findViewById(R.id.lname);
        e3=(EditText) findViewById(R.id.hname);
        e4=(EditText) findViewById(R.id.place);
        e5=(EditText) findViewById(R.id.landmark);
        e6=(EditText) findViewById(R.id.pincode);
        e7=(EditText) findViewById(R.id.phone);
        e8=(EditText) findViewById(R.id.email);
        e9=(EditText) findViewById(R.id.uname);
        e10=(EditText) findViewById(R.id.pass);
        b1=(Button) findViewById(R.id.button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fname=e1.getText().toString();
                lname=e2.getText().toString();
                hname=e3.getText().toString();
                place=e4.getText().toString();
                landmark=e5.getText().toString();
                pincode=e6.getText().toString();
                phone=e7.getText().toString();
                email=e8.getText().toString();
                uname=e9.getText().toString();
                pass=e10.getText().toString();

                if(fname.equalsIgnoreCase("")){
                    e1.setError("Enter Your Firstname");
                    e1.setFocusable(true);
                } else if (lname.equalsIgnoreCase("")) {
                    e2.setError("Enter Your Lastname");
                    e2.setFocusable(true);
                }
                else if (hname.equalsIgnoreCase("")) {
                    e3.setError("Enter Your Housename");
                    e3.setFocusable(true);
                }
                else if (place.equalsIgnoreCase("")) {
                    e4.setError("Enter Your Place");
                    e4.setFocusable(true);
                }
                else if (landmark.equalsIgnoreCase("")) {
                    e5.setError("Enter Your Landmark");
                    e5.setFocusable(true);
                }
                else if (pincode.equalsIgnoreCase("")) {
                    e6.setError("Enter Your Pincode");
                    e6.setFocusable(true);
                }
                else if (phone.equalsIgnoreCase("")) {
                    e7.setError("Enter Your Phone");
                    e7.setFocusable(true);
                }
                else if (email.equalsIgnoreCase("")) {
                    e8.setError("Enter Your Email");
                    e8.setFocusable(true);
                }
                else if (uname.equalsIgnoreCase("")) {
                    e9.setError("Enter Your Username");
                    e9.setFocusable(true);
                }
                else if (pass.equalsIgnoreCase("")) {
                    e9.setError("Enter Your Password");
                    e9.setFocusable(true);
                }
                else {

                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) Register.this;
                    String q = "/userregister?fname=" + fname + "&lname=" + lname + "&hname=" + hname + "&place=" + place + "&landmark=" + landmark + "&pincode=" + pincode + "&phone=" + phone + "&email=" + email + "&uname=" + uname + "&pass=" + pass;
                    q = q.replace(" ", "%20");
                    JR.execute(q);
                }
            }
        });



    }

    @Override
    public void response(JSONObject jo) throws JSONException {



        try {
            String status = jo.getString("status");
            Log.d("pearl", status);

            if (status.equalsIgnoreCase("success")) {

                Toast.makeText(getApplicationContext(), "Registration Succesfull", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),Home.class));

            }
        } catch (Exception e) {
            // TODO: handle exception

            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}