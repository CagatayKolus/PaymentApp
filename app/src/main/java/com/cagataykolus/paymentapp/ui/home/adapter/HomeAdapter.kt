package com.cagataykolus.paymentapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.cagataykolus.paymentapp.databinding.ItemPaymentMethodsBinding
import com.cagataykolus.paymentapp.model.Applicable
import com.cagataykolus.paymentapp.ui.home.viewholder.HomeViewHolder

/**
 * Created by Çağatay Kölüş on 27.08.2021.
 * cagataykolus@gmail.com
 */
class HomeAdapter (
    private val onItemClicked: (Applicable) -> Unit
) : ListAdapter<Applicable, HomeViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HomeViewHolder(
        ItemPaymentMethodsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) =
        holder.bind(getItem(position), onItemClicked)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Applicable>() {
            override fun areItemsTheSame(oldItem: Applicable, newItem: Applicable): Boolean =
                oldItem.label == newItem.label

            override fun areContentsTheSame(oldItem: Applicable, newItem: Applicable): Boolean =
                oldItem == newItem
        }
    }
}
