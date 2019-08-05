package com.example.thecarpediem;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AmbassadorRule extends AppCompatActivity {
    TextView desc,para;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambassador_rule);
        desc=findViewById(R.id.desc);
        para=findViewById(R.id.introhead);
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Ambassador");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot x:dataSnapshot.getChildren()) {

                    name = dataSnapshot.child("rules").getValue().toString();


                }
                desc.setText(name);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
