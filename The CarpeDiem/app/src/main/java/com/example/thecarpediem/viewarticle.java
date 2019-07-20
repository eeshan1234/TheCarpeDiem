package com.example.thecarpediem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class viewarticle extends AppCompatActivity {
    private List<writings> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewarticle);
        final TextView tv1,tv2;

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Intent intent=getIntent();
        final int z=0;
        int pos=0;
        //final int pos=intent.getIntExtra("pgno",0);
        if(pos==0)
        {
            DatabaseReference refinsp= FirebaseDatabase.getInstance().getReference("categories").child("inspiration");
            refinsp.child("poetry").child("english").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    String name1="";

                    for(DataSnapshot x:dataSnapshot.getChildren()){
                        name1= x.getValue().toString();

//                        writings writeup = new writings(name1);
//                        movieList.add(writeup);
                    }
                Toast.makeText(viewarticle.this,""+movieList,Toast.LENGTH_SHORT).show();
//                mAdapter = new MyAdapter(movieList);
//                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        }
        else if(pos==1)
        {
            DatabaseReference reference=FirebaseDatabase.getInstance().getReference("categories").child("love");
            reference.child("love").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    int n=1;

                    for(DataSnapshot x:dataSnapshot.getChildren()){

                        String name1= dataSnapshot.child("quotes").child("english").child(""+n).getValue().toString();

                        Toast.makeText(viewarticle.this, ""+name1, Toast.LENGTH_SHORT).show();
                        //  Toast.makeText(viewarticle.this, "1st pass", Toast.LENGTH_SHORT).show();

//                        writings user = new writings(name1);
//                        movieList.add(user);
                        n=n+1;
                    }
//                    mAdapter = new MyAdapter(movieList);
//                    recyclerView.setAdapter(mAdapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        else if(pos==2)
        {
            DatabaseReference reference=FirebaseDatabase.getInstance().getReference("categories").child("sad");
            reference.child("sad").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {  int n=1;

                    for(DataSnapshot x:dataSnapshot.getChildren()){

                        String name1= dataSnapshot.child("quotes").child("english").child(""+(n)).getValue().toString();

                        Toast.makeText(viewarticle.this, ""+name1, Toast.LENGTH_SHORT).show();
                        //  Toast.makeText(viewarticle.this, "1st pass", Toast.LENGTH_SHORT).show();

//                        writings user = new writings(name1);
//                        movieList.add(user);
                        n=n+1;
                    }
//                    mAdapter = new MyAdapter(movieList);
//                    recyclerView.setAdapter(mAdapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        else
        {
            final DatabaseReference reference=FirebaseDatabase.getInstance().getReference("categories").child("science fiction");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    int n=1;

                    for(DataSnapshot x:dataSnapshot.getChildren()){

                        String name1= dataSnapshot.child("quotes").child("english").child(n+"").getValue().toString();

                        Toast.makeText(viewarticle.this,"Name= "+name1,Toast.LENGTH_SHORT).show();

                        //  Toast.makeText(viewarticle.this, "1st pass", Toast.LENGTH_SHORT).show();

//                        writings user = new writings(name1);
//                        movieList.add(user);
                        n=n+1;
                    }
//                    mAdapter = new MyAdapter(movieList);
//                    recyclerView.setAdapter(mAdapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

    }
}
