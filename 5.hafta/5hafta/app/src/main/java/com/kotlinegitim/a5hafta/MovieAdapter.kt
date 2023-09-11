package com.kotlinegitim.a5hafta

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.kotlinegitim.a5hafta.databinding.MovieItemBinding

class MovieAdapter(private val mContext: Context, private val movieList: List<Movie>): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(val binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        val binding = holder.binding
        val imageId = mContext.resources.getIdentifier(movie.image, "drawable", mContext.packageName)
        binding.cardName.text = movie.name
        binding.cardContent.text = movie.comment
        binding.cardAuthor.text = movie.author
        binding.cardImage.setImageResource(imageId)

        binding.cardButton.setOnClickListener{
            Toast.makeText(mContext, "${movie.name} purchased", Toast.LENGTH_SHORT).show()
        }

    }

}