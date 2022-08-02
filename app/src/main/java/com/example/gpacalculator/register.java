package com.example.gpacalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {
    public FirebaseAuth auth;
    public EditText mailId;
    public EditText password;
    public EditText studName;

    public ProgressBar bar;
    Spinner pasYear;
    public String passYear;
    public EditText usn;
    public Spinner branch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mailId=(EditText)findViewById(R.id.facname);
        password=(EditText)findViewById(R.id.password1);
        bar=findViewById(R.id.progress2);
        bar.setVisibility(View.INVISIBLE);
        auth=FirebaseAuth.getInstance();
        pasYear=findViewById(R.id.yearPas);

        studName=(EditText)findViewById(R.id.nameStud);

        usn=(EditText)findViewById(R.id.usn1);
        branch=(Spinner)findViewById(R.id.spinner);








        Button register=(Button)findViewById(R.id.register1);
        register.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View view){
                        bar.setVisibility(View.VISIBLE);

                        String str=String.valueOf(mailId.getText()).trim();
                        String str1=String.valueOf(password.getText()).trim();
                        if(TextUtils.isEmpty(str)||TextUtils.isEmpty(str1)){
                            Toast.makeText(register.this, "empty credentials", Toast.LENGTH_SHORT).show();
                            bar.setVisibility(View.INVISIBLE);
                        }
                        else{
                            regiterUser(str,str1);

                        }


                    }
                }
        );
    }
    private void regiterUser(String email, String pass) {
       auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    bar.setVisibility(View.INVISIBLE);
                    String year=String.valueOf(pasYear.getSelectedItem());
                    String name=String.valueOf(studName.getText());
//                    String branchh=String.valueOf(Branch.getText());
                    String branch1=branch.getSelectedItem().toString();
                    String usn1=String.valueOf(usn.getText());
                    FirebaseDatabase.getInstance().getReference().child("batchDetails").child(year).child(branch1).push().setValue(usn1+" : "+name);

                    Toast.makeText(getApplicationContext(),"regitration successful",Toast.LENGTH_SHORT).show();
                    Intent i4=new Intent(register.this,MainActivity5.class);
                    startActivity(i4);
                }
                if(!task.isSuccessful()){
                    FirebaseAuthException e = (FirebaseAuthException)task.getException();
                    Toast.makeText(register.this, "Failed Registration: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("LoginActivity", "Failed Registration", e);
                    return;
                }
//                else{
//                    Toast.makeText(getApplicationContext(),"regitration not successful",Toast.LENGTH_SHORT).show();
//                }
            }
        });


    }
// keerthi.ee20@bmsce.ac.in





    }
