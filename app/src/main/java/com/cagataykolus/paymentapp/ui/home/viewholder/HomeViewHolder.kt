package com.cagataykolus.paymentapp.ui.home.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.cagataykolus.paymentapp.R
import com.cagataykolus.paymentapp.databinding.ItemPaymentMethodsBinding
import com.cagataykolus.paymentapp.model.Applicable

/**
 * Created by Çağatay Kölüş on 27.08.2021.
 * cagataykolus@gmail.com
 */
class HomeViewHolder(private val binding: ItemPaymentMethodsBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(applicable: Applicable, onItemClicked: (Applicable) -> Unit) {
        binding.textviewPaymentMethodName.text = applicable.label
        binding.textviewPaymentMethodImage.load(applicable.links.logo) {
            placeholder(R.drawable.ic_placeholder)
            error(R.drawable.ic_broken_image)
        }

        binding.root.setOnClickListener {
            onItemClicked(applicable)
        }
    }
}