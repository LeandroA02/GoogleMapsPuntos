package com.example.googlemaps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
implements OnMapReadyCallback, GoogleMap.OnMapClickListener
{
    GoogleMap Mapa;

    int contador;
    ArrayList<LatLng> puntos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        contador =0;
        puntos =new ArrayList<LatLng>();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Mapa=googleMap;
        //esta conectado al mapa
        Mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        Mapa.getUiSettings().setZoomControlsEnabled(true);
        //Mover el mapa a una ubicacion
        CameraUpdate camUpd1 =
                CameraUpdateFactory
                        .newLatLngZoom(new LatLng(-1.0126093556377402, -79.46924565879871), 19);
        Mapa.moveCamera(camUpd1);

        Mapa.setOnMapClickListener(this);

    }
    /*ghhhh
    ghhhhh
        hhh*/


    @Override
    public void onMapClick(@NonNull LatLng latLng) {

        TextView lbLati = findViewById(R.id.lbLatitud);
        lbLati.setText(String.format("%.4f", latLng.latitude));

        TextView lbLon = findViewById(R.id.lbLongitud);
        lbLon.setText(String.format("%.4f", latLng.longitude));

        Mapa.addMarker(new MarkerOptions().position(latLng)
                .title("Marcador"));

        contador = contador +1;
        puntos.add(latLng);
        if (contador==4){
            PolylineOptions lineas = new
                    PolylineOptions()
                    .add(puntos.get(0))
                    .add(puntos.get(1))
                    .add(puntos.get(2))
                    .add(puntos.get(3))
                    .add(puntos.get(0));
            lineas.width(8);
            lineas.color(Color.RED);
            Mapa.addPolyline(lineas);

            contador= 0;
            puntos.clear();

        }
    }

    public void PintarRecUTEQ(View view) {
        Mapa.addMarker(new MarkerOptions().position(
                new LatLng(-1.012074934779557, -79.47086353352543)));
        Mapa.addMarker(new MarkerOptions().position(
                new LatLng(-1.0129549240730638, -79.47091098362901)));
        Mapa.addMarker(new MarkerOptions().position(
                new LatLng(-1.0129750203063144, -79.4686930128605)));
        Mapa.addMarker(new MarkerOptions().position(
                new LatLng(-1.012265960930701, -79.46854722858969)));



        PolylineOptions lineas = new
                PolylineOptions()
                .add(new LatLng(-1.012074934779557, -79.47086353352543))
                .add(new LatLng(-1.0129549240730638, -79.47091098362901))
                .add(new LatLng(-1.0129750203063144, -79.4686930128605))
                .add(new LatLng(-1.012265960930701, -79.46854722858969))
                .add(new LatLng(-1.012074934779557, -79.47086353352543));
        lineas.width(8);
        lineas.color(Color.RED);
        Mapa.addPolyline(lineas);

}

}