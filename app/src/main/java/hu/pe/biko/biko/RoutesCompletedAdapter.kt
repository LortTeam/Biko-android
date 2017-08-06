package hu.pe.biko.biko

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by nikita on 27.05.17.
 */

class RoutesCompletedAdapter(private val routeList: List<Route>) : RecyclerView.Adapter<RoutesCompletedAdapter.MyViewHolder>() {

    internal var flag = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutesCompletedAdapter.MyViewHolder {

        if (flag) {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.info_user_card, parent, false)
            flag = false
            return RoutesCompletedAdapter.MyViewHolder(v)
        } else {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.info_user_card, parent, false)
            return RoutesCompletedAdapter.MyViewHolder(v)
        }
    }

    override fun onBindViewHolder(holder: RoutesCompletedAdapter.MyViewHolder, position: Int) {
        val (name, description, image, city, country, state, distance, time, places) = routeList[position]
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
