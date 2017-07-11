package app.uninstall.udmatech.com.uninstall;

import android.Manifest;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;

import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.vision.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private Button btn;
    private GoogleMap mMap;
    private ArrayList<String> ashu = new ArrayList<String>();
    private ArrayList<String> ashu1 = new ArrayList<String>();
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn1);
        textView = (TextView) findViewById(R.id.t1);
        ashu.add("Monkey");
        ashu.add("Donkey");
        ashu.add("Pig");
        ashu.add("Kothi");
        ashu.add("Pig");
        ashu.add("Guubay");
        ashu.add("fan");
        ashu1.add("Name");
        ashu1.add("Address");
        ashu1.add("PetName");
        ashu1.add("Caste");
        ashu1.add("Place");
        ashu1.add("Nationalty");
        ashu1.add("State");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    Toast.makeText(getApplicationContext(), "Searching Location", Toast.LENGTH_SHORT).show();
                    fungetloc();
                } else {
                    showGPSDisabledAlertToUser();
                }

            }
        });
        createLayoutDynamically(ashu.size(), ashu);
        createTextDynamically(ashu.size(), ashu, ashu1);
    }

    private void showGPSDisabledAlertToUser() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("GPS is disabled on your device. Would you like to enable it?")
                .setCancelable(false)
                .setPositiveButton("Enable GPS",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent callGPSSettingIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(callGPSSettingIntent);
                            }
                        });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    private void fungetloc() {
        try {
            LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            //Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            Location location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            String stlongitude = Double.toString(longitude);
            String stlatitude = Double.toString(latitude);
            Log.d("ege", stlongitude + "," + stlatitude);
            getCompleteAddressString(latitude, longitude);
        } catch (Exception e) {
            //Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    private String getCompleteAddressString(double latitude, double longitude) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                Log.w("exe", "" + strReturnedAddress.toString());
                textView.setText(strReturnedAddress.toString());
            } else {
                Log.w("exe", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("My Cur location address", "Canont get Address!");
        }
        return strAdd;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    private void createLayoutDynamically(int n, ArrayList items) {

        for (int i = 0; i < n; i++) {
            Button myButton = new Button(this);
            myButton.setText("I am :" + items.get(i));
            myButton.setId(i);
            final int id_ = myButton.getId();

            LinearLayout layout = (LinearLayout) findViewById(R.id.l1);
            layout.addView(myButton);

            myButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this,
                            "Button clicked index = " + id_, Toast.LENGTH_SHORT)
                            .show();
                    Intent i = new Intent(MainActivity.this, DialogActivity.class);
                    startActivity(i);
                }
            });
        }
    }

    private void createTextDynamically(int n, ArrayList items,ArrayList itemNames) {

        for (int i = 0; i < n; i++) {
            TextView tv = new TextView(this);
            tv.setText(itemNames.get(i)+" \t\t\t\t"+ items.get(i));
            tv.setId(i);
            final int id_ = tv.getId();

            LinearLayout layout = (LinearLayout) findViewById(R.id.l1);
            layout.addView(tv);

            tv.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this,
                            "Button clicked index = " + id_, Toast.LENGTH_SHORT)
                            .show();
                    Intent i = new Intent(MainActivity.this, DialogActivity.class);
                    startActivity(i);
                }
            });
        }
    }
}
