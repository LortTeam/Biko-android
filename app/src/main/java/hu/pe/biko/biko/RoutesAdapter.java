package hu.pe.biko.biko;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RoutesAdapter extends RecyclerView.Adapter<RoutesAdapter.MyViewHolder> {

    private List<Route> routeList;

    public RoutesAdapter(List<Route> routes) { routeList = routes; }

    @Override
    public RoutesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.route_card, parent, false);
        return new RoutesAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RoutesAdapter.MyViewHolder holder, int position) {
        Route route = routeList.get(position);
        holder.name.setText(route.getName());
        holder.description.setText(route.getDescription());
        Glide.with(holder.view.getContext())
                .load(route.getImage())
                .error(R.drawable.caption)
                .placeholder(R.drawable.caption)
                .into(holder.image);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.view.getContext(), RouteActivity.class);
                intent.putExtra("name", holder.name.getText().toString());
                intent.putExtra("description", holder.description.getText().toString());
                // Image ???
                holder.view.getContext().startActivity(intent);
            }
        });
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
