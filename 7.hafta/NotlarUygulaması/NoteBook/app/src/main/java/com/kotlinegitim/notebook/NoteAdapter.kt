package com.kotlinegitim.notebook

import android.content.Context
import com.kotlinegitim.notebook.ui.fragments.HomePageFragmentDirections
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.FirebaseDatabase
import com.kotlinegitim.notebook.databinding.NotdefteriCardDesignBinding

//BABA KODLAR

class NoteAdapter(var mContext: Context, var noteList: List<Notes>) : //iKİ PARAMETREMİZ VAR BİRİ CONTEXT DİĞERİ LİSTE CLASS TİPİNDE
    RecyclerView.Adapter<NoteAdapter.CardTasarimTutucu>() {
    val dataBase = FirebaseDatabase.getInstance()
    val refNotes = dataBase.getReference("Notes")

    //BURAYI HATIRLIYORSUN CLASS İÇİNDE CLASS
    inner class CardTasarimTutucu(tasarim: NotdefteriCardDesignBinding) :  //CARD TASARIMIMIZI BİÇİMİNDE BİR TASARIM İŞTE
        RecyclerView.ViewHolder(tasarim.root) {  //RECYCLERVİEWLA BAĞDAŞTIRIYORUZ BU SAATTEN SONRA BİLİYORMUŞ GİBİ ANLATICAM

        var tasarim: NotdefteriCardDesignBinding

        init {
            this.tasarim = tasarim  //NOLUOYR BİLMYİROUM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim = NotdefteriCardDesignBinding.inflate(layoutInflater, parent, false)
        return CardTasarimTutucu(tasarim) //ANLAMADIM
    }

    //BURASI listeleme işte size kadar
    override fun getItemCount(): Int {
        return noteList.size

    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val note = noteList.get(position)  //Gerekli pozisyona atıyoruz dfklşsdfşds
        val t = holder.tasarim

        t.textViewNote.text = "${note.noteContent}"  //Pardon tamam, listeden gelen notu textview'e atıyoruz

        //Sil buttonuna tıkladığımızda çalışan kodlar
        t.imageViewSil.setOnClickListener {
            Snackbar.make(it, "Silinsin mi?", Snackbar.LENGTH_LONG)
                .setAction("EVET") {
                    //eVET DEDĞİİMİZDE
                    refNotes.child(note.noteId.toString()).removeValue() //Veritabanındaki çocuğumuzun id'sine göre bulup siliyor
                    return@setAction  //Aynı sayfayı yeniliyor
                }.show()
        }

        //Card'a tıkladığımızda güncelleme sayfasına yönlendirme biliyorsun bunu en bildiğin konu
        //ama önemli bir husus var, giderken verileri de götürüyor bak!!
        //işte burda plugin'e eklediğimiz şeyi kullanııyoruz
        //HomePageFragmentDirections' ne demek, anasayfanın yönlendirme çizgilerinin olduğu sınıf demek
        //plugin eklemediğim için bu sınıf gelmiyordu bi türlü ekleyince geldi, yani bak gösteriyorum
        //ama bunu kullanmamızın sebebi verileri de taşımak

        t.satirCard.setOnClickListener {
            val gecis = HomePageFragmentDirections.noteUpdateTransition(note = note)
            Navigation.findNavController(it).navigate(gecis)
        }
    }//bu kadar basitmiş, şaka şaka anam ağladı, aldığım kaynaklara bak şimdi, 1-hocanın ders videosu,2-firebase dokümanı, 3-eski projeler,4-hande, 5-hoca
}