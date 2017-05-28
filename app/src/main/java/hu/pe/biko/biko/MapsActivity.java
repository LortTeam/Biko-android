package hu.pe.biko.biko;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.directions.route.AbstractRouting;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import io.reactivex.Flowable;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Intent intent = getIntent();
        Gson gson = new GsonBuilder().create();
        String string = getIntent().getStringExtra("route");
        hu.pe.biko.biko.Route route = gson.fromJson(string, hu.pe.biko.biko.Route.class);
        Flowable.fromIterable(route.getPlaces())
                .map(place -> {
                    LatLng latLng = new LatLng(place.getLat(), place.getLng());
                    mMap.addMarker(new MarkerOptions().position(latLng)
                            .title(place.getName()).snippet(place.getDescription()));
                    return latLng;
                }).toList().subscribe(latLngs -> new Routing.Builder()
                .key("AIzaSyDNOZJGI2nNtqmqiHdsvqcQMYF_6VBdi38 ")
                .waypoints(latLngs)
                .travelMode(AbstractRouting.TravelMode.BIKING)
                .withListener(new RoutingListener() {
                    @Override
                    public void onRoutingFailure(RouteException e) {

                    }

                    @Override
                    public void onRoutingStart() {

                    }

                    @Override
                    public void onRoutingSuccess(ArrayList<Route> routes, int i) {
                        mMap.addPolyline(routes.get(0).getPolyOptions());
                        mMap.moveCamera(CameraUpdateFactory
                                .newLatLngBounds(routes.get(0).getLatLgnBounds(), 10));
                    }

                    @Override
                    public void onRoutingCancelled() {

                    }
                }).build().execute());
    }
}
