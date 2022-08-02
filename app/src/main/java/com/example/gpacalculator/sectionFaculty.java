package com.example.gpacalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class sectionFaculty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_faculty);


        Bundle bundle=getIntent().getExtras();
        String branch=bundle.getString("branch");
        String name=bundle.getString("name");


        ListView sectionList=(ListView)findViewById(R.id.sectionListFac);
        ArrayList<String> sections =new ArrayList<>();
        sections.add("Semester 1");
        sections.add("Semester 2");
        sections.add("Semester 3");
        sections.add("Semester 4");
        sections.add("Semester 5");
        sections.add("Semester 6");
        sections.add("Semester 7");
        sections.add("Semester 8");





        ArrayAdapter myAdap=new ArrayAdapter(this,android.R.layout.simple_list_item_1,sections);
        sectionList.setAdapter(myAdap);
        sectionList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String sem=(String)sectionList.getItemAtPosition(position);
                            Intent i = new Intent(sectionFaculty.this,facultyChoice.class);

                            i.putExtra("branch",branch);
                            i.putExtra("sem",sem);
                            i.putExtra("name",name);

                            startActivity(i);
                        }



                }
        );

    }
}