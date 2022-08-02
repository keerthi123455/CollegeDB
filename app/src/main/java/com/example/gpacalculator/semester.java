package com.example.gpacalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class semester extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester);

        Bundle bundle=getIntent().getExtras();
        String name=bundle.getString("name");
        String branch=bundle.getString("branch");


        ListView semList=(ListView )findViewById(R.id.sem);
        ArrayList<String> mySem=new ArrayList<>();
        mySem.add("Semester 1");
        mySem.add("Semester 2");
        mySem.add("Semester 3");
        mySem.add("Semester 4");
        mySem.add("Semester 5");
        mySem.add("Semester 6");
        mySem.add("Semester 7");
        mySem.add("Semester 8");
        ArrayAdapter mySemAdap=new ArrayAdapter(this,android.R.layout.simple_list_item_1,mySem);
        semList.setAdapter(mySemAdap);

        semList.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                        String semester=(String)semList.getItemAtPosition(position);
                        Intent i=new Intent(semester.this,studentChoice.class);
                        i.putExtra("name",name);
                        i.putExtra("branch",branch);
                        i.putExtra("sem",semester);
                        startActivity(i);
                    }
                }
        );



    }
}