package com.example.starwars.ui.dashboard.residents.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.starwars.R
import com.example.starwars.databinding.FragmentResidentDetailBinding

class ResidentDetailFragment : Fragment() {
    private lateinit var residentDetailViewModel: ResidentDetailViewModel
    private lateinit var residentId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        residentDetailViewModel = ViewModelProviders.of(this).get(ResidentDetailViewModel::class.java)

        arguments?.let {
            val args = ResidentDetailFragmentArgs.fromBundle(it)

            residentId = args.residentId

            residentDetailViewModel.getResident(residentId)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_resident_detail, container, false)

        val binding = FragmentResidentDetailBinding.bind(view)

        residentDetailViewModel.liveResident.observe(viewLifecycleOwner, Observer {
            binding.resident = it
        })

        return view
    }
}
