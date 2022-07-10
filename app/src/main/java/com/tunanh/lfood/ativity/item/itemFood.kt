package com.tunanh.lfood.ativity.item

import android.graphics.Bitmap
import android.media.Image

class itemFood {



    var saleOff:Bitmap
    var imgfood:Bitmap
    var distance:String
    var foodName:String
    var rating:String
    public constructor(saleOff: Bitmap, imgfood: Bitmap, distance: String, foodName: String, rating: String) {
        this.saleOff = saleOff
        this.imgfood = imgfood
        this.distance = distance
        this.foodName = foodName
        this.rating = rating
    }
}