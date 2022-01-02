package com.edvora.demo.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edvora.demo.Model.ProductDetails
import com.edvora.demo.databinding.ItemChildBinding
import com.edvora.demo.databinding.ItemParentRecyclerBinding
import com.ibrahim.spikhairsaloon.Database.MyDatabase
import kotlinx.android.synthetic.main.item_child.view.*

class ChildRecyclerAdapter(val context: Context) :
    RecyclerView.Adapter<ChildRecyclerAdapter.ChildRecyclerViewHolder>() {

    var productDetailsList: List<ProductDetails> = ArrayList()

    fun updateData(productDetailsList: List<ProductDetails>) {

        this.productDetailsList = productDetailsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChildRecyclerAdapter.ChildRecyclerViewHolder {
        return ChildRecyclerAdapter.ChildRecyclerViewHolder(
            ItemChildBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ChildRecyclerAdapter.ChildRecyclerViewHolder,
        position: Int
    ) {
        var item = productDetailsList[position]

        if(productDetailsList.size > 0){
            if(position == 0){
                return
            }
        }

        holder.binding.productName.text = item.product_name
        holder.binding.brandName.text = item.brand_name
        holder.binding.price.text = item.price.toString()
        holder.binding.date.text = item.date
        holder.binding.location.text = item.city + "," +item.state
        holder.binding.description.text = item.discription

        Glide.with(context).load(item.image).into(holder.binding.image)


    }

    override fun getItemCount(): Int {
     return productDetailsList.size
    }


    class ChildRecyclerViewHolder(val binding: ItemChildBinding) :
        RecyclerView.ViewHolder(binding.root)

}