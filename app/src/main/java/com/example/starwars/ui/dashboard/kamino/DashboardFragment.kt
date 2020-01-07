package com.example.starwars.ui.dashboard.kamino

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.starwars.R
import com.example.starwars.api.Planet
import com.example.starwars.databinding.FragmentDashboardBinding
import com.example.starwars.util.Message

class DashboardFragment : Fragment() {
    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var navController: NavController

    private var liked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)

        dashboardViewModel.planet

        dashboardViewModel.message.observe(this, Observer {
            Message.show(requireView(), it)
        })

        dashboardViewModel.liveLike.observe(this, Observer {
            var message = it.toString()

            if (liked) {
                message = "Already liked. $message"
            } else {
                liked = true
            }

            Message.show(requireView(), message)
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        val binding = FragmentDashboardBinding.bind(view)

        binding.planet = Planet()

        binding.image.setOnClickListener {
            dashboardViewModel.imageUrl.observe(viewLifecycleOwner, Observer {
                navController.navigate(DashboardFragmentDirections.moveToPlanetImage(it))
            })
        }

        binding.show.setOnClickListener {
            dashboardViewModel.residents.observe(viewLifecycleOwner, Observer {
                navController.navigate(DashboardFragmentDirections.moveToResidents(it.toTypedArray()))
            })
        }

        binding.like.setOnClickListener {
            dashboardViewModel.like()
        }

        dashboardViewModel.livePlanet.observe(this, Observer { planet ->
            binding.planet = planet

            binding.imageUrl = planet.imageUrl

            planet.residents?.let { dashboardViewModel.publish(it) }
        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)
    }
}
