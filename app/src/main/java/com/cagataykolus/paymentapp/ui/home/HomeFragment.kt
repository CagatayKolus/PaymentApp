package com.cagataykolus.paymentapp.ui.home

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cagataykolus.paymentapp.R
import com.cagataykolus.paymentapp.databinding.FragmentHomeBinding
import com.cagataykolus.paymentapp.model.Applicable
import com.cagataykolus.paymentapp.model.State
import com.cagataykolus.paymentapp.ui.home.adapter.HomeAdapter
import com.cagataykolus.paymentapp.util.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Çağatay Kölüş on 27.08.2021.
 * cagataykolus@gmail.com
 */
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding by viewBinding { FragmentHomeBinding.bind(it) }
    private val viewModel by viewModels<HomeViewModel>()

    private val mAdapter = HomeAdapter(this::onItemClicked)

    override fun onResume() {
        super.onResume()
        getApplicables()
    }

    override fun onStart() {
        super.onStart()

        observeApplicables()
        handleNetworkChanges()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle()
        initView()
    }

    private fun setTitle() {
        requireActivity().title = getString(R.string.payment_methods)
    }

    /**
     * Observe data.
     */
    private fun observeApplicables() {
        viewModel.applicablesLiveData.observe(viewLifecycleOwner) { state ->
            hideKeyboard()
            when (state) {
                is State.Loading -> {
                    showLoading(true)
                }
                is State.Success -> {
                    if (state.data.isNotEmpty()) {
                        mAdapter.submitList(state.data.toMutableList())
                        showLoading(false)
                    }
                }
                is State.Error -> {
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                    showLoading(false)
                }
            }
        }
    }

    private fun getApplicables() = viewModel.getApplicables()

    /**
     * Initialize recyclerview with values.
     */
    private fun initView() {
        binding.run {
            recyclerviewHomeList.adapter = mAdapter
            swiperefreshlayoutHomeRefresh.setOnRefreshListener { getApplicables() }

        }
        viewModel.applicablesLiveData.value?.let { currentState ->
            if (!currentState.isSuccessful()) {
                getApplicables()
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.swiperefreshlayoutHomeRefresh.isRefreshing = isLoading
    }

    private fun onItemClicked(applicable: Applicable) {
        Toast.makeText(requireContext(), applicable.label, Toast.LENGTH_SHORT).show()
    }

    /**
     * Observes network changes.
     */
    private fun handleNetworkChanges() {
        NetworkUtils.getNetworkLiveData(requireContext()).observe(this) { isConnected ->
            if (!isConnected) {
                binding.textviewHomeNetworkStatus.text =
                    getString(R.string.internet_connectivity_fail)
                binding.linearlayoutHomeNetworkStatus.apply {
                    show()
                    setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.connectivity_fail
                        )
                    )
                }
            } else {
                if (viewModel.applicablesLiveData.value is State.Error || mAdapter.itemCount == 0) {
                    getApplicables()
                }
                binding.textviewHomeNetworkStatus.text =
                    getString(R.string.internet_connectivity_success)
                binding.linearlayoutHomeNetworkStatus.apply {
                    setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.connectivity_success
                        )
                    )

                    animate()
                        .alpha(1f)
                        .setStartDelay(1000L)
                        .setDuration(1000L)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                hide()
                            }
                        })
                }
            }
        }
    }
}