package com.edvora.demo.Model.NetworkModel

data class Product (
    var product_name: String,
    var brand_name: String,
    var price : Int,
    var discription: String,
    var date: String,
    var time: String,
    var image: String,
    var address: Address


) {
    override fun toString(): String {
        return "Product(product_name='$product_name', brand_name='$brand_name', price=$price, discription='$discription', date='$date', time='$time', image='$image', address=$address)"
    }
}
