package com.kotlinegitim.cocukeglenceuygulamasi.data.repo

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.kotlinegitim.cocukeglenceuygulamasi.data.model.Categories
import com.kotlinegitim.cocukeglenceuygulamasi.data.model.CategoryDetail

class HomeRepository {
    fun kategorileriGetir(
        refCategory: DatabaseReference,
        categoryLiveData: MutableLiveData<List<Categories>>
    ) {

        refCategory.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val categoryList = ArrayList<Categories>()


                for (i in snapshot.children) {
                    val category = i.getValue(Categories::class.java)
                    if (category != null) {
                        categoryList.add(category)
                        categoryLiveData.postValue(categoryList)
                    } else {
                        categoryLiveData.postValue(null)
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun icerikleriGetir(
        refContent: DatabaseReference,
        contentLiveData: MutableLiveData<List<CategoryDetail>>
    ) {
        refContent.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val contentList = ArrayList<CategoryDetail>()
                for (i in snapshot.children) {
                    val content = i.getValue(CategoryDetail::class.java)

                    if (content != null) {
                        contentList.add(content)
                        contentLiveData.postValue(contentList)
                    } else {
                        contentLiveData.postValue(null)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}