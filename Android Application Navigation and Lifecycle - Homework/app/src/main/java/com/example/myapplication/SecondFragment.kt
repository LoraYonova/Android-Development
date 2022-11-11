package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.databinding.SecondActivityBinding
import com.example.myapplication.databinding.SecondFragmentBinding

class SecondFragment: Fragment() {

    lateinit var binding: SecondFragmentBinding



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SecondFragmentBinding.inflate(inflater, container, false)

        binding.btnSecondFragment.setOnClickListener{ view ->
            val action = SecondFragmentDirections.actonSecondFragmentToThirdFragment()
            view.findNavController().navigate(action)
        }

        return binding.root
    }


}