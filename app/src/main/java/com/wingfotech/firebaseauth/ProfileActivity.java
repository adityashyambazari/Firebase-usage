package com.wingfotech.firebaseauth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    FirebaseAuth firebaseAuth;
    TextView textViewUserEmail;
    Button logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        textViewUserEmail = (TextView)findViewById(R.id.editText3);
        logOut = (Button)findViewById(R.id.button3);
        logOut.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()==null)
        {
            finish();
            Intent i = new Intent(this,LoginActivity.class);
            startActivity(i);
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();
        textViewUserEmail.setText("welcome " + user.getEmail());
    }

    @Override
    public void onClick(View v) {

        if(v==logOut)
        {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }

    }
}
