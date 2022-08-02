package com.example.gpacalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class facultyChoice extends AppCompatActivity {


    public FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_choice);

        Bundle bundle=getIntent().getExtras();
        String branch=bundle.getString("branch");
        String sem=bundle.getString("sem");
        String name=bundle.getString("name");

        fab=findViewById(R.id.floatingActionButton1);
        Button proctor=(Button)findViewById(R.id.proctor1);
        Button marksDetails=(Button)findViewById(R.id.marksDetails);
        Button batchDetails=(Button)findViewById(R.id.batch);
        marksDetails.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        Intent i=new Intent(facultyChoice.this,section1.class);
                        i.putExtra("branch",branch);
                        i.putExtra("sem",sem);
                        startActivity(i);
                    }
                }
        );

        fab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i=new Intent(facultyChoice.this,message.class);
                        i.putExtra("branch",branch);
                        i.putExtra("sem",sem);
                        startActivity(i);
                    }
                }
        );



        proctor.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        Intent i=new Intent(facultyChoice.this,proctor.class);
                        i.putExtra("name",name);
                        i.putExtra("branch",branch);
                        i.putExtra("sem",sem);
                        startActivity(i);
                    }
                }
        );
        batchDetails.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        Intent i=new Intent(facultyChoice.this,batch.class);
                        i.putExtra("name",name);
                        i.putExtra("branch",branch);
                        startActivity(i);
                    }
                }
        );


    }
}