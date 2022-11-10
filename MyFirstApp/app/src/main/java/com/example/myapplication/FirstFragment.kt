package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.example.myapplication.databinding.FragmentFirstBinding

class FirstFragment: Fragment() {

    lateinit var binding: FragmentFirstBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)

        binding.btnFirstFragment.setOnClickListener {
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.container, SecondFragment())
            transaction.addToBackStack("second_transaction")
            transaction.commit()
        }
        return binding.root
    }
}