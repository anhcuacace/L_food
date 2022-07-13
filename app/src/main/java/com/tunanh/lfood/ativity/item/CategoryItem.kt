package com.tunanh.lfood.ativity.item

import android.graphics.Bitmap

class CategoryItem {
    var icon: Bitmap
    var name: String

    public constructor(icon: Bitmap, name: String) {
        this.icon = icon
        this.name = name
    }
}