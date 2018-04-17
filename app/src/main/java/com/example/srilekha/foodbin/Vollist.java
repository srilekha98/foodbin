package com.example.srilekha.foodbin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * Created by Srilekha on 14-04-2018.
 */

public class Vollist extends AppCompatActivity {

    private ListView listView;
    private vollistadapter mAdapter;
    DatabaseReference databaseOrders;

     ArrayList<Vollistitem> orders= new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.vollist);

        Button submit1=(Button) findViewById(R.id.submit);

        submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMain = new Intent(Vollist.this ,
                        HomeForm.class);
                Vollist.this.startActivity(intentMain);
            }
        });
        databaseOrders = FirebaseDatabase.getInstance().getReference("fudbin");
      //  Vollistitem t = ;
       // ArrayList<Vollistitem> order = new ArrayList<>();
        orders.add(new Vollistitem("dg","47682","df","sjf" ,"try"));


        loadorders();


        listView = (ListView) findViewById(R.id.custom_list);

       /* moviesList.add(new Movie(R.drawable.movie_after_earth, "After Earth" , "2013"));
        moviesList.add(new Movie(R.drawable.movie_baby_driver, "Baby Driver" , "2017"));
        moviesList.add(new Movie(R.drawable.movie_deadpool, "Deadpool" , "2016"));
       */

        mAdapter = new vollistadapter(this,0,orders);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long iid) {

                final long itemid=iid;
                Object o = listView.getItemAtPosition(position);
                Vollistitem data = (Vollistitem) o;
                final String key=data.getid();
                //out.println(key+"seeeeeeeeeeeeeeeeeeeeeeeee");
                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(getApplicationContext());
                View promptsView = li.inflate(R.layout.promt, null);

                // set prompts.xml to alertdialog builder

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Vollist.this);
                alertDialogBuilder.setView(promptsView);

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("ACCEPT",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        Toast.makeText(getApplicationContext(), "Thank you :)", Toast.LENGTH_LONG).show();
                                        //databaseOrders.child(itemid).setValue(null);

                                        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("fudbin").child(key);

                                        //removing artist
                                        dR.removeValue();


                                    }
                                })
                        .setNegativeButton("DECLINE",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();


                //  Toast.makeText(getActivity().getApplicationContext(), "Selected :" + " " + newsData, Toast.LENGTH_LONG).show();
           mAdapter.notifyDataSetChanged();
            }
        });


    }


    public void loadorders() {

        databaseOrders.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous artist list
                 orders.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    Vollistitem artist = postSnapshot.getValue(Vollistitem.class);
                    //adding artist to the list
                    orders.add(artist);
                }
                mAdapter.notifyDataSetChanged();

                for (int i = 0; i < orders.size(); i++) {
                    Vollistitem element = orders.get(i);
                    out.println(element.getdname());

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}

