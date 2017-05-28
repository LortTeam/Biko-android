package hu.pe.biko.biko;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class RouteActivity extends AppCompatActivity {
    Route route;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        route = getIntent().getParcelableExtra("route");
        TextView textView = (TextView) findViewById(R.id.text_route)
        textView.setText(route.getDescription());
        getSupportActionBar().setTitle(route.getName());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            Intent intent = new Intent(RouteActivity.this, MapsActivity.class);
            intent.putExtra("route", route);
            startActivity(intent);
        });
    }
}
