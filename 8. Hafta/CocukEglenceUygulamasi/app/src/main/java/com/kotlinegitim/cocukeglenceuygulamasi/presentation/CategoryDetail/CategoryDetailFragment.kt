package com.kotlinegitim.cocukeglenceuygulamasi.presentation.CategoryDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kotlinegitim.cocukeglenceuygulamasi.databinding.FragmentCategoryDetailBinding


class CategoryDetailFragment : Fragment() {
    private lateinit var binding: FragmentCategoryDetailBinding
    private lateinit var contentAdapter: CategoryDetailAdapter
    private val args: CategoryDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryDetailBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        val gelenCategory = args.category
        val database = FirebaseDatabase.getInstance()
        val refContent = database.getReference(gelenCategory.toString())
        binding.rvContentList.setHasFixedSize(true)
        binding.rvContentList.layoutManager = LinearLayoutManager(requireContext())
        var contentLiveData: MutableLiveData<List<CategoryDetail>> = MutableLiveData()

        refContent.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val contentList = ArrayList<CategoryDetail>()
                for (i in snapshot.children) {
                    val content = i.getValue(CategoryDetail::class.java)

                    if (content != null) {
                        contentList.add(content)
                        contentLiveData.postValue(contentList)
                    } else {
                        contentLiveData.postValue(null)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        contentLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                contentAdapter = CategoryDetailAdapter(it, ::navigateToDetail)
                binding.rvContentList.adapter = contentAdapter
            } else {
                Snackbar.make(requireView(), "List is empty!!", Snackbar.LENGTH_SHORT).show()
            }
        }

        binding.toolbarContentList.title = "${gelenCategory.categoryName}"
        return view
    }

    private fun navigateToDetail(content: CategoryDetail) {
        val action =
            CategoryDetailFragmentDirections.actionCategoryDetailFragmentToContentDetailFragment(
                content
            )
        findNavController().navigate(action)
    }

}