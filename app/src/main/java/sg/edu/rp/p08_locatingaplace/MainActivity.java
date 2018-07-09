package sg.edu.rp.p08_locatingaplace;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                LatLng Singapore = new LatLng(1.352083, 103.819836);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(Singapore,
                        15));

                // NORTH

                LatLng northHQ = new LatLng(1.466740, 103.815147);
                Marker nHQ = map.addMarker(new MarkerOptions()
                        .position(northHQ)
                        .title("North - HQ")
                        .snippet("Blk 333, Admiralty Ave 3, 765654" +
                                "Operating hours: 10am-5pm" +
                                "Tel: 65433456")
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.star)));

                // CENTRAL

                LatLng central = new LatLng(1.300761, 103.839746);
                Marker central1 = map.addMarker(new MarkerOptions()
                        .position(central)
                        .title("Central")
                        .snippet("Blk 3A, Orchard Ave 3, 134542" +
                                "Operating hours: 11am-8pm" +
                                "Tel: 67788652")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                // EAST

                LatLng east = new LatLng(1.349591, 103.956788);
                Marker east1 = map.addMarker(new MarkerOptions()
                        .position(east)
                        .title("East")
                        .snippet("Blk 555, Tampines Ave 3, 287788" +
                                "Operating hours: 9am-5pm" +
                                "Tel: 66776677")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));


                UiSettings ui = map.getUiSettings();
                ui.setCompassEnabled(true);
                ui.setZoomControlsEnabled(true);
                ui.setMyLocationButtonEnabled(true);

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);
                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                }
                if(permissionCheck != PermissionChecker.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                    return;
                }
            }
        });

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);

        // NORTH
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng northHQ = new LatLng(1.466740, 103.815147);
                if (map != null) {
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(northHQ, 15));
                }
                Toast.makeText(MainActivity.this, "North", Toast.LENGTH_SHORT).show();
            }
        });

        // CENTRAL
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng central = new LatLng(1.300761, 103.839746);
                if (map != null) {
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(central, 15));
                }
                Toast.makeText(MainActivity.this, "Central", Toast.LENGTH_SHORT).show();
            }
        });

        // EAST
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng east = new LatLng(1.349591, 103.956788);
                if (map != null) {
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(east, 15));
                }
                Toast.makeText(MainActivity.this, "East", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
