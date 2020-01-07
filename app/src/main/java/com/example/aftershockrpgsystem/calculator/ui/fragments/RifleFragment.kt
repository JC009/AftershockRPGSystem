package com.example.aftershockrpgsystem.calculator.ui.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.aftershockrpgsystem.R
import kotlinx.android.synthetic.main.fragment_rifle.*

class RifleFragment : Fragment() {

    companion object {
        fun newInstance() = RifleFragment()
    }

    private lateinit var viewModel: RifleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rifle, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RifleViewModel::class.java)
        // TODO: Use the ViewModel

    }

}
