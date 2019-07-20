package com.example.thecarpediem;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Toast;

public class PageAdapter extends FragmentPagerAdapter {

    private int numoftabs;

    PageAdapter(FragmentManager fm,int numoftabs){
        super(fm);
        this.numoftabs=numoftabs;
        }
    @Override
    public Fragment getItem(int position){
        switch(position){
            case 0:
                return new QuoteFragment();
            case 1:
                return new StoryFragment();
            case 2:
                return new ArticleFragment();

            case 3:
                return new PoetryFragment();

            default:
                return null;
        }
    }
    @Override
    public int getCount(){
    return numoftabs;
    }
}
