package hu.pe.biko.biko

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide

class RouteActivity : AppCompatActivity() {
    internal var route : Route? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        route = intent.getParcelableExtra<Route>("route")
        val textView = findViewById(R.id.text_route) as TextView
        textView.text = route!!.description
        supportActionBar!!.title = route!!.name
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            //                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            //                        .setAction("Action", null).show();
            val intent = Intent(this@RouteActivity, MapsActivity::class.java)
            intent.putExtra("route", route)
            startActivity(intent)
        }
        Glide.with(this).load(route!!.image)
                .error(R.drawable.caption)
                .placeholder(R.drawable.caption)
                .into(findViewById(R.id.image_route) as ImageView)
    }
}