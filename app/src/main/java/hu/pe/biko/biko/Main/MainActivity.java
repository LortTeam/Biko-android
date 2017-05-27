package hu.pe.biko.biko.Main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import hu.pe.biko.biko.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Fragment fragment1 = new HomeFragment();
        final Fragment fragment2 = new WorldFragment();
        final Fragment fragment3 = new AccountFragment();

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
        fragmentTransaction1.replace(R.id.container_main, fragment1).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                                fragmentTransaction1.replace(R.id.container_main, fragment1).commit();
                                return true;
                            case R.id.navigation_world:
                                FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
                                fragmentTransaction2.replace(R.id.container_main, fragment2).commit();
                                return true;
                            case R.id.navigation_account:
                                FragmentTransaction fragmentTransaction3 = fragmentManager.beginTransaction();
                                fragmentTransaction3.replace(R.id.container_main, fragment3).commit();
                                return true;
                        }
                        return true;
                    }

                });
    }

}
