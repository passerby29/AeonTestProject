package dev.passerby.aeon_project.presentation.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dev.passerby.aeon_project.databinding.FragmentLoginBinding
import dev.passerby.aeon_project.domain.models.LoginDataModel
import dev.passerby.aeon_project.presentation.viewmodels.LoginViewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = _binding ?: throw RuntimeException("FragmentLoginBinding is null")

    private val viewModel by lazy {
        ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkToken()

        addTextChangeListeners()
        observeViewModel()

        binding.loginCheckButton.setOnClickListener {
            binding.loading.visibility = View.VISIBLE
            binding.loginCheckButton.isEnabled = false

            val login = binding.loginLoginEditText.text?.trim().toString()
            val password = binding.loginPasswordEditText.text?.trim().toString()

            viewModel.login(LoginDataModel(login, password))
        }
    }

    private fun observeViewModel() {
        viewModel.apply {
            with(binding) {
                tokenSuccess.observe(viewLifecycleOwner) {
                    when (it) {
                        "" -> {
                            loginLoginContainer.error = null
                            loginPasswordContainer.error = null
                            binding.loginCheckButton.isEnabled = true
                        }

                        "false" -> {
                            loginLoginContainer.error = "Неправильные данные"
                            loginPasswordContainer.error = "Неправильные данные"
                            loading.visibility = View.GONE
                            binding.loginCheckButton.isEnabled = true
                        }

                        "true" -> {
                            loginLoginContainer.error = null
                            loginPasswordContainer.error = null
                            navToPayments()
                            loading.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    private fun addTextChangeListeners() {
        with(binding) {
            loginLoginEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    viewModel.resetToken()
                }

                override fun afterTextChanged(p0: Editable?) {}
            })
            loginPasswordEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    viewModel.resetToken()
                }

                override fun afterTextChanged(p0: Editable?) {}
            })
        }
    }

    private fun checkToken() {
        if (viewModel.isTokenAdded) {
            navToPayments()
        }
    }

    private fun navToPayments() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToPaymentsFragment())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}