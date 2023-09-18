package com.kotlinegitim.cocukeglenceuygulamasi.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import com.kotlinegitim.cocukeglenceuygulamasi.databinding.CategoryCardDesignBinding
import com.kotlinegitim.cocukeglenceuygulamasi.ui.fragment.HomeFragmentDirections
import com.kotlinegitim.cocukeglenceuygulamasi.data.model.Categories

class CategoryAdapter(private val categoryList: List<Categories>,
                      private val onClick: (Categories) -> Unit) :
    RecyclerView.Adapter<CategoryAdapter.CardTasarimTutucu>() {
    val dataBase = FirebaseDatabase.getInstance()
    val refCategories = dataBase.getReference("Categories")

    inner class CardTasarimTutucu(private val binding: CategoryCardDesignBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Categories) {
            with(binding) {
                textViewCategory.text = category.categoryName
            }
            itemView.setOnClickListener {
                onClick.invoke(category)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val binding =
            CategoryCardDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardTasarimTutucu(binding)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val category = categoryList[position]
        holder.bind(category)
    }
}