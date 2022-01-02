package com.edvora.demo.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductDetails(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var product_name: String,
    var brand_name: String,
    var price: Int,
    var state: String,
    var city: String,
    var discription: String,
    var date: String,
    var time: String,
    var image: String
)

