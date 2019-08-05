package com.example.thecarpediem;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class live extends AppCompatActivity {
    private VideoView mainVideoView;
    private ImageView playbtn;
    private ImageView fullbtn;
    private ProgressBar currentprogress;
    private ProgressBar currentprogress1;
    private int current=0;
    private int duration=0;
    private Uri videuri;
    private TextView tv;
    int flag=0;
    String name1;
    TextView desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);
        mainVideoView=findViewById(R.id.myvideo);
        currentprogress=findViewById(R.id.myprogress);
        desc=findViewById(R.id.desc);
        fullbtn=findViewById(R.id.full);
        //  currentprogress1=findViewById(R.id.myprogress1);
        // tv=findViewById(R.id.tv);
        playbtn=findViewById(R.id.playpause);
        //  currentprogress1.setMax(100);
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("live");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot x:dataSnapshot.getChildren()) {

                    name1 = dataSnapshot.child("text").getValue().toString();
                   // Toast.makeText(live.this, ""+name1, Toast.LENGTH_SHORT).show();
                    desc.setText(name1);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        videuri=Uri.parse("https://firebasestorage.googleapis.com/v0/b/android-app-5c25d.appspot.com/o/Sample%20Video.mp4?alt=media&token=0b5a7319-bd71-43bd-b7fb-b45a043f3a8b");
        mainVideoView.setVideoURI(videuri);
        mainVideoView.requestFocus();
        mainVideoView.start();
        playbtn.setVisibility(View.INVISIBLE);
        fullbtn.setVisibility(View.INVISIBLE);
        // currentprogress1.setVisibility(View.INVISIBLE);
        mainVideoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playbtn.setVisibility(View.VISIBLE);
                fullbtn.setVisibility(View.VISIBLE);
            }
        });
        playbtn.setVisibility(View.INVISIBLE);
        mainVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {

                duration=mp.getDuration()/1000;
//                    String dstring=String.format("%02d:%02d",duration/60,duration%60);
//                    tv.setText(""+dstring);
                mp.start();

                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {

                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int arg1, int arg2) {
                        // TODO Auto-generated method stub
                        // Log.e(TAG, "Changed");
                        currentprogress.setVisibility(View.GONE);
                        mp.start();
                    }
                });



            }
        });

        fullbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(live.this,video.class);
                startActivity(i);

            }
        });

        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==0) {
                    mainVideoView.pause();
                    flag=1;
                    playbtn.setImageResource(R.drawable.wp);

                }
                else
                {
                    mainVideoView.start();
                    flag=0;
                    playbtn.setImageResource(R.drawable.pw);
                    playbtn.setVisibility(View.INVISIBLE);
                    fullbtn.setVisibility(View.INVISIBLE);
                }
            }
        });

//           new AsyncTask<Void,Integer, Void>()
//            {
//
//                @Override
//                protected Void doInBackground(Void... voids) {
//                    do{
//                        current=mainVideoView.getCurrentPosition()/1000;
//                        try {
//                            int currentpercent = current * 100 / duration;
//                            publishProgress(currentpercent);
//                            Toast.makeText(MainActivity.this, "Curr %= "+currentpercent, Toast.LENGTH_SHORT).show();
//                        }
//                        catch (Exception e) {
//
//                        }
//
//                    }while (currentprogress1.getProgress()<=100);
//                    return null;
//                }
//
//                @Override
//                protected void onProgressUpdate(Integer... values) {
//                    super.onProgressUpdate(values);
//                    currentprogress1.setProgress(values[0]);
//                    Toast.makeText(MainActivity.this, "value= "+values[0], Toast.LENGTH_SHORT).show();
//                }
//            };

    }
}
