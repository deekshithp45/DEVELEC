package com.inspire.user1.develec;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ElecParts extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Query dbref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elec_parts);
        recyclerView = findViewById(R.id.elecrecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        dbref = FirebaseDatabase.getInstance().getReference().child("ElecParts").orderByChild("cat").equalTo("elec");
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
                Intent intent1 = new Intent(ElecParts.this,About_US.class);
                startActivity(intent1);
                break;
            case R.id.rateus:
                final Intent intent = new Intent(ElecParts.this,rateus.class);
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
        FirebaseRecyclerAdapter<eleccontent_items,ElecParts.ImageViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<eleccontent_items, ElecParts.ImageViewHolder>
                (eleccontent_items.class,R.layout.elec_contents,ElecParts.ImageViewHolder.class,dbref) {
            @Override
            protected void populateViewHolder(ElecParts.ImageViewHolder viewHolder, final eleccontent_items model, int position) {
                viewHolder.setElecurl(getApplicationContext(),model.getElecurl());
                viewHolder.setElectext(model.getElectext());
                viewHolder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openAactvity10(model.getElectext());
                    }
                });
            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder{
        View v;
        public ImageViewHolder(View itemview)
        {
            super(itemview);
            v = itemview;
        }

        public void setElecurl(Context context, String elecurl){
            ImageView elimage = v.findViewById(R.id.elecimg);
            Glide.with(context).load(elecurl).thumbnail(0.5f).into(elimage);
        }
        public void setElectext(String electext){
            TextView eltext = v.findViewById(R.id.electext);
            eltext.setText(electext);
        }

    }

    public void openAactvity10(String s4){
        Intent intent = new Intent(ElecParts.this,Elecsub_parts.class);
        intent.putExtra("content",s4);
        startActivity(intent);
    }


}
