package com.example.starwars.ui.dashboard.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.starwars.R
import com.example.starwars.databinding.FragmentPlanetImageBinding

class PlanetImageFragment : Fragment() {
    private var imageUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val args = PlanetImageFragmentArgs.fromBundle(it)
            imageUrl = args.imageUrl
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_planet_image, container, false)

        val binding = FragmentPlanetImageBinding.bind(view)

        Glide
            .with(view)
            .load(imageUrl)
            .into(binding.imageUrl)

        return view
    }
}
