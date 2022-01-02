package com.ibrahim.spikhairsaloon.Database.Repo

import android.content.LocusId
import android.util.Log
import androidx.lifecycle.LiveData
import com.edvora.demo.Model.ProductDetails
import com.edvora.demo.Network.NetworkService
import com.edvora.demo.Network.RetrofitAPIClient
import com.edvora.demo.Network.RetrofitAPIClient.retrofitInstance
import com.ibrahim.spikhairsaloon.Database.Dao.ProductsDao
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers


class ProductDetailsRepo(private val dao: ProductsDao) {
    private val networkSerice: NetworkService = retrofitInstance.create(
        NetworkService::class.java
    )
    var compositeDisposable = CompositeDisposable()

     fun insert() {

         dao.clearAll()

        compositeDisposable.add(
            networkSerice.getProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { products ->
                        for (product in products) {
                            Log.i("data:", product.toString())

                            dao.insert(
                                ProductDetails(
                                    0,
                                    product.product_name,
                                    product.brand_name,
                                    product.price,
                                    product.address.state,
                                    product.address.city,
                                    product.discription,
                                    product.date,
                                    product.time,
                                    product.image
                                )
                            )
                        }
                    },
                    { throwable ->
                        Log.e("Error:", throwable.message ?: "onError")
                    }
                )
        )

    }

     fun fetchProfiles() : LiveData<List<ProductDetails>> {

      return  dao.getAll()
    }

    fun getAllProductNames() : LiveData<List<String>>{
        return dao.getAllProductNames()
    }

    fun getAllProductWithTitle(productTitle : String) : List<ProductDetails>{
        return dao.getProductsWithTitle(productTitle)
    }
}