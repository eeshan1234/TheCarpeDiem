package com.example.thecarpediem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

public class Favourites extends AppCompatActivity {
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_favourites);

        BottomNavigationView navigationView=findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId())
                    {
                        case R.id.home:
                            Intent i=new Intent(Favourites.this,MainActivity.class);
                            startActivity(i);
                            break;

                        case R.id.writingact:
                            Intent in=new Intent(Favourites.this,WritingActivity.class);
                            startActivity(in);
                            break;

                        case R.id.notification:
                            Intent inte=new Intent(Favourites.this,Notification.class);
                            startActivity(inte);
                            break;
                    }
                    return true;
                }
            };


}
