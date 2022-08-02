package com.example.gpacalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class resMes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_mes);

        Bundle bundle=getIntent().getExtras();

        String branch=bundle.getString("branch");
        String sem=bundle.getString("sem");
        ProgressBar prog=findViewById(R.id.progress3);


        ListView mesList=findViewById(R.id.meslist1);
        ArrayList<String> mesArrayList=new ArrayList<>();
        ArrayAdapter myAdap1=new ArrayAdapter(this,android.R.layout.simple_list_item_1,mesArrayList);
        mesList.setAdapter(myAdap1);

        
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("ann").child(branch).child(sem);
        reference.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                        mesArrayList.clear();
                        for(DataSnapshot snapshot:datasnapshot.getChildren()){
                            mesArrayList.add(snapshot.getValue().toString());
                        }
                        myAdap1.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }
        );




    }
}