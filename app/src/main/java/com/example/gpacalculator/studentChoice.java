package com.example.gpacalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class studentChoice extends AppCompatActivity {
    public String name;
    public String branch;
    public String sem;
    FloatingActionButton fab;
    Button semEntry;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_choice);
        Bundle bundle=getIntent().getExtras();
        name=bundle.getString("name");
        branch=bundle.getString("branch");
        sem=bundle.getString("sem");
        semEntry=(Button)findViewById(R.id.button4);

        semEntry.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i=new Intent(studentChoice.this,semEntry.class);
                        i.putExtra("branch",branch);
                        startActivity(i);
                    }
                }
        );

        fab=findViewById(R.id.floatingActionButton2);


        fab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i=new Intent(studentChoice.this,resMes.class);
                        i.putExtra("branch",branch);
                        i.putExtra("sem",sem);
                        startActivity(i);
                    }
                }
        );



    }

    public void marks(View view){
        Intent i=new Intent(studentChoice.this,sections.class);
        i.putExtra("name",name);
        i.putExtra("branch",branch);
        i.putExtra("sem",sem);
        startActivity(i);
    }
    public void proc(View view){
        Intent i=new Intent(studentChoice.this,proctorDetails.class);

        i.putExtra("name",name);
        i.putExtra("branch",branch);
        i.putExtra("sem",sem);
        startActivity(i);
    }
}