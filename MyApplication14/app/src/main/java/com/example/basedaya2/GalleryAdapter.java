package com.example.basedaya2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder>{
ArrayList<ImageList>imageLists;

    public GalleryAdapter(ArrayList<ImageList> imageLists) {
        this.imageLists = imageLists;
    }

    @NonNull
    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card,parent,false);
        return new GalleryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryAdapter.ViewHolder holder, int position) {
        holder.price.setText(String.valueOf(imageLists.get(position).getPrice()));
holder.textView.setText(imageLists.get(position).getName());
        Picasso.get().load(imageLists.get(position).getUri()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imageLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button button;
        ImageView imageView;
        TextView textView;
        TextView price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            price=itemView.findViewById(R.id.textOwner);
            button=itemView.findViewById(R.id.button4);
            imageView=(ImageView) itemView.findViewById(R.id.imageView3);
            textView=(TextView) itemView.findViewById(R.id.textName);

        }
    }
}

