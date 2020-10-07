package com.android.gerchik.presentation.utils.adapterdelegates

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager

internal class NumberListDelegationAdapter(
    private val delegatesManager: AdapterDelegatesManager<List<Int>>,
    minValue: Int = 0,
    maxValue: Int = 12
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    constructor(
        vararg delegate: AdapterDelegate<List<Int>>,
        minValue: Int = 0,
        maxValue: Int = 12
    ) : this(AdapterDelegatesManager(*delegate), minValue, maxValue)

    var minValue = minValue
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var maxValue = maxValue
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun addDelegate(delegate: AdapterDelegate<List<Int>>) {
        delegatesManager.addDelegate(delegate)
    }

    fun getValue(position: Int) = minValue + position

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegatesManager.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegatesManager.onBindViewHolder(getList(position), 0, holder, null)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: List<*>
    ) {
        delegatesManager.onBindViewHolder(getList(position), 0, holder, payloads)
    }

    override fun getItemViewType(position: Int): Int {
        return delegatesManager.getItemViewType(getList(position), 0)
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        delegatesManager.onViewRecycled(holder)
    }

    override fun onFailedToRecycleView(holder: RecyclerView.ViewHolder): Boolean {
        return delegatesManager.onFailedToRecycleView(holder)
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        delegatesManager.onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        delegatesManager.onViewDetachedFromWindow(holder)
    }

    override fun getItemCount() = maxValue - minValue

    override fun getItemId(position: Int): Long {
        return (minValue + position).toLong()
    }

    private fun getList(position: Int) = listOf(minValue + position)
}