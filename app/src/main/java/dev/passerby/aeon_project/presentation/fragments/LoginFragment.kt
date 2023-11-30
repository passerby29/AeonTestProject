package dev.passerby.aeon_project.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dev.passerby.aeon_project.databinding.FragmentLoginBinding
import dev.passerby.aeon_project.domain.models.LoginDataModel
import dev.passerby.aeon_project.presentation.viewmodels.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

        binding.loginMainButton.setOnClickListener {
            val login = binding.loginLoginEditText.text?.trim().toString()
            val password = binding.loginPasswordEditText.text?.trim().toString()

            viewModel.login(LoginDataModel(login, password))
            viewModel.tokenSuccess.observe(viewLifecycleOwner) {
                    if (it.equals("true")) {
                        nav()
                    } else {
                        binding.loginLoginEditText.error = "error data"
                        binding.loginPasswordEditText.error = "error data"
                    }
            }
        }
    }

    private fun nav(){
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToPaymentsFragment())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}