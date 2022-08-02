package com.example.gpacalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        ArrayList<String> branch=new ArrayList<>();
        branch.add("Electrical and Electronics");
        branch.add("Electronics and Communication");
        branch.add("Electronics and Instrumentation");
        branch.add("Electronics and Telecommunication");
        branch.add("Information Science");
        branch.add("AI ML");
        branch.add("Biotechnology");
        branch.add("Aerospace");
        branch.add("Computer Science");
        branch.add("Medical Electronics");
        branch.add("Civil");
        branch.add("Mechanical");

        Bundle bundle=getIntent().getExtras();
        String name=bundle.getString("name");



        ArrayAdapter<String> myAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,branch);

        ListView branchList=(ListView)findViewById(R.id.BanchList);
        branchList.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent , View view, int position,long id){
                        String branch=(String)branchList.getItemAtPosition(position);
                        Intent i=new Intent(MainActivity4.this,semester.class);

                        i.putExtra("branch",branch);
                        i.putExtra("name",name);

                        startActivity(i);
                    }
                }
        );



        branchList.setAdapter(myAdapter);
    }
}