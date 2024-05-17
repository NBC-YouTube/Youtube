package com.nbc.youtube.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.nbc.youtube.R
import com.nbc.youtube.databinding.FragmentDetailBinding
import com.nbc.youtube.presentation.App

class DetailFragment : Fragment() {

    private val appContainer by lazy {
        (requireActivity().application as App).appContainer
    }

    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding
        get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()

    private val viewModel: DetailViewModel by viewModels {
        appContainer.createViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.setData(args.videoInfo)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObserve()
        setListener()
    }

    private fun setObserve() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            viewModel.readyForPop()
        }
        viewModel.videoInfo.observe(viewLifecycleOwner) {
            Glide.with(requireContext())
                .load(it.thumbnail)
                .placeholder(R.drawable.sample_placeholder)
                .into(binding.ivThumbnail)
            binding.tvTitle.text = it.title
            binding.tvDescription.text = it.description
        }
        viewModel.isLiked.observe(viewLifecycleOwner) { isLiked ->
            binding.btnLike.text = if (isLiked) getString(R.string.dislike) else getString(R.string.like)
        }
        viewModel.finalLiked.observe(viewLifecycleOwner) { isLiked ->
            val navController = findNavController()
            navController.previousBackStackEntry?.savedStateHandle?.set(
                KEY_FOR_VIDEO_LIKED,
                isLiked
            )
            findNavController().popBackStack()
        }
    }

    private fun setListener() {
        binding.btnLike.setOnClickListener {
            viewModel.updateLiked()
        }
        binding.btnShare.setOnClickListener {
            Toast.makeText(requireContext(),
                getString(R.string.not_implemented_yet), Toast.LENGTH_SHORT).show()
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