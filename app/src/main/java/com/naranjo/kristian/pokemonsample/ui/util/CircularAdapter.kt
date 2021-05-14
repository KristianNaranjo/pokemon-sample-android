package com.naranjo.kristian.pokemonsample.ui.util

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class CircularAdapter<T, R, VH : RecyclerView.ViewHolder>(identifier: T.() -> R) :
    ListAdapter<T, VH>(DefaultDiffUtil(identifier)) {
    var itemList: List<T> = emptyList()

    abstract override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VH

    override fun onBindViewHolder(holder: VH, position: Int) {
        if (itemList.isEmpty()) return

        onBindCircularViewHolder(holder, position)
    }

    abstract fun onBindCircularViewHolder(holder: VH, position: Int)

    override fun getItemCount(): Int = Int.MAX_VALUE

    override fun submitList(list: List<T>?) {
        itemList = list ?: emptyList()

        super.submitList(list)
    }

    fun getCenterPage(position: Int = 0) = Int.MAX_VALUE / 2 + position

    fun Int.toCircularPosition(): Int = calculateCircularPosition(
        position = this,
        itemCount,
        itemList.size
    )
}

class DefaultDiffUtil<T, R>(private val identifier: T.() -> R) : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem.identifier() == newItem.identifier()

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem == newItem

}