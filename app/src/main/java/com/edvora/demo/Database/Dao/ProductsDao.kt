package com.ibrahim.spikhairsaloon.Database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.edvora.demo.Model.ProductDetails


@Dao
interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(productDetails: ProductDetails)

     @Query("Select * from productdetails")
     fun getAll() : LiveData<List<ProductDetails>>

     @Query("Select Distinct(city) from productdetails")
     fun getCities() : List<String>

     @Query("Select Distinct(state) from productdetails")
     fun getStates() : List<String>

    @Query("Delete from productdetails")
    fun clearAll()

    @Query("Select Distinct(product_name) from productdetails")
    fun getAllProductNames() : LiveData<List<String>>

    @Query("Select Distinct(product_name) from productdetails")
    fun getAllProducts() : List<String>

    @Query("Select * from productdetails where product_name = :productName")
    fun getProductsWithTitle(productName : String) : List<ProductDetails>

}