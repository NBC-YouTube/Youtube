package com.nbc.youtube.presentation.search

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nbc.youtube.databinding.FragmentSearchBinding
import com.nbc.youtube.presentation.search.model.VideoInfoWithLiked

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
            val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(it.likedToVideoEntity())
            findNavController().navigate(action)
        },
        likeClick = { videoEntityWithLiked ->
            viewModel.addFavoriteVideo(videoEntityWithLiked)
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

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<VideoInfoWithLiked>(
            KEY_FOR_VIDEO_LIKED)?.observe(viewLifecycleOwner) { liked ->
                if (liked.liked) {
                    viewModel.addFavoriteVideo(liked)
                } else {
                    viewModel.removeFavoriteVideo(liked)
                }
        }
    }

    private fun setSearchListeners() {
        baseButtonColor = binding.kidsBtn.backgroundTintList

        binding.searchBtn.setOnClickListener {
            val query = binding.searchText.text.toString()
            viewModel.loadSearchVideos(query, "moderate")

            baseButtonColor?.let { color ->
                binding.kidsBtn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#F6F6F6"))
            }
        }

        binding.kidsBtn.setOnClickListener {
            val query = binding.searchText.text.toString()
            viewModel.loadSearchVideos(query, "strict")

            binding.kidsBtn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#ACACAC"))
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