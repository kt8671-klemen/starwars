package com.example.starwars.ui.dashboard.residents.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R

class ResidentFragment : Fragment() {
    private var columnCount = 1

    var residents: List<String> = ArrayList()
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            val args =
                ResidentFragmentArgs.fromBundle(
                    it
                )
            this.residents = args.residents.toList()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_resident_list, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.list)



        // Set the adapter
        with(recyclerView) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }

            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

            adapter = ResidentRecyclerViewAdapter(
                residents,
                object :
                    OnListFragmentInteractionListener {
                    override fun onListFragmentInteraction(item: String?) {
                        val url: String? = item

                        if (url != null) {
                            val split = url.split("/")
                            val id: String = split[split.size - 1]

                            navController.navigate(ResidentFragmentDirections.moveToResidentDetail(id))
                        }
                    }
                })
        }

        return view
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: String?)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)
    }
}
