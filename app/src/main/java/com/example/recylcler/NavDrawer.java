package com.example.recylcler;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NavDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private  List<Details> recycList;
    private RecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navdrawer);

        //----TOOLBAR SETUP------------
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //----FLOATING ACTION BUTTON TO MAIN ACTIVITY-----
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NavDrawer.this,MainActivity.class);
                startActivity(intent);
            }
        });

        //-----DRAWER LAYOUT INITIALIZED WITH ACTION BAR TOGGLE----
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //----NAVIGATION VIEW INITIALIZED AND ON CLICK LISTENER SETUP----
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.bottom_home:
                        Toast.makeText(NavDrawer.this, "Home is selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.bottom_messages:
                        Toast.makeText(NavDrawer.this, "Messages is selected", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(NavDrawer.this,DataWorker.class);
                        startActivity(intent);

                        return true;
                    case R.id.bottom_profile:
                        Toast.makeText(NavDrawer.this, "Profile is selected", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }
                
            }
        });

        initializeAdapter();
    }

    private void initializeAdapter(){

        RecyclerView recycler = findViewById(R.id.recyclerView);
        LinearLayoutManager linear_manager = new LinearLayoutManager(this);
        recycler.setLayoutManager(linear_manager);
        //-----DATA TO POPULATE THE LAYOUT MANAGER IS OBTAINED FROM DATA CLASS------------
        recycList = listGenerator(5);


        //----NEW INSTANCE OF ADAPTER CLASS IS CREATED AND THE CONTEXT AND DATA LIST IS PASSED INTO IT
        adapter = new RecyclerAdapter(this, recycList);
        recycler.setAdapter(adapter);
    }

    public List<Details> listGenerator(int loopcount){
        List<Details> details = new ArrayList<>();
        for(int i = 0; i<loopcount;i++){
            details.add(new Details("John","Teacher"));
            details.add(new Details("James","Banker"));
            details.add(new Details("Peter","Farmer"));

        }
        return details;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
