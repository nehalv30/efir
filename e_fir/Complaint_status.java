package com.example.e_fir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class Complaint_status extends AppCompatActivity {
    TextView tvdoi,tvtc,tvdescr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_status);
        tvdoi=findViewById(R.id.tvdoi);
        tvtc=findViewById(R.id.tvtc);
        tvdescr=findViewById(R.id.tvdescr);
        Intent intent=getIntent();
        String type=intent.getStringExtra(Complaint_list.TYPE);
        String day=intent.getStringExtra(Complaint_list.DAY);
        String month=intent.getStringExtra(Complaint_list.MONTH);
        String year=intent.getStringExtra(Complaint_list.YEAR);
        String description=intent.getStringExtra(Complaint_list.DESCRIPTION);
        String date=day.trim()+"/"+month.trim()+"/"+year.trim();
        tvdoi.setText(date);
        tvtc.setText(type);
        tvdescr.setText(description);
        tvdescr.setMovementMethod(new ScrollingMovementMethod());
    }
}
