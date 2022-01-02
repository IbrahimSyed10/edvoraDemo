package com.edvora.demo

import android.R.attr
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.edvora.demo.Adapter.MyPagerAdapter
import com.edvora.demo.Adapter.RecyclerAdapter
import com.edvora.demo.databinding.ActivityMainBinding
import com.ibrahim.spikhairsaloon.Database.ViewModel.ProductDetailsViewModel
import android.view.WindowManager

import android.view.Gravity
import android.view.Window
import android.R.attr.gravity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import com.ibrahim.spikhairsaloon.Database.MyDatabase


class MainActivity : AppCompatActivity() {

    lateinit var productDetailsViewModel: ProductDetailsViewModel

    lateinit var binding: ActivityMainBinding

    lateinit var recyclerAdapter: RecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        productDetailsViewModel = ViewModelProvider(this).get(ProductDetailsViewModel::class.java)
        binding.progress.visibility = VISIBLE
        binding.filterSpinner.isEnabled = false


        setDialog()
        initDialogDropDowns()
        setRecycler()
        updateRecycler()

        binding.filterSpinner.setOnClickListener(View.OnClickListener {


            dialog.show()

            Toast.makeText(
                this,
                "Please Provide products,states and cities API's to fetch data",
                LENGTH_LONG
            ).show()

        })


    }










    fun initDialogDropDowns() {
        val products = dialog.findViewById<AutoCompleteTextView>(R.id.products)
        val cities = dialog.findViewById<AutoCompleteTextView>(R.id.cities)
        val states = dialog.findViewById<AutoCompleteTextView>(R.id.states)

        val stateAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(
                this@MainActivity,
                R.layout.item_dropdown,
                MyDatabase.getDatabase(this).paymentDao().getStates()
            )
        states.setAdapter(stateAdapter)

        val cityAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(
                this@MainActivity,
                R.layout.item_dropdown,
                MyDatabase.getDatabase(this).paymentDao().getCities()
            )
        cities.setAdapter(cityAdapter)


        val productsAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(
                this@MainActivity,
                R.layout.item_dropdown,
                MyDatabase.getDatabase(this).paymentDao().getAllProducts()
            )
        products.setAdapter(productsAdapter)
    }

    lateinit var dialog: Dialog

    fun setDialog() {

        dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_filter)

        val window = dialog.window

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

    }


    private fun setRecycler() {
//        val pagerAdapter: MyPagerAdapter
//        pagerAdapter = MyPagerAdapter(this)

        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            recyclerAdapter = RecyclerAdapter(context)
            adapter = recyclerAdapter
        }

    }


    private fun updateRecycler() {


        productDetailsViewModel.fetchProducts().observe(this, { products ->
            if (products.size != 0) {

                productDetailsViewModel.getAllProductNames().observe(this, { names ->
                    recyclerAdapter.updateData(names)

                    binding.progress.visibility = GONE
                    binding.filterSpinner.isEnabled = true
                })
            }

        })
    }

    fun setPosition(yValue: Int) {
        val window: Window = window
        val param: WindowManager.LayoutParams = window.getAttributes()
        param.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
        param.y = yValue
        window.setAttributes(param)
        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
    }
}