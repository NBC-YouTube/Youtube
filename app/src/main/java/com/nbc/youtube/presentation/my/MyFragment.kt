package com.nbc.youtube.presentation.my

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.nbc.youtube.databinding.FragmentMyBinding
import com.nbc.youtube.presentation.model.UserInfo
import com.nbc.youtube.presentation.model.VideoEntity


class MyFragment : Fragment() {
    private var _binding: FragmentMyBinding? = null
    private val binding: FragmentMyBinding
        get() = _binding!!

    private val viewModel by viewModels<MyViewModel> {
        MyViewModelFactory()
    }

    private val adapter = MyAdapter {
        val action = MyFragmentDirections.actionMyFragmentToDetailFragment(it)
        findNavController().navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadUserInfo()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setThumbnailClip()
        setRecyclerView()
        setObserve()
    }

    private fun loadUserInfo() {
        viewModel.loadUserInfo()
    }

    private fun setThumbnailClip() {
        binding.ivThumbnail.clipToOutline = true
    }

    private fun setRecyclerView() {
        binding.rvFavoriteVideo.adapter = adapter
    }

    private fun setObserve() {
        viewModel.userInfo.observe(viewLifecycleOwner) {
            setUI(it)
        }
        viewModel.favoriteVideos.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<VideoEntity>(KEY_FOR_VIDEO_ENTITY_DISLIKED)?.observe(viewLifecycleOwner) { video ->
            viewModel.removeFavoriteVideo(video)
        }
    }

    private fun setUI(userInfo: UserInfo) {
        val glide = Glide.with(requireContext())

        glide.load(userInfo.channelThumbnail)
            .into(binding.ivChannelThumbnail)

        glide.load(userInfo.profileThumbnail)
            .into(binding.ivThumbnail)

        binding.tvChannelName.text = userInfo.name
        binding.tvDescription.text = userInfo.introduction
    }

    companion object {
        const val KEY_FOR_VIDEO_ENTITY_DISLIKED = "KEY_FOR_VIDEO_ENTITY_DISLIKED"
    }
}