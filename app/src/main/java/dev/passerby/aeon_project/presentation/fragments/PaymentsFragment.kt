package dev.passerby.aeon_project.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dev.passerby.aeon_project.databinding.FragmentPaymentsBinding
import dev.passerby.aeon_project.presentation.adapters.PaymentsAdapter
import dev.passerby.aeon_project.presentation.viewmodels.PaymentsViewModel

class PaymentsFragment : Fragment() {

    private var _binding: FragmentPaymentsBinding? = null
    private val binding: FragmentPaymentsBinding
        get() = _binding ?: throw RuntimeException("FragmentPaymentsBinding is null")

    private val viewModel by lazy {
        ViewModelProvider(this)[PaymentsViewModel::class.java]
    }

    private lateinit var paymentsAdapter: PaymentsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        paymentsAdapter = PaymentsAdapter()
        binding.paymentsRecycler.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

            adapter = paymentsAdapter
        }
        viewModel.payments.observe(viewLifecycleOwner) {
            paymentsAdapter.submitList(it)
        }
        binding.paymentsLogoutButton.setOnClickListener {
            findNavController().navigate(PaymentsFragmentDirections.actionPaymentsFragmentToLoginFragment())
            viewModel.removeToken()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}