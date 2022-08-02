package com.example.gpacalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;

public class display extends AppCompatActivity {
    public StorageReference reference;
    public ImageView img1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Bundle bundle=getIntent().getExtras();
        String name=bundle.getString("studName");
        String branch=bundle.getString("branch");
        String sem=bundle.getString("sem");

        String disp=branch+"/"+sem+"/"+name;


        reference= FirebaseStorage.getInstance().getReference().child(disp);
        try{
            final File localFile=File.createTempFile("stud","image");
            reference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    img1=findViewById(R.id.studImg1);
                    img1.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull  Exception e) {
                    Toast.makeText(getApplication(),"error",Toast.LENGTH_LONG).show();
                }
            });
        }catch(Exception e){

        }

        ListView myList1=(ListView)findViewById(R.id.myList1);
        ArrayList<String> myList=new ArrayList<>();
        ArrayAdapter adapter1=new ArrayAdapter(this, android.R.layout.simple_list_item_1,myList);
        myList1.setAdapter(adapter1);
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("studentProc").child(branch).child(sem).child(name);
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
}