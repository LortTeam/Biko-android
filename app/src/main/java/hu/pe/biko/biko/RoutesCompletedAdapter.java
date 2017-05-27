package hu.pe.biko.biko;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nikita on 27.05.17.
 */

public class RoutesCompletedAdapter extends RecyclerView.Adapter<RoutesCompletedAdapter.MyViewHolder> {

    private List<Route> routeList;

    public RoutesCompletedAdapter(List<Route> routes) { routeList = routes; }

    boolean flag = true;

    @Override
    public RoutesCompletedAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (flag) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_user_card, parent, false);
            flag = false;
            return new RoutesCompletedAdapter.MyViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_user_card, parent, false);
            return new RoutesCompletedAdapter.MyViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(final RoutesCompletedAdapter.MyViewHolder holder, int position) {
        Route route = routeList.get(position);
    }

    @Override
    public int getItemCount() { return routeList.size(); }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public ImageView image;
        public TextView name;
        public TextView description;

        public  MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            name = (TextView) itemView.findViewById(R.id.name_card_routes);
            description = (TextView) itemView.findViewById(R.id.shortdescription_card_routes);
            image = (ImageView) itemView.findViewById(R.id.img_card_main);
        }

    }
}
