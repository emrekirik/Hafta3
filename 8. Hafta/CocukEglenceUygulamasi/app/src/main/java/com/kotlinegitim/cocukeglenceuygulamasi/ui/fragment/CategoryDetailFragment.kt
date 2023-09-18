package com.kotlinegitim.cocukeglenceuygulamasi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.FirebaseDatabase
import com.kotlinegitim.cocukeglenceuygulamasi.ui.adapter.CategoryDetailAdapter
import com.kotlinegitim.cocukeglenceuygulamasi.databinding.FragmentCategoryDetailBinding
import com.kotlinegitim.cocukeglenceuygulamasi.data.model.CategoryDetail
import com.kotlinegitim.cocukeglenceuygulamasi.ui.viewmodel.CategoryDetailViewModel
import com.kotlinegitim.cocukeglenceuygulamasi.ui.fragment.CategoryDetailFragmentArgs
import com.kotlinegitim.cocukeglenceuygulamasi.ui.fragment.CategoryDetailFragmentDirections


class CategoryDetailFragment : Fragment() {
    private lateinit var binding: FragmentCategoryDetailBinding
    private lateinit var categoryDetailAdapter: CategoryDetailAdapter
    private val viewModel: CategoryDetailViewModel by viewModels()
    private val args: CategoryDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryDetailBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        val gelenCategory = args.categoryName
        val database = FirebaseDatabase.getInstance()
        val refContent = database.getReference(gelenCategory.toString())
        binding.rvContentList.setHasFixedSize(true)
        binding.rvContentList.layoutManager = LinearLayoutManager(requireContext())
        if (gelenCategory != null) {
            binding.toolbarContentList.title = "${gelenCategory.categoryName}"
        }
        viewModel.getContent(refContent)
        observeLiveData()
        return view
    }
    private fun observeLiveData() {
        viewModel.contentLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                categoryDetailAdapter = CategoryDetailAdapter(it, ::navigateToDetail)
                binding.rvContentList.adapter = categoryDetailAdapter
            } else {
                Snackbar.make(requireView(), "List is empty!!", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToDetail(content: CategoryDetail) {
        val action =
            CategoryDetailFragmentDirections.actionCategoryDetailFragmentToContentDetailFragment(
                content
            )
        findNavController().navigate(action)
    }

}