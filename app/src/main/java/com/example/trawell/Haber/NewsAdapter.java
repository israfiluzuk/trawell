package com.example.trawell.Haber;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trawell.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    Context context;
    ArrayList<HaberDB> haberDBS;

    public NewsAdapter(Context c, ArrayList<HaberDB> hDB){
        context = c;
        haberDBS= hDB;

    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new NewsViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview, viewGroup , false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int position) {
        newsViewHolder.author.setText(haberDBS.get(position).getAuthor());
        newsViewHolder.desc.setText(haberDBS.get(position).getDesc());
        newsViewHolder.title.setText(haberDBS.get(position).getTitle());
        newsViewHolder.publishedAt.setText(haberDBS.get(position).getPublishedAt());
        newsViewHolder.source.setText(haberDBS.get(position).getSource());
        newsViewHolder.time.setText(haberDBS.get(position).getDesc());
        Picasso.get().load(haberDBS.get(position).getImg()).into(newsViewHolder.img);

    }

    @Override
    public int getItemCount() {
        return haberDBS.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder{

        TextView author, title, desc, publishedAt, source, time;
        ImageView img;

     public NewsViewHolder(@NonNull View itemView) {

         super(itemView);

         author = (TextView) itemView.findViewById(R.id.author);
         title = (TextView) itemView.findViewById(R.id.title);
         desc = (TextView) itemView.findViewById(R.id.desc);
         publishedAt = (TextView) itemView.findViewById(R.id.publishedAt);
         source = (TextView) itemView.findViewById(R.id.source);
         time = (TextView) itemView.findViewById(R.id.time);
         img = (ImageView) itemView.findViewById(R.id.img);

     }

 }
}
