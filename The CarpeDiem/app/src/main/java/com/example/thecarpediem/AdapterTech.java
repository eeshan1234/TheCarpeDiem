package com.example.thecarpediem;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AdapterTech extends PagerAdapter {

    private List<model> models;
    private LayoutInflater layoutinflater;
    private Context context;

    public AdapterTech(List<model> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position)
    {
        layoutinflater =LayoutInflater.from(context);
        final View view=layoutinflater.inflate(R.layout.itemtech,container,false);

        ImageView imageView;
        TextView title,desc;

        imageView=view.findViewById(R.id.image);
        title=view.findViewById(R.id.tite);
        desc=view.findViewById(R.id.desc);

        imageView.setImageResource(models.get(position).getImage());
        title.setText(models.get(position).getTitle());
        desc.setText(models.get(position).getDesc());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position==0)
                    Toast.makeText(view.getContext(),"Inspiration",Toast.LENGTH_SHORT).show();

                else if(position==1)
                    Toast.makeText(view.getContext(),"Love",Toast.LENGTH_SHORT).show();

                else if(position==2)
                    Toast.makeText(view.getContext(),"Sad",Toast.LENGTH_SHORT).show();

                else if(position==3)
                    Toast.makeText(view.getContext(),"Science Fiction",Toast.LENGTH_SHORT).show();


                view.getContext().startActivity(new Intent(view.getContext(),Category.class).putExtra("Pos",position));

            }
        });
        container.addView(view,0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container,int position,@NonNull Object object){
        container.removeView((View)object);
    }
}
