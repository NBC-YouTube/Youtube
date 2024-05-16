package com.nbc.youtube.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nbc.youtube.databinding.FragmentHomeBinding
import com.nbc.youtube.presentation.home.adapters.CategorySpinnerAdapter
import com.nbc.youtube.presentation.home.adapters.VideoAdapter
import com.nbc.youtube.presentation.home.videmodels.VideosViewModel
import com.nbc.youtube.presentation.home.videmodels.VideosViewModelFactory
import com.nbc.youtube.presentation.model.VideoEntity


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding!!
    private val popularVideoAdapter by lazy { VideoAdapter() }
    private val categoryVideoAdapter by lazy { VideoAdapter() }
    private val videosViewModel by viewModels<VideosViewModel> {
        VideosViewModelFactory()
    }
    private val spinnerAdapter by lazy { CategorySpinnerAdapter(requireContext()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        videosViewModel.loadPopularVideos()
        videosViewModel.loadCategories()
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
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        with(binding.rvMostPopular) {
            adapter = popularVideoAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
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
                videosViewModel.loadCategoryVideos(selectedItem)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun observeData() {
        videosViewModel.categories.observe(viewLifecycleOwner, Observer { data ->
            spinnerAdapter.setCategoryList(data)
        })
        videosViewModel.categoryVideos.observe(viewLifecycleOwner, Observer { data ->
            categoryVideoAdapter.listUpdate(data, 2)
        })
        videosViewModel.popularVideos.observe(viewLifecycleOwner, Observer { data ->
            popularVideoAdapter.listUpdate(data, 1)
        })
    }


    private fun itemOnClick() {
        setItemClick(popularVideoAdapter)
        setItemClick(categoryVideoAdapter)
    }

    private fun setItemClick(adapter: VideoAdapter) {
        adapter.itemClick = object : VideoAdapter.ItemClick {
            override fun onClick(item: VideoEntity) {
                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(item)
                findNavController().navigate(action)
            }
        }
    }
}
