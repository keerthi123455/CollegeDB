package com.example.gpacalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        EditText input=(EditText)findViewById(R.id.sgpa1);
        EditText input1=(EditText)findViewById(R.id.sgpa2);
        Button myButton=(Button)findViewById(R.id.button3);

        myButton.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View view) {

                        if (String.valueOf(input.getText()).isEmpty() || String.valueOf(input1.getText()).isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Enter the SGPA", Toast.LENGTH_SHORT).show();
                        } else {
                            float i = new Float(input.getText().toString());
                            float i1 =new Float(input1.getText().toString());
                            float cgpa = (float) (i + i1) / 2;
                            String str = Float.toString(cgpa);
                            TextView Result = (TextView) findViewById(R.id.cgpa);
                            Result.setText("Your CGPA is : " + str);
                        }
                    }
                }
        );
    }
}