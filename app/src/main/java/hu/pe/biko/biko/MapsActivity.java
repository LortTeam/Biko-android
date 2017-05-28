package hu.pe.biko.biko;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.model.Direction;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import io.reactivex.Flowable;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Intent intent = getIntent();
        Route route = intent.getParcelableExtra("route");
        Flowable.fromIterable(route.getPlaces()).map(place -> {
            LatLng latLng = new LatLng(place.getLat(), place.getLng());
            mMap.addMarker(new MarkerOptions().position(latLng)
                    .title(place.getName()).snippet(place.getDescription()));
            return latLng;
        }).toList().subscribe(latLngs -> GoogleDirection.withServerKey("AIzaSyAaqf2W1ZIxhDhE8GSz1urY2ntK7ERArc0")
                .from(latLngs.get(0))
                .to(latLngs.get(latLngs.size() - 1))
                .waypoints(latLngs.subList(1, latLngs.size() - 1))
                .transportMode(TransportMode.WALKING)
                .execute(new DirectionCallback() {
                    @Override
                    public void onDirectionSuccess(Direction direction, String rawBody) {
                        if (direction.isOK()) {
                            com.akexorcist.googledirection.model.Route route = direction.getRouteList().get(0);
                            mMap.addPolyline(new PolylineOptions().addAll(route.getOverviewPolyline().getPointList()));
                            LatLngBounds bounds = new LatLngBounds(
                                    route.getBound().getSouthwestCoordination().getCoordination(),
                                    route.getBound().getNortheastCoordination().getCoordination());
                            mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 10));
                        } else {
                            Log.i("tag", direction.getStatus());
                        }
                    }

                    @Override
                    public void onDirectionFailure(Throwable t) {
                        t.printStackTrace();
                    }
                }));
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

            AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
            builder.setTitle("Congratulations! ")
                    .setMessage("You've just finished this route")
                    .setCancelable(false)
                    .setNegativeButton("ОК", (dialog, which) -> {
                        dialog.cancel();
                        finish();

                    })
                    .setPositiveButton("Share", (dialog, which) -> {
                        //Sharing
                    });
            AlertDialog alert = builder.create();
            alert.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
