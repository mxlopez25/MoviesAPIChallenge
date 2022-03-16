package com.mxlopez.moviesapichallenge.util

import java.text.DecimalFormat

class MovieUtils {
    fun parsePopularity(rate: Float): String {
        val df = DecimalFormat("#.#")
        // ().toString()
        return df.format(rate/1000).toString()
    }
}