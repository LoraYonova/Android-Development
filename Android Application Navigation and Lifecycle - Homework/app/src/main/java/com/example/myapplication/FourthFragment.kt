package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.myapplication.databinding.FourthFragmntBinding
import com.example.myapplication.databinding.SecondFragmentBinding

class FourthFragment: Fragment() {

    lateinit var binding: FourthFragmntBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FourthFragmntBinding.inflate(inflater, container, false)
        binding.btnFourthFragment.setOnClickListener { view ->
            val action = FourthFragmentDirections.actionFourthFragmentToFirstFragment()
            view.findNavController().navigate(action)
        }
        return binding.root
    }
}