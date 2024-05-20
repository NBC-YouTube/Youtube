package com.nbc.youtube.presentation.search

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nbc.youtube.databinding.FragmentSearchBinding
import com.nbc.youtube.presentation.detail.DetailFragment
import com.nbc.youtube.presentation.search.model.SafeSearchType

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = _binding!!
    private val viewModel by viewModels<SearchViewModel> {
        SearchViewModelFactory()
    }
    private var baseButtonColor: ColorStateList? = null

    private val adapter = SearchAdapter (
        onClick = {
            val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(it.likedToVideoInfo())
            findNavController().navigate(action)
        },
        likeClick = { videoEntityWithLiked ->
            viewModel.updateSelectedItem(videoEntityWithLiked)
            viewModel.updateLiked(videoEntityWithLiked)
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
        setObserve()
        setSearchListeners()
    }

    private fun setRecyclerView() {
        binding.rvSearchVideo.adapter = adapter
    }

    private fun setObserve() {
        viewModel.searchVideo.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.kidSearch.observe(viewLifecycleOwner) { kidSearch ->
            if (kidSearch) {
                binding.kidsBtn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#ACACAC"))
            } else {
                binding.kidsBtn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#F6F6F6"))
            }
        }

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Boolean>(
            DetailFragment.KEY_FOR_VIDEO_LIKED)?.observe(viewLifecycleOwner) { liked ->
            viewModel.onLikedChange(liked)
        }
    }

    private fun hideKeyboard() {
        val inputMutableList = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMutableList.hideSoftInputFromWindow(binding.searchText.windowToken, 0)
    }

    private fun setSearchListeners() {
        baseButtonColor = binding.kidsBtn.backgroundTintList

        binding.searchBtn.setOnClickListener {
            val query = binding.searchText.text.toString()
            viewModel.loadSearchVideos(query, SafeSearchType.moderate)
            hideKeyboard()

            viewModel.kidsSearchType(false)
        }

        binding.kidsBtn.setOnClickListener {
            val query = binding.searchText.text.toString()
            viewModel.loadSearchVideos(query, SafeSearchType.strict)

            viewModel.kidsSearchType(true)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}