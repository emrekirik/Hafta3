package com.kotlinegitim.notebook.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.firebase.database.FirebaseDatabase
import com.kotlinegitim.notebook.Notes
import com.kotlinegitim.notebook.R
import com.kotlinegitim.notebook.databinding.FragmentNoteUpdateBinding

//Güncelleme
class NoteUpdateFragment : Fragment() {
    private lateinit var tasarim: FragmentNoteUpdateBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tasarim = FragmentNoteUpdateBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        val bundle: NoteUpdateFragmentArgs by navArgs() //Böyle bir şey varmış, kütüphaneden çekiyor
        val gelenNote = bundle.note   //Bundle ne bilmiyorum sadkşlsadksa

        tasarim.editTextUpdateNote.setText(gelenNote.noteContent)  //setText ne bilmiyorum gelen not'a bir şey yapıyor işte

        //Güncelleme kodları burda
        tasarim.buttonUpdate.setOnClickListener {
            val noteContent = tasarim.editTextUpdateNote.text.toString() //ekrandan gelen text'i atadık

            guncelle(gelenNote.noteId.toString(), noteContent)
            return@setOnClickListener Navigation.findNavController(it).navigate(R.id.action_noteUpdateFragment_to_homePageFragment)
        }
        return tasarim.root
    }
    fun guncelle(noteId:String, noteContent: String){
        val dataBase = FirebaseDatabase.getInstance()
        val refNotes = dataBase.getReference("Notes")

        //Güncellemek için hashmap kullanmamız lazım bir liste aslında, key,value şeklinde iki değer tutuyor, ben birinde Id'yi birinde notu tutuorum
        val bilgiler = HashMap<String, Any>() //bilgiler isminde bir hashmap oluşturuyoruz
        bilgiler.put("noteContent", noteContent)  //bilgiler listesine iki değer atıyorum, ilki veritabanındaki not, ikincisi ekrandan gelen not
        refNotes.child(noteId).updateChildren(bilgiler) //veritabanındaki çocuğumuzun ID'sinin olduğu notu updateChilderen hazır fonksiyonuyla güncelliyoruz
        //bilgiler hashmap'indeki ilk değeri ikinci değerle güncelliyoruz yani, ilki eski ikincisi yeni

    } //neyse bura da tamam şimdi BABA KODLARA GİDİYORUZ
}