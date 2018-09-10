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

public class Websub_parts extends AppCompatActivity {
    private RecyclerView recyclerView3;
    private Query websubquery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_websub_parts);
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Intent intent = this.getIntent();
        String filter = intent.getStringExtra("option");
        recyclerView3 = findViewById(R.id.websub_recycler);
        recyclerView3.setHasFixedSize(true);
        recyclerView3.setLayoutManager(new GridLayoutManager(this,2));
        websubquery = FirebaseDatabase.getInstance().getReference().child("WebSubParts").orderByChild("filter").equalTo(filter);
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
                Intent intent1 = new Intent(Websub_parts.this,About_US.class);
                startActivity(intent1);
                break;
            case R.id.rateus:
                final Intent intent = new Intent(Websub_parts.this,rateus.class);
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
        FirebaseRecyclerAdapter<Subparts_electronics,Websub_parts.ImageViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Subparts_electronics, Websub_parts.ImageViewHolder>
                (Subparts_electronics.class,R.layout.elec_contents,Websub_parts.ImageViewHolder.class,websubquery) {
            @Override
            protected void populateViewHolder(Websub_parts.ImageViewHolder viewHolder, final Subparts_electronics model, int position) {
                viewHolder.setSuburl(getApplicationContext(),model.getSuburl());
                viewHolder.setSubtext(model.getSubtext());
                viewHolder.v3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        OpenActivity7(model.getCat1());
                    }
                });

            }

        };
        recyclerView3.setAdapter(firebaseRecyclerAdapter);
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder{
        View v3;
        public ImageViewHolder(View itemview)
        {
            super(itemview);
            v3 = itemview;
        }

        public void setSuburl(Context context, String elecurl){
            ImageView elimage = v3.findViewById(R.id.elecimg);
            Glide.with(context).load(elecurl).thumbnail(0.5f).into(elimage);
        }
        public void setSubtext(String electext){
            TextView eltext = v3.findViewById(R.id.electext);
            eltext.setText(electext);
        }

    }
    public void OpenActivity7(String s4){
        Intent intent = new Intent(Websub_parts.this,Contents_parts.class);
        intent.putExtra("choice",s4);
        startActivity(intent);
    }


}