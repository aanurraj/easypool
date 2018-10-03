package com.example.anubhav.easypool;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class signup extends AppCompatActivity implements View.OnClickListener {

    EditText em, pass;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        em = (EditText) findViewById(R.id.em);
        pass = (EditText) findViewById(R.id.pass);

        mAuth = FirebaseAuth.getInstance();


        findViewById(R.id.signup).setOnClickListener(this);
    }

    private void registerUser(){

        String email  = em.getText().toString().trim();
        String password  = pass.getText().toString().trim();

        if(email.isEmpty()){
            em.setError("Email is Required !");
            em.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            em.setError("Enter a valid Email !");
            em.requestFocus();
            return;
        }

        if(password.isEmpty()){
            pass.setError("Password is Required !");
            pass.requestFocus();
            return;
        }

        if(password.length()<6){
            pass.setError("Password is small !");
            pass.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"user Registered Su cessfully", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.signup:
                registerUser();

            case R.id.login:

                startActivity(new Intent(this, MainActivity.class));

                break;

        }

    }
}