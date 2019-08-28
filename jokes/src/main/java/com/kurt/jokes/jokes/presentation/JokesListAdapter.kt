package com.kurt.jokes.jokes.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kurt.jokes.mobile.domain.entities.Joke
import com.kurt.jokes.jokes.R

class JokesListAdapter : ListAdapter<Joke, RecyclerView.ViewHolder>(
    object : DiffUtil.ItemCallback<Joke>() {
        override fun areItemsTheSame(oldItem: Joke, newItem: Joke) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Joke, newItem: Joke) = oldItem == newItem
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_item_joke, parent, false)

        return object : RecyclerView.ViewHolder(view) {}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val joke = getItem(position)

        holder.itemView.apply {
            val txtSetup = findViewById<TextView>(R.id.txt_setup)
            val txtPunchline = findViewById<TextView>(R.id.txt_punchline)

            var isShown = false

            txtSetup.text = joke.setup
            txtPunchline.text = joke.punchline

            setOnClickListener {
                txtPunchline.visibility = if (isShown) View.GONE else View.VISIBLE
                isShown = !isShown
            }
        }
    }

}