package com.example.recylcler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    private  List<Details> recycList;
    private TextView name_entry;
    private TextView name_occupation;
    private TextView title_name;
    private TextView title_occupation;
    private Button button;
    private RecyclerAdapter adapter;
    private Boolean state;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recyclerview);



//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //------RECYCLER VIEW IN THE LAYOUT FILE IS CONNECTED BY FIND VIEW BY ID----------
        //------LAYOUT MANAGER IS SET--------------
//        RecyclerView recycler = findViewById(R.id.recycler_view);
//        LinearLayoutManager linear_manager = new LinearLayoutManager(this);
//        recycler.setLayoutManager(linear_manager);
//        //-----DATA TO POPULATE THE LAYOUT MANAGER IS OBTAINED FROM DATA CLASS------------
//        recycList = listGenerator(5);
//
//
//        //----NEW INSTANCE OF ADAPTER CLASS IS CREATED AND THE CONTEXT AND DATA LIST IS PASSED INTO IT
//        adapter = new RecyclerAdapter(this, recycList);
//        recycler.setAdapter(adapter);
        initializeAdapter();

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(state == true){
                dataAdder(name_entry.getText().toString(),name_occupation.getText().toString());
                }
                else if(state == false){
                    dataRemover(Integer.parseInt(name_entry.getText().toString()));
                }
            }
        });


    }

    private void initializeAdapter(){
        RecyclerView recycler = findViewById(R.id.recycler_view);
        LinearLayoutManager linear_manager = new LinearLayoutManager(this);
        recycler.setLayoutManager(linear_manager);
        //-----DATA TO POPULATE THE LAYOUT MANAGER IS OBTAINED FROM DATA CLASS------------
        recycList = listGenerator(0);


        //----NEW INSTANCE OF ADAPTER CLASS IS CREATED AND THE CONTEXT AND DATA LIST IS PASSED INTO IT
        adapter = new RecyclerAdapter(this, recycList);
        recycler.setAdapter(adapter);
    }

    public List<Details> listGenerator(int loopcount){
        List<Details> details = new ArrayList<>();
        for(int i = 0; i<loopcount;i++){
            details.add(new Details("John","Teacher","biking"));
            ;

        }
        return details;
    }

    public void dataRemover(int position){
        if(position>= recycList.size()){
            Toast.makeText(this, "The list isn't up to that number", Toast.LENGTH_SHORT).show();
            name_entry.setVisibility(View.INVISIBLE);
            button.setVisibility(View.INVISIBLE);

        }
        else{

        recycList.remove(position);
        name_entry.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);
        adapter.notifyDataSetChanged();
        }
    }


    public void dataAdder (String name, String occupation){
//        name = name_entry.getText().toString();
//        occupation = name_occupation.getText().toString();
        if(!Objects.equals(name, "") && !Objects.equals(occupation, "")) {
            recycList.add(new Details(name, occupation,"biking"));
            name_entry.setText("");
            name_occupation.setText("");
            title_name.setVisibility(View.INVISIBLE);
            title_occupation.setVisibility(View.INVISIBLE);
            name_entry.setVisibility(View.INVISIBLE);
            name_occupation.setVisibility(View.INVISIBLE);
            button.setVisibility(View.INVISIBLE);
            adapter.notifyDataSetChanged();
        }
        else if((Objects.equals(name, "")) || (Objects.equals(occupation, ""))){
            Toast.makeText(this, "Enter Values into the field", Toast.LENGTH_SHORT).show();
        }
    }

    public void additem(View view) {
        PopupMenu pop = new PopupMenu(this,view);
        pop.setOnMenuItemClickListener(this);
        pop.inflate(R.menu.popup_menu);
        pop.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {

        title_name = findViewById(R.id.option_name);
        title_occupation = findViewById(R.id.option_occupation);
        name_entry = findViewById(R.id.input_name);
        name_occupation = findViewById(R.id.input_occupation);


        switch (menuItem.getItemId()){
            case R.id.popup_entry:
                return true;
            case R.id.popup_no:
                //Toast.makeText(this, "Nah", Toast.LENGTH_SHORT).show();
                title_name.setVisibility(View.INVISIBLE);
                title_occupation.setVisibility(View.INVISIBLE);
                name_entry.setVisibility(View.INVISIBLE);
                name_occupation.setVisibility(View.INVISIBLE);
                button.setVisibility(View.INVISIBLE);
                return true;
            case R.id.popup_yes:
                title_name.setVisibility(View.VISIBLE);
                title_occupation.setVisibility(View.VISIBLE);
                name_entry.setVisibility(View.VISIBLE);
                name_occupation.setVisibility(View.VISIBLE);
                state = true;
                button.setVisibility(View.VISIBLE);
                return true;
            case R.id.popup_remove:
                return true;
            case R.id.item_remove:
                Toast.makeText(this, "Enter position", Toast.LENGTH_SHORT).show();
                state = false;
                name_entry.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
                return true;
            case R.id.item_remain:
                clearlist();
                Toast.makeText(this, "List cleared", Toast.LENGTH_SHORT).show();
                return true;


            default:
                return false;
        }
    }

    public void clearlist() {
        recycList.clear();
        adapter.notifyDataSetChanged();
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
}

