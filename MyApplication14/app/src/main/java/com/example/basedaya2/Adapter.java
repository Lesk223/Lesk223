package com.example.basedaya2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Context context;
    private ArrayList <ImageList>imageLists;
ItemClickListener itemClickListener;

    public Adapter(Context context, ArrayList<ImageList> imageLists, ItemClickListener itemClickListener) {
        this.context = context;
        this.imageLists = imageLists;
        this.itemClickListener = itemClickListener;
    }


    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card,parent,false);
return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
holder.button.setText(String.valueOf(imageLists.get(position).getPrice()));
        holder.textView.setText(imageLists.get(position).getName());
        holder.ownerText.setText("owner  "+imageLists.get(position).getOwner());
        if(imageLists.get(position).getOwner()!=" "){holder.button.setEnabled(false);
        holder.button.setBackgroundColor(0xFFFFFF00);}else{holder.button.setEnabled(true);
        holder.button.setBackgroundColor(0xFFFFFFFF);}
Picasso.get().load(imageLists.get(position).getUri()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imageLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
      ImageView imageView;
      TextView textView;
      TextView ownerText;
      Button button;
        public ViewHolder (View view){
            super(view);
            ownerText=view.findViewById(R.id.textOwner);
            button=view.findViewById(R.id.button4);
            imageView=(ImageView) view.findViewById(R.id.imageView3);
            textView=(TextView) view.findViewById(R.id.textName);
        button.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view,imageLists.get(getAdapterPosition()).getName(),imageLists.get(getAdapterPosition()).getPrice(),imageLists.get(getAdapterPosition()).getUri());
        }
    }

}
