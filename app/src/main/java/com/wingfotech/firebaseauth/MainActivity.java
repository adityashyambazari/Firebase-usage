package com.wingfotech.firebaseauth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText passwordText,emailText;
    Button button;
    TextView textView;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        passwordText = (EditText) findViewById(R.id.passwordText);

        if(firebaseAuth.getCurrentUser()!=null)
        {
            //profile activity here
            finish();
            Intent i = new Intent(getApplicationContext(),ProfileActivity.class);
            startActivity(i);
        }

        firebaseAuth = FirebaseAuth.getInstance();

        emailText = (EditText) findViewById(R.id.emailText);
        button = (Button) findViewById(R.id.registerButton);
        textView = (TextView) findViewById(R.id.logInText);

        button.setOnClickListener(this);
        textView.setOnClickListener(this);


    }

    public void registerUser()
    {
        String email = emailText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"please enter email first !!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"enter password first !!",Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(MainActivity.this,"successful!!",Toast.LENGTH_SHORT).show();

                    finish();
                    Intent i = new Intent(getApplicationContext(),ProfileActivity.class);
                    startActivity(i);
                }
                else
                    Toast.makeText(MainActivity.this,"failed!!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view==button)
        {
            registerUser();
        }
        else if(view==textView)
        {
            Intent i =new Intent(MainActivity.this,LoginActivity.class);
            startActivity(i);
        }
    }
}
