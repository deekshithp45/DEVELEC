package com.inspire.user1.develec;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class WebParts extends AppCompatActivity {
    private RecyclerView recyclerView2;
    private Query webquery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_parts);
        Intent intent = this.getIntent();
        String txt = intent.getStringExtra("actname");
        recyclerView2 = findViewById(R.id.websub_recycler);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new GridLayoutManager(this,2));
        webquery = FirebaseDatabase.getInstance().getReference().child("ElecParts").orderByChild("cat").equalTo("web");
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.app_list_menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.aboutus:
                Intent intent1 = new Intent(WebParts.this,About_US.class);
                startActivity(intent1);
                break;
            case R.id.rateus:
                final Intent intent = new Intent(WebParts.this,rateus.class);
                startActivity(intent);
                break;
            case R.id.share_icon:
                Intent share = new Intent(Intent.ACTION_SEND);
                String appPackageName = getApplicationContext().getPackageName();
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_SUBJECT,"My new app");
                String applink = "http://play.google.com/store/apps/details?id=" + appPackageName;
                share.putExtra(Intent.EXTRA_TEXT,"Try our new app: "+ applink);
                startActivity(Intent.createChooser(share,"Share Via"));


        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<eleccontent_items,WebParts.ImageViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<eleccontent_items, WebParts.ImageViewHolder>
                (eleccontent_items.class,R.layout.elec_contents,WebParts.ImageViewHolder.class,webquery) {
            @Override
            protected void populateViewHolder(WebParts.ImageViewHolder viewHolder, final eleccontent_items model, int position) {

                viewHolder.setElecurl(getApplicationContext(),model.getElecurl());
                viewHolder.setElectext(model.getElectext());
                viewHolder.v2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        OpenActivity6(model.getElectext());
                    }
                });
            }

        };
        recyclerView2.setAdapter(firebaseRecyclerAdapter);
    }
    public static class ImageViewHolder extends RecyclerView.ViewHolder{
        View v2;
        public ImageViewHolder(View itemview)
        {
            super(itemview);
            v2 = itemview;
        }

        public void setElecurl(Context context, String elecurl){
            ImageView elimage = v2.findViewById(R.id.elecimg);
            Glide.with(context).load(elecurl).thumbnail(0.5f).into(elimage);
        }
        public void setElectext(String electext){
            TextView eltext = v2.findViewById(R.id.electext);
            eltext.setText(electext);
        }

    }
    public void OpenActivity6(String option){
        Intent intent = new Intent(WebParts.this,Websub_parts.class);
        intent.putExtra("option",option);
        startActivity(intent);

    }

}
