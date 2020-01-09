package com.example.e_fir;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;

public class Login extends AppCompatActivity {
    Button btnlogin;
    Button csignup;
    TextView tvcurrentdate;
    EditText etlemail;
    EditText etlpassword;
    private FirebaseAuth mAuth;
    @Override
    public void onStart() {  ///edit this!!!
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            Intent intent=new Intent(Login.this,com.example.e_fir.Choice.class);
            startActivity(intent);
            //
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tvcurrentdate=findViewById(R.id.tvcurrentdate);
        mAuth = FirebaseAuth.getInstance();
        etlemail=findViewById(R.id.etlemail);
        etlpassword=findViewById(R.id.etlpassword);
        btnlogin=findViewById(R.id.btnlogin);
        csignup=findViewById(R.id.csignup);
        Thread t=new Thread(){
            @Override
            public void run(){
                try{
                    while(!isInterrupted()){
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvcurrentdate=findViewById(R.id.tvcurrentdate);
                                long date=System.currentTimeMillis();
                                SimpleDateFormat sdf=new SimpleDateFormat("MMM dd yyyy\nhh mm ss a");
                                String dateString =sdf.format(date);
                                tvcurrentdate.setText(dateString);


                            }
                        });
                    }
                } catch (InterruptedException e){}
            }
        };
        t.start();
        csignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,com.example.e_fir.Signup.class);
                startActivity(intent);
                //
                finish();
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(etlemail.getText().toString())&&!TextUtils.isEmpty(etlpassword.getText().toString())){
                    mAuth.signInWithEmailAndPassword(etlemail.getText().toString().trim(), etlpassword.getText().toString().trim())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("TAG", "createUserWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Intent intent=new Intent(Login.this,Choice.class);
                                        startActivity(intent);
                                        //
                                        finish();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("TAG", "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(Login.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }

                                    // ...
                                }
                            });
                }
                else{
                    Toast.makeText(Login.this, "Empty Fields", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}
