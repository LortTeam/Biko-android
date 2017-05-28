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
import hu.pe.biko.biko.RoutesCompletedAdapter;
import io.reactivex.android.schedulers.AndroidSchedulers;


public class AccountFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Route> routesList;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_acc);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        Biko biko = new Biko();
        biko.getRoutes().observeOn(AndroidSchedulers.mainThread())
                .subscribe(routes -> recyclerView.setAdapter(new RoutesAdapter(routes)),
                        Throwable::printStackTrace);
        return view;
    }

}
