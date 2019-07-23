package com.example.thecarpediem;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<String> userList;
    public ImageView shareimg;
    public ImageView likeimg;
    public int counter=0;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView likescountertxt;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            likescountertxt=(TextView)view.findViewById(R.id.likestxtview);
        }
    }

    public MyAdapter(List<String> moviesList) {
        this.userList = moviesList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list, parent, false);
        shareimg=(ImageView)itemView.findViewById(R.id.shareimgview);
        likeimg=(ImageView)itemView.findViewById(R.id.likeimgview);


        //Share particular article
        shareimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "The CarpeDiem");
                intent.putExtra(Intent.EXTRA_TEXT, "Install *The CarpeDiem* App now! https://github.com/sarthaksarm/TheCarpeDiem"); //give article's or app's link here
                itemView.getContext().startActivity(Intent.createChooser(intent, "Share!"));
            }
        });

       return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        String writ = userList.get(position);
        holder.title.setText(writ);

        likeimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                if(counter%2!=0){
                    holder.likescountertxt.setText("1");

                }

                else{
                    holder.likescountertxt.setText("0");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}