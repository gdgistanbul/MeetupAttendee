package com.gdgistanbul.attendence.ui.event_list

import androidx.recyclerview.widget.DiffUtil
import coil.api.load
import com.gdgistanbul.attendence.R
import com.gdgistanbul.attendence.extension.onClick
import com.gdgistanbul.extension.android.listAdapter
import com.gdgistanbul.extension.formatDateTime
import com.gdgistanbul.model.Event
import kotlinx.android.synthetic.main.item_event.*

fun eventListAdapter(onClickEvent: (event: Event) -> Unit = {}) =
    listAdapter(R.layout.item_event, eventDiffCallback) {
        itemView.onClick {
            onClickEvent(item)
        }

        bind {
            eventImageView.load(item.featuredPhoto?.photoLink)
            eventNameTextView.text = item.name
            eventDateTextView.text = item.localDateTime?.formatDateTime(
                Event.LOCAL_DATE_TIME_PATTERN,
                context.getString(R.string.event_list_date_time_pattern)
            )
            eventLocationTextView.text = item.venue?.name
        }
    }

private val eventDiffCallback
    get() = object : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Event, newItem: Event) = oldItem == newItem
    }