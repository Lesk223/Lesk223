package com.example.basedaya2;

import static com.example.basedaya2.R.drawable.card;

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
holder.priceText.setText(String.valueOf(" "+imageLists.get(position).getPrice()));
        holder.textView.setText(imageLists.get(position).getName());
        holder.ownerText.setText(imageLists.get(position).getOwner());
        if(imageLists.get(position).getOwner()!=null){
            holder.button.setEnabled(false);
            holder.button.setVisibility(View.INVISIBLE);
            holder.text.setVisibility(View.VISIBLE);
       }
        else{holder.button.setEnabled(true);
            holder.button.setVisibility(View.VISIBLE);
            holder.text.setVisibility(View.INVISIBLE);
        }
Picasso.get().load(imageLists.get(position).getUri()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imageLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
      ImageView imageView;
      TextView textView,priceText,text;
      TextView ownerText;
      Button button;
        public ViewHolder (View view){
            super(view);
            ownerText=view.findViewById(R.id.OwnerCard);
            priceText=view.findViewById(R.id.PriceCard);
            button=view.findViewById(R.id.button4);
            imageView=(ImageView) view.findViewById(R.id.imageView3);
            textView=(TextView) view.findViewById(R.id.textOwner);
            text=(TextView)view.findViewById(R.id.TextCARD);
        button.setOnClickListener(this);
             }
        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view,imageLists.get(getAdapterPosition()).getName(),imageLists.get(getAdapterPosition()).getPrice(),imageLists.get(getAdapterPosition()).getUri());
        }
    }

}
