package com.example.srilekha.foodbin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import	android.app.AlertDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Srilekha on 12-04-2018.
 */


public class VolReg extends AppCompatActivity {
    private int registerflag=0;
    private static final String TAG = "VolReg";
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    DatabaseReference Userdb;



    /*public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        //FirebaseUser currentUser = mFirebaseAuth.getCurrentUser();
        mFirebaseAuth = FirebaseAuth.getInstance();
        //updateUI(currentUser);
    }*/
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mFirebaseAuth = FirebaseAuth.getInstance();

        final EditText emailEditText =  (EditText) findViewById(R.id.username);
        final EditText passwordEditText =  (EditText) findViewById(R.id.password);
        final EditText usernameEditText =  (EditText) findViewById(R.id.displayname);
        Userdb = FirebaseDatabase.getInstance().getReference("users");


        Button signup= (Button) findViewById(R.id.go);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = passwordEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String username = usernameEditText.getText().toString();

                password = password.trim();
                email = email.trim();
                String id = Userdb.push().getKey();
                Userobj order1=new Userobj(email,username,password);
                Userdb.child(id).setValue(order1);
                registerflag=1;
                Intent intentMain = new Intent(VolReg.this,
                        Vollogin.class);
                VolReg.this.startActivity(intentMain);
            }
        });
    }
}
