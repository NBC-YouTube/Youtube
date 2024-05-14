package com.nbc.youtube.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nbc.youtube.data.repository.testRepo
import com.nbc.youtube.databinding.FragmentHomeBinding
import com.nbc.youtube.presentation.home.adapters.CategorySpinnerAdapter
import com.nbc.youtube.presentation.home.adapters.VideoAdapter
import com.nbc.youtube.presentation.home.videmodels.CategoryViewModel
import com.nbc.youtube.presentation.home.videmodels.CategoryViewModelFactory
import com.nbc.youtube.presentation.home.videmodels.VideosViewModel
import com.nbc.youtube.presentation.home.videmodels.VideosViewModelFactory


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding!!
    private val popularVideoAdapter by lazy { VideoAdapter() }
    private val categoryVideoAdapter by lazy { VideoAdapter() }
    private val categoryViewModel by viewModels<CategoryViewModel>{
        CategoryViewModelFactory()
    }
    private val videosViewModel by viewModels<VideosViewModel> {
        VideosViewModelFactory()
    }
    private lateinit var categorySpinnerAdapter: CategorySpinnerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categorySpinnerBind()
        category()
        defaultRecyclerViewBind()
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
        videosViewModel.popularVideos.observe(viewLifecycleOwner, Observer { data ->
            popularVideoAdapter.listUpdate(data, 1)
        })
        videosViewModel.loadPopularVideos()
    }
    private fun categoryBind() {
        with(binding.rvCategoryVideo) {
            adapter = categoryVideoAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        videosViewModel.categoryVideos.observe(viewLifecycleOwner, Observer { data ->
            categoryVideoAdapter.listUpdate(data, 2)
        })
        videosViewModel.loadCategoryVideos("test")
    }

    private fun categorySpinnerBind() {
        val spinner: Spinner = binding.spCategoryList
        categorySpinnerAdapter = CategorySpinnerAdapter(requireContext())
        spinner.adapter = categorySpinnerAdapter
    }



    private fun category() {
        categoryViewModel.categories.observe(viewLifecycleOwner, Observer { data ->
            categorySpinnerAdapter.setCategoryList(data)
        })
        categoryViewModel.loadCategories()
    }

}
