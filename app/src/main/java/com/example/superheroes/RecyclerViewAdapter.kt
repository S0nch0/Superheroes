package com.example.superheroes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import retrofit2.Call

class RecyclerViewAdapter(private val items: List<Superhero>): RecyclerView.Adapter<RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.hero_item, parent, false)
        return RecyclerViewHolder(itemView)
    }

    override fun getItemCount(): Int =items.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.name.text = items[position].name
        holder.fullName.text = items[position].biography.fullName
        holder.gender.text = items[position].appearance.gender
        holder.race.text = items[position].appearance.race
        Glide.with(holder.itemView)
            .load(items[position].images.sm)
            .into(holder.listImage)
    }

}

class RecyclerViewHolder(view: View): RecyclerView.ViewHolder(view){
    val name = view.findViewById<TextView>(R.id.name)
    val fullName = view.findViewById<TextView>(R.id.fullName)
    val gender = view.findViewById<TextView>(R.id.gender)
    val race = view.findViewById<TextView>(R.id.race)
    val listImage = view.findViewById<ImageView>(R.id.imageView)
}