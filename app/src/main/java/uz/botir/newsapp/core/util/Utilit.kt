package uz.botir.newsapp.core.util

import android.view.View

const val API_KEY = "235c003291ba4b74a0ec09e29230dd7d";
const val BASE_URL = "https://newsapi.org";


fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}