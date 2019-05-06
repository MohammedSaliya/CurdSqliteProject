package com.example.curdsqlite;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class RecycActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton fab;
    RecycAdapter getAdapter;


    List<Bean> beans=new ArrayList<>();

    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyc);
        myDb = new DatabaseHelper(getApplicationContext());

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);



        Cursor res = myDb.getAllData();
        if (res.getCount() == 0) {
            // show message
         //   showMessage("Error", "Nothing found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Id :" + res.getString(0) + "\n");
            buffer.append("Name :" + res.getString(1) + "\n");
            buffer.append("Surname :" + res.getString(2) + "\n");
            buffer.append("Marks :" + res.getString(3) + "\n\n");



            beans.add(new Bean(res.getString(0),res.getString(1),res.getString(2),res.getString(3)));
        }


        getAdapter=new RecycAdapter(getApplicationContext(),beans );

        recyclerView.setAdapter(getAdapter);


        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecycActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
