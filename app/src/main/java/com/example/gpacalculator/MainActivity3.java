package com.example.gpacalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

public class MainActivity3 extends AppCompatActivity {
    private spinnerFunc total=new spinnerFunc();
    public TextView gpa;
    private int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        Bundle bundle=getIntent().getExtras();
        String name=bundle.getString("name");
        String branch=bundle.getString("branch");
        String sem=bundle.getString("sem");
        String section= bundle.getString("section");
        String subject= bundle.getString("subject");
        String exam= bundle.getString("exam");









        Spinner mySpinner = (Spinner) findViewById(R.id.spinner8);
        Spinner mySpinner1 = (Spinner) findViewById(R.id.spinner21);
        Spinner mySpinner2 = (Spinner) findViewById(R.id.spinner10);
        Spinner mySpinner3 = (Spinner) findViewById(R.id.spinner11);
        Spinner mySpinner4 = (Spinner) findViewById(R.id.spinner12);
        Spinner mySpinner5 = (Spinner) findViewById(R.id.spinner13);
        Spinner mySpinner6 = (Spinner) findViewById(R.id.spinner14);
        Spinner mySpinner7 = (Spinner) findViewById(R.id.spinner15);
        Spinner mySpinner8 = (Spinner) findViewById(R.id.spinner16);
        Spinner mySpinner9 = (Spinner) findViewById(R.id.spinner17);
        Spinner mySpinner10 = (Spinner) findViewById(R.id.spinner23);
        Spinner mySpinner11 = (Spinner) findViewById(R.id.spinner24);
        Spinner mySpinner12 = (Spinner) findViewById(R.id.spinner18);
        Spinner mySpinner13 = (Spinner) findViewById(R.id.spinner22);
        Spinner mySpinner14 = (Spinner) findViewById(R.id.spinner20);
        Spinner mySpinner15 = (Spinner) findViewById(R.id.spinner19);


        TextView myText  = (TextView) findViewById(R.id.textView29);
        TextView myText1 = (TextView) findViewById(R.id.textView31);
        TextView myText2 = (TextView) findViewById(R.id.textView33);
        TextView myText3 = (TextView) findViewById(R.id.textView35);
        TextView myText4 = (TextView) findViewById(R.id.textView37);
        TextView myText5 = (TextView) findViewById(R.id.textView39);
        TextView myText6 = (TextView) findViewById(R.id.textView41);
        TextView myText7 = (TextView) findViewById(R.id.textView43);

        String str= (String) myText.getText();


