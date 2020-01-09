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
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class Signup extends AppCompatActivity {
    FirebaseAuth mAuth;
    Button clogin;
    TextView textView10;
    //Button btnotp;
    EditText etname;
    EditText etage;
    EditText etpn;
    EditText etemail;
    EditText etpassword;
    //EditText etotp;
    Button btnsignup;

    DatabaseReference databaseUser;
    //String verification_code;
    //int flag;
    //PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;


    //override onstart method if already signed in user
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        clogin=findViewById(R.id.clogin);
        //btnotp=findViewById(R.id.btnotp);
        //etotp=findViewById(R.id.etotp);
        etname=findViewById(R.id.etname);
        etage=findViewById(R.id.etage);
        etpn=findViewById(R.id.etpn);
        etemail=findViewById(R.id.etemail);
        btnsignup=findViewById(R.id.btnsignup);
        etpassword=findViewById(R.id.etpassword);
        mAuth=FirebaseAuth.getInstance();
        //textView10=findViewById(R.id.textView10); //


        databaseUser= FirebaseDatabase.getInstance().getReference("User");
        FirebaseApp.initializeApp(this);

        clogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Signup.this,com.example.e_fir.Login.class);
                startActivity(intent);
            }
        });

       // mCallback=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            //@Override
            //public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            //}

            //@Override
            //public void onVerificationFailed(FirebaseException e) {

            //}
            //public void onCodeSent(String s,PhoneAuthProvider.ForceResendingToken forceResendingToken){
              //  super.onCodeSent(s, forceResendingToken);
                //verification_code=s.trim();
                //Toast.makeText(getApplicationContext(),"code sent to the number",Toast.LENGTH_LONG).show();
                //textView10.setText(verification_code); //remove after testing
            //}
        //};






    }
    //public void verify(View v){
        //String input_code=etotp.getText().toString().trim();
        //verifyPhoneNumber(verification_code,input_code);
    //}

    //public void verifyPhoneNumber(String verifyCode,String input_code){
        //PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verifyCode,input_code);
        //signInWithPhone(credential);

    //}

    //public void signInWithPhone(PhoneAuthCredential credential){
        //mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            //@Override
            //public void onComplete(@NonNull Task<AuthResult> task) {
                //if(task.isSuccessful()){
                    //Toast.makeText(getApplicationContext(),"user signed in successfully",Toast.LENGTH_LONG).show();
                //}
            //}
        //});
    //}
    //either login with emAIL AND PASSWORD(PHONE NO NO USE) OR CREATE CUSTOM TOKEN
    //ALSO CHECK FOR LINKING IN LOGIN AND REALTIME DATABASE

    //public void send_sms(View v){
      //  String number=etpn.getText().toString().trim();
        //PhoneAuthProvider.getInstance().verifyPhoneNumber(number,60, TimeUnit.SECONDS,this,mCallback);
    //}

    public void addUser(){
        FirebaseUser use =FirebaseAuth.getInstance().getCurrentUser();
        String name=etname.getText().toString().trim();
        String phno=etpn.getText().toString().trim();
        String age=etage.getText().toString().trim();
        String email=etemail.getText().toString().trim();
        String password=etpassword.getText().toString().trim();
        if(!TextUtils.isEmpty(name)&& !TextUtils.isEmpty(phno) && !TextUtils.isEmpty(age)&&!TextUtils.isEmpty(email) && ! TextUtils.isEmpty(password)){
            DatabaseReference users=databaseUser;
            users.push();
            User user1;


            user1=new User(use.getUid(),name,age,phno,email,password);
            databaseUser.child(use.getUid()).setValue(user1); //here was the problem!!!!
            Toast.makeText(this,"User added",Toast.LENGTH_LONG).show();



        }
        else{
            Toast.makeText(this,"complete all fields",Toast.LENGTH_LONG).show();
        }



    }
    public void verify(View v){
        //if("1234".equals(etotp.getText().toString().trim())){
            if(!TextUtils.isEmpty(etemail.getText().toString())&&!TextUtils.isEmpty(etpassword.getText().toString())){
                mAuth.createUserWithEmailAndPassword(etemail.getText().toString().trim(), etpassword.getText().toString().trim())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("TAG", "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    addUser();
                                    Intent intent=new Intent(Signup.this,Choice.class);
                                    startActivity(intent);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("TAG", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(Signup.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }

                                // ...
                            }
                        });

            }
            else{
                Toast.makeText(Signup.this, "Empty Fields", Toast.LENGTH_SHORT).show();
            }


        //}
        //else{
          //  Toast.makeText(Signup.this,"Incorrect otp entered",Toast.LENGTH_LONG).show();
        //}

    }
}
