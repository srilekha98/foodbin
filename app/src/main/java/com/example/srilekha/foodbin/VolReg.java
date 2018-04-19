package com.example.srilekha.foodbin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import	android.app.AlertDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.srilekha.foodbin.R.id.textView;

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
        
        FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.fab);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentMain = new Intent(VolReg.this,
                        Vollogin.class);
                VolReg.this.startActivity(intentMain);


            }
        });


        Button signup= (Button) findViewById(R.id.go);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = passwordEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String username = usernameEditText.getText().toString();

                if(validateemail() ){//&& validatepassword()) {

                    password = password.trim();
                    final String email1 = email.trim();
                    final String id = Userdb.push().getKey();
                    final Userobj order1 = new Userobj(email1, username, password);
                    Userdb.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            int flag=1;
                            //tracks.clear();
                            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                Userobj track = postSnapshot.getValue(Userobj.class);
                                System.out.println("Entering fn");
                                // tacks.add(track);
                                if(track.emaileq(email1))
                                {
                                    flag=0;
                                }
                            }
                            if(flag==1){
                            Userdb.child(id).setValue(order1);
                            registerflag = 1;
                            Intent intentMain = new Intent(VolReg.this,
                                    Vollogin.class);
                            VolReg.this.startActivity(intentMain);}
                            else
                            {
                                Toast.makeText(getApplicationContext(),"email address exists",Toast.LENGTH_SHORT).show();

                            }
                            // TrackList trackListAdapter = new TrackList(ArtistActivity.this, tracks);
                            //listViewTracks.setAdapter(trackListAdapter);
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                    /* {
                        Userdb.child(id).setValue(order1);
                        registerflag = 1;
                        Intent intentMain = new Intent(VolReg.this,
                                Vollogin.class);
                        VolReg.this.startActivity(intentMain);
                    }*/
                }
            }
        });

    }
    public boolean validateemail()
    {
        final EditText emailEditText =  (EditText) findViewById(R.id.username);
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String email=emailEditText.getText().toString().trim();
        if (email.matches(emailPattern) && email.length() > 0)
        {
            //Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
            // or
            //textView.setText("valid email");
            return true;
        }
        else {
            Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    public boolean validatepassword()
    {
        final EditText passwordEditText =  (EditText) findViewById(R.id.password);
        String emailPattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
        String email=passwordEditText.getText().toString().trim();
        if (email.matches(emailPattern) && email.length() > 0)
        {
            //Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
            // or
            //textView.setText("valid email");
            return true;
        }
        else {
            Toast.makeText(getApplicationContext(), "minimum 6 characters at least 1 Alphabet, 1 Number and 1 Special Character", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}
