package com.inspire.user1.develec;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
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

public class Contents_parts extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Query databaseReference;
    private static final String TAG = "Contentsparts";
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contents_parts);
        Intent intent = this.getIntent();
        String choice = intent.getStringExtra("choice");
        mAdView = findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Tech").orderByChild("Category").equalTo(choice);

        recyclerView = findViewById(R.id.recycler1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
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
                Intent intent1 = new Intent(Contents_parts.this,About_US.class);
                startActivity(intent1);
                break;
            case R.id.rateus:
                final Intent intent = new Intent(Contents_parts.this,rateus.class);
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

        FirebaseRecyclerAdapter<list_items,ImageViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<list_items, ImageViewHolder>
                (list_items.class,R.layout.list_item,ImageViewHolder.class,databaseReference) {
            @Override
            protected void populateViewHolder(ImageViewHolder viewHolder, final list_items model, int position) {
                viewHolder.setUrl(getApplicationContext(),model.getUrl());
                viewHolder.setHead(model.getHead());
                viewHolder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        OpenActivity10(model.getHead());
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
        public void setUrl(Context ctx, String url){
            ImageView icon_image = v.findViewById(R.id.images);
            Glide.with(ctx).load(url).thumbnail(0.5f).into(icon_image);

        }
        public void setHead(String title){
            TextView imagetitle = (TextView) v.findViewById(R.id.title);
            imagetitle.setText(title);
        }


    }
    public void OpenActivity10(String s1){
        Intent intent1 = new Intent(Contents_parts.this,Cardparts.class);
        intent1.putExtra("content",s1);
        startActivity(intent1);
    }
}
