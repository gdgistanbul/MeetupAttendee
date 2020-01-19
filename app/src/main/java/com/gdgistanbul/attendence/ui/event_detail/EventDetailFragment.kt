package com.gdgistanbul.attendence.ui.event_detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.gdgistanbul.attendence.extension.observe
import com.gdgistanbul.model.Event
import com.gdgistanbul.viewmodel.MeetupViewModel
import kotlinx.android.synthetic.main.fragment_event_detail.*
import org.koin.android.ext.android.inject

class EventDetailFragment : Fragment() {
    private val viewModel: MeetupViewModel by inject()
    private val args: EventDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.eventLiveData) { initUI(it) }

        var eventID = args.eventID

        viewModel.getEvent(eventID)


    }

    fun initUI(event: Event) {
        btnBack.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }

        txtEventName.text = event.name
        txtEventDate.text = event.localDate
        txtEventVenue.text = event.venue?.name

        txtAttendee.text = "${event.yesRsvpCount} Attendees"

        txtDetail.text = event.description

        btnCheckIn.setOnClickListener {
            //TODO attendees page
        }

        //TODO need event photo link
//        Glide.with(context)
//            .load(event.)
//            .transition(DrawableTransitionOptions.withCrossFade())
//            .skipMemoryCache(true)
//            .into(ivImage)
    }
}