package com.rentokil.pci.rauditor;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Locationtest extends AppCompatActivity {

    Button getLL,getlocation;
    EditText latitudeLL,longitudeLL;

    String lati,longi;

    TextView result;
    Geocoder geocoder;
    List<Address> addressList;
    GPSTracker gps;

    // GPSTracker class    GPSTracker gps;

    Context mContext;

    double latitude,longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locationtest);

        result = (TextView)findViewById(R.id.result);
        geocoder = new Geocoder(this, Locale.getDefault());


        latitudeLL = (EditText)findViewById(R.id.latitude);
        longitudeLL = (EditText)findViewById(R.id.longitude);

        getlocation = (Button)findViewById(R.id.getLocation);

        mContext = this;


        getlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    if (ContextCompat.checkSelfPermission(mContext,
                            Manifest.permission.ACCESS_FINE_LOCATION)

                            != PackageManager.PERMISSION_GRANTED

                            && ActivityCompat.checkSelfPermission(mContext,
                            Manifest.permission.ACCESS_COARSE_LOCATION)

                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(Locationtest.this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

                    } else {
                        Toast.makeText(mContext, "You need have granted permission",

                                Toast.LENGTH_SHORT).show();
                        gps = new GPSTracker(mContext, Locationtest.this);

                        // Check if GPS enabled

                        if (gps.canGetLocation()) {

                            latitude = gps.getLatitude();
                            longitude = gps.getLongitude();

                            // \n is for new line

                            Toast.makeText(getApplicationContext(),
                                    "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                            lati = Double.toString(latitude);
                            longi = Double.toString(longitude);

                        } else {
                            // Can't get location.

                            // GPS or network is not enabled.

                            // Ask user to enable GPS/network in settings.

                            gps.showSettingsAlert();
                        }
                    }
                    addressList = geocoder.getFromLocation(latitude,longitude,1);

                    String addressStr = addressList.get(0).getAddressLine(0);
                    String areaStr = addressList.get(0).getLocality();
                    String cityStr = addressList.get(0).getAdminArea();
                    String countryStr = addressList.get(0).getCountryName();
                    String postalcodeStr = addressList.get(0).getPostalCode();

                    String fullAddress = addressStr+", "+areaStr+", "+cityStr+", "+countryStr+", "+postalcodeStr;

                    result.setText(fullAddress);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,

                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.

                if (grantResults.length > 0

                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    gps = new GPSTracker(mContext, Locationtest.this);

                    // Check if GPS enabled

                    if (gps.canGetLocation()) {

                        double latitude = gps.getLatitude();
                        double longitude = gps.getLongitude();

                        // \n is for new line

/*                        Toast.makeText(getApplicationContext(),
                                "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                   */ } else {
                        // Can't get location.

                        // GPS or network is not enabled.

                        // Ask user to enable GPS/network in settings.

                        gps.showSettingsAlert();
                    }

                } else {

                    // permission denied, boo! Disable the

                    // functionality that depends on this permission.
                  /*  Toast.makeText(mContext, "You need to grant permission",

                            Toast.LENGTH_SHORT).show();*/
                }
                return;
            }
        }
    }
}


