package com.example.basedaya2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {
   private RecyclerView recyclerView;
    private ArrayList<ImageList> imageLists;
    private FirebaseDatabase database;
    private DatabaseReference mReference;
    private GalleryAdapter adapter;
    Query query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        recyclerView=findViewById(R.id.GalleryRecycler);
        database = FirebaseDatabase.getInstance("https://clicker-768c1-default-rtdb.europe-west1.firebasedatabase.app");
        mReference = database.getReference();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);
        imageLists=new ArrayList<>();

init();

    }
    private void clearAll() {
        if (imageLists != null) {
            imageLists.clear();
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }

        }
    }
    private void init() {
        clearAll();
       // query = mReference.child("user").child(uID).child("images").orderByChild("type").equalTo("free");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                clearAll();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ImageList imageList = new ImageList();
                    imageList.setUri(dataSnapshot.child("URI").getValue().toString());
                    imageList.setName(dataSnapshot.child("name").getValue().toString());
                    imageList.setPrice(Integer.valueOf(dataSnapshot.child("price").getValue().toString()));
                    imageLists.add(imageList);


                }
                adapter = new GalleryAdapter(imageLists);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }}