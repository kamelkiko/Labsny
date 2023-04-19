package com.example.labsny.util

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: Int) {
    Glide.with(this)
        .load(url)
        .into(this)
}