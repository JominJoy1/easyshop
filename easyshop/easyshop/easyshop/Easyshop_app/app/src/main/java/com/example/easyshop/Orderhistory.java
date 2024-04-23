package com.example.easyshop;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Orderhistory extends AppCompatActivity implements JsonResponse{


    ListView l1;

    SharedPreferences sh;
    String[] product,image,price,totalamount,quantity,value,product_id;

    String[] orderdetails_id,ordermaster;

    String idpro;
    TextView t3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderhistory);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        l1=(ListView) findViewById(R.id.list);
        t3=(TextView) findViewById(R.id.mssg);




        t3.setText("                                         Tap to Rate the Product.....!                           ");
        // Enable marquee scroll
        t3.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        t3.setMarqueeRepeatLimit(-1); // -1 means infinite loop

// Make the TextView focusable to enable scrolling
        t3.setFocusable(true);
        t3.setFocusableInTouchMode(true);

// Set the scrolling behavior (you can customize it)
        t3.setSingleLine(true); // Scrolls horizontally
        t3.setHorizontallyScrolling(true);
        t3.setSelected(true); // Start scrolling



        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Orderhistory.this;
        String q = "/orderhistory?userid="+sh.getString("user_id","");
        q = q.replace(" ", "%20");
        JR.execute(q);
    }

    @Override
    public void response(JSONObject jo) throws JSONException {
        {
            // TODO Auto-generated method stub

            try {

                String status = jo.getString("status");
                Log.d("pearl", status);

                if (status.equalsIgnoreCase("success")) {
                    Toast.makeText(getApplicationContext(), "jjjj", Toast.LENGTH_LONG).show();

                    JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                    product = new String[ja1.length()];
                    image = new String[ja1.length()];
                    price = new String[ja1.length()];
                    totalamount = new String[ja1.length()];
                    quantity = new String[ja1.length()];
                    orderdetails_id = new String[ja1.length()];
                    product_id = new String[ja1.length()];
                    ordermaster = new String[ja1.length()];




                    value = new String[ja1.length()];


                    for (int i = 0; i < ja1.length(); i++) {
                        product[i] = ja1.getJSONObject(i).getString("product_name");
                        image[i] = ja1.getJSONObject(i).getString("image");
                        price[i] = ja1.getJSONObject(i).getString("price");
                        totalamount[i] = ja1.getJSONObject(i).getString("amount");
                        quantity[i] = ja1.getJSONObject(i).getString("quantity");
                        orderdetails_id[i] = ja1.getJSONObject(i).getString("orderdetails_id");
                        product_id[i] = ja1.getJSONObject(i).getString("product_id");
                        ordermaster[i] = ja1.getJSONObject(i).getString("ordermaster_id");










//                        value[i] = "product : " + product[i] +"\ndetails :"+details[i]+"\nprice :"+price[i];


//                        Toast.makeText(getApplicationContext(),val[i], Toast.LENGTH_SHORT).show();
//                        val[i]="Fuel Type : "+fuel_type[i]+"\nVehicle : "+vehicle[i]+"\nReg.No : "+regnum[i]+"\nDriver Name : "+dname[i]+"\nPhone : "+phone[i]+"\nEmail : "+email[i];


                    }
//                    ArrayAdapter<String> ar=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_gallery_item,value);
//                    g1.setAdapter(ar);

                    Customorder ci = new Customorder(Orderhistory.this, image, product, price, totalamount);
//
                    l1.setAdapter(ci);

//
                    l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            // Handle item click here
                            idpro = product_id[position];
//                            amt=totalamount[position];
                            Toast.makeText(getApplicationContext(), "yyyy", Toast.LENGTH_LONG).show();



                            SharedPreferences.Editor e = sh.edit();
                            e.putString("idpro", idpro);


                            e.commit();

                            AlertDialog.Builder builder = new AlertDialog.Builder(Orderhistory.this); // Use the correct context here
                            builder.setTitle("EASYSHOP")
                                    .setMessage("Are you sure you want to proceed?");
                            builder.setPositiveButton("RATE", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Handle the "Yes" button click event
                                    startActivity(new Intent(getApplicationContext(),Rating.class));



                                }
                            });
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
                            AlertDialog dialog = builder.create();
                            dialog.show();
//                        }
//                    });


//                Intent intent = new Intent(Viewpost.this, Updatedelete.class);
//                        startActivity(intent);
//                    }
//                });


                        }
                    });
                }


                else {
                    Toast.makeText(getApplicationContext(), "no data", Toast.LENGTH_LONG).show();

                }
            }

//            if(method.equalsIgnoreCase("user_send_work_request"))
//            {
//                String status=jo.getString("status");
//                Toast.makeText(getApplicationContext(),status, Toast.LENGTH_LONG).show();
//                if(status.equalsIgnoreCase("success"))
//                {
//                    Toast.makeText(getApplicationContext(),"Requset Send Successfully!", Toast.LENGTH_LONG).show();
//                }
//                else{
//                    Toast.makeText(getApplicationContext(),"Sending Failed", Toast.LENGTH_LONG).show();
//                }
//            }


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
}