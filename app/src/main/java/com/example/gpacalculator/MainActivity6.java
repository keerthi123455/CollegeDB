package com.example.gpacalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity6 extends AppCompatActivity {
    public String name;
    public FirebaseAuth auth;
    EditText key;
    EditText Name;
    EditText mailId;
    EditText password;
    public ProgressBar prog;
    TextView dispFail;
    TextView forgotPas1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        auth=FirebaseAuth.getInstance();

        prog=findViewById(R.id.progress3);
        prog.setVisibility(View.INVISIBLE);

        dispFail=findViewById(R.id.failMes);
        forgotPas1=findViewById(R.id.forgotPas);


        forgotPas1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText mailId=new EditText(v.getContext());
                AlertDialog.Builder passwordRes=new AlertDialog.Builder(v.getContext());
                passwordRes.setTitle("Reset Password?");
                passwordRes.setMessage("Enter the mailId here:");
                passwordRes.setView(mailId);
                passwordRes.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String mail=mailId.getText().toString().trim();
                        auth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getApplicationContext(),"Email has been sent to reset password",Toast.LENGTH_LONG).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull  Exception e) {
                                Toast.makeText(getApplicationContext(),"Some Error occurred Try again later!",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                passwordRes.setNegativeButton("No",null)
                .show();

            }
        });




    }
    public void go1(View view) {
        key = (EditText) findViewById(R.id.usn1);
        Name=(EditText)findViewById(R.id.facname);
        mailId=(EditText)findViewById(R.id.emailfac);
        password=(EditText)findViewById(R.id.pass);
        String str = String.valueOf(key.getText());
        name=String.valueOf(Name.getText());

        prog.setVisibility(View.VISIBLE);

        if (str.equals("bmsce123")) {
            if (name.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please enter name", Toast.LENGTH_LONG).show();
            } else {
                String mail = String.valueOf(mailId.getText()).trim();
                String pas = String.valueOf(password.getText()).trim();

                loginUser(mail, pas);

            }
        }
        else{
            Toast.makeText(getApplicationContext(),"Invalid Key",Toast.LENGTH_LONG).show();
            prog.setVisibility(View.INVISIBLE);
        }
    }

    public void loginUser(String mail,String pas){
        auth.signInWithEmailAndPassword(mail,pas).addOnSuccessListener(new OnSuccessListener<AuthResult>() {

            @Override
            public void onSuccess(AuthResult authResult) {

                Toast.makeText(getApplicationContext(),"login successful",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(MainActivity6.this, MainActivity7.class);
                i.putExtra("name",name);
                prog.setVisibility(View.INVISIBLE);
                startActivity(i);


            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull  Exception e) {
                dispFail.setText("EmailId or password is Invalid");
                prog.setVisibility(View.INVISIBLE);
            }
        });
    }






}