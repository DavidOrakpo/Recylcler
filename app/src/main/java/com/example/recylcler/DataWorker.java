package com.example.recylcler;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class DataWorker extends AppCompatActivity {
    DataOpenHelper mHelper;
    private TextView mCoursename, mCourseID, mNoteTitle, mNoteText, mNoteCourseID;
    private Button mButton;
    public static final String NOTE_TITLE_VALUE = "Note Title";
    public static final String COURSE_NAME ="Course Name";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_worker);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mCoursename = findViewById(R.id.getCourseName);
        mCourseID = findViewById(R.id.getCourseID);
        mNoteTitle = findViewById(R.id.getNoteTitle);
        mNoteText = findViewById(R.id.getNoteText);
        mNoteCourseID = findViewById(R.id.noteCourseID);
        mButton = findViewById(R.id.done_button);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData();
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set();
            }
        });
    }

    private void set() {
        Intent data = new Intent(this, NavDrawer.class);
        data.putExtra(NOTE_TITLE_VALUE,mNoteTitle.getText().toString());
        data.putExtra(COURSE_NAME, mCoursename.getText().toString());
//        NavDrawer draw = new NavDrawer();
        startActivity(data);


    }

    private void addData() {
        mHelper = new DataOpenHelper(this);
        boolean row = mHelper.insertDataClass1(mCoursename.getText().toString(),
                mCourseID.getText().toString());

        boolean row2 = mHelper.insertDataClass2(mNoteTitle.getText().toString(),
                mNoteText.getText().toString(),
                mNoteCourseID.getText().toString());
        if (row == true && row2 == true) {
            Toast.makeText(this, "Data added to database", Toast.LENGTH_SHORT).show();
        } else if(row == false && row2 == true){
            Toast.makeText(this, "Course Database not updated, Note database updated",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        mHelper = new DataOpenHelper(DataWorker.this);
        mHelper.close();
        super.onDestroy();
    }
}
