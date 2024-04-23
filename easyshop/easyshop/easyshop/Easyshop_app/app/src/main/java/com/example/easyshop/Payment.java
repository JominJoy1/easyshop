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
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.PublicKey;

public class Payment extends AppCompatActivity implements JsonResponse{

    TextView t1;

    SharedPreferences sh;

    String name,cardno,cvv;

    String[] ordermaster_id,shop_id,total_amount,value;

    Button b1;
    EditText e1,e2,e3;

    public static String amt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        t1=(TextView) findViewById(R.id.amount);
        b1=(Button) findViewById(R.id.button);
        e1=(EditText) findViewById(R.id.cardno);
        e2=(EditText) findViewById(R.id.cvv);
        e3=(EditText) findViewById(R.id.name);





        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Payment.this;
        String q = "/pay?id="+Cart.id1 ;
        q = q.replace(" ", "%20");
        JR.execute(q);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardno=e1.getText().toString();
                cvv=e2.getText().toString();
                name=e3.getText().toString();
                if(cardno.equalsIgnoreCase("")){
                    e1.setError("Enter Your Cardno");
                    e1.setFocusable(true);
                } else if (cvv.equalsIgnoreCase("")) {
                    e2.setError("Enter Your Cvv");
                    e2.setFocusable(true);


                } else if (name.equalsIgnoreCase("")) {
                    e3.setError("Enter Your name");
                    e3.setFocusable(true);

                }
                else {
                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) Payment.this;
                    String q = "/paymentlast?id=" + Cart.id1 + "&shopid=" + Paymentscanner.aa + "&amount=" + Payment.amt + "&userid=" + sh.getString("user_id", "");
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
                        ordermaster_id = new String[ja1.length()];
                        total_amount = new String[ja1.length()];
                        shop_id = new String[ja1.length()];



                        value = new String[ja1.length()];


                        for (int i = 0; i < ja1.length(); i++) {
                            ordermaster_id[i] = ja1.getJSONObject(i).getString("ordermaster_id");
                            total_amount[i] = ja1.getJSONObject(i).getString("total_amount");
                            shop_id[i] = ja1.getJSONObject(i).getString("shop_id");



                            value[i] = "ordermaster_id : " + ordermaster_id[i] + "\ntotal_amount :" + total_amount[i] + "\nshop_id :" + shop_id[i] ;

                            int position = 0;
                            t1.setText(total_amount[position]);


                            amt = total_amount[position];
//                            shop = shopid[position];


//                            SharedPreferences.Editor e = sh.edit();
//                            e.putString("res1", img);
//                            e.commit();


                        }


                    }
                }
                if(method.equalsIgnoreCase("payment")){

                    String status=jo.getString("status");
                    Log.d("pearl",status);


                    if(status.equalsIgnoreCase("success")){

                        Toast.makeText(getApplicationContext(), "payment completed", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),Congratulations.class));


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
    public void onBackPressed()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),Cart.class);
        startActivity(b);
    }
}