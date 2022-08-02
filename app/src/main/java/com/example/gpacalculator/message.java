package com.example.gpacalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.ForkJoinPool;

public class message extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Bundle bundle=getIntent().getExtras();
        String branch=bundle.getString("branch");
        String sem=bundle.getString("sem");

        ProgressBar progmes=findViewById(R.id.progressBar2);
        progmes.setVisibility(View.INVISIBLE);

        EditText message=findViewById(R.id.message);
        FloatingActionButton mes=findViewById(R.id.sendMes);

        mes.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String mes=String.valueOf(message.getText());
                        FirebaseDatabase.getInstance().getReference().child("ann").child(branch).child(sem).push().setValue(mes).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getApplicationContext(),"Message has been sent",Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),"Something went wrong!",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
        );


    }
}