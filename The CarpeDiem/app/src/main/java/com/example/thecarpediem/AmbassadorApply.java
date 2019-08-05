package com.example.thecarpediem;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AmbassadorApply extends AppCompatActivity {

    TextView desc,para;
    Button b1;
    String name1,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambassador_apply);
        b1=findViewById(R.id.resbtn);
        desc=findViewById(R.id.desc);
        para=findViewById(R.id.introhead);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Ambassador");
                Toast.makeText(AmbassadorApply.this, "Opening Link ...", Toast.LENGTH_SHORT).show();


                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot x:dataSnapshot.getChildren()) {

                            name1 = dataSnapshot.child("register link").getValue().toString();
                            //Toast.makeText(Main3Activity.this, ""+name1, Toast.LENGTH_SHORT).show();

                        }
                        Intent i=new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(name1));
                        //Toast.makeText(Main3Activity.this, "name1: "+name1, Toast.LENGTH_SHORT).show();
                        startActivity(i);
                        final Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                desc.setText("Thank You for registration !!");
                                b1.setEnabled(false);
                            }
                        },5000);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });





            }
        });
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Ambassador");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot x:dataSnapshot.getChildren()) {

                    name = dataSnapshot.child("register").getValue().toString();


                }
                desc.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
