package com.example.basedaya2;

import static android.view.KeyEvent.KEYCODE_ENTER;
import static com.example.basedaya2.R.id.chipArt;
import static com.example.basedaya2.R.id.chipMusic;
import static com.example.basedaya2.R.id.chipMy;
import static com.example.basedaya2.R.id.chipOther;
import static com.example.basedaya2.R.id.chipPremium;
import static com.example.basedaya2.R.id.chipTOP;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NftShop extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private Context context;
    private TextView textView;
    private Integer points;
    private FirebaseDatabase database;
    private DatabaseReference mReference;
    private RecyclerView recyclerView;
    private TextInputEditText search;
    private ArrayList<ImageList> imageLists;
    private String text, uID, UserName;
    private Adapter adapter;
    Query query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nft_shop);
        context = this;
        Intent intent = getIntent();
        search = findViewById(R.id.searchin);
        uID = intent.getStringExtra("uID");
        points = intent.getIntExtra("point", 0);
        textView = findViewById(R.id.points);
        Chip buttonTop = findViewById(R.id.chipTOP);
        Chip buttonPremium = findViewById(R.id.chipPremium);
        Chip buttonMusic = findViewById(R.id.chipMusic);
        Chip buttonSport = findViewById(R.id.chipSport);
        Chip buttonOther = findViewById(R.id.chipOther);
        Chip buttonArt = findViewById(R.id.chipArt);
        Chip buttonMy = findViewById(R.id.chipMy);
        buttonTop.setOnClickListener(this);
        buttonArt.setOnClickListener(this);
        buttonSport.setOnClickListener(this);
        buttonMy.setOnClickListener(this);
        buttonPremium.setOnClickListener(this);
        buttonMusic.setOnClickListener(this);
        buttonOther.setOnClickListener(this);
        recyclerView = findViewById(R.id.recycler);
        database = FirebaseDatabase.getInstance("https://clicker-768c1-default-rtdb.europe-west1.firebasedatabase.app");
        mReference = database.getReference();
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        recyclerView.setHasFixedSize(true);
        imageLists = new ArrayList<>();
        text = "premium";
        UserName = "s";
        buttonPremium.isChecked();
        textView.setText(String.valueOf(points));
        query = mReference.child("image").orderByChild("category").equalTo(text);
        init();
        onKeyDown();
        // getData();
    }

    private void onKeyDown() {
        search.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KEYCODE_ENTER) {
                query = mReference.child("image").orderByChild("name").startAt(String.valueOf(search.getText())).endAt(search.getText() + "\uf8ff");
                init();
            }

            return false;
        });
    }

    private void init() {
        clearAll();
        query.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                clearAll();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ImageList imageList = new ImageList();
                    imageList.setUri(String.valueOf(dataSnapshot.child("uri").getValue()));
                    imageList.setName(String.valueOf(dataSnapshot.child("name").getValue()));

                    if (dataSnapshot.child("user").getValue() != null) {
                        imageList.setOwner(String.valueOf(dataSnapshot.child("user").getValue()));
                    } else {

                    }
                    if (dataSnapshot.child("price").getValue() != null) {
                        imageList.setPrice(Integer.valueOf(String.valueOf(dataSnapshot.child("price").getValue())));
                    } else {
                        mReference.child("image").child(imageList.getName()).child("price").setValue(5);
                    }
                    imageLists.add(imageList);


                }
                adapter = new Adapter(context, imageLists, itemClickListener);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    ItemClickListener itemClickListener = new ItemClickListener() {
        @Override
        public void onClick(View view, String name, Integer price, String Uri) {
            if (points > price) {
                mReference.child("image").child(name).child("user").setValue(UserName);
                points = points - price;
                textView.setText(String.valueOf(points));
                mReference.child("user").child(uID).child("images").child(name).child("name").setValue(name);
                mReference.child("user").child(uID).child("images").child(name).child("uri").setValue(Uri);
                mReference.child("user").child(uID).child("images").child(name).child("price").setValue(price);
                mReference.child("user").child(uID).child("images").child(name).child("user").setValue(UserName);
                mReference.child("user").child(uID).child("images").child(name).child("type").setValue("free");


            } else
                Toast.makeText(NftShop.this, "need:" + (price - points), Toast.LENGTH_SHORT).show();
        }

    };

    @Override
    protected void onPause() {
        super.onPause();

        mReference.child("user").child(uID).child("info").child("points").setValue(points);
    }

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

        if (view.getId() == chipTOP) {
            query = mReference.child("image").orderByChild("category").equalTo("TOP");
            init();
        }
        if (view.getId() == chipPremium) {
            query = mReference.child("image").orderByChild("category").equalTo("premium");
            init();
        }
        if (view.getId() == chipArt) {
            query = mReference.child("user").child(uID).child("images").orderByChild("type").equalTo("free");
            init();
        }
        if (view.getId() == chipMusic) {
            query = mReference.child("image").orderByChild("category").equalTo("Music");
            init();
        }
        if (view.getId() == chipOther) {
            query = mReference.child("image").orderByChild("name").startAt("k").endAt("korzh2");
            init();
        }
        if (view.getId() == chipMy) {
            query = mReference.child("user").child(uID).child("images").orderByChild("type").equalTo("free");
            init();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }
  /*  private void getData(){
        mReference.child("user").child(uID).child("Username").get().addOnCompleteListener(task -> {
            if (task.getResult().getValue() != null) {
                UserName = task.getResult().getValue().toString();


            } else {
                Toast.makeText(NftShop.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
        }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
    }*/
}