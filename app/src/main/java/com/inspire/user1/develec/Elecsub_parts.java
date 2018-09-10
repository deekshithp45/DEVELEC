package com.inspire.user1.develec;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
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

public class Elecsub_parts extends AppCompatActivity {
    private RecyclerView recyclerView2;
    private Query dbquery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elecsub_parts);
        recyclerView2 = findViewById(R.id.subparts_recycle);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Intent intent = this.getIntent();
        String content = intent.getStringExtra("content");
        dbquery = FirebaseDatabase.getInstance().getReference().child("ElecSubParts").orderByChild("filter").equalTo(content);
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
                Intent intent1 = new Intent(Elecsub_parts.this,About_US.class);
                startActivity(intent1);
                break;
            case R.id.rateus:
                final Intent intent = new Intent(Elecsub_parts.this,rateus.class);
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
        FirebaseRecyclerAdapter<Subparts_electronics,SubViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Subparts_electronics, Elecsub_parts.SubViewHolder>
                (Subparts_electronics.class,R.layout.elec_subparts,Elecsub_parts.SubViewHolder.class,dbquery){
            @Override
            protected void populateViewHolder(Elecsub_parts.SubViewHolder viewHolder, final Subparts_electronics model, int position) {
                viewHolder.Suburl(getApplicationContext(),model.getSuburl());
                viewHolder.Subtext(model.getSubtext());
                viewHolder.v1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openActivity4(model.getSubtext());
                    }
                });
            }
        };
        recyclerView2.setAdapter(firebaseRecyclerAdapter);
    }

    public static class SubViewHolder extends RecyclerView.ViewHolder{
        View v1;
        public SubViewHolder(View itemview)
        {
            super(itemview);
            v1 = itemview;
        }
        public void Suburl(Context ctx, String suburl){
            ImageView subimg = v1.findViewById(R.id.elecsubimg);
            Glide.with(ctx).load(suburl).thumbnail(0.5f).into(subimg);
        }
        public void Subtext(String subtext){
            TextView subtxt = v1.findViewById(R.id.elecsubtext);
            subtxt.setText(subtext);
        }
    }
    public void openActivity4(String s1){
        Intent intent = new Intent(Elecsub_parts.this,Cardparts.class);
        intent.putExtra("content",s1);
        startActivity(intent);
    }
}
