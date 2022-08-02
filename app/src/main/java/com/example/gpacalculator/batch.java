package com.example.gpacalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.LineNumberReader;
import java.util.ArrayList;

public class batch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch);
        Bundle bundle=getIntent().getExtras();
        String branch=bundle.getString("branch");
        String name=bundle.getString("name");

        ArrayList<String> bat=new ArrayList<>();
        bat.add("2015");
        bat.add("2016");
        bat.add("2017");
        bat.add("2018");
        bat.add("2019");
        bat.add("2020");
        bat.add("2021");
        bat.add("2022");
        bat.add("2023");
        bat.add("2024");
        bat.add("2025");
        bat.add("2026");
        bat.add("2027");
        bat.add("2028");
        bat.add("2029");
        bat.add("2030");
        bat.add("2031");
        bat.add("2032");
        bat.add("2033");
        bat.add("2034");
        bat.add("2035");
        bat.add("2036");





        ArrayAdapter myAdapter1=new ArrayAdapter(this,android.R.layout.simple_list_item_1,bat);
        ListView batch=(ListView)findViewById(R.id.batchList);
        batch.setAdapter(myAdapter1);

        batch.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String year=String.valueOf(batch.getItemAtPosition(position));
                        Intent i=new Intent(batch.this,batchDisp.class);
                        i.putExtra("year",year);
                        i.putExtra("name",name);
                        i.putExtra("branch",branch);
                        startActivity(i);
                    }
                }
        );





    }
}