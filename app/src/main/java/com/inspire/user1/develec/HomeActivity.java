package com.inspire.user1.develec;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class HomeActivity extends AppCompatActivity {
    private String developmenturl = "https://firebasestorage.googleapis.com/v0/b/develec-dd016.appspot.com/o/development_icon.png?alt=media&token=05c19b67-0f02-40ba-9ff7-4d0471974d70";
    private String elecurl = "https://firebasestorage.googleapis.com/v0/b/develec-dd016.appspot.com/o/elec-icon.png?alt=media&token=70cdd1aa-f0c4-4a83-8d74-d9253e4b15d0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TextView text1 = findViewById(R.id.textdevel);
        TextView text2 = findViewById(R.id.textelec);
        ImageView webimage = (ImageView) findViewById(R.id.imgdevel);
        ImageView elecimage = (ImageView) findViewById(R.id.imageelec);
        Glide.with(this).load(developmenturl).thumbnail(0.5f).into(webimage);
        Glide.with(this).load(elecurl).thumbnail(0.5f).into(elecimage);


        webimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenActivity1();
            }
        });
        elecimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenActivity2();
            }
        });


    }
    public void OpenActivity1(){
        Intent Webcategories =new Intent(HomeActivity.this,WebParts.class);
        String s1 = findViewById(R.id.textdevel).toString();
        Webcategories.putExtra("actname",s1);
        startActivity(Webcategories);

    }
    public void OpenActivity2(){
        Intent Eleccategories = new Intent(HomeActivity.this,ElecParts.class);
        String s1 = findViewById(R.id.textelec).toString();
        Eleccategories.putExtra("actname",s1);
        startActivity(Eleccategories);

    }
}
