package com.kotlinegitim.notebook.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kotlinegitim.notebook.NoteAdapter
import com.kotlinegitim.notebook.Notes
import com.kotlinegitim.notebook.R
import com.kotlinegitim.notebook.databinding.FragmentHomePageBinding


class HomePageFragment : Fragment() {
    private lateinit var tasarim: FragmentHomePageBinding
    private lateinit var adapter: NoteAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tasarim = FragmentHomePageBinding.inflate(layoutInflater)
        tasarim.rv.setHasFixedSize(true)
        tasarim.rv.layoutManager = LinearLayoutManager(requireContext())
        val noteList = ArrayList<Notes>()    //Notlar class'ını değişkene atıyoruz
        val dataBase = FirebaseDatabase.getInstance()   //database yetkilendirme işlemi
        val refNotes = dataBase.getReference("Notes")  //database'deki "Notes" Tablosunu
                                                            // refNotes değişkenine atıyoruz

        refNotes.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {  //Burda veritabanından veri çekiyoruz
                noteList.clear()
                for (i in snapshot.children) {          //Her bir tablodaki veri childeren olarak isimlenmiş
                    val note = i.getValue(Notes::class.java) //Çocuklar gibi skdfşsdil
                    if (note != null) {
                        note.noteId = i.key            //her bir notun Id'sini database'deki id'ye atıoyruz sanırım ein değimlim

                        noteList.add(note)          //NoteList arraylist'İne ekliyoruz her bir notu
                                                    //Bu arraylist Notes class'ına bağlanıyor
                    }
                }
                adapter.notifyDataSetChanged()  //Burası olması gereken bir şey, verileri çekememe problemim bunu yazmadığım içinmiş listeyi güncellğyor işte
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        // burda recyclerView'i adapter'a bağlıyoruz, aynı zamanda noteLİst'i de adapter parametresine yazıyoruz çünküüü
        adapter = NoteAdapter(requireContext(), noteList)
        tasarim.rv.adapter = adapter

        //button'a tıkladığımda ekleme sayfasına gidiyor
        tasarim.floatingActionButton.setOnClickListener {  // Anasayfadaki ekleme buttonyna tıkladığım zaman ekleme sayfasının açılması için yazdığım kod
            Navigation.findNavController(it)
                .navigate(R.id.note_add_transition) //yönlendirdiğim sayfa için nav_Graphdaki yönlendirme çizgisinin Id'si
        }



        return tasarim.root
    }
}