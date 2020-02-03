package com.gdgistanbul.recyclerviewdsl

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

fun <T : Any> listAdapter(
    @LayoutRes layoutRes: Int,
    diffCallback: DiffUtil.ItemCallback<T>,
    viewHolderBlock: ListAdapterViewHolder<T>.() -> Unit
) = BaseListAdapter(layoutRes, diffCallback, viewHolderBlock)

fun <T : Any> listAdapter(
    @LayoutRes layoutRes: Int,
    viewHolderBlock: ListAdapterViewHolder<T>.() -> Unit
) = BaseListAdapter(layoutRes, defaultDiffCallback(), viewHolderBlock)

private fun <T> defaultDiffCallback() = object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem == newItem
    override fun areContentsTheSame(oldItem: T, newItem: T) = true
}

class BaseListAdapter<T : Any>(
    @LayoutRes private val layoutRes: Int,
    diffCallback: DiffUtil.ItemCallback<T>,
    private val viewHolderBlock: ListAdapterViewHolder<T>.() -> Unit
) : ListAdapter<T, ListAdapterViewHolder<T>>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapterViewHolder<T> {
        val itemView = LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
        return ListAdapterViewHolder<T>(itemView).apply(viewHolderBlock)
    }

    override fun onBindViewHolder(holder: ListAdapterViewHolder<T>, position: Int) {
        holder.apply {
            item = currentList[position]
            bind?.invoke()
        }
    }
}

class ListAdapterViewHolder<T : Any>(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    val context: Context get() = containerView.context

    lateinit var item: T
        internal set

    internal var bind: (() -> Unit)? = null

    fun bind(block: () -> Unit) {
        bind = block
    }
}