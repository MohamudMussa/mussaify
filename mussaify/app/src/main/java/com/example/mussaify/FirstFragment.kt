package com.example.mussaify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bugsnag.android.BreadcrumbType
import com.bugsnag.android.Bugsnag
import com.bugsnag.android.Configuration
import com.example.mussaify.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {

            try {
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

                //Click
                Bugsnag.leaveBreadcrumb("User Clicked on Password")

                //Feature
                Bugsnag.addFeatureFlag("Checkout button color", "Purple")

            } catch (e: Throwable) {
                Bugsnag.notify(e)
                Bugsnag.notify(RuntimeException("Incorrect Password/Email"))
                //I tried finding a way to use something other than 'to' so that I can log a incorrect login attempt
                Bugsnag.leaveBreadcrumb("Incorrect Username or Password")
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}