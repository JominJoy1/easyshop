package com.example.easyshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Congratulations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulations);

        ImageView gifImageView = findViewById(R.id.imageView19);

        // Set the GIF resource
        Glide.with(this)
                .asGif()
                .load(R.drawable.congratz) // Change 'your_gif_file' to the actual file name
                .into(gifImageView);
    }
    public void onBackPressed()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),Userhome.class);
        startActivity(b);
    }
}