package com.example.easyshop;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//
//public class Userhome extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_userhome);
//    }
//}

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.easyshop.JsonResponse;
import com.example.easyshop.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Userhome extends AppCompatActivity implements JsonResponse {
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;
    private int[] images = {R.drawable.add10, R.drawable.add2, R.drawable.add3, R.drawable.add4, R.drawable.add5};
    private int currentIndex = 0;
    private Handler handler;
    private Runnable runnable;

    ImageView i1;

    EditText e1, e2;
    Button b1;

    String[] product,details,price,image,value,productplaced,productid,rating,offerprice,sdate,edate,product_id;

    String loginid, user_id, usertype;

    SharedPreferences sh;

    GridView g1,g2;
    private Spinner spinner;
    ImageView i2,scanner,cart,complaint,notification,orderhistory,search;

    String count,content;
    TextView t1;
    EditText type,price1,price2;

    public static String post_id,id1;

    String p1,p2,ty1;
    ImageView close;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userhome);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        startService(new Intent(getApplicationContext(),LocationService.class));


        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        g1 = (GridView) findViewById(R.id.products);
        g2 = (GridView) findViewById(R.id.offer);

        spinner = findViewById(R.id.spinner);
        i2 = (ImageView) findViewById(R.id.filter);
        scanner = (ImageView) findViewById(R.id.scanner);
        cart = (ImageView) findViewById(R.id.cart);
        complaint = (ImageView) findViewById(R.id.com);
        notification = (ImageView) findViewById(R.id.notification);
        t1 = (TextView) findViewById(R.id.noti);
        orderhistory = findViewById(R.id.orderhistory);
        search=(ImageView) findViewById(R.id.search);
        type=(EditText) findViewById(R.id.type);
        price1=(EditText) findViewById(R.id.price1);
        price2=(EditText) findViewById(R.id.price2);
        close=(ImageView) findViewById(R.id.close);








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


        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Userhome.this;
        String q = "/products";
        q = q.replace(" ", "%20");
        JR.execute(q);


        // Add a new JSON request for "/offer"
        JsonReq JR2 = new JsonReq();
        JR2.json_response = (JsonResponse) Userhome.this;
        String q2 = "/offer";
        q2 = q2.replace(" ", "%20");
        JR2.execute(q2);





        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinner.getVisibility() == View.VISIBLE) {
                    spinner.setVisibility(View.GONE); // Hide the Spinner
                } else {
                    spinner.setVisibility(View.VISIBLE); // Display the Spinner
                }
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"select","price","rating","near by location"});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Handle the selected item here
                String selectedItem = (String) parentView.getItemAtPosition(position);

                SharedPreferences.Editor e = sh.edit();
                e.putString("filter", selectedItem);
                e.commit();

                Toast.makeText(getApplicationContext(), "jjjj"+selectedItem, Toast.LENGTH_LONG).show();
                if ("price".equals(selectedItem)) {
                    price1.setVisibility(View.VISIBLE);
                    price2.setVisibility(View.VISIBLE);
                    search.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            p1=price1.getText().toString();
                            p2=price2.getText().toString();
                            ty1=type.getText().toString();

                            JsonReq JR = new JsonReq();
                            JR.json_response = (JsonResponse) Userhome.this;
                            String q = "/pricesearch?price1=" + p1 + "&price2=" + p2+"&type="+ty1;
                            q = q.replace(" ", "%20");
                            JR.execute(q);



                        }
                    });
                }
                else {
                    price1.setVisibility(View.GONE);
                    price2.setVisibility(View.GONE);
                }
                if("rating".equals(selectedItem)){
                    search.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ty1=type.getText().toString();

                            JsonReq JR = new JsonReq();
                            JR.json_response = (JsonResponse) Userhome.this;
                            String q = "/ratesearch?type="+ty1;
                            q = q.replace(" ", "%20");
                            JR.execute(q);

                        }
                    });

                }
                if("near by location".equals(selectedItem)){


                    search.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ty1=type.getText().toString();


                            JsonReq JR=new JsonReq();
                            JR.json_response=(JsonResponse) Userhome.this;
                            String q = "/nearby?lati="+LocationService.lati+"&logi="+LocationService.logi+"&type="+ty1;
                            q=q.replace(" ","%20");
                            JR.execute(q);

                        }
                    });

                }


                // Do something with the selected item
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }
        });




        scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Scanner.class));
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Cart.class));
            }
        });
        complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Complaints.class));


            }
        });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Notifications.class));


            }
        });
        orderhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Orderhistory.class));


            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               type.setText("");
               startActivity(new Intent(getApplicationContext(),Userhome.class));
            }


        });




    }




    @Override
    public void response(JSONObject jo) throws JSONException {
        {
            // TODO Auto-generated method stub

            try {
                String method = jo.getString("method");
                if (method.equalsIgnoreCase("ok")) {

                    String status = jo.getString("status");
                    Log.d("pearl", status);

                    if (status.equalsIgnoreCase("success")) {
                        Toast.makeText(getApplicationContext(), "jjjj", Toast.LENGTH_LONG).show();

                        JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                        product = new String[ja1.length()];
                        image = new String[ja1.length()];
                        details = new String[ja1.length()];
                        price = new String[ja1.length()];
                        productplaced = new String[ja1.length()];
                        product_id = new String[ja1.length()];
                        rating = new String[ja1.length()];



                        value = new String[ja1.length()];


                        for (int i = 0; i < ja1.length(); i++) {
                            product[i] = ja1.getJSONObject(i).getString("product_name");
                            image[i] = ja1.getJSONObject(i).getString("image");
                            details[i] = ja1.getJSONObject(i).getString("details");
                            price[i] = ja1.getJSONObject(i).getString("price");
                            productplaced[i] = ja1.getJSONObject(i).getString("product_placed");
                            product_id[i] = ja1.getJSONObject(i).getString("product_id");
                            rating[i] = ja1.getJSONObject(i).getString("rating");

                            if(rating[i].equals("None"))
                            {
                                rating[i]="0";
                            }
                            else{
                                rating[i]=ja1.getJSONObject(i).getString("rating");
                            }







                            value[i] = "product : " + product[i] + "\ndetails :" + details[i] + "\nprice :" + price[i];


//                        Toast.makeText(getApplicationContext(),val[i], Toast.LENGTH_SHORT).show();
//                        val[i]="Fuel Type : "+fuel_type[i]+"\nVehicle : "+vehicle[i]+"\nReg.No : "+regnum[i]+"\nDriver Name : "+dname[i]+"\nPhone : "+phone[i]+"\nEmail : "+email[i];


                        }
//                    ArrayAdapter<String> ar=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_gallery_item,value);
//                    g1.setAdapter(ar);

                        Custimage ci = new Custimage(Userhome.this, image, product, details, price,rating);
//
                        g1.setAdapter(ci);
                        g1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                // Handle item click here
                                id1 = product_id[position];
                                startActivity(new Intent(getApplicationContext(),Product_details.class));

                            }
                        });

//
//                    g1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                            // Handle item click here
//                            post_id=postid[position];
//                            vimage=image[position];
//
//
//                            SharedPreferences.Editor e = sh.edit();
//                            e.putString("image", vimage);
//                            e.putString("post_id", post_id);
//
//
//                            e.commit();
//
//                            AlertDialog.Builder builder = new AlertDialog.Builder(Viewpost.this); // Use the correct context here
//                            builder.setTitle("CYBERBULLYING  ")
//                                    .setMessage("Are you sure you want to proceed?");
//                            builder.setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    // Handle the "Yes" button click event
//                                    startActivity(new Intent(getApplicationContext(),New.class));
//
//
//
//                                }
//                            });
//
//                            builder.setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    // Handle the "No" button click event
//                                    // Put your code here for the action to be taken on "No"
//                                    JsonReq JR = new JsonReq();
//                                    JR.json_response = (JsonResponse) Viewpost.this;
//                                    String q = "/updatedelete?id=" + Viewpost.post_id;
//                                    q = q.replace(" ", "%20");
//                                    JR.execute(q);
//                                    Toast.makeText(getApplicationContext(), "DELETED", Toast.LENGTH_LONG).show();
//
//
//                                    startActivity(new Intent(getApplicationContext(),Viewpost.class));
//
//
//                                }
//                            });
//                            AlertDialog dialog = builder.create();
//                            dialog.show();
//                        }
//                    });


//                Intent intent = new Intent(Viewpost.this, Updatedelete.class);
//                        startActivity(intent);
//                    }
//                });


                    } else {
                        Toast.makeText(getApplicationContext(), "no data", Toast.LENGTH_LONG).show();

                    }
                }
                if (method.equalsIgnoreCase("off")) {
                    String status = jo.getString("status");
                    Log.d("pearl", status);

                    if (status.equalsIgnoreCase("success")) {
                        Toast.makeText(getApplicationContext(), "kkkk", Toast.LENGTH_LONG).show();

                        JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                        product = new String[ja1.length()];
                        image = new String[ja1.length()];
                        details = new String[ja1.length()];
                        price = new String[ja1.length()];
                        productplaced = new String[ja1.length()];
                        productid = new String[ja1.length()];
//                        rating = new String[ja1.length()];
                        offerprice = new String[ja1.length()];
                        sdate = new String[ja1.length()];
                        edate = new String[ja1.length()];


                        value = new String[ja1.length()];


                        for (int i = 0; i < ja1.length(); i++) {
                            product[i] = ja1.getJSONObject(i).getString("product_name");
                            image[i] = ja1.getJSONObject(i).getString("image");
                            details[i] = ja1.getJSONObject(i).getString("details");
                            price[i] = ja1.getJSONObject(i).getString("price");
                            productplaced[i] = ja1.getJSONObject(i).getString("product_placed");
                            productid[i] = ja1.getJSONObject(i).getString("product_id");
//                            rating[i] = ja1.getJSONObject(i).getString("rating");
                            offerprice[i] = ja1.getJSONObject(i).getString("offer_price");
                            sdate[i] = ja1.getJSONObject(i).getString("start_date");
                            edate[i] = ja1.getJSONObject(i).getString("end_date");


//                            value[i] = "product : " + product[i] + "\ndetails :" + details[i] + "\nprice :" + price[i];


                        }
                        Offer_cust ci = new Offer_cust(Userhome.this, image, product, details, price, productplaced, rating, offerprice, edate);
//
                        g2.setAdapter(ci);
                        g2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                // Handle item click here
                                post_id = productid[position];
                                startActivity(new Intent(getApplicationContext(),Offerdetails.class));

                            }
                        });
                    }
                    if(status.equalsIgnoreCase("failed")){
                        Toast.makeText(getApplicationContext(), "kkkk", Toast.LENGTH_LONG).show();
                    }

                }
                    JsonReq JR = new JsonReq();
                JR.json_response = (JsonResponse) Userhome.this;
                String q = "/noti";
                q = q.replace(" ", "%20");
                JR.execute(q);

                if (method.equalsIgnoreCase("noti")) {
                    String status = jo.getString("status");
                    if (status.equalsIgnoreCase("success")) {
                        JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                        count=ja1.getJSONObject(0).getString("cc");
                        t1.setText(count);                    } else {
                    }
                }
            }

            catch (Exception e)
            {
                // TODO: handle exception

                Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_LONG).show();
            }



        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
    public void onBackPressed()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),Home.class);
        startActivity(b);
    }
}