package com.example.e_fir;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.content.IntentFilter;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class Choice extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Button btncomplaint;
    Button btnlist;
    Button emebutton;
    private DrawerLayout drawer;
    Button btnshare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer=findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        btncomplaint=findViewById(R.id.btncomplaint) ;
        btncomplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Choice.this,com.example.e_fir.File_a_complaint.class);
                startActivity(intent);
            }
        });
        btnlist=findViewById(R.id.btnlist);
        btnlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Choice.this,com.example.e_fir.Complaint_list.class);
                startActivity(intent );
            }
        });
        emebutton=findViewById(R.id.emebutton);
        emebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Choice.this,com.example.e_fir.Coordinates.class);
                startActivity(intent);
            }
        });
        //
        BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action=intent.getAction();
                if(action.equals("finish activity")){
                    finish();
                }

            }
        };
        registerReceiver(broadcastReceiver,new IntentFilter("finish activity"));

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.user_info:
                Intent intent=new Intent(Choice.this,com.example.e_fir.userinfo.class);
                menuItem.setChecked(false);
                startActivity(intent);
                break;
            case R.id.logout:
                Intent intent1 =new Intent(Choice.this,com.example.e_fir.Logout.class);
                menuItem.setChecked(false);
                startActivity(intent1);
                break;

        }
        return true;
    }

    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

}
