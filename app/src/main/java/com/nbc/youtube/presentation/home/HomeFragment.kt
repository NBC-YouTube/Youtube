package com.nbc.youtube.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nbc.youtube.databinding.FragmentHomeBinding
import com.nbc.youtube.presentation.App
import com.nbc.youtube.presentation.home.adapters.CategorySpinnerAdapter
import com.nbc.youtube.presentation.home.adapters.VideoAdapter
import com.nbc.youtube.presentation.home.videmodels.HomeViewModel
import com.nbc.youtube.presentation.model.VideoInfo


class HomeFragment : Fragment() {

    private val appContainer by lazy {
        (requireActivity().application as App).appContainer
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding!!
    private val popularVideoAdapter by lazy { VideoAdapter() }
    private val categoryVideoAdapter by lazy { VideoAdapter() }
    private val homeViewModel by viewModels<HomeViewModel> {
        appContainer.createViewModelFactory()
    }
    private val spinnerAdapter by lazy { CategorySpinnerAdapter(requireContext()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.loadPopularVideos()
        homeViewModel.loadCategories()
    }

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
        defaultRecyclerViewBind()
        observeData()
        itemOnClick()
    }

    override fun onDestroyView() {
        _binding = null

        super.onDestroyView()
    }

    private fun defaultRecyclerViewBind() {
        with(binding.rvCategoryVideo) {
            adapter = categoryVideoAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        with(binding.rvMostPopular) {
            adapter = popularVideoAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

    }

    private fun categorySpinnerBind() {
        val spinner: Spinner = binding.spCategoryList
        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = spinnerAdapter.getItem(position)
                homeViewModel.loadCategoryVideos(selectedItem, position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun observeData() {
        homeViewModel.categories.observe(viewLifecycleOwner) { data ->
            spinnerAdapter.setCategoryList(data)
        }
        homeViewModel.categoryVideos.observe(viewLifecycleOwner) { data ->
            categoryVideoAdapter.listUpdate(data, 2)
        }
        homeViewModel.popularVideos.observe(viewLifecycleOwner) { data ->
            popularVideoAdapter.listUpdate(data, 1)
        }
    }


    private fun itemOnClick() {
        setItemClick(popularVideoAdapter)
        setItemClick(categoryVideoAdapter)
    }

    private fun setItemClick(adapter: VideoAdapter) {
        adapter.itemClick = object : VideoAdapter.ItemClick {
            override fun onClick(item: VideoInfo) {
                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(item)
                findNavController().navigate(action)
            }
        }
    }
}
