package com.kotlinegitim.cocukeglenceuygulamasi.presentation.Home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import com.kotlinegitim.cocukeglenceuygulamasi.databinding.CategoryCardDesignBinding
import com.kotlinegitim.cocukeglenceuygulamasi.presentation.CategoryDetail.CategoryDetailFragmentDirections

class CategoryAdapter(var mContext: Context, var categoryList: List<Categories>) :
    RecyclerView.Adapter<CategoryAdapter.CardTasarimTutucu>() {
    val dataBase = FirebaseDatabase.getInstance()
    val refCategories = dataBase.getReference("Categories")

    inner class CardTasarimTutucu(tasarim: CategoryCardDesignBinding) :
        RecyclerView.ViewHolder(tasarim.root) {
        var tasarim: CategoryCardDesignBinding

        init {
            this.tasarim = tasarim
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim = CategoryCardDesignBinding.inflate(layoutInflater, parent, false)
        return CardTasarimTutucu(tasarim)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val category = categoryList.get(position)
        val t = holder.tasarim

        t.textViewCategory.text = "${category.categoryName}"
        t.satirCardCategory.setOnClickListener {
            val gecis = HomeFragmentDirections.homeToContents(category = category)
            Navigation.findNavController(it).navigate(gecis)
        }
    }

}