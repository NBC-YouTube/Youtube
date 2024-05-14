package com.nbc.youtube.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nbc.youtube.data.repository.testRepo
import com.nbc.youtube.databinding.FragmentHomeBinding
import com.nbc.youtube.presentation.home.adapters.VideoAdapter


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding!!
    private val popularVideoAdapter by lazy { VideoAdapter() }
    private val categoryVideoAdapter by lazy { VideoAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        defaultRecyclerViewBind()
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null

        super.onDestroyView()
    }

    private fun defaultRecyclerViewBind() {
        rvMostPopularBind()
        categoryBind()
    }

    private fun rvMostPopularBind() {
        with(binding.rvMostPopular) {
            adapter = popularVideoAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        popularVideoAdapter.listUpdate(testRepo().getPopularVideos(),1)
    }
    private fun categoryBind() {
        with(binding.rvCategoryVideo) {
            adapter = categoryVideoAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        categoryVideoAdapter.listUpdate(testRepo().getPopularVideos(),2)
    }

}
