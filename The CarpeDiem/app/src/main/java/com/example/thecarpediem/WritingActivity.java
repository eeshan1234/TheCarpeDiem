package com.example.thecarpediem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WritingActivity extends AppCompatActivity {
    Button nextButton;
    Button previousButton;
    private FirebaseAuth.AuthStateListener mauthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);

        setContentView(R.layout.activity_writing);
        setupFirebaseListener();

        nextButton = (Button) findViewById(R.id.nextButton);
        previousButton = (Button) findViewById(R.id.previousButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WritingActivity.this, ScreenSlidePager.class);
                startActivity(intent);
            }
        });
    }

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
                    Intent i = new Intent(WritingActivity.this, Login.class);
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



