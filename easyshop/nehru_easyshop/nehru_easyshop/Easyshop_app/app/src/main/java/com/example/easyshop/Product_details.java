package com.example.easyshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Product_details extends AppCompatActivity implements JsonResponse{

    ImageButton img1;

    TextView t1,t2,t3,t5,t6,t7;

    SharedPreferences sh;

    String img;

    String[] product,image,details,price,productplaced,productid,stock,shopid,value;

    public static String am;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        img1=(ImageButton) findViewById(R.id.image2);
        t1=(TextView) findViewById(R.id.products);
        t2=(TextView) findViewById(R.id.details);
        t3=(TextView) findViewById(R.id.price);
        t5=(TextView) findViewById(R.id.stock);
        t6=(TextView) findViewById(R.id.placed);
        t7=(TextView) findViewById(R.id.shop);




        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Product_details.this;
        String q = "/normalplaced?product_id=" + Userhome.id1    ;
        q = q.replace(" ", "%20");
        JR.execute(q);



        String pth = "http://"+sh.getString("ip", "")+"/"+ Product_details.am;
        pth = pth.replace("~", "");
//	       Toast.makeText(context, pth, Toast.LENGTH_LONG).show();

        Log.d("-------------", pth);
        Picasso.with(getApplicationContext())
                .load(pth)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background).into(img1);


    }

    @Override
    public void response(JSONObject jo) throws JSONException {

        {
            // TODO Auto-generated method stub

            try {

                String method = jo.getString("method");
                if (method.equalsIgnoreCase("normal")) {
                    String status = jo.getString("status");
                    Log.d("pearl", status);
//				Toast.makeText(getApplicationContext(),status, Toast.LENGTH_SHORT).show();
                    if (status.equalsIgnoreCase("success")) {
                        Toast.makeText(getApplicationContext(), "jjjj", Toast.LENGTH_LONG).show();

                        JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                        product = new String[ja1.length()];
                        image = new String[ja1.length()];
                        details = new String[ja1.length()];
                        price = new String[ja1.length()];
                        productplaced = new String[ja1.length()];
                        productid = new String[ja1.length()];
                        stock = new String[ja1.length()];
                        shopid = new String[ja1.length()];


                        value = new String[ja1.length()];


                        for (int i = 0; i < ja1.length(); i++) {
                            product[i] = ja1.getJSONObject(i).getString("product_name");
                            image[i] = ja1.getJSONObject(i).getString("image");
                            details[i] = ja1.getJSONObject(i).getString("details");
                            price[i] = ja1.getJSONObject(i).getString("price");
                            productplaced[i] = ja1.getJSONObject(i).getString("product_placed");
                            productid[i] = ja1.getJSONObject(i).getString("product_id");
                            stock[i] = ja1.getJSONObject(i).getString("stock_quantity");
                            shopid[i] = ja1.getJSONObject(i).getString("shop_name");


                            value[i] = "products : " + product[i] + "\ndetails :" + details[i] + "\nprices :" + price[i] + "\nimg:" + image;

                            int position = 0;
                            t1.setText(product[position]);
                            t2.setText("DETAILS : " +details[position]);
                            t3.setText("PRICE   : " +price[position]);
                            t5.setText("STOCK   : " + stock[position]);
                            t6.setText("PLACED  : " +productplaced[position]);
                            t7.setText("SHOP    : " +shopid[position]);

                            am = image[position];
//                            shop = shopid[position];


                            SharedPreferences.Editor e = sh.edit();
                            e.putString("res12", img);
                            e.commit();


                        }


                    }
                }
            }catch (Exception e) {
                // TODO: handle exception

                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            }


        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}