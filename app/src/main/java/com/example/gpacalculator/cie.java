package com.example.gpacalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

public class cie extends AppCompatActivity {
    private int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cie);

        Bundle bundle=getIntent().getExtras();
        String name=bundle.getString("name");
        String branch=bundle.getString("branch");
        String sem=bundle.getString("sem");
        String section= bundle.getString("section");
        String subject= bundle.getString("subject");
        String exam= bundle.getString("exam");
        EditText myMarks=(EditText)findViewById(R.id.marks);



        Button submit=(Button)findViewById(R.id.submit);
        submit.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        new AlertDialog.Builder(cie.this)
                                .setTitle("Upload")
                                .setIcon(android.R.drawable.ic_menu_add)
                                .setMessage("Are you sure you want to upload marks?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String marks=String.valueOf(myMarks.getText());
                                        FirebaseDatabase.getInstance().getReference().child(branch).child(sem).child(subject).child(section).push().setValue(name+" : "+exam+" : "+marks);
                                        Toast.makeText(getApplicationContext(),"marks submitted",Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setNegativeButton("No",null)
                                .show();


                    }
                }
        );

    }
}