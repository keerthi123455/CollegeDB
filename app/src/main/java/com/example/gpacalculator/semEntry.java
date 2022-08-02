package com.example.gpacalculator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.InputStream;

public class semEntry extends AppCompatActivity {
    public String branch;
    public EditText name1;
    public EditText placet1;
    public EditText nextSem;
    public EditText cumGPA;
    public EditText prevBac;
    public EditText sub1;
    public EditText sub2;
    public EditText USN1;
    public Spinner semester;
    public Spinner section12;
    public Spinner back;
    public ImageView img;
    public Button up;
    public FloatingActionButton add1;
    public Uri filePath;
    public Bitmap bitmap;

    public FloatingActionButton nextBut;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem_entry);

        Bundle bundle=getIntent().getExtras();
        branch=bundle.getString("branch");




        name1=(EditText)findViewById(R.id.name);
        placet1=(EditText)findViewById(R.id.placet);
        nextSem=(EditText)findViewById(R.id.nextSem);
        cumGPA=(EditText)findViewById(R.id.cumGPA);
        prevBac=(EditText)findViewById(R.id.prevBac);
        sub1=(EditText)findViewById(R.id.sub1);
        sub2=(EditText)findViewById(R.id.sub2);
        USN1=(EditText)findViewById(R.id.USN1);
        //SPINNERS:
        semester=(Spinner)findViewById(R.id.semester);
        section12=(Spinner)findViewById(R.id.section);
        back=(Spinner)findViewById(R.id.back);

        //button:
        nextBut=(FloatingActionButton)findViewById(R.id.nextBut);
        add1=findViewById(R.id.browse);
        img=findViewById(R.id.studImg);
        up=findViewById(R.id.upl);
        up.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        uploadToFirebase();
                    }
                }
        );
        add1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dexter.withActivity(semEntry.this)
                                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                                .withListener(new PermissionListener() {
                                    @Override
                                    public void onPermissionGranted(PermissionGrantedResponse response) {
                                        Intent i=new Intent(Intent.ACTION_PICK);
                                        i.setType("image/*");
                                        startActivityForResult(Intent.createChooser(i,"please select image"),1);

                                    }

                                    @Override
                                    public void onPermissionDenied(PermissionDeniedResponse response) {

                                    }

                                    @Override
                                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                        token.continuePermissionRequest();
                                    }
                                }).check();
                    }
                }
        );

        nextBut.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       String semesterE= semester.getSelectedItem().toString();
                       String sectionE= section12.getSelectedItem().toString();
                       String back1= back.getSelectedItem().toString();


                        String name=name1.getText().toString();
                        String place=placet1.getText().toString();
                        String nextSem1=nextSem.getText().toString();
                        String cum=cumGPA.getText().toString();
                        String prevBack=prevBac.getText().toString();
                        String subO=sub1.getText().toString();
                        String subT=sub2.getText().toString();
                        String USN=USN1.getText().toString();

                        Intent i=new Intent(semEntry.this,semEntry1.class);
                        i.putExtra("name1",name);
                        i.putExtra("place",place);
                        i.putExtra("nextSem1",nextSem1);
                        i.putExtra("cum",cum);
                        i.putExtra("prevBack",prevBack);
                        i.putExtra("subO",subO);
                        i.putExtra("subT",subT);
                        i.putExtra("semesterE",semesterE);
                        i.putExtra("sectionE",sectionE);
                        i.putExtra("back1",back1);
                        i.putExtra("USN",USN);
                        startActivity(i);

                    }
                }
        );




    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1&&resultCode==RESULT_OK){
            filePath=data.getData();
            try{
                InputStream inputStream=getContentResolver().openInputStream(filePath);
                bitmap= BitmapFactory.decodeStream(inputStream);
                img.setImageBitmap(bitmap);
            }catch(Exception e){

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void uploadToFirebase(){
        FirebaseStorage storage=FirebaseStorage.getInstance();
        String usn=USN1.getText().toString();
        String name=name1.getText().toString();

        StorageReference uploader=storage.getReference().child(usn+" : "+name);
        uploader.putFile(filePath);
    }
}