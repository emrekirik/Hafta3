package com.kotlinegitim.cocukeglenceuygulamasi.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.kotlinegitim.cocukeglenceuygulamasi.data.model.CategoryDetail
import com.kotlinegitim.cocukeglenceuygulamasi.data.repo.HomeRepository

class CategoryDetailViewModel: ViewModel() {
    private val homeRepository = HomeRepository()
    var contentLiveData: MutableLiveData<List<CategoryDetail>> = MutableLiveData()

    fun getContent(refContent: DatabaseReference) =
        homeRepository.icerikleriGetir(refContent, contentLiveData)
}