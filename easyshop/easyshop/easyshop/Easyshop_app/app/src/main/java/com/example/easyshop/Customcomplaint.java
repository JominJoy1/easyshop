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

import com.squareup.picasso.Picasso;

public class Customcomplaint extends ArrayAdapter<String>  {

    private Activity context;       //for to get current activity context
    SharedPreferences sh;
    private String[] complaints,reply,date;


    public Customcomplaint(Activity context, String[] complaints,String[] reply,String[] date) {
        //constructor of this class to get the values from main_activity_class

        super(context, R.layout.activity_customcomplaint, complaints);
        this.context = context;
        this.complaints=complaints;
        this.reply=reply;
        this.date=date;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //override getView() method

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_customcomplaint, null, true);
        //cust_list_view is xml file of layout created in step no.2

        TextView t=(TextView)listViewItem.findViewById(R.id.textView7);
        TextView t1=(TextView)listViewItem.findViewById(R.id.textView8);
        TextView t2=(TextView)listViewItem.findViewById(R.id.textView9);





        t.setText("complaint : "+complaints[position]);
        t1.setText("reply : "+ reply[position]);
        t2.setText("date : "+ date[position]);




        sh=PreferenceManager.getDefaultSharedPreferences(getContext());

        String pth = "http://"+sh.getString("ip", "")+"/"+complaints[position];
        pth = pth.replace("~", "");
//	       Toast.makeText(context, pth, Toast.LENGTH_LONG).show();



        return  listViewItem;
    }

    private TextView setText(String string) {
        // TODO Auto-generated method stub
        return null;
    }
}