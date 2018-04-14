package com.example.srilekha.foodbin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Srilekha on 12-04-2018.
 */

public class VolReg extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        final EditText edit =  (EditText) findViewById(R.id.username);
        final EditText edit1 =  (EditText) findViewById(R.id.password);
        final EditText edit2 =  (EditText) findViewById(R.id.displayname);

        Button go= (Button) findViewById(R.id.go);

        FloatingActionButton fab =
                (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMain = new Intent(VolReg.this ,
                        Vollogin.class);
                VolReg.this.startActivity(intentMain);
            }

        });



    }
}
