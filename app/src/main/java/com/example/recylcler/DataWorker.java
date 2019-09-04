package com.example.recylcler;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class DataWorker extends AppCompatActivity {
    DataOpenHelper mHelper;
    private TextView mCoursename, mCourseID, mNoteTitle, mNoteText, mNoteCourseID;


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

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData();
            }
        });
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
        } else {
            Toast.makeText(this, "Data not added,error", Toast.LENGTH_SHORT).show();
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
}
