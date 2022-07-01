package com.tunanh.lfood.ativity.item

class itemFood {


    var saleOff:Int
    var imgfood:Int
    var distance:String
    var foodName:String
    var rating:String
    public constructor(saleOff: Int, imgfood: Int, distance: String, foodName: String, rating: String) {
        this.saleOff = saleOff
        this.imgfood = imgfood
        this.distance = distance
        this.foodName = foodName
        this.rating = rating
    }
}