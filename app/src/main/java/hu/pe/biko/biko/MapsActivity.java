package hu.pe.biko.biko;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

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
        hu.pe.biko.biko.Route route = intent.getParcelableExtra("route");
        Flowable.fromIterable(route.getPlaces())
                .map(place -> {
                    LatLng latLng = new LatLng(place.getLat(), place.getLng());
                    mMap.addMarker(new MarkerOptions().position(latLng)
                            .title(place.getName()).snippet(place.getDescription()));
                    return latLng;
                }).toList().subscribe(latLngs -> new Routing.Builder()
                .key("AIzaSyAaqf2W1ZIxhDhE8GSz1urY2ntK7ERArc0")
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    //TODO

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_finish) {
            FinishedDialog fragment = new FinishedDialog();
            fragment.show(getFragmentManager(), null);

            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
