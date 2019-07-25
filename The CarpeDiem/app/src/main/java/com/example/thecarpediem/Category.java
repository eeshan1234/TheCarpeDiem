package com.example.thecarpediem;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
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
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    private FirebaseAuth.AuthStateListener mauthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_category);
        toolbar= findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);
        setupFirebaseListener();

        tabLayout=findViewById(R.id.tablayout);
        tabQuotes=findViewById(R.id.tabquotes);
        tabStories=findViewById(R.id.tabstories);
        tabArticles=findViewById(R.id.tabArticles);
        tabPoetry=findViewById(R.id.tabPoetry);

        viewPager=findViewById(R.id.viewPager);

        pageAdapter=new PageAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);

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
                }
                else if (tab.getPosition()==1){
                    toolbar.setBackgroundColor(ContextCompat.getColor(Category.this, R.color.darker_grey));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(Category.this, R.color.darker_grey));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(Category.this, R.color.darker_grey));
                    }

                }
                else if (tab.getPosition()==2){
                    toolbar.setBackgroundColor(ContextCompat.getColor(Category.this, R.color.colorAccent));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(Category.this, R.color.colorAccent));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(Category.this, R.color.colorAccent));
                    }

                }
                else if (tab.getPosition()==3){
                    toolbar.setBackgroundColor(ContextCompat.getColor(Category.this, R.color.colorAccent));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(Category.this, R.color.colorAccent));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(Category.this, R.color.colorAccent));
                    }
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

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId())
                    {
                        case R.id.home:
                            Intent i=new Intent(Category.this,MainActivity.class);
                            startActivity(i);
                            break;

                        case R.id.favouritecollec:
                            Intent in=new Intent(Category.this,Favourites.class);
                            startActivity(in);
                            break;

                        case R.id.notification:
                            Intent inte=new Intent(Category.this,Notification.class);
                            startActivity(inte);
                            break;
                    }
                    return true;
                }
            };
    private void setupFirebaseListener() {

        mauthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //signed-in
                    Log.d("AccountManager", "onAuthStateChanged: signed_in: " + user.getUid());

                } else {
                    //signout
                    Intent i = new Intent(Category.this, Login.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                }
            }
        };
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mauthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mauthStateListener!=null)
            FirebaseAuth.getInstance().removeAuthStateListener(mauthStateListener);
    }
}