package com.example.gpacalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class batchDisp extends AppCompatActivity {
    public ArrayAdapter adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch_disp);


        Bundle bundle=getIntent().getExtras();
        String branch=bundle.getString("branch");
        String name=bundle.getString("name");
        String year=bundle.getString("year");

        ListView bathDisp=(ListView)findViewById(R.id.batchStud);
        ArrayList<String> myList=new ArrayList<>();

        bathDisp.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String store=bathDisp.getItemAtPosition(position).toString();
                        Intent i=new Intent(batchDisp.this,batchDispFull.class);
                        i.putExtra("name4",store);
                        startActivity(i);
                    }
                }
        );

        adapter1=new ArrayAdapter(this, android.R.layout.simple_list_item_1,myList);
        bathDisp.setAdapter(adapter1);
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("batchDetails").child(year).child(branch);
        reference.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                        myList.clear();
                        for(DataSnapshot snapshot:datasnapshot.getChildren()){
                            myList.add(snapshot.getValue().toString());
                        }
                        adapter1.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }
        );


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem=menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView)menuItem.getActionView();
        searchView.setQueryHint("Enter Student Name");

        searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {

                        adapter1.getFilter().filter(newText);
                        return false;
                    }
                }
        );
        return super.onCreateOptionsMenu(menu);
    }
}