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
import static android.R.id.edit;

/**
 * Created by Srilekha on 24-03-2018.
 */

public class Vollogin extends AppCompatActivity {

    String email="", password="";
    int signinflag=0;
    private FirebaseAuth mFirebaseAuth;
    private static final String TAG = "Vollogin";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mFirebaseAuth = FirebaseAuth.getInstance();
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
                SavePreferences("EMAIL", email);
                SavePreferences("PASSWORD", password);

              //  app.loginemail=email;
                System.out.println("clicked, signinflag= "+signinflag);
             //   Callapi task=new Callapi();
            //    task.execute();
                // check if user is present
                //if yes,,,then intent to vollist
                mFirebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Vollogin.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mFirebaseAuth.getCurrentUser();
                                    signinflag=1;
                                    //updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(Vollogin.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }

                                // [START_EXCLUDE]
                                if (!task.isSuccessful()) {
                                   // mStatusTextView.setText(R.string.auth_failed);
                                }
                                //hideProgressDialog();
                                // [END_EXCLUDE]
                            }
                        });

                System.out.println("clicked, signinflag= "+signinflag);

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
