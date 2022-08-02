package com.example.gpacalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;

public class semEntry1 extends AppCompatActivity {

    public EditText company;
    public EditText offon;
    public EditText CTC;
    public EditText intern;
    public EditText comp1;
    public EditText comp2;
    public EditText note1;
    public EditText totbac1;
    public EditText allbac;

    //Strings:
    public String branch;
    public String name;
    public String usn;
    public String place;
    public String back1;
    public String nextSem1;
    public String cum;
    public String subO;
    public String subT;
    public String prevBack;
    public String semester1;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem_entry1);

        Bundle bundle=getIntent().getExtras();
        branch=bundle.getString("branch");
        name=bundle.getString("name1");
        usn=bundle.getString("USN");
        place=bundle.getString("place");
        nextSem1=bundle.getString("nextSem1");
        cum=bundle.getString("cum");
        subO=bundle.getString("subO");
        subT=bundle.getString("subT");
        prevBack=bundle.getString("prevBack");
        semester1=bundle.getString("semesterE");
        back1=bundle.getString("back1");

        company=(EditText)findViewById(R.id.placedIn);
        offon=(EditText)findViewById(R.id.offon);
        CTC=(EditText)findViewById(R.id.CTC);
        intern=(EditText)findViewById(R.id.intern);
        comp1=(EditText)findViewById(R.id.comp1);
        comp2=(EditText)findViewById(R.id.comp2);
        note1=(EditText)findViewById(R.id.note);
        totbac1=(EditText)findViewById(R.id.totBac);
        allbac=(EditText)findViewById(R.id.allBac);

        Button submitData=(Button)findViewById(R.id.button7);

        submitData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String compname=company.getText().toString();
                        String offOn=offon.getText().toString();
                        String ctc=CTC.getText().toString();
                        String internshhip=intern.getText().toString();
                        String compO=comp1.getText().toString();
                        String compT=comp2.getText().toString();
                        String Note=note1.getText().toString();
                        String totbac=totbac1.getText().toString();
                        String allbak=allbac.getText().toString();

                        String store=usn+" : "+name;
                        FirebaseDatabase.getInstance().getReference().child("batch details").child(store).child(semester1).push().setValue("Name: "+name);
                        FirebaseDatabase.getInstance().getReference().child("batch details").child(store).child(semester1).push().setValue("BackLogs in prev SEM: "+prevBack);
                        FirebaseDatabase.getInstance().getReference().child("batch details").child(store).child(semester1).push().setValue("cumulative CGPA till this sem: "+cum);
                        FirebaseDatabase.getInstance().getReference().child("batch details").child(store).child(semester1).push().setValue("Placement Training: "+place);
                        FirebaseDatabase.getInstance().getReference().child("batch details").child(store).child(semester1).push().setValue("Subjects in BackLog this sem: "+back1);
                        FirebaseDatabase.getInstance().getReference().child("batch details").child(store).child(semester1).push().setValue("Subject 1 in backLog: "+subO);
                        FirebaseDatabase.getInstance().getReference().child("batch details").child(store).child(semester1).push().setValue("Subject 2 in backLog: "+subT);
                        FirebaseDatabase.getInstance().getReference().child("batch details").child(store).child(semester1).push().setValue("Done internship in: "+internshhip);
                        FirebaseDatabase.getInstance().getReference().child("batch details").child(store).child(semester1).push().setValue("Company 1 internship: "+compO);
                        FirebaseDatabase.getInstance().getReference().child("batch details").child(store).child(semester1).push().setValue("Company 1 internship: "+compT);
                        FirebaseDatabase.getInstance().getReference().child("batch details").child(store).child(semester1).push().setValue("Placed in: "+compname);
                        FirebaseDatabase.getInstance().getReference().child("batch details").child(store).child(semester1).push().setValue("package(in CTC): "+ctc);
                        FirebaseDatabase.getInstance().getReference().child("batch details").child(store).child(semester1).push().setValue("off/on campus: "+offOn);
                        FirebaseDatabase.getInstance().getReference().child("batch details").child(store).child(semester1).push().setValue("Total back logs till this sem: "+totbac);
                        FirebaseDatabase.getInstance().getReference().child("batch details").child(store).child(semester1).push().setValue("Are all backlogs cleared till this sem: "+allbak);
                        FirebaseDatabase.getInstance().getReference().child("batch details").child(store).child(semester1).push().setValue("IMPORTANT NOTE: "+Note);


                    }
                }
        );









    }
}