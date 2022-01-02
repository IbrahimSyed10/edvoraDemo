package com.ibrahim.spikhairsaloon.Database.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.edvora.demo.Model.ProductDetails
import com.ibrahim.spikhairsaloon.Database.MyDatabase
import com.ibrahim.spikhairsaloon.Database.Repo.ProductDetailsRepo
import kotlinx.coroutines.launch

class ProductDetailsViewModel(application: Application):AndroidViewModel(application) {

    private val productDetailsRepo:ProductDetailsRepo


    init {
        val dao= MyDatabase.getDatabase(application).paymentDao()
        productDetailsRepo= ProductDetailsRepo(dao)
    }


    fun insert(){
        productDetailsRepo.insert()
    }


    fun fetchProducts():LiveData<List<ProductDetails>>{
        insert()
        return productDetailsRepo.fetchProfiles()
    }

    fun getAllProductNames() : LiveData<List<String>>{
        return productDetailsRepo.getAllProductNames()
    }

    fun getAllProductsWithTitles(productName : String) : List<ProductDetails>{
        return productDetailsRepo.getAllProductWithTitle(productName)
    }
}