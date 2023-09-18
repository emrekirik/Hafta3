package com.kotlinegitim.cocukeglenceuygulamasi.presentation.CategoryDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlinegitim.cocukeglenceuygulamasi.databinding.CategoryDetailCardDesignBinding
import com.kotlinegitim.cocukeglenceuygulamasi.presentation.Home.Categories

class CategoryDetailAdapter(
    private val contentList: List<CategoryDetail>,
    private val onClick: (CategoryDetail) -> Unit
):RecyclerView.Adapter<CategoryDetailAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(val binding: CategoryDetailCardDesignBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(categoryDetail: CategoryDetail){
                with(binding){
                    textViewCategoryDetail.text = categoryDetail.contentTitle

                    itemView.setOnClickListener{
                        onClick.invoke(categoryDetail)
                    }
                }

            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val binding =
            CategoryDetailCardDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardTasarimTutucu(binding)
    }

    override fun getItemCount(): Int {
        return contentList.size
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val content = contentList[position]
        holder.bind(content)
    }
}