package com.kotlinegitim.cocukeglenceuygulamasi.presentation.CategoryDetail

import android.media.Image
import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
data class CategoryDetail(var contentTitle: String?="",
                          var contentDetail: String?="",
                          ): Serializable {
}