package com.kotlinegitim.cocukeglenceuygulamasi.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.kotlinegitim.cocukeglenceuygulamasi.data.model.Categories
import com.kotlinegitim.cocukeglenceuygulamasi.data.repo.HomeRepository

class HomeViewModel: ViewModel() {
    private val homeRepository = HomeRepository()
    var categoryLiveData: MutableLiveData<List<Categories>> = MutableLiveData()

    fun kategoriyiAl(refCategory: DatabaseReference) =
        homeRepository.kategorileriGetir(refCategory, categoryLiveData)
}