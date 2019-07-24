package com.example.thecarpediem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class Settings extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId())
                    {
                        case R.id.home:
                            Intent i=new Intent(Settings.this,MainActivity.class);
                            startActivity(i);
                            break;

                        case R.id.writingact:
                            Intent intent=new Intent(Settings.this,WritingActivity.class);
                            startActivity(intent);
                            break;

                        case R.id.favouritecollec:
                            Intent in=new Intent(Settings.this,Favourites.class);
                            startActivity(in);
                            break;

                        case R.id.notification:
                            Intent inte=new Intent(Settings.this,Notification.class);
                            startActivity(inte);
                            break;
                    }
                    return true;
                }
            };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(navListener);
    }
}
