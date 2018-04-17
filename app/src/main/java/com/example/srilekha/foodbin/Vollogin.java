package com.example.srilekha.foodbin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
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
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import static android.R.id.edit;

/**
 * Created by Srilekha on 24-03-2018.
 */

public class Vollogin extends AppCompatActivity {

    String email="", password="";
    int signinflag=0;
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference Userdb;
    private static final String TAG = "Vollogin";
    List<Userobj> tracks;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Userdb = FirebaseDatabase.getInstance().getReference("users");

        FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.fab);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentMain = new Intent(Vollogin.this,
                        VolReg.class);
                Vollogin.this.startActivity(intentMain);


            }
        });
        LoadPreferences();

        final EditText edit =  (EditText) findViewById(R.id.usernamer);
        final EditText edit1 =  (EditText) findViewById(R.id.passwordr);

        Button login= (Button) findViewById(R.id.go1);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=edit.getText().toString();
                password=edit1.getText().toString();
                final Userobj a=new Userobj(email,"",password);
                SavePreferences("EMAIL", email);
                SavePreferences("PASSWORD", password);

                //  app.loginemail=email;
                System.out.println("clicked, signinflag= "+signinflag);
                //   Callapi task=new Callapi();
                //    task.execute();
                // check if user is present
                //if yes,,,then intent to vollist
                Userdb.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //tracks.clear();
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            Userobj track = postSnapshot.getValue(Userobj.class);
                            System.out.println("Entering fn");
                            // tracks.add(track);
                            if(track.ifequal(a))
                            {
                                System.out.println("Entering innerfn");
                                signinflag=1;
                                System.out.println("clicked, signinflagafter= "+signinflag);
                                Intent intentMain = new Intent(Vollogin.this,
                                        Vollist.class);
                                Vollogin.this.startActivity(intentMain);
                            }
                        }
                        if(signinflag==0)
                        {
                            Toast.makeText(Vollogin.this, "Invalid id or password", Toast.LENGTH_LONG).show();
                        }
                        // TrackList trackListAdapter = new TrackList(ArtistActivity.this, tracks);
                        //listViewTracks.setAdapter(trackListAdapter);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });





            }
        });
    }

    private void SavePreferences(String key, String value){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    private void LoadPreferences(){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        email = sharedPreferences.getString("EMAIL", "");
        password = sharedPreferences.getString("PASSWORD", "");


    }
}
