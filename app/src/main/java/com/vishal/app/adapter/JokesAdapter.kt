package com.vishal.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vishal.app.Model.Joke
import com.vishal.app.R

class JokesAdapter  : RecyclerView.Adapter<JokeViewHolder>() {

    var jokeList = mutableListOf<Joke>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.joke_item, parent, false)
        return JokeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return jokeList.size
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        val joke = jokeList[position]
        val str  = "Joke ID - "+joke.id+"--->"+joke.joke

        holder.textView.text = str
    }

    fun setItems(it: List<Joke>) {
        this.jokeList = it.toMutableList()
        notifyDataSetChanged()
    }

}

class JokeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val textView: TextView = itemView.findViewById(R.id.joke_item_title)
}