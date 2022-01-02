package com.edvora.demo.Network

import com.edvora.demo.Model.NetworkModel.Product
import com.edvora.demo.Model.ProductDetails
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import java.util.*

interface NetworkService {

    @GET("/")
    public fun getProducts() : Observable<List<Product>>
}