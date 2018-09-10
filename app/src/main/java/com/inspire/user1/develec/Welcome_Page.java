package com.inspire.user1.develec;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import static com.inspire.user1.develec.R.layout.activity_welcome__page;

public class Welcome_Page extends AppCompatActivity {
    private static int SPLASH_TIME_OUT=5000;
    private String url = "https://firebasestorage.googleapis.com/v0/b/develec-dd016.appspot.com/o/Inspire.png?alt=media&token=c86bd072-599a-416d-8e8f-0670955cc15e";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_welcome__page);
        ImageView logo1 = (ImageView) findViewById(R.id.Logo);
        Glide.with(this).load(url).thumbnail(0.2f).into(logo1);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent= new Intent(Welcome_Page.this, Phone_Auth.class);
                startActivity(homeIntent);
                finish();
            }

        },SPLASH_TIME_OUT);
    }
}
