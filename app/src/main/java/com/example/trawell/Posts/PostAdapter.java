package com.example.trawell.Posts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trawell.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    Boolean like_Kontrol = false;

    Context context;
    ArrayList<PostDB> postDBS;

    public PostAdapter(Context c, ArrayList<PostDB> pDB){
        context = c;
        postDBS= pDB;

    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PostViewHolder(LayoutInflater.from(context).inflate(R.layout.post_cardview,viewGroup,false));

    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder postViewHolder, int i) {
        postViewHolder.heritage_city.setText(postDBS.get(i).getHeritage_city());
        postViewHolder.heritage_date.setText(postDBS.get(i).getHeritage_date());
        postViewHolder.heritage_name.setText(postDBS.get(i).getHeritage_name());
        postViewHolder.heritage_desc.setText(postDBS.get(i).getHeritage_desc());
        Picasso.get().load(postDBS.get(i).getHeritage_img()).into(postViewHolder.heritage_img);

        postViewHolder.imgBtnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                like_Kontrol= true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return postDBS.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder{

        TextView heritage_city, heritage_date, heritage_name, heritage_desc;
        ImageView heritage_img;
        ImageView imgBtnLike, imgBtnDislike, imgBtnAdd, imgBtnComment;
        TextView txtLike, txtDislike, txtAdd;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            heritage_city = (TextView) itemView.findViewById(R.id.heritage_city);
            heritage_date = (TextView) itemView.findViewById(R.id.heritage_date);
            heritage_name = (TextView) itemView.findViewById(R.id.heritage_name);
            heritage_desc = (TextView) itemView.findViewById(R.id.heritage_desc);
            heritage_img = (ImageView) itemView.findViewById(R.id.heritage_img);
            imgBtnLike = (ImageButton) itemView.findViewById(R.id.imgBtnLike);
            imgBtnDislike = (ImageButton) itemView.findViewById(R.id.imgBtnDislike);
            imgBtnAdd = (ImageButton) itemView.findViewById(R.id.imgBtnAdd);
            imgBtnComment = (ImageButton) itemView.findViewById(R.id.imgBtnComment);
            txtLike = (TextView) itemView.findViewById(R.id.txtLike);
            txtDislike = (TextView) itemView.findViewById(R.id.txtDislike);
            txtAdd = (TextView) itemView.findViewById(R.id.txtAdd);


        }
    }

}
