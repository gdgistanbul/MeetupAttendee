package com.gdgistanbul.attendence.ui.event_list


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gdgistanbul.attendence.R
import com.gdgistanbul.attendence.extension.navigate
import com.gdgistanbul.attendence.extension.onClick
import com.gdgistanbul.model.Event
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

        buttonNavigateUserList.onClick {
            // TODO - EventID Will replace with returning eventID from MeetupAPI
            val eventID = (0..99999999).random()
            navigate(EventListFragmentDirections.actionEventListFragmentToUserListFragment(eventID))
        }

//        val event:Event = Event()
//        navigate(EventListFragmentDirections.actionEventListFragmentToEventDetailFragment(event))
    }
}
