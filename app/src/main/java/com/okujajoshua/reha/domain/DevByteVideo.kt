package com.okujajoshua.reha.domain

import com.okujajoshua.reha.util.smartTruncate

class DevByteVideo (val title: String,
                    val description: String,
                    val url: String,
                    val updated: String,
                    val thumbnail: String) {

    /**
     * Short description is used for displaying truncated descriptions in the UI
     */
    val shortDescription: String
        get() = description.smartTruncate(200)
}