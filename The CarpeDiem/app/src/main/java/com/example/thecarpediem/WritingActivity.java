package com.example.thecarpediem;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class WritingActivity extends AppCompatActivity {
    Button nextButton;
    Button previousButton;
    Button titleBoldButton;
    Button titleItalicsButton;
    Button titleTextStyleButton;

    Button contentBoldButton;
    Button contentItalicsButton;
    Button contentTextStyleButton;

    int titleStyle[] = {0,0,0};   //Indices:  Bold : 0   Italics : 1    TextStyle: 2
    int contentStyle[]= {0,0,0};  //Indices:  Bold : 0   Italics : 1    TextStyle: 2

    EditText titleEditText;
    TextInputEditText textInputEditTextContent;
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
        titleEditText = (EditText)findViewById(R.id.titleEditText);
        textInputEditTextContent = (TextInputEditText) findViewById(R.id.contentInputEditText) ;
        final String sTitle = titleEditText.getText().toString().trim();
        String content = textInputEditTextContent.getText().toString().trim();

        //Initialising titles component
        titleBoldButton = (Button) findViewById(R.id.BoldButton);
        titleItalicsButton = (Button) findViewById(R.id.ItalicButton);
        titleTextStyleButton = (Button) findViewById(R.id.textStylebutton);
        // Initialising content component
        contentBoldButton = (Button) findViewById(R.id.boldButton2);
        contentItalicsButton = (Button)findViewById(R.id.italicButton2);
        contentTextStyleButton = (Button) findViewById(R.id.textStylebutton2);

        titleBoldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (titleBoldButton.getBackground()!= null)
                    titleBoldButton.setBackgroundResource(R.color.color1);
                else
                    titleBoldButton.setBackgroundResource(R.color.circle_image_shadow);
                titleStyle[0] = (titleStyle[0] == 1)? 0:1;
            }
        });
        //title : if clicked set a flag
        titleItalicsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titleStyle[1] = (titleStyle[1] == 1)? 0:1;
            }
        });
        titleTextStyleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titleStyle[2] = (titleStyle[2] == 1)? 0:1;
            }
        });

        //content
        contentBoldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentStyle[0] = (contentStyle[0] == 1) ? 0: 1;
            }
        });
        contentItalicsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentStyle[1] = (contentStyle[1] == 1) ? 0: 1;
            }
        });
        contentTextStyleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentStyle[2] = (contentStyle[2] == 1) ? 0: 1;
            }
        });

    }
}



