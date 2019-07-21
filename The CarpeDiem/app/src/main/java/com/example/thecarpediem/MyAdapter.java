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
    public ImageView img;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        //public ImageView img;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            // img=(ImageView)view.findViewById(R.id.shareimgview);
        }
    }

    public MyAdapter(List<String> moviesList) {
        this.userList = moviesList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list, parent, false);
        img=(ImageView)itemView.findViewById(R.id.shareimgview);

        //Share particular article
        img.setOnClickListener(new View.OnClickListener() {
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
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}