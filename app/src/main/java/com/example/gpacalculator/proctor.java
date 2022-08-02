package com.example.gpacalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class proctor extends AppCompatActivity {
    public ListView studList;
    public ArrayAdapter adap;
    public ArrayList<String> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proctor);

        Bundle bundle=getIntent().getExtras();
        String name=bundle.getString("name");
        String branch=bundle.getString("branch");
        String sem=bundle.getString("sem");

        EditText studentName=(EditText)findViewById(R.id.sn);
        Button addButton=(Button)findViewById(R.id.addStud);
        studList=(ListView)findViewById(R.id.studentList1);
        arrayList=new ArrayList<>();

        adap=new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
        studList.setAdapter(adap);

        addButton.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        String stud=String.valueOf(studentName.getText());
                        FirebaseDatabase.getInstance().getReference().child("proctor").child(name).child(branch).child(sem).child(stud).setValue(stud);
                        arrayList.add(stud);
                        adap.notifyDataSetChanged();
                    }
                }
        );
        studList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String nameStud=String.valueOf(studList.getItemAtPosition(position));
                        Intent i=new Intent(proctor.this,display.class);
                        i.putExtra("branch",branch);
                        i.putExtra("sem",sem);
                        i.putExtra("studName",nameStud);
                        startActivity(i);

                    }
                }
        );

        studList.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener(){
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
                        final int whichItem=position;
                        new AlertDialog.Builder(proctor.this)
                                .setTitle("Delete")
                                .setIcon(android.R.drawable.ic_delete)
                                .setMessage("Are you sure you want to remove this Student?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String stud=String.valueOf(studList.getItemAtPosition(whichItem));
                                        FirebaseDatabase.getInstance().getReference().child("proctor").child(name).child(branch).child(sem).child(stud).setValue(null);
                                        arrayList.remove(whichItem);
                                        adap.notifyDataSetChanged();
                                    }
                                })
                                .setNegativeButton("No",null)
                                .show();

                        return true;
                    }
                }
        );
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("proctor").child(name).child(branch).child(sem);
        reference.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                        arrayList.clear();
                        for(DataSnapshot snapshot:datasnapshot.getChildren()){
                            arrayList.add(snapshot.getValue().toString());
                        }
                        adap.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }
        );




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.my_add,menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
//        ConstraintLayout myLayout=(ConstraintLayout)findViewById(R.id.layout);
        switch(item.getItemId()) {
            case R.id.add:
                arrayList.clear();
                adap.notifyDataSetChanged();
        }

        return true;
    }

}