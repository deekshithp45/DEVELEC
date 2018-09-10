package com.inspire.user1.develec;

import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.flags.impl.DataUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class rateus extends AppCompatActivity {
    private RatingBar ratingBar;
    private Button submit_rating;
    private TextView starvalue;
    private EditText editText;
    private EditText comts;
    private float val;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String usname;
    private String coments;
    private FirebaseAuth mAuth;
    private LinearLayout layoutrate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rateus);
        ratingBar = findViewById(R.id.ratingBar);
        submit_rating = findViewById(R.id.sunmit_rating);
        starvalue = findViewById(R.id.value);
        editText = findViewById(R.id.username);
        comts = findViewById(R.id.comment);
        layoutrate = findViewById(R.id.ratelayout);

        //data to be sent to firebase


        firebaseDatabase = FirebaseDatabase.getInstance();
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                val = v;
                starvalue.setText(v + "" + "Stars");
            }
        });
        submit_rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                String uid = user.getUid();
                usname = editText.getText().toString();
                coments = comts.getText().toString();
                Ratings ratings = new Ratings(usname,coments,val);
                if (TextUtils.isEmpty(usname)){
                    editText.setError("User name is required!");
                }
                else if (val == 0 ){
                    starvalue.setError("Ratings is required");
                }
                else {
                    databaseReference = firebaseDatabase.getReference().child("Ratings").child(uid);
                    databaseReference.setValue(ratings);
                    Log.v("Msg", "Reveiw submitted successfully");
                    messageBox("Review submitted successfully. Thank you!");
                }
            }
        });

    }
    public void messageBox(String reviewmsg){
        Toast.makeText(this,reviewmsg,Toast.LENGTH_SHORT).show();
        finish();
    }
}
