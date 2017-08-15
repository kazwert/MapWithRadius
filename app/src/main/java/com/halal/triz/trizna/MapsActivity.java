package com.halal.triz.trizna;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.halal.triz.trizna.model.Maps;

import java.util.ArrayList;

import static com.halal.triz.trizna.R.id.map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<Maps> mapdatas;
    private Marker mapMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mapdatas = new ArrayList<>();
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        int n = mapdatas.size();
        for (int i = 0; i< n ; i++){
            String name = mapdatas.get(i).getName();
            double lat =  mapdatas.get(i).getLat();
            double lng =  mapdatas.get(i).getLng();
           mapMarker =  mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(lat, lng))
                    .title(name)
                    .snippet(name)
            );
        }

        LatLng main = new LatLng(mapdatas.get(1).getLat()
                ,mapdatas.get(1).getLng());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(main,15));

    }

    private void dataRumahMakan() {

        mapdatas.add(new Maps("Rumah Makan Lamongan Expres",1.504065, 124.844708));
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
    

}
