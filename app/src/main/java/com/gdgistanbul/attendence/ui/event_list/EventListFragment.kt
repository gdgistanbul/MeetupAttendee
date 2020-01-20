package com.gdgistanbul.attendence.ui.event_list


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.gdgistanbul.attendence.R
import com.gdgistanbul.attendence.extension.addTab
import com.gdgistanbul.attendence.extension.navigate
import com.gdgistanbul.attendence.extension.onClick
import kotlinx.android.synthetic.main.fragment_event_list.*
import kotlinx.android.synthetic.main.layout_event_list_greeting.view.*


class EventListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_event_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSearchView()

        greetingLayout.titleTextView.onClick {
            // TODO - EventID Will replace with returning eventID from MeetupAPI
            val eventID = (0..99999999).random()
            navigate(EventListFragmentDirections.actionEventListFragmentToUserListFragment(eventID))
        }

        tabLayout.apply {
            addTab(R.string.upcoming_events)
            addTab(R.string.past_events)
        }
    }

    private fun initSearchView() = with(searchView) {
        measure(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )

        layoutParams = (layoutParams as ViewGroup.MarginLayoutParams).apply {
            topMargin = -(measuredHeight / 2)
        }

        collapsingToolbarLayout.minimumHeight = measuredHeight / 2
    }
}
