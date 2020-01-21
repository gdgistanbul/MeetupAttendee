package com.gdgistanbul.attendence.ui.event_list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.gdgistanbul.attendence.R
import com.gdgistanbul.attendence.extension.inflate
import com.gdgistanbul.attendence.extension.onClick
import com.gdgistanbul.extension.formatDateTime
import com.gdgistanbul.model.Event
import kotlinx.android.synthetic.main.item_event.view.*

class EventRecyclerAdapter(
    var onClickEvent: (event: Event) -> Unit = {}
) : ListAdapter<Event, EventRecyclerAdapter.EventViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        EventViewHolder(parent.inflate(R.layout.item_event, false))

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) = with(holder.itemView) {
        val event = currentList[position]

        onClick { onClickEvent(event) }
        eventImageView.load(event.featuredPhoto?.photoLink)
        eventNameTextView.text = event.name
        eventDateTextView.text = event.localDateTime?.formatDateTime(
            Event.LOCAL_DATE_TIME_PATTERN,
            context.getString(R.string.event_list_date_time_pattern)
        )
        eventLocationTextView.text = event.venue?.name
    }

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Event>() {
            override fun areItemsTheSame(oldItem: Event, newItem: Event) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Event, newItem: Event) = oldItem == newItem
        }
    }
}