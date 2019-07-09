package com.example.thecarpediem;

import android.os.Build;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class Category extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    PageAdapter pageAdapter;
    TabItem tabQuotes;
    TabItem tabStories;
    TabItem tabArticles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        toolbar= findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);

        tabLayout=findViewById(R.id.tablayout);
        tabQuotes=findViewById(R.id.tabquotes);
        tabStories=findViewById(R.id.tabstories);
        tabArticles=findViewById(R.id.tabArticles);
        viewPager=findViewById(R.id.viewPager);

        pageAdapter=new PageAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition()==1) {
                    toolbar.setBackgroundColor(ContextCompat.getColor(Category.this, R.color.colorAccent));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(Category.this, R.color.colorAccent));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(Category.this, R.color.colorAccent));
                    }
                }  else if (tab.getPosition()==2){
                    toolbar.setBackgroundColor(ContextCompat.getColor(Category.this, R.color.darker_grey));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(Category.this, R.color.darker_grey));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(Category.this, R.color.darker_grey));
                    }

                }else {
                    toolbar.setBackgroundColor(ContextCompat.getColor(Category.this, R.color.colorPrimary));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(Category.this, R.color.colorPrimary));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(Category.this, R.color.colorPrimary));
                    }
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}
