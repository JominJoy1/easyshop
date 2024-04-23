package com.example.easyshop;


//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.os.Handler;
//import android.widget.ImageView;
//
//public class Home extends AppCompatActivity {
//
//    private ImageView imageView1;
//    private ImageView imageView2;
//    private ImageView imageView3;
//    private ImageView imageView4;
//    private ImageView imageView5;
//
//    private int[] images = {R.drawable.add1, R.drawable.add2,R.drawable.add3,R.drawable.add4,R.drawable.add5}; // Add your image resource IDs here
//    private int currentIndex = 0;
//    private Handler handler;
//    private Runnable runnable;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);
//        imageView1 = findViewById(R.id.imageView1);
//        imageView2 = findViewById(R.id.imageView2);
//        imageView2 = findViewById(R.id.imageView3);
//        imageView2 = findViewById(R.id.imageView4);
//        imageView2 = findViewById(R.id.imageView5);
//
//        handler = new Handler();
//        runnable = new Runnable() {
//            @Override
//            public void run() {
//                if (currentIndex % 4 == 0) {
//                    imageView1.setImageResource(images[0]);
//                    imageView2.setImageResource(images[1]);
//                    imageView3.setImageResource(images[2]);
//
//
//                } else {
//                    imageView1.setImageResource(images[2]);
//                    imageView2.setImageResource(images[1]);
//                    imageView3.setImageResource(images[0]);
//
//                }
//                currentIndex = (currentIndex + 1) % 4; // Toggle between 0 and 1
//
//                // Set the delay (in milliseconds) before switching to the next image
//                handler.postDelayed(this, 3000); // Change image every 3 seconds
//            }
//        };
//
//        // Start the image slideshow
//        handler.post(runnable);
//    }
//    }
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class Home extends AppCompatActivity implements JsonResponse{
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;
    private int[] images = {R.drawable.add10, R.drawable.add2, R.drawable.add12, R.drawable.add4, R.drawable.add5};
    private int currentIndex = 0;
    private Handler handler;
    private Runnable runnable;

    ImageView i1,img2,img3,img4,img5,img6;

    EditText e1,e2;
    Button b1;

    String uname,password;

    String loginid,user_id,usertype;

    SharedPreferences sh;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        i1=(ImageView) findViewById(R.id.reg);
        e1=(EditText) findViewById(R.id.uname);
        e2=(EditText) findViewById(R.id.password);
        b1=(Button) findViewById(R.id.button);
        img2=(ImageView) findViewById(R.id.starImageView);
        img3=(ImageView) findViewById(R.id.starImageView2);
        img4=(ImageView) findViewById(R.id.starImageView3);
        img5=(ImageView) findViewById(R.id.starImageView4);
        img6=(ImageView) findViewById(R.id.starImageView5);




        LinearLayout l1=(LinearLayout) findViewById(R.id.li);



        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                imageView1.setImageResource(images[currentIndex]);
                imageView2.setImageResource(images[(currentIndex + 1) % images.length]);
                imageView3.setImageResource(images[(currentIndex + 2) % images.length]);
                imageView4.setImageResource(images[(currentIndex + 3) % images.length]);
                imageView5.setImageResource(images[(currentIndex + 4) % images.length]);

                currentIndex = (currentIndex + 1) % images.length;

                // Set the delay (in milliseconds) before switching to the next image
                handler.postDelayed(this, 3000); // Change image every 3 seconds
            }
        };

        // Start the image slideshow
        handler.post(runnable);


        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uname=e1.getText().toString();
                password=e2.getText().toString();

                if(uname.equalsIgnoreCase(""))
                {
                    e1.setError("Enter your Username");
                    e1.setFocusable(true);
                } else if (password.equalsIgnoreCase("")) {
                    e2.setError("Enter you Password");
                    e2.setFocusable(true);

                }
                else {
                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) Home.this;
                    String q = "/loginapi?username=" + uname + "&password=" + password;
                    q = q.replace(" ", "%20");
                    JR.execute(q);
                }




            }
        });


            // ... (your existing code)

            l1.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            // Show the star
                            img2.setVisibility(View.VISIBLE);
                            img3.setVisibility(View.VISIBLE);
                            img4.setVisibility(View.VISIBLE);
                            img5.setVisibility(View.VISIBLE);
                            img6.setVisibility(View.VISIBLE);



                            // Load the animation
                            Animation animation = AnimationUtils.loadAnimation(Home.this, R.anim.star_animation);

                            // Start the animation
                            img2.startAnimation(animation);
                            img3.startAnimation(animation);
                            img4.startAnimation(animation);
                            img5.startAnimation(animation);
                            img6.startAnimation(animation);



                            break;
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL:
                            // Hide the star
                            img2.setVisibility(View.INVISIBLE);
                            img3.setVisibility(View.INVISIBLE);
                            img4.setVisibility(View.INVISIBLE);
                            img5.setVisibility(View.INVISIBLE);
                            img6.setVisibility(View.INVISIBLE);



                            break;
                    }
                    return true;
                }
            });

            // ... (the rest of your existing code)







    }

    @Override
    public void response(JSONObject jo) throws JSONException {
        try {
            String status = jo.getString("status");
            Log.d("pearl", status);

            if (status.equalsIgnoreCase("success")) {
                JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                loginid = ja1.getJSONObject(0).getString("login_id");
                usertype = ja1.getJSONObject(0).getString("usertype");
                user_id = ja1.getJSONObject(0).getString("user_id");


                SharedPreferences.Editor e = sh.edit();
                e.putString("user_id", user_id);
                e.putString("login_id",loginid);
                e.commit();


                if (usertype.equals("user")) {

                    Toast.makeText(getApplicationContext(), "Login Successfull", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),Userhome.class));
                }



            }
            else{
                Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();


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
