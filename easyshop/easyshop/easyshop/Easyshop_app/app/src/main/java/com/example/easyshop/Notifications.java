package com.example.easyshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Notifications extends AppCompatActivity implements JsonResponse{

    ListView l1;

    String[] product_name,stock_quantity,statuss,value,image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        l1=(ListView) findViewById(R.id.list);

        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Notifications.this;
        String q = "/notifications";
        q = q.replace(" ", "%20");
        JR.execute(q);


    }

    @Override
    public void response(JSONObject jo) throws JSONException {
        try {

            String status = jo.getString("status");
            Log.d("pearl", status);

            if (status.equalsIgnoreCase("success")) {
                Toast.makeText(getApplicationContext(), "lllllllllllllllllll", Toast.LENGTH_LONG).show();

                JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                product_name = new String[ja1.length()];

                stock_quantity = new String[ja1.length()];
                statuss = new String[ja1.length()];
                image = new String[ja1.length()];




                value = new String[ja1.length()];


                for (int i = 0; i < ja1.length(); i++) {
                    product_name[i] = ja1.getJSONObject(i).getString("product_name");
                    stock_quantity[i] = ja1.getJSONObject(i).getString("stock_quantity");
                    statuss[i] = ja1.getJSONObject(i).getString("status");
                    image[i] = ja1.getJSONObject(i).getString("image");



                    value[i] = "product_name : " + product_name[i] + "\nstock_quantity : " + stock_quantity[i] + "\nstatuss : " + statuss[i];


//                        Toast.makeText(getApplicationContext(),val[i], Toast.LENGTH_SHORT).show();
//                        val[i]="Fuel Type : "+fuel_type[i]+"\nVehicle : "+vehicle[i]+"\nReg.No : "+regnum[i]+"\nDriver Name : "+dname[i]+"\nPhone : "+phone[i]+"\nEmail : "+email[i];


                }
//                ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, value);
//                l1.setAdapter(ar);
                Customnotification ar=new Customnotification(Notifications.this,product_name,stock_quantity,statuss,image);
                l1.setAdapter(ar);




            }
        }

        catch (Exception e)
        {
            // TODO: handle exception

            Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}