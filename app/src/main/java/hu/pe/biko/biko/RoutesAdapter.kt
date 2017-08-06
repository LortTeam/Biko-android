package hu.pe.biko.biko

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide

class RoutesAdapter(private val routeList: List<Route>) : RecyclerView.Adapter<RoutesAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutesAdapter.MyViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.route_card, parent, false)
        return RoutesAdapter.MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: RoutesAdapter.MyViewHolder, position: Int) {
        val route = routeList[position]
        holder.name.text = route.name
        holder.description.text = route.description
        Glide.with(holder.view.context)
                .load(route.image)
                .error(R.drawable.caption)
                .placeholder(R.drawable.caption)
                .into(holder.image)
        holder.view.setOnClickListener { v ->
            val intent = Intent(holder.view.context, RouteActivity::class.java)
            intent.putExtra("route", route)
            holder.view.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return routeList.size
    }

    class MyViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView
        var name: TextView
        var description: TextView

        init {
            name = view.findViewById(R.id.name_card_routes) as TextView
            description = view.findViewById(R.id.shortdescription_card_routes) as TextView
            image = view.findViewById(R.id.img_card_main) as ImageView
        }
    }

}
