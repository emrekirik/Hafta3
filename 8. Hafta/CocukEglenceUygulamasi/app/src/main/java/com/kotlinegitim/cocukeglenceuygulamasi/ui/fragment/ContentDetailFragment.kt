package com.kotlinegitim.cocukeglenceuygulamasi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.kotlinegitim.cocukeglenceuygulamasi.databinding.FragmentContentDetailBinding
import com.kotlinegitim.cocukeglenceuygulamasi.ui.fragment.ContentDetailFragmentArgs


class ContentDetailFragment : Fragment() {
    private lateinit var binding: FragmentContentDetailBinding
    private val args: ContentDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContentDetailBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        val args = args.content
        binding.toolbar3.title = "${args.contentTitle}"
        binding.textViewContent.text = args.contentDetail.toString()
        return view
    }
}