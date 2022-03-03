package com.example.basedaya2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class NftShop extends AppCompatActivity implements View.OnClickListener {
    private Context context;
    private TextView url;
    private ImageView img;
    private Context mContext = NftShop.this;
    private FirebaseDatabase database;
    private DatabaseReference mReference;
    private StorageReference childReference;
    private RecyclerView recyclerView;
    private ArrayList<ImageList> imageLists;
    private String text;
    private Adapter adapter;
    private Button button;
    private Chip buttonTop, buttonPremium, buttonMusic, buttonSport, buttonOther, buttonArt;
    Query query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nft_shop);
        context = this;
        buttonTop = (Chip) findViewById(R.id.chipTOP);
        buttonPremium = (Chip) findViewById(R.id.chipPremium);
        buttonMusic = (Chip) findViewById(R.id.chipMusic);
        buttonSport = (Chip) findViewById(R.id.chipSport);
        buttonOther = (Chip) findViewById(R.id.chipOther);
        buttonArt = (Chip) findViewById(R.id.chipArt);
        buttonTop.setOnClickListener(this);
        buttonArt.setOnClickListener(this);
        buttonSport.setOnClickListener(this);
        buttonPremium.setOnClickListener(this);
        buttonMusic.setOnClickListener(this);
        buttonOther.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        // place here
        database = FirebaseDatabase.getInstance("https://clicker-768c1-default-rtdb.europe-west1.firebasedatabase.app");
        mReference = database.getReference();
        // button = findViewById(R.id.button2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        recyclerView.setHasFixedSize(true);
        childReference = FirebaseStorage.getInstance().getReference();
        imageLists = new ArrayList<>();text = "premium";
        init();

    }

    private void init() {
        clearAll();
        query = mReference.child("image").orderByChild("category").equalTo(text);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                clearAll();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ImageList imageList = new ImageList();
                    imageList.setUri(dataSnapshot.child("uri").getValue().toString());
                    imageList.setName(dataSnapshot.child("name").getValue().toString());
                    imageLists.add(imageList);

                }
                adapter = new Adapter(context, imageLists,itemClickListener);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
ItemClickListener itemClickListener=new ItemClickListener() {
    @Override
    public void onClick(View view, String name) {
mReference.child("image").child(name).child("user").setValue("nope");

//Toast.makeText(NftShop.this,name,imageLists.ge).sh
    }


};
    private void clearAll() {
        if (imageLists != null) {
            imageLists.clear();
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }

        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chipTOP:
                text = "TOP";
                buttonTop.setBackgroundColor(0x00008B);
                init();
                break;
            case R.id.chipPremium:
                text = "premium";
                buttonPremium.setBackgroundColor(0xFFFF00);
                init();
                break;
            case R.id.chipArt:
                text = "korzh";
                init();
                break;
        }
    }
}