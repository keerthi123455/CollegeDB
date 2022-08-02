package com.example.gpacalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
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

public class batchDispFull extends AppCompatActivity {
    public StorageReference reference;
    public ArrayAdapter adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch_disp_full);

        Bundle bundle=getIntent().getExtras();
        String name=bundle.getString("name4");

        Spinner semSpin=(Spinner)findViewById(R.id.sem2);
        Button displayData=(Button)findViewById(R.id.button8);
        ImageView img1 =(ImageView)findViewById(R.id.batchStudImgDisp);

        reference= FirebaseStorage.getInstance().getReference().child(name);
        try{
            final File localFile=File.createTempFile("stud","image");
            reference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
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



        ListView bathDisp=(ListView)findViewById(R.id.batchListDisp);
        ArrayList<String> myList=new ArrayList<>();
        adapter1=new ArrayAdapter(this, android.R.layout.simple_list_item_1,myList);

        bathDisp.setAdapter(adapter1);

        displayData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String sem=semSpin.getSelectedItem().toString();
                        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("batch details").child(name).child(sem);
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
        );



    }
}