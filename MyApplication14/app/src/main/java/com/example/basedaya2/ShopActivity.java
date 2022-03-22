package com.example.basedaya2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShopActivity extends AppCompatActivity {
    private Button buyButton1;
    private Button buyButton2;
    private Button buyButton3;
    private Integer  points,boost,price1060,price1070,price1080,count1080,count1070,count1060;
    FirebaseDatabase Userdatabase;
    DatabaseReference useData;
    private TextView txt;
    private String uID;
    User2 shopInfo=new User2();
    User user =new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        init();
        getFirstData();
        clickShop();
    }



    private void init() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        buyButton1 = findViewById(R.id.buyButton1);
        buyButton2 = findViewById(R.id.buyButton2);
        buyButton3 = findViewById(R.id.buyButton3);
        txt=findViewById(R.id.textView5);
        Userdatabase = FirebaseDatabase.getInstance("https://clicker-768c1-default-rtdb.europe-west1.firebasedatabase.app");
        useData = Userdatabase.getReference();
        Intent intent = getIntent();
        points=intent.getIntExtra("points",0);
        txt.setText(String.valueOf(points));
        uID = intent.getStringExtra("uID");
        boost=intent.getIntExtra("boost",1);
    }
    public void clickShop() {
        buyButton1.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                                              if (points > price1060) {
                                                  points = points - price1060;
                                                  txt.setText(String.valueOf(points));
                                                  shopInfo.setVideo1060(shopInfo.getVideo1060() + 1);
                                                  boost+=1;
                                                  price1060 = 150 * shopInfo.getVideo1060();
                                                  buyButton1.setText(String.valueOf(price1060));
                                              } else
                                                  Toast.makeText(ShopActivity.this, "need  " + String.valueOf(price1060 - points) + " btc", Toast.LENGTH_SHORT).show();
                                          }
                                      });
        buyButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (points > price1070) {
                    points = points - price1070;
                    txt.setText(String.valueOf(points));
                    shopInfo.setVideo1070(shopInfo.getVideo1070() + 1);
                    boost += 3;
                    price1070 = 300 * shopInfo.getVideo1070();
                    buyButton2.setText(String.valueOf(price1070));
                    user.setPoints(points);
                } else {
                    Toast.makeText(ShopActivity.this, "need  " + String.valueOf(price1070 - points) + " btc", Toast.LENGTH_SHORT).show();

                }
            }});
        buyButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (points > price1080) {
                    points = points - price1080;
                    txt.setText(String.valueOf(points));
                    shopInfo.setVideo1080(shopInfo.getVideo1080() + 1);
                    boost += 5;
                    price1080 = 500 * shopInfo.getVideo1080();
                    buyButton3.setText(String.valueOf(price1080));
                    user.setPoints(points);
                } else {
                    Toast.makeText(ShopActivity.this, "need  " + String.valueOf(price1080 - points) + " btc", Toast.LENGTH_SHORT).show();

                }
            }});}
    //  });
    //}


    public void getFirstData() {
        useData.child("user").child(uID).child("video").child("video1060").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    if (task.getResult().getValue() != null) {
                        shopInfo.setVideo1060(Integer.valueOf(String.valueOf(task.getResult().getValue())));
                        price1060=150*shopInfo.getVideo1060();
                        buyButton1.setText(String.valueOf(price1060));


                    } else {
                        shopInfo.setVideo1060(1);
                        price1060 = 150;
                       buyButton1.setText(String.valueOf(price1060));
                        useData.child("user").child(uID).child("video").setValue(shopInfo);
                    }}}});
                            useData.child("user").child(uID).child("video").child("video1070").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DataSnapshot> task) {
                                    if (!task.isSuccessful()) {
                                        Log.e("firebase", "Error getting data", task.getException());
                                    } else {
                                        if (task.getResult().getValue() != null) {
                                            shopInfo.setVideo1070(Integer.valueOf(String.valueOf(task.getResult().getValue())));
                                            price1070 = 300 * shopInfo.getVideo1070();
                                            buyButton2.setText(String.valueOf(price1070));
                                            count1070 = shopInfo.getVideo1070();}
                                        else{shopInfo.setVideo1070(1);
                                        price1070=300;
                                        buyButton2.setText(String.valueOf(price1070));

                                        }
                                    }
                                }
                            });
        useData.child("user").child(uID).child("video").child("video1080").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    if (task.getResult().getValue() != null) {
                        shopInfo.setVideo1080(Integer.valueOf(String.valueOf(task.getResult().getValue())));
                        price1080 = 500 * shopInfo.getVideo1080();
                        buyButton3.setText(String.valueOf(price1080));
                        count1080 = shopInfo.getVideo1080();}
                    else{shopInfo.setVideo1080(1);
                        price1080=500;
                        buyButton3.setText(String.valueOf(price1080));
                        }}}});}

    @Override
    protected void onPause() {
        super.onPause();
    useData.child("user").child(uID).child("video").setValue(shopInfo);
useData.child("user").child(uID).child("info").child("points").setValue(points);
useData.child("user").child(uID).child("info").child("boost").setValue(boost);
Intent intent2 = new Intent(this,MainActivity.class);
        intent2.putExtra("sam",boost);
        Log.d("Boost",String.valueOf(boost));
        finish();

    }
}
