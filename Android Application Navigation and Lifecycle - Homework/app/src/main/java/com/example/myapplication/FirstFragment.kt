package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.myapplication.databinding.FirstFragmentBinding

class FirstFragment: Fragment() {

    lateinit var binding: FirstFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FirstFragmentBinding.inflate(inflater, container, false)

        binding.btnFirstFragment.setOnClickListener { view ->
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment()
            view.findNavController().navigate(action)

        }

        return binding.root
    }

}