package com.example.gpacalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class exam extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        Bundle bundle=getIntent().getExtras();
        String name=bundle.getString("name");
        String branch=bundle.getString("branch");
        String sem=bundle.getString("sem");
        String section= bundle.getString("section");
        String subject= bundle.getString("subject");




        ListView exam=(ListView)findViewById(R.id.exam1);
        ArrayList<String> myExam=new ArrayList<>();
        myExam.add("CIE 1");
        myExam.add("CIE 2");
        myExam.add("CIE 3");
        myExam.add("SEE");
        myExam.add("QUIZ 1");
        myExam.add("QUIZ 2");
        ArrayAdapter myAdap=new ArrayAdapter(this,android.R.layout.simple_list_item_1,myExam);
        exam.setAdapter(myAdap);

        exam.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String str= (String) exam.getItemAtPosition(position);
                        if(str.equals("CIE 1")||str.equals("CIE 2")||str.equals("CIE 3")||str.equals("QUIZ 1")||str.equals("QUIZ 2")){
                            Intent i=new Intent(exam.this,cie.class);
                            i.putExtra("name",name);
                            i.putExtra("branch",branch);
                            i.putExtra("sem",sem);
                            i.putExtra("section",section);
                            i.putExtra("subject",subject);
                            i.putExtra("exam",str);
                            startActivity(i);
                        }
                        else{
                            Intent I=new Intent(exam.this,MainActivity3.class);
                            I.putExtra("name",name);
                            I.putExtra("branch",branch);
                            I.putExtra("sem",sem);
                            I.putExtra("section",section);
                            I.putExtra("subject",subject);
                            I.putExtra("exam",str);
                            startActivity(I);
                        }
                    }
                }
        );


    }
}