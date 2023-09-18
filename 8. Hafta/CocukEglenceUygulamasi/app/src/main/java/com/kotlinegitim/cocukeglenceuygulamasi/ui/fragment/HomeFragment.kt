package com.kotlinegitim.cocukeglenceuygulamasi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.FirebaseDatabase
import com.kotlinegitim.cocukeglenceuygulamasi.ui.adapter.CategoryAdapter
import com.kotlinegitim.cocukeglenceuygulamasi.databinding.FragmentHomeBinding
import com.kotlinegitim.cocukeglenceuygulamasi.data.model.Categories
import com.kotlinegitim.cocukeglenceuygulamasi.ui.viewmodel.HomeViewModel
import com.kotlinegitim.cocukeglenceuygulamasi.ui.fragment.HomeFragmentDirections

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: CategoryAdapter
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        val database = FirebaseDatabase.getInstance()
        val refCategory = database.getReference("Categories")
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        binding.toolbar.title = "Kategoriler"
        binding.rv.setHasFixedSize(true)

        viewModel.kategoriyiAl(refCategory)
        verileriGozlemle()
        return view
    }
    private fun gecisYap(category: Categories) {
        val action = HomeFragmentDirections.homeToContents(category)
        findNavController().navigate(action)
    }

    private fun verileriGozlemle() {
        viewModel.categoryLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                categoryAdapter = CategoryAdapter(it, ::gecisYap)
                binding.rv.adapter = categoryAdapter
            } else {
                Snackbar.make(requireView(), "List is empty!!", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

}