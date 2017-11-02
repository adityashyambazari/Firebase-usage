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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{


    Button loginButton;
    EditText passwordText, emailText;
    TextView signUp;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton = (Button)findViewById(R.id.button2);
        emailText = (EditText) findViewById(R.id.editText);
        passwordText = (EditText)findViewById(R.id.editText2);
        signUp = (TextView)findViewById(R.id.textView2);

        if(firebaseAuth.getCurrentUser()!=null)
        {
            //profile activity here
            finish();
            Intent i = new Intent(getApplicationContext(),ProfileActivity.class);
            startActivity(i);
        }

        firebaseAuth = FirebaseAuth.getInstance();
        loginButton.setOnClickListener(this);
        signUp.setOnClickListener(this);

    }

    public void userLogin()
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

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    //start profile activity
                    finish();
                    Intent i = new Intent(getApplicationContext(),ProfileActivity.class);
                    startActivity(i);
                }

            }
        });
    }

    @Override
    public void onClick(View v) {

        if(v == loginButton)
        {
            userLogin();
        }
        if(v==signUp)
        {
            finish();
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
        }
    }
}
