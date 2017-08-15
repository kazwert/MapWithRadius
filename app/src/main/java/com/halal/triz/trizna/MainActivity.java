package com.halal.triz.trizna;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.halal.triz.trizna.api.ApiClient;
import com.halal.triz.trizna.api.ApiInterface;
import com.halal.triz.trizna.model.MapResponse;
import com.halal.triz.trizna.model.Maps;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.halal.triz.trizna.R.id.map;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    private ArrayList<Maps> mapdatas;
    private Marker mapMarker;
    private Marker markerUser;
    private Circle circleUser;
    private Location mLastLocation;
    private Button btLocation;
    private GoogleApiClient mGoogleApiClient;
    private ArrayList<Maps> maps;
    private static final int REQUEST_LOCATION = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Rumah Makan Halal");
        //FloatingButton
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mLastLocation != null) {
                    userMarker();
                }
            }
        });
        //GLoc
        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        //MAPS
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);

        //RETROFIT
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<MapResponse> call = apiService.getLocation();
        call.enqueue(new Callback<MapResponse>() {
            @Override
            public void onResponse(Call<MapResponse> call, Response<MapResponse> response) {
                maps = response.body().getResult();
                Log.d("MAPSDS :", "Number of location: " + maps.size());
                Log.d("MAPSDB :", "Data: " + maps.toString());
                locationMarker();
            }

            @Override
            public void onFailure(Call<MapResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.toString(),Toast.LENGTH_LONG).show();
                Log.e("MAPSDE", t.toString());
            }
        });
    }

    private void locationMarker() {
        if (mMap != null){

            for (Maps m : maps){
                String name = m.getName();
                double lat = m.getLat();
                double lng = m.getLng();
                String desc = m.getDesc();

                mapMarker = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(lat, lng))
                        .title(name)
                        .snippet(desc)
                        .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(R.drawable.placeholder)))
                );
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        addCustomMarker();
        dataRumahMakan();
    }

    private void addCustomMarker() {

    }

    private void dataRumahMakan() {
        mapdatas = new ArrayList<>();
        mapdatas.add(new Maps("Rumah Makan Lamongan Expres", 1.504065, 124.844708));
        mapdatas.add(new Maps("Rumah Makan Coto Unyil", 1.510667, 124.911541));
        mapdatas.add(new Maps("Rumah Makan Padang Raya", 1.527436, 124.921004));
        mapdatas.add(new Maps("Rumah Makan Pak Raden", 1.526470, 124.920946));
        mapdatas.add(new Maps("Rumah Makan Sabrina 2", 1.529366, 124.921388));
        mapdatas.add(new Maps("Rumah Makan Jawa Timur", 1.536644, 124.921553));
        mapdatas.add(new Maps("Rumah Makan Mega Rasa", 1.525157, 124.921150));
        mapdatas.add(new Maps("Rumah Makan Dapur Resto", 1.523122, 124.921032));
        mapdatas.add(new Maps("Rumah Makan Tuna House Paniki", 1.508973, 124.909179));
        mapdatas.add(new Maps("Rumah Makan Mande Kanduang", 1.498555, 124.887750));
        mapdatas.add(new Maps("Kawan Baru Lippo Plaza", 1.500896, 124.891847));
        mapdatas.add(new Maps("The Kampoeng  Authentic Culinary", 1.501154, 124.892000));
        mapdatas.add(new Maps("Kawan Baru Multi Mart Paal 2", 1.491688, 124.861785));
        mapdatas.add(new Maps("Mawar Sharon Paal 2", 1.493629, 124.865522));
        mapdatas.add(new Maps("Pizza Hut Paal 2", 1.487333, 124.857592));
        mapdatas.add(new Maps("Rumah Makan Padang Duta Minang", 1.486451, 124.858104));
        mapdatas.add(new Maps("Rumah Makan Ayam Royal", 1.485310, 124.848597));
        mapdatas.add(new Maps("Cak Ahmad Lamongan", 1.483845, 124.847866));
        mapdatas.add(new Maps("Tuna House Tikala", 1.487168, 124.848045));
        mapdatas.add(new Maps("Rumah Makan Maharani", 1.487428, 124.842568));
        mapdatas.add(new Maps("Rumah Makan Padang Mande Kanduang", 1.485363, 124.842236));
        mapdatas.add(new Maps("Rumah Makan Dapur Mama", 1.487523, 124.844658));
        mapdatas.add(new Maps("KFC Sudirman", 1.489076, 124.846419));
        mapdatas.add(new Maps("Rumah Makan Raja Oci", 1.488893, 124.847273));
        mapdatas.add(new Maps("Rumah Makan Sakinah Catering", 1.475842, 124.854322));
        mapdatas.add(new Maps("Rumah Makan Jawa Timur", 1.473718, 124.853682));
        mapdatas.add(new Maps("D' Penyetz", 1.489721, 124.842377));
        mapdatas.add(new Maps("Rumah Makan Nasi Kuning Selamat Pagi", 1.489901, 124.841820));
        mapdatas.add(new Maps("Warung Tegal", 1.489904, 124.841797));
        mapdatas.add(new Maps("Texas Chicken", 1.490516, 124.840857));
        mapdatas.add(new Maps("KFC Sarapung", 1.489389, 124.840557));
        mapdatas.add(new Maps("Rumah Makan Padang Minang Putra", 1.488893, 124.840415));
        mapdatas.add(new Maps("Rumah Makan Nasi Kuning Saroja", 1.488466, 124.840851));
        mapdatas.add(new Maps("Rumah Makan Padang Rantau Minang", 1.485827, 124.837282));
        mapdatas.add(new Maps("Rumah Makan Bakar Rica", 1.487217, 124.850082));
        mapdatas.add(new Maps("Rumah Makan Swadaya", 1.479123, 124.836268));
        mapdatas.add(new Maps("Rumah Makan Raja Sate", 1.480840, 124.835155));
        mapdatas.add(new Maps("Rumah Makan Padang Duta Minang", 1.481424, 124.835167));
        mapdatas.add(new Maps("Rumah Makan Sri Solo", 1.479085, 124.836913));
        mapdatas.add(new Maps("Bambuden Sario", 1.469309, 124.834101));
        mapdatas.add(new Maps("Rumah Makan Cotto Makassar Phonix", 1.479926, 124.836611));
        mapdatas.add(new Maps("Rumah Makan Suroboyo", 1.470585, 124.833456));
        mapdatas.add(new Maps("Rumah Resto Puncak Manado", 1.468234, 124.842023));
        mapdatas.add(new Maps("Rumah Makan Padang Raya", 1.474946, 124.843541));
        mapdatas.add(new Maps("Rumah Makan Mawar Sharon", 1.463550, 124.840226));
        mapdatas.add(new Maps("Rumah Makan Mas Topan Arema", 1.458135, 124.838083));
        mapdatas.add(new Maps("KFC Coco", 1.459481, 124.837489));
        mapdatas.add(new Maps("Rumah Makan Padang Ranah Minang", 1.463348, 124.833620));
        mapdatas.add(new Maps("Bambuden Sario", 1.469279, 124.834125));
    }

    private Bitmap getMarkerBitmapFromView(@DrawableRes int resId) {

        View customMarkerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.icon_map, null);
        ImageView markerImageView = (ImageView) customMarkerView.findViewById(R.id.profile_image);
        markerImageView.setImageResource(resId);
        customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        customMarkerView.layout(0, 0, customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight());
        customMarkerView.buildDrawingCache();
        Bitmap returnedBitmap = Bitmap.createBitmap(customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
        Drawable drawable = customMarkerView.getBackground();
        if (drawable != null)
            drawable.draw(canvas);
        customMarkerView.draw(canvas);
        return returnedBitmap;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        // get last location ketika berhasil connect
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Check Permissions Now
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION);
        } else {
            // permission has been granted, continue as usual
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);
            if (mLastLocation != null) {
                //Toast.makeText(this," Connected to Google Location API", Toast.LENGTH_LONG).show();

                userMarker();


            }
        }

    }

    private void userMarker() {
        LatLng main = new LatLng(mLastLocation.getLatitude()
                , mLastLocation.getLongitude());
        if (markerUser != null){
            markerUser.remove();
            circleUser.remove();
            markerUser = mMap.addMarker(new MarkerOptions().position(main));
            circleUser = mMap.addCircle(new CircleOptions()
                    .center(main)
                    .radius(200)
                    .fillColor(0x8000E676)
                    .strokeColor(Color.WHITE));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(main, 17));
        }else {
            markerUser = mMap.addMarker(new MarkerOptions().position(main));
            circleUser = mMap.addCircle(new CircleOptions()
                    .center(main)
                    .radius(200)
                    .fillColor(0x8000E676)
                    .strokeColor(Color.WHITE));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(main, 17));
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, " Can't connect To API : " + connectionResult, Toast.LENGTH_LONG).show();
    }

    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions,
                                           int[] grantResults) {
        if (requestCode == REQUEST_LOCATION) {
            if (grantResults.length == 1
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // We can now safely use the API we requested access to
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                                != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                        mGoogleApiClient);
            } else {
                // Permission was denied or request was cancelled
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                about();
                return true;
            case R.id.action_tutorial:
                tutorial();
                return true;
            case R.id.action_exit:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Anda yakin akan keluar?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                MainActivity.this.finish();
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void tutorial() {

        Intent i = new Intent(MainActivity.this, TutorialActivity.class);
        startActivity(i);
    }

    private void about() {
        Intent i = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(i);
    }
}
