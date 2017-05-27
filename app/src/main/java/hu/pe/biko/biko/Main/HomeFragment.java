package hu.pe.biko.biko.Main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import hu.pe.biko.biko.Biko;
import hu.pe.biko.biko.R;
import hu.pe.biko.biko.Route;
import hu.pe.biko.biko.RoutesAdapter;
import io.reactivex.android.schedulers.AndroidSchedulers;


public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Route> routesList;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment
        recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        routesList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            routesList.add(0, new Route("Route", "Descrption", "image", "Kaliningrad", "Russia", "State",
                    0, "", new ArrayList<Place>()));
        }
        RoutesAdapter adapter = new RoutesAdapter(routesList);
        recyclerView.setAdapter(adapter);
        return view;
    }

}
