package com.example.thecarpediem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class About extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference mFirebaseDatabase;
    TextView nettext;
    CountDownTimer countDownTimer;
    int timeValue = 5;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    Intent intent=new Intent(About.this,MainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.star:
                    Intent intent2=new Intent(About.this,Favourites.class);
                    startActivity(intent2);
                    break;
                case R.id.notification:
                    Intent intent3=new Intent(About.this,Notification.class);
                    startActivity(intent3);
                    break;
                case R.id.info:
//                    Intent intent4=new Intent(About.this,Notification.class);
//                    startActivity(intent4);
                    break;
            }
            return true;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        nettext=findViewById(R.id.nettxt);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        final WebView webView = (WebView) findViewById(R.id.webview1);
        final WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference().child("About");

        final String htmlText = " %s ";

        try {

            countDownTimer = new CountDownTimer(6000, 1000) {
                public void onTick(long millisUntilFinished) {
                    mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String appTitle = dataSnapshot.getValue().toString();

                            String myData = "<html><body style=\"text-align:justify\">" + appTitle + "<br></body></html>";

                            //  Toast.makeText(AboutUs.this,"Data: "+data,Toast.LENGTH_SHORT).show();
                            webView.loadDataWithBaseURL(null, String.format(htmlText, myData), "text/html", "utf-8", "utf-8");
                            webSettings.setDefaultFontSize(19);
                            webView.setBackgroundColor(00000000);
                            webView.setPaddingRelative(2, 2, 2, 2);
                            nettext.setText("Done");

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    if(nettext.getText().equals(""))
                        timeValue -= 1;

                    else
                    {
                        countDownTimer.cancel();
                    }

                }

                //Now user is out of time
                public void onFinish() {
                    //We will navigate him to the time up activity using below method
                    final AlertDialog.Builder builder=new AlertDialog.Builder(About.this);
                    builder.setMessage("Couldn't load. Please check your internet connectivity and try again!");
                    builder.setCancelable(true);

                    builder.setNegativeButton("Reload", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i=new Intent(About.this,About.class);
                            startActivity(i);
                        }
                    });

                    builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i=new Intent(About.this,MainActivity.class);
                            startActivity(i);
                        }
                    });
                    AlertDialog alertdialog=builder.create();
                    alertdialog.show();
                }
            }.start();



        }
        catch (Exception e)
        {

            final AlertDialog.Builder builder=new AlertDialog.Builder(About.this);
            builder.setMessage("Couldn't load. Please check your internet connectivity and try again!");
            builder.setCancelable(true);

            builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i=new Intent(About.this,MainActivity.class);
                    startActivity(i);
                }
            });

            builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i=new Intent(About.this,MainActivity.class);
                    startActivity(i);
                }
            });
            AlertDialog alertdialog=builder.create();
            alertdialog.show();
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        Intent i =new Intent(About.this,MainActivity.class);
        startActivity(i);
    }
}