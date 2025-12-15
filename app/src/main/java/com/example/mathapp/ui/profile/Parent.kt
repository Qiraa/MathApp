package com.example.mathapp.ui.profile

import androidx.annotation.DrawableRes

data class Parent(
    @DrawableRes val imageId: Int? = null,
    val name: String,
    val phone: String,
    val children: List<Child>,
)
