package com.kotlinegitim.cocukeglenceuygulamasi.presentation.Home

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
data class Categories(var categoryName:String?=""): Serializable {
}