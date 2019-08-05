package com.example.thecarpediem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Favourites extends AppCompatActivity {
    private NavigationView nv;
    private FirebaseAuth.AuthStateListener mauthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_favourites);

        BottomNavigationView navigationView=findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(navListener);
        setupFirebaseListener();
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

    private void setupFirebaseListener() {

        mauthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //signed-in
                    Log.d("AccountManager", "onAuthStateChanged: signed_in: " + user.getUid());

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
