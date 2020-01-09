package com.example.e_fir;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Complaint_list extends AppCompatActivity {
    ListView listViewComplaint;
    List<Complaints> complaintsList;
    DatabaseReference databaseReference;
    String user_id;
    public static final String DESCRIPTION="description";
    public static final String DAY="day";
    public static final String MONTH="month";
    public static final String YEAR="year";
    public static final String TYPE="type";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_list);
    databaseReference= FirebaseDatabase.getInstance().getReference("Complaints");
    listViewComplaint=findViewById(R.id.listViewComplaints);
    complaintsList=new ArrayList<>();
    listViewComplaint.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Complaints complaints=complaintsList.get(position);
            int day=complaints.getDate();
            int month=complaints.getMonth();
            int year=complaints.getYear();
            String sday=Integer.toString(day);
            String smonth=Integer.toString(month);
            String syear=Integer.toString(year);

            Intent intent= new Intent(Complaint_list.this,com.example.e_fir.Complaint_status.class);
            intent.putExtra(DAY,sday);
            intent.putExtra(MONTH, smonth);
            intent.putExtra(YEAR,syear);
            intent.putExtra(TYPE,complaints.getType());
            intent.putExtra(DESCRIPTION,complaints.getDescription());
            startActivity(intent);

        }
    });


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        user_id=user.getUid().trim();
        DatabaseReference compref=databaseReference.child(user_id);
        compref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                complaintsList.clear();
                for(DataSnapshot complaintSnapshot:dataSnapshot.getChildren()){
                    Complaints complaints=complaintSnapshot.getValue(Complaints.class);
                    complaintsList.add(complaints);
                }
                Complaint_list_class adapter =new Complaint_list_class(Complaint_list.this,complaintsList);
                listViewComplaint.setAdapter(adapter );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
