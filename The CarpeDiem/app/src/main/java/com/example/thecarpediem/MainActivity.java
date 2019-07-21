package com.example.thecarpediem;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPagerBest,viewPager1,viewPager2;
    Adapter adapter;
    AdapterTech adapter1;
    AdapterBest adapter2;
    List<model> models, models1,models2;
    Integer[] colors=null;
    Integer[] colors1=null;
    ImageView imgword, imglive;
    Integer[] colors2=null;
    ArgbEvaluator argbEvaluator=new ArgbEvaluator();
    private DrawerLayout drawer;
    private NavigationView nv;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    InternetConnection ic;
    private String[] imgUrls=new String[]{
            "https://cdn.pixabay.com/photo/2017/12/21/12/26/glowworm-3031704_960_720.jpg",
            "https://cdn.pixabay.com/photo/2017/11/07/00/07/fantasy-2925250_960_720.jpg",
            "https://cdn.pixabay.com/photo/2016/11/11/23/34/cat-1817970_960_720.jpg",
            "https://cdn.pixabay.com/photo/2017/12/24/09/09/road-3036620_960_720.jpg",
            "https://cdn.pixabay.com/photo/2017/10/10/15/28/butterfly-2837589_960_720.jpg"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ic= new InternetConnection(MainActivity.this);
        checkInternet();
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        nv = (NavigationView)findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                Toast.makeText(MainActivity.this, "ID1= " + id, Toast.LENGTH_LONG).show();
                switch (id) {
                    case R.id.nav_message:
                        //logic
                        break;
                    case R.id.nav_chat:
                        //logic
                        break;
                    case R.id.nav_profile:
                        //logic
                        break;
                    case R.id.nav_share:
                        //logic
                        break;
                    case R.id.nav_send:
                        //logic
                    default:
                        return true;
                }
                return true;
            }
        });


        BottomNavigationView bottomnav=findViewById(R.id.bottom_navigation);
        bottomnav.setOnNavigationItemSelectedListener(navListener);
        imgword=(ImageView)findViewById(R.id.wordimg);
        imglive=(ImageView)findViewById(R.id.liveimg);

        Picasso.get().load("https://cdn.pixabay.com/photo/2017/12/21/12/26/glowworm-3031704_960_720.jpg").placeholder(R.drawable.logo).into(imgword);
        Picasso.get().load("https://cdn.pixabay.com/photo/2017/11/07/00/07/fantasy-2925250_960_720.jpg").placeholder(R.drawable.logo).into(imgword);


        models1=new ArrayList<>();
        models1.add(new model(R.drawable.inspiration,"Inspiration","Grow Inspired"));
        models1.add(new model(R.drawable.love,"Love","A bliss feeling"));
        models1.add(new model(R.drawable.sad,"Sad","Life shows its ways"));
        models1.add(new model(R.drawable.science,"Science Fiction","Innovate with science"));

        adapter=new Adapter(models,this);
        adapter1=new AdapterTech(models1,this);
        adapter2=new AdapterBest(models2,this);

        viewPagerBest =findViewById(R.id.viewPagerbest);

        ViewPagerAdapter adapterBest=new ViewPagerAdapter(this,imgUrls);

        viewPagerBest.setAdapter(adapterBest);

        sliderDotspanel=findViewById(R.id.SliderDots);

        dotscount = 5;
        dots = new ImageView[dotscount];

        for(int i = 0; i < dotscount; i++){

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        viewPagerBest.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager1=findViewById(R.id.viewPager1);
        viewPager1.setAdapter(adapter1);

        viewPager1.setPadding(130,0,130,0);

        Integer[] colors_temp1={
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4),
        };

        colors1=colors_temp1;
        viewPager1.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int i, float v, int i1) {
                if (i<(adapter1.getCount()-1)&&i<(colors1.length-1)){
                    viewPager1.setBackgroundColor(
                            (Integer)argbEvaluator.evaluate(
                                    v,
                                    colors1[i],
                                    colors1[i+1]
                            )
                    );
                }
                else {
                    viewPager1.setBackgroundColor(colors1[colors1.length-1]);
                }
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    public void checkInternet()
    {
        ConnectivityManager cm= (ConnectivityManager)MainActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo= cm.getActiveNetworkInfo();

        boolean isConnected = networkInfo!=null && networkInfo.isConnectedOrConnecting();
        if(!isConnected)
        {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
            builder1.setMessage("NO Internet! Check Internet connection and try again.");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Reload",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            startActivity(new Intent(MainActivity.this,MainActivity.class));
                        }
                    });

            builder1.setNegativeButton(
                    "Exit",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Toast.makeText(MainActivity.this,"Leaving off!",Toast.LENGTH_SHORT).show();
                            finishAffinity();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }

    }
    @Override
    protected void onResume() {
        super.onResume();
        //checkInternet();
    }

    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
            builder1.setMessage("Are you sure, Want to Exit?");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Sure",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Toast.makeText(MainActivity.this,"Have a good day!",Toast.LENGTH_SHORT).show();
                            finishAffinity();
                        }
                    });

            builder1.setNegativeButton(
                    "Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
       }


    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedfragment = null;
                    switch (menuItem.getItemId())
                    {
                        case R.id.home:
                            selectedfragment = new HomeFragment();
                            break;
//                        case R.id.star:
//                            selectedfragment = new StarFragment();
//                            break;
                        case R.id.notification:
                            selectedfragment = new NotificationFragment();
                            break;
                        case R.id.info:
                            selectedfragment = new InfoFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag_cont1,selectedfragment).commit();
                    return true;
                }
            };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.settings:
                Toast.makeText(getApplicationContext(),"settings",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.live:
                Toast.makeText(getApplicationContext(),"live",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.About:
                Toast.makeText(getApplicationContext(),"about",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Share:
                Toast.makeText(getApplicationContext(),"share",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.contactus:
                Toast.makeText(getApplicationContext(),"contacts",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.exit:
                Toast.makeText(getApplicationContext(),"exit",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
