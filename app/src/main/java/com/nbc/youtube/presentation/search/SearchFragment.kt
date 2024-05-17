package com.nbc.youtube.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nbc.youtube.databinding.FragmentSearchBinding
import com.nbc.youtube.presentation.search.model.VideoEntityWithLiked

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = _binding!!
    private val viewModel by viewModels<SearchViewModel> {
        SearchViewModelFactory()
    }

    private val adapter = SearchAdapter {
        val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(it.likedToVideoEntity())
        findNavController().navigate(action)
    }

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

        viewModel.loadSearchVideos("query", "moderate")
    }

    private fun setRecyclerView() {
        binding.rvSearchVideo.adapter = adapter
    }

    private fun setObserve() {
        viewModel.searchVideo.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<VideoEntityWithLiked>(
            KEY_FOR_VIDEO_LIKED)?.observe(viewLifecycleOwner) { liked ->
                if (liked.liked) {
                    viewModel.addFavoriteVideo(liked)
                } else {
                    viewModel.removeFavoriteVideo(liked)
                }
        }
    }

    private fun setSearchListeners() {
        binding.searchBtn.setOnClickListener {
            val query = binding.searchText.text.toString()
        }

        binding.kidsBtn.setOnClickListener {
            val query = binding.searchText.text.toString()
            viewModel.loadSearchVideos(query, "strict")
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        const val KEY_FOR_VIDEO_LIKED = "KEY_FOR_VIDEO_LIKED"
    }
}