package com.example.gpacalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
    public void faculty(View view){
        Intent i1=new Intent(MainActivity.this,MainActivity6.class);
        startActivity(i1);
    }
    public void next(View view){
        Intent i=new Intent(MainActivity.this,MainActivity5.class);
        startActivity(i);
    }
    public void regis(View view){
        Intent i=new Intent(MainActivity.this,register.class);
        startActivity(i);
    }


}




