package com.example.thecarpediem;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Category extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    PageAdapter pageAdapter;
    TabItem tabQuotes;
    TabItem tabStories;
    TabItem tabArticles;
    TabItem tabPoetry;
    private List<String> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;

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
        tabPoetry=findViewById(R.id.tabPoetry);

//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Intent intent=getIntent();
        final int pos=intent.getIntExtra("Pos",0);

        viewPager=findViewById(R.id.viewPager);

        pageAdapter=new PageAdapter(getSupportFragmentManager(),tabLayout.getTabCount(),pos);
        viewPager.setAdapter(pageAdapter);

        populatecontent(pos,"quotes");

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                if (tab.getPosition()==0) {

                    toolbar.setBackgroundColor(ContextCompat.getColor(Category.this, R.color.colorAccent));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(Category.this, R.color.colorAccent));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(Category.this, R.color.colorAccent));

                    }
                    populatecontent(pos,"quotes");
                }
                else if (tab.getPosition()==1){
                    toolbar.setBackgroundColor(ContextCompat.getColor(Category.this, R.color.darker_grey));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(Category.this, R.color.darker_grey));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(Category.this, R.color.darker_grey));
                    }
                    populatecontent(pos,"story");

                }
                else if (tab.getPosition()==2){
                    toolbar.setBackgroundColor(ContextCompat.getColor(Category.this, R.color.colorAccent));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(Category.this, R.color.colorAccent));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(Category.this, R.color.colorAccent));
                    }
                    populatecontent(pos,"article");

                }
                else if (tab.getPosition()==3){
                    toolbar.setBackgroundColor(ContextCompat.getColor(Category.this, R.color.colorAccent));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(Category.this, R.color.colorAccent));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(Category.this, R.color.colorAccent));
                    }
                    populatecontent(pos,"poetry");

                }
                else{
                    Toast.makeText(Category.this,"Unknown Error! We are working on it, sooner it'll be caught!",Toast.LENGTH_SHORT).show();
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

   public void populatecontent(int pos, final String s)
   {

       switch(pos)
       {
           case 0: //inspiration
               DatabaseReference refinsp= FirebaseDatabase.getInstance().getReference("categories").child("inspiration");
               refinsp.child(s).child("english").addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(DataSnapshot dataSnapshot) {

                       String name1="";

                       for(DataSnapshot x:dataSnapshot.getChildren()){
                           name1= x.getValue().toString();

                               movieList.add(name1);
                       }
                       mAdapter = new MyAdapter(movieList);

//                       writings w1=new writings(movieList);
//                       w1.setWriting(movieList);

                   }

                   @Override
                   public void onCancelled(DatabaseError databaseError) {

                   }

               });

                    break;

           case 1: //love
               DatabaseReference reflove=FirebaseDatabase.getInstance().getReference("categories").child("love");
               reflove.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(DataSnapshot dataSnapshot) {
                       int n=1;

                       for(DataSnapshot x:dataSnapshot.getChildren()){

                           String name1= dataSnapshot.child(s).child("english").child(""+n).getValue().toString();

                           Toast.makeText(Category.this, ""+name1, Toast.LENGTH_SHORT).show();
                           //  Toast.makeText(viewarticle.this, "1st pass", Toast.LENGTH_SHORT).show();

                          // writings user = new writings(name1);
                           movieList.add(name1);
                           n=n+1;
                       }
                   }

                   @Override
                   public void onCancelled(DatabaseError databaseError) {

                   }
               });
               mAdapter = new MyAdapter(movieList);
               recyclerView.setAdapter(mAdapter);
                    break;

           case 2: //sad
               DatabaseReference refsad=FirebaseDatabase.getInstance().getReference("categories").child("sad");
               refsad.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(DataSnapshot dataSnapshot) {
                       int n=1;

                       for(DataSnapshot x:dataSnapshot.getChildren()){

                           String name1= dataSnapshot.child(s).child("english").child(""+(n)).getValue().toString();

                           Toast.makeText(Category.this, ""+name1, Toast.LENGTH_SHORT).show();
                           //  Toast.makeText(viewarticle.this, "1st pass", Toast.LENGTH_SHORT).show();

                          // writings user = new writings(name1);
                           movieList.add(name1);
                           n=n+1;
                       }

                   }

                   @Override
                   public void onCancelled(DatabaseError databaseError) {

                   }
               });
               mAdapter = new MyAdapter(movieList);
               recyclerView.setAdapter(mAdapter);
               break;

           case 3: //science fiction
               DatabaseReference refsc=FirebaseDatabase.getInstance().getReference("categories").child("science fiction");
               refsc.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(DataSnapshot dataSnapshot) {
                       int n=1;

                       for(DataSnapshot x:dataSnapshot.getChildren()){

                           String name1= dataSnapshot.child("quotes").child("english").child(n+"").getValue().toString();

                           Toast.makeText(Category.this,"Name= "+name1,Toast.LENGTH_SHORT).show();

                           //  Toast.makeText(viewarticle.this, "1st pass", Toast.LENGTH_SHORT).show();

                          // writings user = new writings(name1);
                           movieList.add(name1);
                           n=n+1;
                       }

                   }

                   @Override
                   public void onCancelled(DatabaseError databaseError) {

                   }
               });
               mAdapter = new MyAdapter(movieList);
               recyclerView.setAdapter(mAdapter);
               break;

            default: Toast.makeText(Category.this,"Unknown error occurred! We'll sooner trace it.",Toast.LENGTH_SHORT).show();

       }
   }
}
