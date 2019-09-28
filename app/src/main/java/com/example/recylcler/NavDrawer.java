package com.example.recylcler;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import static com.example.recylcler.MemoryKeeperContract.*;

public class NavDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private  List<Details> recycList;
    private RecyclerAdapter adapter;
    private DataOpenHelper mHelper = new DataOpenHelper(this);

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
                Intent intent = new Intent(NavDrawer.this,DataWorker.class);
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
                        Intent main = new Intent(NavDrawer.this, MainActivity.class);
                        startActivity(main);
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
        RecyclerView recycler = findViewById(R.id.recyclerView);
        LinearLayoutManager linear_manager = new LinearLayoutManager(this);
        recycler.setLayoutManager(linear_manager);
        initializeAdapter();

    }

    private void initializeAdapter(){
        //----NEW INSTANCE OF ADAPTER CLASS IS CREATED AND THE CONTEXT AND DATA LIST IS PASSED INTO IT
        Intent intent = this.getIntent();
        RecyclerView recycler = findViewById(R.id.recyclerView);
        if (intent != null) {
            recycList = new ArrayList<>();
            readnotes(mHelper);
            adapter = new RecyclerAdapter(this, recycList);
            recycler.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }
        else{
            recycList = listGenerator(0);
            adapter = new RecyclerAdapter(this, recycList);
            recycler.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }

    }

    public List<Details> listGenerator(int loopcount){
        List<Details> details = new ArrayList<>();
        for(int i = 0; i<loopcount;i++){
            details.add(new Details("John","Teacher","biking"));
        }
        return details;
    }

    public void readcourses(DataOpenHelper dbhelper) {
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String[] columnsList1 = {
                ClassOne.COLUMN_COURSE_TITLE,
                ClassOne.COLUMN_COURSE_ID};
        final Cursor course_cursor = db.query(ClassOne.TABLE_NAME, columnsList1,
                null, null,
                null, null, null, null);
        loadCoursefromdatabase(course_cursor);
    }

    public void readnotes(DataOpenHelper dbhelper) {
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String[] columnsList2 = {
                ClassTwo.COLUMN_NOTE_TEXT,
                ClassTwo.COLUMN_NOTE_TITLE,
                ClassTwo.COLUMN_COURSE_ID};
        final Cursor note_cursor = db.query(ClassTwo.TABLE_NAME,
                columnsList2,
                null,null,null,null,null);
        loadNotesFromDatabase(note_cursor);
    }

    public void loadCoursefromdatabase(Cursor cursor) {
        //First, you'd need to use the get column index value to avoid hard coding the column's index
        int courseTitleIndex = cursor.getColumnIndex(ClassOne.COLUMN_COURSE_TITLE);
        int courseIDIndex = cursor.getColumnIndex(ClassOne.COLUMN_COURSE_ID);
        while (cursor.moveToNext()) {
            String courseTitle = cursor.getString(courseTitleIndex);
            String courseID = cursor.getString(courseIDIndex);
//            recycList.add(new Details(courseTitle, courseID));
        }
        cursor.close();
    }

    public void loadNotesFromDatabase(Cursor cursor) {
        int NoteTextIndex = cursor.getColumnIndex(ClassTwo.COLUMN_NOTE_TEXT);
        int NoteTitleIndex = cursor.getColumnIndex(ClassTwo.COLUMN_NOTE_TITLE);
        int NoteIDIndex = cursor.getColumnIndex(ClassTwo.COLUMN_COURSE_ID);
        while (cursor.moveToNext()) {
            String noteText = cursor.getString(NoteTextIndex);
            String noteTItle = cursor.getString(NoteTitleIndex);
            String noteID = cursor.getString(NoteIDIndex);
            recycList.add(new Details(noteText, noteTItle, noteID));
        }
        cursor.close();
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
