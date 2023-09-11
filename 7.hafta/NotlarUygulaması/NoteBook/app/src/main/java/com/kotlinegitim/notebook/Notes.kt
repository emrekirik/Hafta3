package com.kotlinegitim.notebook

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable


@IgnoreExtraProperties //veri tabanıyla ilişkilendirmek için bu kod gerekmiş
data class Notes(var noteId:String?="",
                 var noteContent: String?=""
): Serializable{ // bu da nav_graph'da bi plagin kullanıyorsun onda işe yarıyormuş sonra anlatırım
}