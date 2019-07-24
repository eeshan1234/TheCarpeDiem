package com.example.thecarpediem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class WritingActivity extends AppCompatActivity {
    Button nextButton;
    Button previousButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);
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
}



