package com.example.e_fir;

import android.app.DatePickerDialog;
import java.util.Calendar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class File_a_complaint extends AppCompatActivity {

    Button btnSubmit;
    Button btndate;
    EditText etcomplaint;
    Spinner stype;
    TextView tvdateofincident;
    DatabaseReference databaseComplaints;


    private DatePickerDialog.OnDateSetListener mDateSetListener;
    int date;
    int smonth;
    int syear;
    String email;
    String name;
    TextView tv15;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_a_complaint);
        databaseComplaints= FirebaseDatabase.getInstance().getReference("Complaints");
        tv15=findViewById(R.id.tv15);

        btndate=findViewById(R.id.btndate);
        btnSubmit=findViewById(R.id.btnSubmit);
        etcomplaint=findViewById(R.id.etcomplaint);
        etcomplaint.setMovementMethod(new ScrollingMovementMethod());
        stype=findViewById(R.id.stype);
        tvdateofincident=findViewById(R.id.tvdateofincident);
        btndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month =cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog=new DatePickerDialog(File_a_complaint.this,android.R.style.Theme_Holo
                        ,mDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                dialog.show();
            }
        });
        mDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                String s=dayOfMonth+"/"+month+"/"+year;
                date=dayOfMonth;
                smonth=month;
                syear=year;
                tvdateofincident.setText(s.trim());
            }
        };

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String user_uid=user.getUid();//1
        DatabaseReference user_info=FirebaseDatabase.getInstance().getReference("User").child(user_uid);
        DatabaseReference user_name=user_info.child("name");
        user_name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 name=dataSnapshot.getValue().toString();
                //tv15.setText(name);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        DatabaseReference user_email=user_info.child("email");
        user_email.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 email=dataSnapshot.getValue().toString();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addComplaint();
            }
        });


    }
    private void addComplaint(){


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //if(email==null || name==null){
            //tv15.setText("1");
        //}
       // else{
            //tv15.setText("2");
        //}


        String types=stype.getSelectedItem().toString();
        String des=etcomplaint.getText().toString();


        if(!TextUtils.isEmpty(des)){
            DatabaseReference complaints=databaseComplaints.child(user.getUid());

            //String id=complaints.getKey();
            Complaints complaints1=new Complaints(email, des,types, date, smonth, syear, name, user.getUid());//solve this!!!!!
            complaints.push().setValue(complaints1);
            //databaseComplaints.child(id).setValue(complaints1);
            Toast.makeText(this,"Complaint added",Toast.LENGTH_LONG).show();
            //
            Intent intent=new Intent(File_a_complaint.this,com.example.e_fir.Choice.class);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(this,"complete all fields",Toast.LENGTH_LONG).show();
        }

    }
}
