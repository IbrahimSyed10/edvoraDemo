package com.edvora.demo.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.edvora.demo.Model.ProductDetails
import com.edvora.demo.R
import com.edvora.demo.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.item_child.view.*
import kotlinx.android.synthetic.main.item_parent_recycler.view.*

class MyPagerAdapter(val context: Context) : PagerAdapter() {

   var productDetailsList : List<ProductDetails> = ArrayList()

    override fun getCount(): Int {
        return productDetailsList.size
    }

    fun updateData(productDetailsList: List<ProductDetails>) {
        this.productDetailsList = productDetailsList
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val view = LayoutInflater.from(context).inflate(R.layout.item_child, container, false)

        val item = productDetailsList[position]

        view.product_name.text = item.product_name
        view.brand_name.text = item.brand_name
        view.price.text = item.price.toString()

        container.addView(view, position)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}