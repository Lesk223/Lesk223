package com.example.basedaya2;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    static Integer d;
    Thread t;
    static Integer summary,boost;
    private Integer count, startPoint;
     String uID, email;
    private TextView couter ;
    FirebaseDatabase database;
    DatabaseReference point;
    private ImageView ImageView;
    MediaPlayer mediaPlayer;
    Boolean resume=true;
    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init(); getFirstData();
        getBoostData();
        mainButtonClick();
        mainBitAnimation();
        secCount();


    }

    private void init() {
        d = 1;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Intent intent = getIntent();
        uID = intent.getStringExtra("uID");
        email = intent.getStringExtra("email");
        Integer recieve = 2147483647;

        boost=user.getBoost();
        couter = findViewById(R.id.counter);
        database = FirebaseDatabase.getInstance("https://clicker-768c1-default-rtdb.europe-west1.firebasedatabase.app");
        point = database.getReference();
        ImageView = findViewById(R.id.imageView4);
        user.setEmail(email);

        user.setId(uID);
        mediaPlayer = MediaPlayer.create(this, R.raw.click);
    }

    public void secCount() {
        t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(() -> {
                            if (resume) {
                                Log.d("Fast",String.valueOf(user.getBoost()));
                                summary = user.getPoints();
                                summary = summary + boost;
                                user.setPoints(summary);
                                couter.setText(String.valueOf(summary));
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };   t.start();
    }

    @SuppressLint("ClickableViewAccessibility")
    public void mainBitAnimation() {
        ImageView.setOnTouchListener((v, event) -> {
            int action = event.getAction();

            if (action == MotionEvent.ACTION_DOWN) {  // Кнопка нажата
                ImageView.animate().scaleY(0.5f).scaleX(0.5f);
                mediaPlayer.isPlaying();
            } else if (action == MotionEvent.ACTION_UP) {  // Кнопка отжата
                ImageView.animate().scaleY(0.9f).scaleX(0.9f);
                mediaPlayer.stop();
            }
            return false;
        });
    }

    public void mainButtonClick() {
        ImageView.setOnClickListener(view -> {
            d = 1;

            count = user.getPoints();
            summary = user.getPoints();
            summary = summary + d;
            couter.setText(String.valueOf((summary)));
            user.setPoints(summary);
            user.setBoost(user.getBoost());

        });
    }

    public void getFirstData() {
        point.child("user").child(uID).child("info").child("points").get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            } else {
                if (task.getResult().getValue() != null) {
                    startPoint = Integer.valueOf(String.valueOf(task.getResult().getValue()));
                    Log.d("cool", String.valueOf(startPoint));
                    user.setPoints(startPoint);
                    couter.setText(String.valueOf(user.getPoints()));
                } else {
                    user.setPoints(0);
                    couter.setText("0");
                }
            }
        });
    }
    public void getBoostData() {
        point.child("user").child(uID).child("info").child("boost").get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            } else {
                if (task.getResult().getValue() != null) {
                    boost = Integer.valueOf(String.valueOf(task.getResult().getValue()));
                    Log.d("cool", String.valueOf(startPoint));
                    user.setBoost(boost);

                } else {
                    user.setBoost(1);
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        resume=false;
        point.child("user").child(uID).child("info").setValue(user);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getFirstData();

        getBoostData();
        Intent intent= getIntent();
        boost= intent.getIntExtra("sam",1);
        user.setPoints(startPoint);
        Log.d("coool",String.valueOf(boost));
        resume=true;



    }


    public void EdayButton(View view) {
        Intent intentShop = new Intent(MainActivity.this, ShopActivity.class);

        intentShop.putExtra("uID", user.getId());
        intentShop.putExtra("points", user.getPoints());
        intentShop.putExtra("boost",boost);
        startActivity(intentShop);
        //  finish();

    }

    public void NftEnter(View view) {
        Intent intentNFT = new Intent(MainActivity.this,NftShop.class);
        intentNFT.putExtra("uID",uID);
        intentNFT.putExtra("point",user.getPoints());
        startActivity(intentNFT);
    }
}



