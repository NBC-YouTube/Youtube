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
import com.nbc.youtube.presentation.App
import com.nbc.youtube.presentation.detail.DetailFragment
import com.nbc.youtube.presentation.model.UserInfo


class MyFragment : Fragment() {

    private val appContainer by lazy {
        (requireActivity().application as App).appContainer
    }

    private var _binding: FragmentMyBinding? = null
    private val binding: FragmentMyBinding
        get() = _binding!!

    private val viewModel by viewModels<MyViewModel> {
        appContainer.createViewModelFactory()
    }

    private val adapter = MyAdapter {
        viewModel.updateClickedItem(it)
        val action = MyFragmentDirections.actionMyFragmentToDetailFragment(it)
        findNavController().navigate(action)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.loadUserInfo()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setThumbnailClip()
        setRecyclerView()
        setObserve()
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
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Boolean>(
            DetailFragment.KEY_FOR_VIDEO_LIKED
        )?.observe(viewLifecycleOwner) { liked ->
            if (!liked) {
                viewModel.removeFavoriteVideo()
            } else {
                viewModel.loadFavoriteInfo()
            }
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
}