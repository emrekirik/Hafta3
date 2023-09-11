package com.kotlinegitim.notebook.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.firebase.database.FirebaseDatabase
import com.kotlinegitim.notebook.Notes
import com.kotlinegitim.notebook.R
import com.kotlinegitim.notebook.databinding.FragmentNoteAddBinding
import java.util.zip.Inflater

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class NoteAddFragment : Fragment() {
private lateinit var tasarim: FragmentNoteAddBinding
//ekleme sayfası
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        tasarim = FragmentNoteAddBinding.inflate(layoutInflater, container, false) // tasarım dediğim şey Binding işte

        val dataBase = FirebaseDatabase.getInstance() //database yetkilendirme
        val refNotlar = dataBase.getReference("Notes")  //Notes tablosunu çağırma evet

        //buttona tıkladığımda ekleme ve anasayfaya yönlendirme yapıyorum
        tasarim.buttonEkle.setOnClickListener {
            val note= Notes("","${tasarim.editTextAddNote.text}")  //pardon tam tersi, edittexten aldığımız veriyi class'a atıyoruz
                                                        //Onu da note değişkenine işte
            refNotlar.push().setValue(note)        //Burda note değişkenindeki veriyi veritabanına pushluyoruz
                                                //aslında Notes Class tipine göre veri ekliyoruz
            return@setOnClickListener Navigation.findNavController(it).navigate(R.id.action_noteAddFragment_to_homePageFragment)
        }       //bura da yönlendirme

        return  tasarim.root
    }

}