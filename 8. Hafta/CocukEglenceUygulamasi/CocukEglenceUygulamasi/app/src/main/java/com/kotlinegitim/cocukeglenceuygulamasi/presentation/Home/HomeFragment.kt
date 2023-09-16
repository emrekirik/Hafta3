package com.kotlinegitim.cocukeglenceuygulamasi.presentation.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kotlinegitim.cocukeglenceuygulamasi.R
import com.kotlinegitim.cocukeglenceuygulamasi.databinding.ActivityMainBinding
import com.kotlinegitim.cocukeglenceuygulamasi.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
private lateinit var binding: FragmentHomeBinding
private lateinit var adapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        val database = FirebaseDatabase.getInstance()
        val refCategory = database.getReference("Categories")
        val categoryList = ArrayList<Categories>()
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        binding.toolbar.title = "Kategoriler"


        refCategory.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                categoryList.clear()

                for (i in snapshot.children){
                    val category = i.getValue(Categories::class.java)
                    if (category!=null){
                        categoryList.add(category)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })





        adapter = CategoryAdapter(requireContext(), categoryList)
        binding.rv.adapter = adapter
        return view
    }


}