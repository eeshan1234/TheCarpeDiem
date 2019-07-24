package com.example.thecarpediem;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class BackgroundWritingFragment extends Fragment {


    public BackgroundWritingFragment() {
        // Required empty public constructor
    }
    TextView content;   //TODO : fetch inputs from the writing activity and display appropriately in this Background Writing Fragment.
    static int flag = 5;
    ImageView backgroundImageView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        final View view = inflater.inflate(R.layout.fragment_background_writing, container, false);
        backgroundImageView = (ImageView) view.findViewById(R.id.backgroundImageView);
                content = view.findViewById(R.id.writing_content);
        content.setOnTouchListener(new View.OnTouchListener() {
            private GestureDetector gestureDetector = new GestureDetector(view.getContext(), new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onDoubleTap(MotionEvent e) {
                    //Toast.makeText(view.getContext(),"Double Tap",Toast.LENGTH_SHORT).show();
                    switch (flag)
                    {
                        case 1: backgroundImageView.setImageResource(R.color.secondary_light); flag--;
                        break;
                        case 2: backgroundImageView.setImageResource(R.color.circle_image_shadow); flag--;
                            break;
                        case 3: backgroundImageView.setImageResource(R.color.color3); flag--;
                            break;
                        case 4: backgroundImageView.setImageResource(R.color.color1); flag--;
                            break;
                        case 5: backgroundImageView.setImageResource(R.color.color2);flag--;
                            break;
                            default: // i.e. if flag becomes 0 set flag value back to 5
                                    flag=5;
                    }
                    return super.onDoubleTap(e);
                }
                // implement here other callback methods like onFling, onScroll as necessary
            });

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Log.d("TEST", "Raw event: " + event.getAction() + ", (" + event.getRawX() + ", " + event.getRawY() + ")");
                gestureDetector.onTouchEvent(event);
                return true;

            }

        });
        return view;
    }

}
