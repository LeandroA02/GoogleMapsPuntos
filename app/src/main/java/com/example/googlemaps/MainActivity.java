package com.example.googlemaps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

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
        Mapa.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null; //
            }

            @Override
            public View getInfoContents(Marker marker) {
                View view = getLayoutInflater().inflate(R.layout.informacion, null);
                ImageView imageView = view.findViewById(R.id.imgedificio);
                TextView titleTextView = view.findViewById(R.id.nomfacultad);
                TextView descriptionTextView = view.findViewById(R.id.txtdescripcion);

                switch (marker.getTitle()) {
                    case "Facultad de Ciencias de la Ingeniería":
                        imageView.setImageResource(R.drawable.fci);
                        titleTextView.setText("Facultad de Ciencias de la Ingeniería");
                        descriptionTextView.setText(Html.fromHtml("<b>Las únicas carreras que están en la central FCI son:</b>" +
                                "<br/>Ingeniería en Telemática<br/>Arquitectura<br/>Software"));
                        break;
                    case "Facultad de Ciencias Empresariales":
                        imageView.setImageResource(R.drawable.fce);
                        titleTextView.setText("Facultad de Ciencias Empresariales");
                        descriptionTextView.setText(Html.fromHtml("<b>Carreras:</b><br/>Administración de Empresas<br/>Contabilidad y Auditoría<br/>" +
                                "Gestión del Talento Humano<br/>Mercadotecnia"));
                        break;
                    case "Edificio de rectorado UTEQ":
                        imageView.setImageResource(R.drawable.rectorado);
                        titleTextView.setText("Edificio de rectorado UTEQ");
                        descriptionTextView.setText(Html.fromHtml("<b>Lugar de trabajo de las Autoridades de la institución:</b><br/>Rector" +
                                "<br/>Vicerrectora Académica<br/>Vicerrector administrativo"));
                        break;
                    case "Facultad de Ciencias de la Salud":
                        imageView.setImageResource(R.drawable.enfermeria);
                        titleTextView.setText("Facultad de Ciencias de la Salud");
                        descriptionTextView.setText(Html.fromHtml("<b>Carreras:</b><br/>Enfermería"));
                        break;
                }
                return view;
            }
        });
        //primer edificio
        LatLng punto1 = new LatLng(-1.0125589384339835, -79.47065917792366);
        Mapa.addMarker(new MarkerOptions()
                .position(punto1)
                .title("Facultad de Ciencias de la Ingeniería"));

        //segundo edificio
        LatLng punto2= new LatLng(-1.012160399271335, -79.47015290061624);
        Mapa.addMarker(new MarkerOptions()
                .position(punto2)
                .title("Facultad de Ciencias Empresariales"));

        //tercer edificio (en el mapa no aparece aun el edificio de rectorado)
        LatLng punto3 = new LatLng(-1.0129621003899674, -79.46887162593272);
        Mapa.addMarker(new MarkerOptions()
                .position(punto3)
                .title("Edificio de rectorado UTEQ"));

        //cuarto edificio (google maps la reconoce aun como Facultad de Ciencias Agrarias)
        LatLng punto4 = new LatLng(-1.0128762368669686, -79.46939672444451);
        Mapa.addMarker(new MarkerOptions()
                .position(punto4)
                .title("Facultad de Ciencias de la Salud"));
    }

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