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

public class section1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section1);


        Bundle bundle=getIntent().getExtras();
        String branch=bundle.getString("branch");
        String sem=bundle.getString("sem");


        ListView sectionList=(ListView)findViewById(R.id.sectionList);
        ArrayList<String> sections =new ArrayList<>();
        sections.add("A");
        sections.add("B");
        sections.add("C");
        sections.add("D");
        sections.add("NS");

        EditText subjectName=(EditText)findViewById(R.id.subjectName);


        ArrayAdapter myAdap=new ArrayAdapter(this,android.R.layout.simple_list_item_1,sections);
        sectionList.setAdapter(myAdap);
        sectionList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String section=(String)sectionList.getItemAtPosition(position);
                        String subject = String.valueOf(subjectName.getText());
                        if (subject.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "enter the subect", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent i = new Intent(section1.this, information.class);
                            i.putExtra("branch",branch);
                            i.putExtra("sem",sem);
                            i.putExtra("section",section);
                            i.putExtra("subject",subject);
                            startActivity(i);
                        }
                    }


                }
        );
    }
}