package com.example.thecarpediem;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ViewPagerAdapter extends PagerAdapter {
    private String[] imgurls;
    private Context context;

    ViewPagerAdapter(Context context, String[] imgurls)
    {
        this.imgurls=imgurls;
        this.context=context;
    }

    @Override
    public int getCount() {
        return imgurls.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView=new ImageView(context);
        Picasso.get().load(imgurls[position]).placeholder(R.drawable.logo).into(imageView);

        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

}
