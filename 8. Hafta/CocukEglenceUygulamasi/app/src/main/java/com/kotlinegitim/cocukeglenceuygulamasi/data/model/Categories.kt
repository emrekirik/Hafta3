package com.kotlinegitim.cocukeglenceuygulamasi.data.model

import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
@IgnoreExtraProperties
data class Categories(val categoryName: String?=""): Parcelable {
}