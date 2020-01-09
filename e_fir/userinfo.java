package com.example.e_fir;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class userinfo extends AppCompatActivity {
    String email;
    String name;
    String phno;
    String age;
    TextView uiname,uiphno,uiage,uiemailid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        uiname=findViewById(R.id.uiname);
        uiage=findViewById(R.id.uiage);
        uiphno=findViewById(R.id.uiphno);
        uiemailid=findViewById(R.id.uiemailid);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String user_uid=user.getUid();//1
        DatabaseReference user_info= FirebaseDatabase.getInstance().getReference("User").child(user_uid);
        DatabaseReference user_name=user_info.child("name");
        user_name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name=dataSnapshot.getValue().toString();
                uiname.setText(name);
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
                uiemailid.setText(email);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        DatabaseReference user_phno=user_info.child("phno");
        user_phno.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                phno=dataSnapshot.getValue().toString();
                uiphno.setText(phno);

                //tv15.setText(name);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        DatabaseReference user_age=user_info.child("age");
        user_age.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                age=dataSnapshot.getValue().toString();
                uiage.setText(age);
                //tv15.setText(name);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }
}
