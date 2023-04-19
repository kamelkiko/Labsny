package com.example.labsny.data

import com.example.labsny.R
import com.example.labsny.data.models.IdImage

class ImageSource {

    private val classicJacket: List<IdImage> = listOf(
        IdImage(R.drawable.jacket_1),
        IdImage(R.drawable.pant_1),
        IdImage(R.drawable.shoes_1)
    )

    private val classicJacket1: List<IdImage> = listOf(
        IdImage(R.drawable.jacket_2),
        IdImage(R.drawable.pant_2),
        IdImage(R.drawable.shoes_2)
    )

    private val classicJacket2: List<IdImage> = listOf(
        IdImage(R.drawable.jacket_3),
        IdImage(R.drawable.pant_3),
        IdImage(R.drawable.shoes_3)
    )

    private val classicJacket3: List<IdImage> = listOf(
        IdImage(R.drawable.jacket_4),
        IdImage(R.drawable.pant_4),
        IdImage(R.drawable.shoes_4)
    )

    private val rainCoats: List<IdImage> = listOf(
        IdImage(R.drawable.black_shirt_sunny),
        IdImage(R.drawable.pant_6),
        IdImage(R.drawable.shoes_6),
    )

    private val rainCoats1: List<IdImage> = listOf(
        IdImage(R.drawable.winter_shirt_1),
        IdImage(R.drawable.pant_5),
        IdImage(R.drawable.shoes_5),
    )

    private val rainCoats2: List<IdImage> = listOf(
        IdImage(R.drawable.winter_shirt_2),
        IdImage(R.drawable.pant_4),
        IdImage(R.drawable.shoes_1),
    )

    private val rainCoats3: List<IdImage> = listOf(
        IdImage(R.drawable.jacket_1),
        IdImage(R.drawable.pant_4),
        IdImage(R.drawable.shoes_3),
    )

    private val coolWeatherCloth: List<IdImage> = listOf(
        IdImage(R.drawable.sunny_shirt_1),
        IdImage(R.drawable.short_1),
        IdImage(R.drawable.shoes_1)
    )

    private val sunnyshirt: List<IdImage> = listOf(
        IdImage(R.drawable.sunny_shirt2),
        IdImage(R.drawable.pant_3),
        IdImage(R.drawable.shoes_2)
    )

    private val sunnyshirt1: List<IdImage> = listOf(
        IdImage(R.drawable.sunny_shirt_1),
        IdImage(R.drawable.pant_6),
        IdImage(R.drawable.shoes_3)
    )

    private val sunnyCloth: List<IdImage> = listOf(
        IdImage(R.drawable.sunny_shirt2),
        IdImage(R.drawable.short_1),
        IdImage(R.drawable.shoes_4)
    )

    val sunnyClothes: List<IdImage> =
        listOf(sunnyshirt, sunnyCloth, coolWeatherCloth, sunnyshirt1) as List<IdImage>
    val rainClothe: List<IdImage> =
        listOf(rainCoats, rainCoats1, rainCoats2, rainCoats3) as List<IdImage>
    val winterClothe: List<IdImage> =
        listOf(classicJacket, classicJacket1, classicJacket3, classicJacket2) as List<IdImage>
}