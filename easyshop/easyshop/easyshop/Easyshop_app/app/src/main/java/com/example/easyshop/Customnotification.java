package com.example.easyshop;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class Customnotification extends ArrayAdapter<String>  {

    private Activity context;       //for to get current activity context
    SharedPreferences sh;
    private String[] product_name,stock_quantity,statuss,image;


    public Customnotification(Activity context, String[] product_name,String[] stock_quantity,String[] statuss,String[] image) {
        //constructor of this class to get the values from main_activity_class

        super(context, R.layout.activity_customnotification, product_name);
        this.context = context;
        this.product_name=product_name;
        this.stock_quantity=stock_quantity;
        this.statuss=statuss;
        this.image=image;



    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //override getView() method

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_customnotification, null, true);
        //cust_list_view is xml file of layout created in step no.2

        TextView t=(TextView)listViewItem.findViewById(R.id.textView12);
        TextView t1=(TextView)listViewItem.findViewById(R.id.textView13);
        TextView t2=(TextView)listViewItem.findViewById(R.id.textView14);
        ImageView im = (ImageView) listViewItem.findViewById(R.id.imageView20);



//
//        // Set the GIF resource
//        Glide.with(this.getContext())
//                .asGif()
//                .load(R.drawable.notigif) // Change 'your_gif_file' to the actual file name
//                .into(im);





        t.setText(statuss[position]);
        t1.setText("product_name : "+ product_name[position]);
        t2.setText("stock_quantity : "+ stock_quantity[position]);




        sh=PreferenceManager.getDefaultSharedPreferences(getContext());

        String pth = "http://"+sh.getString("ip", "")+"/"+image[position];
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