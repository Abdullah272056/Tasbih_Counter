package com.example.tasbihcounter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DisplayData extends AppCompatActivity {
    DataBaseHelper dataBaseHelper;
   // FloatingActionButton addButton;
    RecyclerView recyclerView;
    CustomAdapter customAdapter;
    List<Note> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_display_data);
        recyclerView=findViewById(R.id.recyclerViewId);
        dataBaseHelper=new DataBaseHelper(DisplayData.this);
        dataBaseHelper.getWritableDatabase();

        recyclerView.setLayoutManager(new LinearLayoutManager (DisplayData.this));
        loadData();
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent (DisplayData.this,MainActivity.class);
        startActivity (intent);
        DisplayData.this.finish ();
    }

    private void loadData() {
        dataList  = new ArrayList<> ();
        dataList = dataBaseHelper.getAllNote();
        if (dataList.size() > 0){
            customAdapter = new CustomAdapter(DisplayData.this,dataList);
            recyclerView.setAdapter(customAdapter);

            customAdapter.notifyDataSetChanged();
            int size=dataList.size ();
            Toast.makeText (this, String.valueOf (size), Toast.LENGTH_SHORT).show ();
        }else {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }
    }
}
