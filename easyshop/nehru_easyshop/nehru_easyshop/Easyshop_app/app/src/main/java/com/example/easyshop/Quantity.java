package com.example.easyshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Quantity extends AppCompatActivity implements JsonResponse {


    ListView l1;

    SharedPreferences sh;
    String[] product, details, price,image, value, productplaced, productid,stock,shopid;

    String img,quantity;


    ImageButton img1;
    ImageView img2;
    TextView t1,t2,t3,t4;
    public static String am,shop,stk;

    EditText e1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quantity);
        img1=(ImageButton) findViewById(R.id.image2);
        t1=(TextView) findViewById(R.id.products) ;
        t2=(TextView) findViewById(R.id.details) ;
        t3=(TextView) findViewById(R.id.price) ;
        e1=(EditText) findViewById(R.id.quantity);
        img2=(ImageView) findViewById(R.id.addcart);
        t4=(TextView) findViewById(R.id.stock);


        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());





        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Quantity.this;
        String q = "/viewselectedproducts?product_id=" + Scanner.aa;
        q = q.replace(" ", "%20");
        JR.execute(q);



        String pth = "http://"+sh.getString("ip", "")+"/"+Quantity.am;
        pth = pth.replace("~", "");
//	       Toast.makeText(context, pth, Toast.LENGTH_LONG).show();

        Log.d("-------------", pth);
        Picasso.with(getApplicationContext())
                .load(pth)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background).into(img1);



        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity=e1.getText().toString();

                if(quantity.equalsIgnoreCase("")){
                    e1.setError("Enter a valid Quantity");
                    e1.setFocusable(true);
                }

                 else {

                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) Quantity.this;
                    String q = "/cart?product_id=" + Scanner.aa + "&quantity=" + quantity + "&price=" + t3.getText().toString() + "&shopid=" + Quantity.shop + "&userid=" + sh.getString("user_id", "");
                    q = q.replace(" ", "%20");
                    JR.execute(q);
                }

            }
        });






    }



    @Override
    public void response(JSONObject jo) throws JSONException {

        {
            // TODO Auto-generated method stub

            try {

                String method = jo.getString("method");
                if (method.equalsIgnoreCase("view")) {
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
                                price[i] = ja1.getJSONObject(i).getString("offer_price");
                                productplaced[i] = ja1.getJSONObject(i).getString("product_placed");
                                productid[i] = ja1.getJSONObject(i).getString("product_id");
                                stock[i] = ja1.getJSONObject(i).getString("stock_quantity");
                                shopid[i] = ja1.getJSONObject(i).getString("shop_id");


                                value[i] = "products : " + product[i] + "\ndetails :" + details[i] + "\nprices :" + price[i] + "\nimg:" + image;

                                int position = 0;
                                t1.setText(product[position]);
                                t2.setText("Details : "+details[position]);
                                t3.setText(price[position]);
                                t4.setText("stock :" + stock[position]);

                                am = price[position];
                                shop = shopid[position];
                                stk=stock[position];


                                SharedPreferences.Editor e = sh.edit();
                                e.putString("res1", img);
                                e.commit();


                            }


                        } else if (status.equalsIgnoreCase("success2")) {
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
                            shopid[i] = ja1.getJSONObject(i).getString("shop_id");


                            value[i] = "products : " + product[i] + "\ndetails :" + details[i] + "\nprices :" + price[i] + "\nimg:" + image;

                            int position = 0;
                            t1.setText(product[position]);
                            t2.setText("Details : "+details[position]);
                            t3.setText(price[position]);
                            t4.setText("stock :" + stock[position]);

                            am = price[position];
                            shop = shopid[position];


                            SharedPreferences.Editor e = sh.edit();
                            e.putString("res1", img);
                            e.commit();


                        }


                    } else{
                        startActivity(new Intent(getApplicationContext(),Emptycart.class));
                    }
                    }
                if(method.equalsIgnoreCase("cart")){

                    String status=jo.getString("status");
                    Log.d("pearl",status);


                    if(status.equalsIgnoreCase("failed")){

                        Toast.makeText(getApplicationContext(), "cart is empty", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),Cart.class));


                    }
                    if(status.equalsIgnoreCase("success")){

                        Toast.makeText(getApplicationContext(), "added", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),Cart.class));


                    }

                }
                else{
                    Toast.makeText(getApplicationContext(), "Quantity", Toast.LENGTH_LONG).show();


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