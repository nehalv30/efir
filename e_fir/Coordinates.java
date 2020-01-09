package com.example.e_fir;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class Coordinates extends AppCompatActivity implements LocationListener {
    private final static int SEND_SMS_PERMISSION_REQUEST_CODE=1111;
    Button getLocationBtn, send_sms;
    TextView locationText;

    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_coordinates);
        send_sms=findViewById(R.id.send_sms);
        send_sms.setEnabled(true);

        getLocationBtn = (Button)findViewById(R.id.getLocationBtn);
        locationText = (TextView)findViewById(R.id.locationText);


        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }


        getLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
            }
        });
        if(checkPermission(Manifest.permission.SEND_SMS)){
            send_sms.setEnabled(true);

        }
        else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},SEND_SMS_PERMISSION_REQUEST_CODE);

        }
        send_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg=locationText.getText().toString();
                if(!TextUtils.isEmpty(msg)){
                    if(checkPermission(Manifest.permission.SEND_SMS)){
                        SmsManager smsManager=SmsManager.getDefault();
                        smsManager.sendTextMessage("+918054783082",null,msg,null,null);
                    }
                    else{
                        Toast.makeText(Coordinates.this,"permission denied",Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(Coordinates.this,"press get location",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        locationText.setText("Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude());

        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            locationText.setText(locationText.getText() + "\n"+addresses.get(0).getAddressLine(0)+", "+
                    addresses.get(0).getAddressLine(1)+", "+addresses.get(0).getAddressLine(2));
        }catch(Exception e)
        {

        }

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(Coordinates.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    private boolean checkPermission(String permission){
        int checkPermission=ContextCompat.checkSelfPermission(this,permission);
        return checkPermission==PackageManager.PERMISSION_GRANTED;

    }
    public void onRequestPemissionResult(int requestCode,String[] permission,int[] grantResults){
        switch(requestCode){
            case SEND_SMS_PERMISSION_REQUEST_CODE:
                if(grantResults.length>0&&(grantResults[0]==PackageManager.PERMISSION_GRANTED)){
                    send_sms.setEnabled(true);
                }break;
        }
    }






}