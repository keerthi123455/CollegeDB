package com.example.gpacalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.widget.SearchView;

import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class information extends AppCompatActivity {
    public ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        Bundle bundle=getIntent().getExtras();
        String branch=bundle.getString("branch");
        String sem=bundle.getString("sem");
        String section=bundle.getString("section");
        String subject=bundle.getString("subject");

//        ProgressBar progBar=findViewById(R.id.progress3);



        ListView display1=(ListView)findViewById(R.id.diaplyListMarks1);
        final ArrayList<String> myList=new ArrayList<>();
        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,myList);
        display1.setAdapter(adapter);


        Button myButton=(Button)findViewById(R.id.displayButton);
        myButton.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View view){
//                        progBar.setVisibility(View.VISIBLE);
                        TextView dis=(TextView)findViewById(R.id.textView3);
                        dis.setText("Student Data :");
                        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child(branch).child(sem).child(subject).child(section);
                        reference.addValueEventListener(
                                new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot datasnapshot) {
//                                        progBar.setVisibility(View.INVISIBLE);
                                        myList.clear();
                                        for(DataSnapshot snapshot:datasnapshot.getChildren()){
                                            myList.add(snapshot.getValue().toString());
                                        }
                                        adapter.notifyDataSetChanged();

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                }
                        );


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

                        adapter.getFilter().filter(newText);
                        return false;
                    }
                }
        );
                return super.onCreateOptionsMenu(menu);
    }


















}