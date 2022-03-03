package com.example.basedaya2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
holder.textView.setText(imageLists.get(position).getName());
Picasso.get().load(imageLists.get(position).getUri()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imageLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
      ImageView imageView;
      TextView textView;
      Button button;
        public ViewHolder (View view){
            super(view);
            button=view.findViewById(R.id.button4);
            imageView=(ImageView) view.findViewById(R.id.imageView3);
            textView=(TextView) view.findViewById(R.id.textView4);
        button.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view,imageLists.get(getAdapterPosition()).getName());
        }
    }

}
