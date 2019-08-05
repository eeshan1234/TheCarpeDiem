package com.example.thecarpediem;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

public class video extends AppCompatActivity {
    private VideoView mainVideoView;
    private ImageView playbtn;
    private ProgressBar currentprogress;
    private ProgressBar currentprogress1;
    private int current=0;
    private int duration=0;
    private Uri videuri;
    private TextView tv;
    int flag=0;
    String name1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        mainVideoView=findViewById(R.id.myvideo);
        currentprogress=findViewById(R.id.myprogress);
        //  currentprogress1=findViewById(R.id.myprogress1);
        // tv=findViewById(R.id.tv);
        playbtn=findViewById(R.id.playpause);
        //  currentprogress1.setMax(100);

        videuri=Uri.parse("https://firebasestorage.googleapis.com/v0/b/android-app-5c25d.appspot.com/o/Sample%20Video.mp4?alt=media&token=0b5a7319-bd71-43bd-b7fb-b45a043f3a8b");
        mainVideoView.setVideoURI(videuri);
        mainVideoView.requestFocus();
        mainVideoView.start();
        playbtn.setVisibility(View.INVISIBLE);

        // currentprogress1.setVisibility(View.INVISIBLE);
        mainVideoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playbtn.setVisibility(View.VISIBLE);
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
