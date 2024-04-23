package com.example.easyshop;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Productquantity extends ArrayAdapter<String>  {

    private Activity context;       //for to get current activity context
    SharedPreferences sh;
    private String[] file,post,details,price;


    public Productquantity(Activity context, String[] file, String[] post,String[] details,String[] price) {
        //constructor of this class to get the values from main_activity_class

        super(context, R.layout.activity_productquantity, file);
        this.context = context;
        this.file = file;
        this.post=post;
        this.details=details;
        this.price=price;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //override getView() method

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_productquantity, null, true);
        //cust_list_view is xml file of layout created in step no.2

        ImageView im = (ImageView) listViewItem.findViewById(R.id.imageView1);
        TextView t=(TextView)listViewItem.findViewById(R.id.textView);
        TextView t1=(TextView)listViewItem.findViewById(R.id.textView1);
        TextView t2=(TextView)listViewItem.findViewById(R.id.textView2);




        t.setText(post[position]);
        t1.setText( details[position]);
        t2.setText( price[position]+"/-");




        sh=PreferenceManager.getDefaultSharedPreferences(getContext());

        String pth = "http://"+sh.getString("ip", "")+"/"+file[position];
        pth = pth.replace("~", "");
//	       Toast.makeText(context, pth, Toast.LENGTH_LONG).show();

        Log.d("-------------", pth);
        Picasso.with(context)
                .load(pth)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background).into(im);

        return  listViewItem;
    }

    private TextView setText(String string) {
        // TODO Auto-generated method stub
        return null;
    }
}