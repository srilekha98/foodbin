package com.example.srilekha.foodbin;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class HomeForm extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,LocationListener {

    EditText username;

    private GoogleMap mMap;
    EditText contact;
    EditText address;
    Button submit1;
    Switch type;
    Boolean stype;
    Switch pack;Boolean spack;
    DatabaseReference databaseOrders;
    ArrayAdapter<Integer> adapter;
    String typeoffood,packing,noserves;
    private static final int REQUEST_CODE_PERMISSION = 1;

    int dscore=10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //DATABASE CONNECTIO


        databaseOrders = FirebaseDatabase.getInstance().getReference("fudbin");
        setContentView(R.layout.activity_home_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        dscore = Integer.valueOf(sharedPreferences.getString("SCORE", ""));

        TextView tv1 = (TextView)findViewById(R.id.score);
        tv1.setText(Integer.toString(dscore));
        username = (EditText)findViewById(R.id.ename);
        contact = (EditText)findViewById(R.id.econt);
        address = (EditText)findViewById(R.id.eaddr);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                /*checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)*/
        != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this ,"Hello", Toast.LENGTH_SHORT).show();
            System.out.println("No permission given");
            /*ActivityCompat.requestPermissions(HomeForm.this,
                    new String[]{mPermission,
                    },
                    REQUEST_CODE_PERMISSION);*/
            ActivityCompat.requestPermissions(HomeForm.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_PERMISSION);
            return;
        }
        GPSTracker gpsTracker = new GPSTracker(this);

        if (gpsTracker.getIsGPSTrackingEnabled())
        {



            //Toast.makeText(this , "Longitude:" + stringLatitude + "\nLatitude:" + stringLongitude, Toast.LENGTH_SHORT).show();


            String city = gpsTracker.getLocality(this);

            String postalCode = gpsTracker.getPostalCode(this);

            String addressLine = gpsTracker.getAddressLine(this);

            TrackGps gps = new TrackGps(this);

            double lat=0,lng=0;
            if(gps.canGetLocation()){

                lng = gps.getLongitude();
                lat = gps .getLatitude();
                String latitude = String.valueOf(lat);
                String longitude = String.valueOf(lng);

                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                try
                {
                    List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
                    Log.e("Addresses","-->"+addresses);
                    city = addresses.get(0).getLocality().toString();
                    addressLine=addresses.get(0).getAddressLine(0).toString();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }


                Toast.makeText(getApplicationContext(),"Longitude:"+longitude+"\nLatitude:"+latitude,Toast.LENGTH_SHORT).show();
            }
            else
            {
                gps.showSettingsAlert();
            }
                address.setText(addressLine + ", " + city, TextView.BufferType.EDITABLE);

        }
        else
        {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gpsTracker.showSettingsAlert();
        }


        submit1=(Button) findViewById(R.id.submit);
        // initiate a Switch
        type = (Switch) findViewById(R.id.stype);
        // check current state of a Switch (true or false).
        stype = type.isChecked();
        if(stype)
        {
            typeoffood="Veg";
        }
        else
        {
            typeoffood="Non-Veg";
        }
        // initiate a Switch
       pack = (Switch) findViewById(R.id.spack);

// check current state of a Switch (true or false).
        spack = pack.isChecked();
        if(stype)
        {
            packing="packed";
        }
        else
        {
            packing="unpacked";
        }

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
                SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
                dscore = Integer.valueOf(sharedPreferences.getString("SCORE", ""));
                dscore=dscore+10;
                TextView tv1 = (TextView)findViewById(R.id.score);
                tv1.setText(Integer.toString(dscore));
                SavePreferences("SCORE", Integer.toString(dscore));


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
                noserves =Integer.toString(position);
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
            Intent intentMain = new Intent(HomeForm.this ,
                    Aboutus.class);
            HomeForm.this.startActivity(intentMain);


        }
        else if (id == R.id.nav_feedback) {
            Intent intentMain = new Intent(HomeForm.this ,
                    Feedback.class);
            HomeForm.this.startActivity(intentMain);


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
        Order order1=new Order(dname,contact1,address1,address1,id);
        databaseOrders.child(id).setValue(order1);
        Toast.makeText(this, "order added", Toast.LENGTH_LONG).show();
    }

    private void SavePreferences(String key, String value){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    private void LoadPreferences(){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        dscore = Integer.valueOf(sharedPreferences.getString("SCORE", ""));


    }
    @Override
    public void onLocationChanged(Location location) {

        Double lat = location.getLatitude();
        Double lng = location.getLongitude();

        Log.i("Location info: Lat", lat.toString());
        Log.i("Location info: Lng", lng.toString());

    }

}