package com.interntask.wiseLeap.ui.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.interntask.wiseLeap.databinding.GridViewItemBinding
import com.interntask.wiseLeap.network.MarketItem

/**
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
 * data, including computing diffs between lists.
 */
class PhotoGridAdapter( private val onClickListener: OnClickListener ) :
        ListAdapter<MarketItem,
                PhotoGridAdapter.MarsPropertyViewHolder>(DiffCallback) {

    /**
     * The MarsPropertyViewHolder constructor takes the binding variable from the associated
     * GridViewItem, which nicely gives it access to the full [MarketItem] information.
     */
    class MarsPropertyViewHolder(private var binding: GridViewItemBinding):
            RecyclerView.ViewHolder(binding.root) {
        fun bind(marketItem: MarketItem) {
            binding.item = marketItem
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [MarketItem]
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<MarketItem>() {
        override fun areItemsTheSame(oldItem: MarketItem, newItem: MarketItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: MarketItem, newItem: MarketItem): Boolean {
            return oldItem.symbol == newItem.symbol
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MarsPropertyViewHolder {
        return MarsPropertyViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: MarsPropertyViewHolder, position: Int) {
        val marsProperty = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(marsProperty)
        }
        holder.bind(marsProperty)
    }

    /**
     * Custom listener that handles clicks on [RecyclerView] items.  Passes the [MarketItem]
     * associated with the current item to the [onClick] function.
     * @param clickListener lambda that will be called with the current [MarketItem]
     */
    class OnClickListener(val clickListener: (marsProperty:MarketItem) -> Unit) {
        fun onClick(marsProperty:MarketItem) = clickListener(marsProperty)
    }
}

