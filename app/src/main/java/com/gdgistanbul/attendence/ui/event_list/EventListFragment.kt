package com.gdgistanbul.attendence.ui.event_list


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gdgistanbul.attendence.R
import kotlinx.android.synthetic.main.fragment_event_list.*

class EventListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_event_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonNavigateUserList.setOnClickListener {
            val eventID = (0..99999999).random()
            val action = EventListFragmentDirections.actionEventListFragmentToUserListFragment(eventID)
            findNavController().navigate(action)
        }
    }
}
