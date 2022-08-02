package com.example.gpacalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity5 extends AppCompatActivity {
    public FirebaseAuth auth;
    public EditText mail;
    public EditText password;
    public EditText name;
    public String name1;
    public ProgressBar prog;
    public TextView exist1;
    public TextView forgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        mail=(EditText)findViewById(R.id.facname);
        password=(EditText)findViewById(R.id.password);
        name=(EditText)findViewById(R.id.studentName);
        auth=FirebaseAuth.getInstance();
        prog=findViewById(R.id.progressBar);
        prog.setVisibility(View.INVISIBLE);
        exist1=findViewById(R.id.exist);
        TextView forgot=findViewById(R.id.forgot);

        Button logIn=(Button)findViewById(R.id.register1);
        logIn.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        String mailId=String.valueOf(mail.getText()).trim();
                        String USN1=String.valueOf(password.getText()).trim();
                        name1=String.valueOf(name.getText());
                        prog.setVisibility(View.VISIBLE);

                        Toast.makeText(getApplicationContext(),"Hello "+name1,Toast.LENGTH_SHORT).show();
                        loginUser(mailId,USN1);
                    }
                }
        );

        forgot.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText mailres=new EditText(v.getContext());
                        AlertDialog.Builder passwordReset=new AlertDialog.Builder(v.getContext());
                        passwordReset.setTitle("Reset password");
                        passwordReset.setMessage("enter the mail id");
                        passwordReset.setView(mailres);
                        passwordReset.setPositiveButton("Yes",new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String mail=mailres.getText().toString().trim();
                                auth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(getApplicationContext(),"reset Link sent to your mail",Toast.LENGTH_LONG).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getApplicationContext(),"Some error has occurred Try later",Toast.LENGTH_LONG).show();
                                    }
                                });
                            }


                        });
                        passwordReset.setNegativeButton("No",null)
                                .show();
                    }
                }
        );








    }
    public void loginUser(String mail,String password1){
        auth.signInWithEmailAndPassword(mail,password1).addOnSuccessListener(new OnSuccessListener<AuthResult>() {

            @Override
            public void onSuccess(AuthResult authResult) {


                Intent i=new Intent(MainActivity5.this,MainActivity4.class);
                i.putExtra("name",name1);
                prog.setVisibility(View.INVISIBLE);
                startActivity(i);


            }

        }).addOnFailureListener(
                new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        prog.setVisibility(View.INVISIBLE);
                        Log.e("LoginActivity", "Failed Registration", e);
                        exist1.setText("Email Id or Password does not exist");
                    }
                }
        );

    }









}
