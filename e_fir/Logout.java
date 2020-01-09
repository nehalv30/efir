package com.example.e_fir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Logout extends AppCompatActivity {
    private  FirebaseAuth mAuth;
    Button btnlogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        mAuth= FirebaseAuth.getInstance();
        btnlogout=findViewById(R.id.btnlogout);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Toast.makeText(Logout.this,"User Logged out",Toast.LENGTH_SHORT).show();
                finish();
                Intent intent=new Intent(Logout.this,com.example.e_fir.Login.class);
                startActivity(intent);
                //
                Intent finish=new Intent("finish activity");
                sendBroadcast(finish);

            }
        });
    }
}
