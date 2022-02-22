package com.rajesh.newsify;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    ArrayList<ModalClass> modelList;

    public Adapter(Context context, ArrayList<ModalClass> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_item, null, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Adapter.ViewHolder holder, int position) {
        int finalPosition = position;
        holder.cd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, webView.class);
                intent.putExtra("url", modelList.get(finalPosition).getUrl());
                context.startActivity(intent);
            }
        });

        position = holder.getAdapterPosition();
        holder.time.setText("Published At: " + modelList.get(position).getPublishedAt());
        holder.author.setText(modelList.get(position).getAuthor());
        holder.heading.setText(modelList.get(position).getTitle());
        holder.content.setText(modelList.get(position).getDescription());
        Glide.with(context).load(modelList.get(position).getUrlToImage()).into(holder.iv);



    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView heading, author, content, time;
        CardView cd;
        ImageView iv;
        public ViewHolder(View itemView) {
            super(itemView);
            heading = itemView.findViewById(R.id.mainHeading);
            author = itemView.findViewById(R.id.author);
            content = itemView.findViewById(R.id.content);
            time = itemView.findViewById(R.id.time);
            cd = itemView.findViewById(R.id.cardView);
            iv = itemView.findViewById(R.id.imageView);
            
        }
    }
}
