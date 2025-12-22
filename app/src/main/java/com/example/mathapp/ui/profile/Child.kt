package com.example.mathapp.ui.profile

import androidx.annotation.DrawableRes

data class Child(
    @DrawableRes val imageId: Int? = null,
    val name: String,
    val phone: String,
    val courses: List<Course>,
)
