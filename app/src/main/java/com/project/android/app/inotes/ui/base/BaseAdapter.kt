package com.project.android.app.inotes.ui.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.project.android.app.inotes.data.model.Entity

/**
 * @param<B> - mean layout binding
 * @param<H> - mean recycler view holder
 * @param<M> - mean model class
 */
abstract class BaseAdapter<B: ViewDataBinding, H: RecyclerView.ViewHolder, M: Entity>
    (private val context: Context, private val listItem: List<M>) : RecyclerView.Adapter<H>() {

    private var binding: B? = null

    protected abstract fun getContentView(): Int

    protected abstract fun getViewHolder(ui: View): H

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): H {
        binding = DataBindingUtil.inflate<B>(LayoutInflater.from(context), getContentView(), parent, false)

        return getViewHolder(binding?.root!!)
    }

    override fun getItemCount(): Int = listItem.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int = position

    protected fun getBinding() = binding

}