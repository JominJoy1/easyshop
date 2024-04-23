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

public class Offer_cust extends ArrayAdapter<String>  {

    private Activity context;       //for to get current activity context
    SharedPreferences sh;
    private String[] file,post,details,price,productplaced,rating,offerprice,edate;


    public Offer_cust(Activity context, String[] file, String[] post,String[] details,String[] price,String[] productplaced,String[] rating,String[] offerprice,String[] edate) {
        //constructor of this class to get the values from main_activity_class

        super(context, R.layout.activity_offer_cust, file);
        this.context = context;
        this.file = file;
        this.post=post;
        this.details=details;
        this.price=price;
        this.productplaced=productplaced;
        this.rating=rating;
        this.offerprice=offerprice;
        this.edate=edate;



    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //override getView() method

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_offer_cust, null, true);
        //cust_list_view is xml file of layout created in step no.2

        ImageView im = (ImageView) listViewItem.findViewById(R.id.imageView1);
        TextView t=(TextView)listViewItem.findViewById(R.id.textView);
        TextView t1=(TextView)listViewItem.findViewById(R.id.textView1);
//        TextView t2=(TextView)listViewItem.findViewById(R.id.textView2);
//        TextView t3=(TextView)listViewItem.findViewById(R.id.textView3);
        TextView t4=(TextView)listViewItem.findViewById(R.id.textView4);
        TextView t5=(TextView)listViewItem.findViewById(R.id.offeramount);
        TextView t6=(TextView)listViewItem.findViewById(R.id.date);
        ImageView img=(ImageView)listViewItem.findViewById(R.id.star);


        Float rate = Float.valueOf(rating[position]);








        t.setText(post[position]);
        t1.setText( details[position]);
//        t3.setText(productplaced[position]);
//        t2.setText(rating[position]);
        t4.setText( price[position]+"/-");
        t5.setText( offerprice[position]+"/-");
        t6.setText(edate[position]);


        if(rate==1.0 && rate <= 1.9){
            img.setImageResource(R.drawable.starnew);


        }
        if(rate==2.0 && rate <= 2.9){
            img.setImageResource(R.drawable.newtwostar);
        }
        if(rate==3.0 && rate <= 3.9){
            img.setImageResource(R.drawable.newthreestar);
        }
        if(rate==4.0 && rate <= 4.9){
            img.setImageResource(R.drawable.newfourstar);
        }
        if(rate==5){
            img.setImageResource(R.drawable.newfivestar);


        }










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