package com.example.thecarpediem;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AmbassadorIntro extends AppCompatActivity {

    Button b1,b2;
    TextView desc,para;
    ImageView i;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambassador_intro);
        i=findViewById(R.id.img);
        b1=findViewById(R.id.resbtn);
        b2=findViewById(R.id.regbtn);
        desc=findViewById(R.id.desc);
        para=findViewById(R.id.introhead);

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Ambassador");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot x:dataSnapshot.getChildren()) {

                    name = dataSnapshot.child("intro").getValue().toString();


                }
                desc.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AmbassadorIntro.this,AmbassadorRule.class);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AmbassadorIntro.this,AmbassadorApply.class);
                startActivity(i);
            }
        });

    }
}
