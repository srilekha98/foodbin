package com.example.srilekha.foodbin;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class HomeForm extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText username;
    EditText contact;
    EditText address;
    Button submit1;
    Switch type;
    Boolean stype;
    Switch pack;Boolean spack;
    DatabaseReference databaseOrders;
    ArrayAdapter<Integer> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //DATABASE CONNECTION
        databaseOrders = FirebaseDatabase.getInstance().getReference("fudbin");
        setContentView(R.layout.activity_home_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        username = (EditText)findViewById(R.id.ename);
        contact = (EditText)findViewById(R.id.econt);
        address = (EditText)findViewById(R.id.eaddr);
        submit1=(Button) findViewById(R.id.submit);
        // initiate a Switch
        type = (Switch) findViewById(R.id.stype);
        // check current state of a Switch (true or false).
        stype = type.isChecked();

        // initiate a Switch
       pack = (Switch) findViewById(R.id.spack);

// check current state of a Switch (true or false).
        spack = pack.isChecked();


        Spinner spinner = (Spinner) findViewById(R.id.qspinner);
        int a[] ={1,2,3,4,5,6,7,8};


        List<Integer> spinnerArray = new ArrayList<>();
        spinnerArray.add(1);
        spinnerArray.add(2);
        spinnerArray.add(3);
        spinnerArray.add(4);
        spinnerArray.add(5);
        spinnerArray.add(6);
        spinnerArray.add(7);
        spinnerArray.add(8);

// (3) create an adapter from the list
        adapter = new ArrayAdapter<Integer>(
                this,
                android.R.layout.simple_spinner_item,
                spinnerArray
        );
        spinner.setAdapter(adapter);

        submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calling the method addArtist()
                //the method is defined below
                //this method is actually performing the write operation
                addOrder();
            }
        });

        /*
        String[] tileName = new String[]{"Clouds", "Temperature", "Precipitations", "Snow", "Rain", "Wind", "Sea level press."};

        ArrayAdapter adpt = new ArrayAdapter(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, tileName);

        spinner.setAdapter(adpt);
         */

        final int[] q = new int[1];

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                ((TextView) selectedItemView).setTextColor(Color.BLACK);
                q[0] =position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


       DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
     /*   DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }  */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_form, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_volunteer) {
            // Handle the camera action

            Intent intentMain = new Intent(HomeForm.this ,
                    Vollogin.class);
           HomeForm.this.startActivity(intentMain);

        } else if (id == R.id.nav_gallery) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void addOrder()
    {
        String dname = username.getText().toString();
        String contact1 = contact.getText().toString();
        String address1 = address.getText().toString();
        String id = databaseOrders.push().getKey();
        Order order1=new Order(dname,contact1,address1,address1);
        databaseOrders.child(id).setValue(order1);
        Toast.makeText(this, "order added", Toast.LENGTH_LONG).show();
    }

}
