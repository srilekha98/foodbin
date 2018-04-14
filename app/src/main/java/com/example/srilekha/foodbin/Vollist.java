package com.example.srilekha.foodbin;

import android.content.Context;
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
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Srilekha on 14-04-2018.
 */

public class Vollist extends AppCompatActivity {

    private ListView listView;
    private vollistadapter mAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vollist);

        listView = (ListView) findViewById(R.id.custom_list);
        ArrayList<Vollistitem> moviesList = new ArrayList<>();
       /* moviesList.add(new Movie(R.drawable.movie_after_earth, "After Earth" , "2013"));
        moviesList.add(new Movie(R.drawable.movie_baby_driver, "Baby Driver" , "2017"));
        moviesList.add(new Movie(R.drawable.movie_deadpool, "Deadpool" , "2016"));
       */

        mAdapter = new vollistadapter(this,0,moviesList);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Vollistitem data = (Vollistitem) o;


                //  prompt accept button


            /*    Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse(newsData.getHref()));
                startActivity(viewIntent);          */

                //  Toast.makeText(getActivity().getApplicationContext(), "Selected :" + " " + newsData, Toast.LENGTH_LONG).show();
            }
        });

    }




}

