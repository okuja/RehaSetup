package com.okujajoshua.reha.presentation.carddetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class CardDetailViewModel(
    val cardid: String,
    app: Application
) : AndroidViewModel(app) {

    var cardId = cardid
}