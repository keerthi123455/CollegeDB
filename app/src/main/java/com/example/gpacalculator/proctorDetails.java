package com.example.gpacalculator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.InputStream;

public class proctorDetails extends AppCompatActivity {
    public String section;
    public EditText placement;
    public String name;
    public String branch;
    public String sem;
    public ImageView img;
    public Button up;
    public FloatingActionButton add1;
    public Uri filePath;
    public Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proctor_details);

        Bundle bundle=getIntent().getExtras();
        name=bundle.getString("name");
        branch=bundle.getString("branch");
        sem=bundle.getString("sem");

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
                        Dexter.withActivity(proctorDetails.this)
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






        Spinner mySection=(Spinner)findViewById(R.id.section);
        mySection.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener(){
                    @Override
                    public void onItemSelected(AdapterView<?> parent , View view, int position, long id){
                        section=String.valueOf(mySection.getSelectedItem());
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent){

                    }
                }
        );

        placement=(EditText)findViewById(R.id.placet);



        Button submitData=(Button)findViewById(R.id.submitData);
        submitData.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View view){

                        new AlertDialog.Builder(proctorDetails.this)
                                .setTitle("Upload")
                                .setIcon(android.R.drawable.ic_menu_share)
                                .setMessage("Are you sure you want to upload the data?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        EditText nextSem=(EditText)findViewById(R.id.nextSem);
                                        EditText cumGPA=(EditText)findViewById(R.id.cumGPA);
                                        EditText sub1=(EditText)findViewById(R.id.sub1);
                                        EditText sub2=(EditText)findViewById(R.id.sub2);

                                        Spinner section=(Spinner)findViewById(R.id.section);
                                        Spinner back=(Spinner)findViewById(R.id.back);

                                        String place=placement.getText().toString();
                                        String nextem=nextSem.getText().toString();
                                        String gpa=cumGPA.getText().toString();
                                        String sub=sub1.getText().toString();
                                        String suba=sub2.getText().toString();

                                        FirebaseDatabase.getInstance().getReference().child("studentProc").child(branch).child(sem).child(name).child("Placement Training:").setValue("Placement Training: "+place);
                                        FirebaseDatabase.getInstance().getReference().child("studentProc").child(branch).child(sem).child(name).child("Eligibility for next Sem:").setValue("Eligibility for next Sem: "+nextem);
                                        FirebaseDatabase.getInstance().getReference().child("studentProc").child(branch).child(sem).child(name).child("gpa").setValue("total cgpa till now: "+gpa);
                                        FirebaseDatabase.getInstance().getReference().child("studentProc").child(branch).child(sem).child(name).child("sub").setValue("subject 1 failed: "+sub);
                                        FirebaseDatabase.getInstance().getReference().child("studentProc").child(branch).child(sem).child(name).child("suba").setValue("subject 2 failed: "+suba);
                                        Toast.makeText(getApplicationContext(),"data submitted",Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setNegativeButton("No",null)
                                .show();






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
        StorageReference uploader=storage.getReference().child(branch).child(sem).child(name);
        uploader.putFile(filePath);
    }
}