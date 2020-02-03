package com.gdgistanbul.attendence.ui.event_list


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import coil.api.load
import coil.transform.CircleCropTransformation
import com.gdgistanbul.attendence.R
import com.gdgistanbul.attendence.extension.*
import com.gdgistanbul.model.Event
import com.gdgistanbul.model.EventStatus
import com.gdgistanbul.model.Member
import com.gdgistanbul.viewmodel.EventListViewModel
import kotlinx.android.synthetic.main.fragment_event_list.*
import kotlinx.android.synthetic.main.layout_event_list_greeting.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class EventListFragment : Fragment() {
    private val eventListViewModel by viewModel<EventListViewModel>()
    private val eventAdapter = eventListAdapter(::navigateToUserList)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_event_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSearchView()
        initTabLayout()
        initRecyclerView()
        observe(eventListViewModel.memberLiveData, ::showGreeting)
        observe(eventListViewModel.eventsLiveData, ::showEventList)
        observeEvent(eventListViewModel.toastLiveData, ::toast)
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

        onQueryTextChange = { eventListViewModel.query = it }
    }

    private fun initTabLayout() = with(tabLayout) {
        onTabSelected {
            eventListViewModel.eventStatus = tabEventStatusList[it.position]
        }
    }

    private fun initRecyclerView() = with(recyclerView) {
        setHasFixedSize(true)
        adapter = eventAdapter
    }

    private fun showGreeting(member: Member) = with(greetingLayout) {
        avatarImageView.load(member.photo?.photoLink) {
            crossfade(true)
            transformations(CircleCropTransformation())
        }
        titleTextView.text =
            context?.getString(R.string.event_list_greeting_title, member.firstName)
        subtitleTextView.setText(R.string.event_list_greeting_subtitle)
    }

    private fun showEventList(eventList: List<Event>) {
        eventAdapter.submitList(eventList)
    }

    private fun navigateToUserList(event: Event) {
        event.id?.let {
            navigate(
                EventListFragmentDirections.actionEventListFragmentToUserListFragment(it)
            )
        } ?: toast("Event ID cannot be null")
    }

    companion object {
        private val tabEventStatusList = listOf(EventStatus.UPCOMING, EventStatus.PAST)
    }
}
