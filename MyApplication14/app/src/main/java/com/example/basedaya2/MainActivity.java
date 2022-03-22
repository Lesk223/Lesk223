package com.example.basedaya2;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    static Integer d;
    Thread t;

    static Integer summary,boost;
    private Integer count, startPoint,count1060;
     String uID, email;
    private TextView couter, txt, goal;
    private SharedPreferences settings;
    private boolean ImageBool = false;
    FirebaseDatabase database;
    DatabaseReference point;
    private ImageView ImageView;
    MediaPlayer mediaPlayer;
    Handler handler;
    Boolean resume=true;
    private SharedPreferences.Editor prefEditor;
    ChildEventListener pointChildEventListener;
    FirebaseUser firebase;
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
        goal = findViewById(R.id.Goal);
        user.setEmail(email);
        // point.child("user").child(uID).child("videocard").child("1060").setValue(1);

        user.setId(uID);
        mediaPlayer = MediaPlayer.create(this, R.raw.click);
        txt = findViewById(R.id.textView);
        settings = getSharedPreferences("PREFS_FILE", MODE_PRIVATE);
        txt.setText("При каждом 10ом числе,прибавляется на 1 единицу больше");
    }

    public void secCount() {
        t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (resume==true) {
                                    //                                 Intent intent2=getIntent();
                                    //                                   user.setBoost(intent2.getIntExtra("sam",1));
                                    Log.d("Fast",String.valueOf(user.getBoost()));
                                    //user.setVideo1060(count1060);
                                    //boost=user.getBoost();
                                    //Log.d("Fastik",String.valueOf(user.getVideo1060()));
                                    summary = user.getPoints();
                                    user.getBoost();
                                    summary = summary + boost;
                                    user.setPoints(summary);
                                    couter.setText(String.valueOf(summary));

                                    //point.child("user").child(uID).child("info").setValue(user);
                                    // point.child("user").child(uID).setValue(user);
                                }
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
        ImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();

                if (action == MotionEvent.ACTION_DOWN) {  // Кнопка нажата
                    ImageView.animate().scaleY(0.5f).scaleX(0.5f);
                    mediaPlayer.isPlaying();
                } else if (action == MotionEvent.ACTION_UP) {  // Кнопка отжата
                    ImageView.animate().scaleY(0.9f).scaleX(0.9f);
                    mediaPlayer.stop();
                }
                return false;
            }
        });
    }

    public void mainButtonClick() {
        ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d = 1;

                count = user.getPoints();
                summary = user.getPoints();
                summary = summary + d;
                couter.setText(String.valueOf((summary)));
                user.setPoints(summary);
                user.setBoost(user.getBoost());

            }
        });
    }

    public void getFirstData() {
        point.child("user").child(uID).child("info").child("points").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    if (task.getResult().getValue() != null) {
                        startPoint = Integer.valueOf(String.valueOf(task.getResult().getValue()));
                        Log.d("cool", String.valueOf(startPoint));
                        user.setPoints(startPoint);
                        //user.setVideo1060(user.getVideo1060());
                        //  user.setBoost(user.getBoost());

                        couter.setText(String.valueOf(user.getPoints()));
                    } else {
                        user.setPoints(0);
                        couter.setText("0");
                    }
                }
            }
        });
    }
    public void getBoostData() {
        point.child("user").child(uID).child("info").child("boost").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    if (task.getResult().getValue() != null) {
                        boost = Integer.valueOf(String.valueOf(task.getResult().getValue()));
                        Log.d("cool", String.valueOf(startPoint));
                        user.setBoost(boost);
                        //user.setVideo1060(user.getVideo1060());
                        //  user.setBoost(user.getBoost());

                        //couter.setText(String.valueOf(user.getPoints()));
                    } else {
                        user.setBoost(1);
                        // couter.setText("0");
                    }
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

        //   Log.d("vid",String.valueOf(user.getVideo1060()));


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



