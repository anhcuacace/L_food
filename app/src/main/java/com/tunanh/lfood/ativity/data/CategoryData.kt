package com.tunanh.lfood.ativity.data

import com.tunanh.lfood.R

class CategoryData {
    var img: List<Int> = listOf(
        R.drawable.rice,
        R.drawable.banh_mi,
        R.drawable.bubble_tea,
        R.drawable.fast_food,
        R.drawable.tea,
        R.drawable.international,
        R.drawable.junk_food,
        R.drawable.vegetable
    )
        get() = field
    var name: List<Int> = listOf(
        R.string.Vietnamese_rice,
        R.string.other_Vietnamese_dishes,
        R.string.milktea,
        R.string.fastFood,
        R.string.coffee_and_tea,
        R.string.international_dishes,
        R.string.junk_food,
        R.string.vegetarian_food
    )
        get() = field

}

class itemfood {
    var saleOff: List<Int> = listOf(
        R.drawable.saleoff70,
        R.drawable.off50, R.drawable.off70
    )
        get() = field
    var imgFood: List<Int> = listOf(
        R.drawable.food1, R.drawable.food2
    )
        get() = field
    var distance: List<Int> = listOf(
        R.string.distance1, R.string.distance2, R.string.distance3, R.string.distance4
    )
    get() = field
    var name: List<Int> = listOf(
        R.string.name_food1, R.string.name_food2
    )
        get() = field
    var ratting: List<Int> = listOf(
        R.string.ratting1, R.string.ratting2, R.string.ratting3, R.string.ratting4
    )
        get() = field
}