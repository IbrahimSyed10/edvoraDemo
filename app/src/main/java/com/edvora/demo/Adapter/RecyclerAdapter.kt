package com.edvora.demo.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.edvora.demo.MainActivity
import com.edvora.demo.Model.ProductDetails
import com.edvora.demo.databinding.ItemParentRecyclerBinding
import com.ibrahim.spikhairsaloon.Database.MyDatabase
import com.ibrahim.spikhairsaloon.Database.Repo.ProductDetailsRepo
import com.ibrahim.spikhairsaloon.Database.ViewModel.ProductDetailsViewModel

class RecyclerAdapter(val context: Context) :
    RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {

    var productDetailsList: List<String> = ArrayList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewHolder {
        return RecyclerViewHolder(
            ItemParentRecyclerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun updateData(productDetailsList: List<String>) {

        this.productDetailsList = productDetailsList
        notifyDataSetChanged()
    }

    lateinit var productDetailsLists: List<ProductDetails>

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        var item = productDetailsList[position]

        holder.binding.title.text = item


        var aRdapter: ChildRecyclerAdapter

        holder.binding.recycler.apply {
            layoutManager= LinearLayoutManager(context, HORIZONTAL,false)
            aRdapter = ChildRecyclerAdapter(context)
            adapter= aRdapter
        }

        //main thread query for pager data list(no need of live data here as data already fetched earlier)
        productDetailsLists =
            MyDatabase.getDatabase(context).paymentDao().getProductsWithTitle(item)

        aRdapter.updateData(productDetailsLists)




    }


    override fun getItemCount(): Int {
        return productDetailsList.size;
    }

    class RecyclerViewHolder(val binding: ItemParentRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root)

}