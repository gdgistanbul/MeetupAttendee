package com.gdgistanbul.attendence.ui.event_list


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gdgistanbul.attendence.R
import kotlinx.android.synthetic.main.fragment_event_list.*

/**
 * Created by Furkan on 10.07.2019
 */

class EventListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonNavigateUserList.setOnClickListener {
            val eventID = (0..123456789).random()
            val action = EventListFragmentDirections.actionEventListFragmentToUserListFragment(eventID)
            findNavController().navigate(action)
        }
    }
}
