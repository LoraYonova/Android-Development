package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.myapplication.databinding.SecondFragmentBinding
import com.example.myapplication.databinding.ThirdFragmentBinding

class ThirdFragment: Fragment() {

    lateinit var binding: ThirdFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ThirdFragmentBinding.inflate(inflater, container, false)
        binding.btnThirdFragment.setOnClickListener { view ->
        val action = ThirdFragmentDirections.actionThirdFragmentToFourthFragment()
            view.findNavController().navigate(action)
        }
        return binding.root
    }

}