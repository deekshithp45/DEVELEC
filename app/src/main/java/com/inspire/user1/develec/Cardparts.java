package com.inspire.user1.develec;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.appinvite.AppInviteInvitation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Cardparts extends AppCompatActivity {
    private Query databaseReference1;
    private RecyclerView recyclerView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration configuration = getResources().getConfiguration();
        if (configuration.screenHeightDp >= 500) {
            setContentView(R.layout.activity_cardparts);
        }
        else {
            setContentView(R.layout.responsive_cardparts);
        }
        recyclerView1 = findViewById(R.id.content_recycler);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Intent intent1 = this.getIntent();
        String contents = intent1.getStringExtra("content");


        databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Contents").orderByChild("type").equalTo(contents);
        databaseReference1.keepSynced(true);

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
                Intent intent1 = new Intent(Cardparts.this,About_US.class);
                startActivity(intent1);
                break;
            case R.id.rateus:
                final Intent intent = new Intent(Cardparts.this,rateus.class);
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

        FirebaseRecyclerAdapter<card_items, Cardparts.CardViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<card_items, Cardparts.CardViewHolder>
                (card_items.class, R.layout.content_item, CardViewHolder.class, databaseReference1) {
            @Override
            protected void populateViewHolder(Cardparts.CardViewHolder viewHolder, final card_items model, int position) {
                viewHolder.setCardtitle(model.getCardtitle());
                viewHolder.setLink(model.getLink());
                viewHolder.setSshot(getApplicationContext(), model.getSshot());
                viewHolder.setDescription(model.getDescription());



            }
        };

        recyclerView1.setAdapter(firebaseRecyclerAdapter);
    }


    public static class CardViewHolder extends RecyclerView.ViewHolder {
        View mview;

        public CardViewHolder(View itemview) {
            super(itemview);
            mview = itemview;
        }

        public void setCardtitle(String cardtitle) {
            TextView titlemain = (TextView) mview.findViewById(R.id.contenthead);
            titlemain.setText(cardtitle);
        }

        public void setLink(final String link) {

            Button visit = mview.findViewById(R.id.visitbutton);
            visit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(link));
                    mview.getContext().startActivity(i);
                }
            });


        }

        public void setSshot(Context ctx, String sshot) {
            ImageView cardimage = (ImageView) mview.findViewById(R.id.webpic);
            Glide.with(ctx).load(sshot).thumbnail(0.5f).into(cardimage);
        }

        public void setDescription(String description) {
            TextView descmain = (TextView) mview.findViewById(R.id.contentdescription);
            descmain.setText(description);
        }

    }

}