        mySpinner1.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener(){
                    @Override
                    public void onItemSelected(AdapterView<?> parent , View view, int position, long id){
                        int t = total.getTotal(mySpinner, mySpinner1);
                        String str = String.valueOf(t);
                        myText.setText(str);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent){

                    }
                }
        );
        mySpinner.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener(){
                    @Override
                    public void onItemSelected(AdapterView<?> parent ,View view,int position,long id){
                        int t = total.getTotal(mySpinner, mySpinner1);
                        String str = String.valueOf(t);
                        myText.setText(str);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent){

                    }
                }
        );
        mySpinner2.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener(){
                    @Override
                    public void onItemSelected(AdapterView<?> parent ,View view,int position,long id){
                        int t = total.getTotal(mySpinner2, mySpinner10);
                        String str = String.valueOf(t);
                        myText1.setText(str);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent){

                    }
                }
        );
        mySpinner10.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener(){
                    @Override
                    public void onItemSelected(AdapterView<?> parent ,View view,int position,long id){
                        int t = total.getTotal(mySpinner2, mySpinner10);
                        String str = String.valueOf(t);
                        myText1.setText(str);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent){

                    }
                }
        );
        mySpinner3.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener(){
                    @Override
                    public void onItemSelected(AdapterView<?> parent ,View view,int position,long id){
                        int t = total.getTotal(mySpinner3, mySpinner11);
                        String str = String.valueOf(t);
                        myText2.setText(str);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent){

                    }
                }
        );
        mySpinner11.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener(){
                    @Override
                    public void onItemSelected(AdapterView<?> parent ,View view,int position,long id){
                        int t = total.getTotal(mySpinner3, mySpinner11);
                        String str = String.valueOf(t);
                        myText2.setText(str);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent){

                    }
                }
        );
        mySpinner4.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener(){
                    @Override
                    public void onItemSelected(AdapterView<?> parent ,View view,int position,long id){
                        int t = total.getTotal(mySpinner4, mySpinner9);
                        String str = String.valueOf(t);
                        myText3.setText(str);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent){

                    }
                }
        );
        mySpinner9.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener(){
                    @Override
                    public void onItemSelected(AdapterView<?> parent ,View view,int position,long id){
                        int t = total.getTotal(mySpinner4, mySpinner9);
                        String str = String.valueOf(t);
                        myText3.setText(str);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent){

                    }
                }
        );
        mySpinner5.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener(){
                    @Override
                    public void onItemSelected(AdapterView<?> parent ,View view,int position,long id){
                        int t = total.getTotal(mySpinner5, mySpinner12);
                        String str = String.valueOf(t);
                        myText4.setText(str);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent){

                    }
                }
        );
        mySpinner12.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener(){
                    @Override
                    public void onItemSelected(AdapterView<?> parent ,View view,int position,long id){
                        int t = total.getTotal(mySpinner5, mySpinner12);
                        String str = String.valueOf(t);
                        myText4.setText(str);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent){

                    }
                }
        );
        mySpinner6.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener(){
                    @Override
                    public void onItemSelected(AdapterView<?> parent ,View view,int position,long id){
                        int t = total.getTotal(mySpinner6, mySpinner13);
                        String str = String.valueOf(t);
                        myText5.setText(str);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent){

                    }
                }
        );
        mySpinner13.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener(){
                    @Override
                    public void onItemSelected(AdapterView<?> parent ,View view,int position,long id){
                        int t = total.getTotal(mySpinner6, mySpinner13);
                        String str = String.valueOf(t);
                        myText5.setText(str);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent){

                    }
                }
        );
        mySpinner7.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener(){
                    @Override
                    public void onItemSelected(AdapterView<?> parent ,View view,int position,long id){
                        int t = total.getTotal(mySpinner7, mySpinner14);
                        String str = String.valueOf(t);
                        myText6.setText(str);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent){

                    }
                }
        );
        mySpinner14.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener(){
                    @Override
                    public void onItemSelected(AdapterView<?> parent ,View view,int position,long id){
                        int t = total.getTotal(mySpinner7, mySpinner14);
                        String str = String.valueOf(t);
                        myText6.setText(str);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent){

                    }
                }
        );
        mySpinner14.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener(){
                    @Override
                    public void onItemSelected(AdapterView<?> parent ,View view,int position,long id){
                        int t = total.getTotal(mySpinner7, mySpinner14);
                        String str = String.valueOf(t);
                        myText6.setText(str);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent){

                    }
                }
        );
        mySpinner8.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener(){
                    @Override
                    public void onItemSelected(AdapterView<?> parent ,View view,int position,long id){
                        int t = total.getTotal(mySpinner8, mySpinner15);
                        String str = String.valueOf(t);
                        myText7.setText(str);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent){

                    }
                }
        );
        mySpinner15.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener(){
                    @Override
                    public void onItemSelected(AdapterView<?> parent ,View view,int position,long id){
                        int t = total.getTotal(mySpinner8, mySpinner15);
                        String str = String.valueOf(t);
                        myText7.setText(str);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent){

                    }
                }
        );
        Button Calculate=(Button)findViewById(R.id.button);
        Calculate.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        new AlertDialog.Builder(MainActivity3.this)
                                .setTitle("Submit ?")
                                .setIcon(android.R.drawable.ic_menu_add)
                                .setMessage("Are you sure you want to upload SGPA?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        int i=Integer.parseInt(String.valueOf(myText.getText()));
                                        int i1=Integer.parseInt(String.valueOf(myText1.getText()));
                                        int i2=Integer.parseInt(String.valueOf(myText2.getText()));
                                        int i3=Integer.parseInt(String.valueOf(myText3.getText()));
                                        int i4=Integer.parseInt(String.valueOf(myText4.getText()));
                                        int i5=Integer.parseInt(String.valueOf(myText5.getText()));
                                        int i6=Integer.parseInt(String.valueOf(myText6.getText()));
                                        int i7=Integer.parseInt(String.valueOf(myText7.getText()));

                                        float sum=(float)i+i1+i2+i3+i4+i5+i6+i7;

                                        int j=Integer.parseInt(String.valueOf(mySpinner.getSelectedItem()));
                                        int j1=Integer.parseInt(String.valueOf(mySpinner2.getSelectedItem()));
                                        int j2=Integer.parseInt(String.valueOf(mySpinner3.getSelectedItem()));
                                        int j3=Integer.parseInt(String.valueOf(mySpinner4.getSelectedItem()));
                                        int j4=Integer.parseInt(String.valueOf(mySpinner5.getSelectedItem()));
                                        int j5=Integer.parseInt(String.valueOf(mySpinner6.getSelectedItem()));
                                        int j6=Integer.parseInt(String.valueOf(mySpinner7.getSelectedItem()));
                                        int j7=Integer.parseInt(String.valueOf(mySpinner8.getSelectedItem()));

                                        float grades=(float)j+j1+j2+j3+j4+j5+j6+j7;

                                        float sgpa=(float)(sum/grades);
                                        gpa=(TextView)findViewById(R.id.grade);

                                        String str11=Float.toString(sgpa);

                                        FirebaseDatabase.getInstance().getReference().child(branch).child(sem).child(subject).child(section).push().setValue(name+" : "+exam+" : "+str11);


                                        gpa.setText(str11);
                                        Toast.makeText(getApplicationContext(),"SGPA submitted and displayed",Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setNegativeButton("No",null)
                                .show();







                    }
                }
        );
        Button resetButton=(Button)findViewById(R.id.button2);
        resetButton.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick( View view){
                        myText.setText("0");
                        myText1.setText("0");
                        myText2.setText("0");
                        myText3.setText("0");
                        myText4.setText("0");
                        myText5.setText("0");
                        myText6.setText("0");
                        myText7.setText("0");

                        mySpinner.setSelection(0);
                        mySpinner1.setSelection(0);
                        mySpinner2.setSelection(0);
                        mySpinner3.setSelection(0);
                        mySpinner4.setSelection(0);
                        mySpinner5.setSelection(0);
                        mySpinner6.setSelection(0);
                        mySpinner7.setSelection(0);
                        mySpinner8.setSelection(0);
                        mySpinner9.setSelection(0);
                        mySpinner10.setSelection(0);
                        mySpinner11.setSelection(0);
                        mySpinner12.setSelection(0);
                        mySpinner13.setSelection(0);
                        mySpinner14.setSelection(0);

                        gpa.setText(" ");
                    }
                }
        );


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        ConstraintLayout myLayout=(ConstraintLayout)findViewById(R.id.layout);
        switch(item.getItemId()){
            case R.id.cgpa_menu:
                Intent i=new Intent(MainActivity3.this,MainActivity2.class);
                startActivity(i);


        }
        return true;
    }
    }
