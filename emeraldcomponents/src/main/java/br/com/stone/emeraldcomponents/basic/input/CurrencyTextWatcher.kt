package br.com.stone.emeraldcomponents.basic.input

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.text.NumberFormat
import java.util.Locale

/**
 * Created by renan.silva on 17/07/2018.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
class CurrencyTextWatcher(val editText: EditText,
                          private val locale: Locale = Locale.getDefault(),
                          private val valueListener: (unmaskedText: String) -> Unit = {}) : TextWatcher {

    private val numberFormat = NumberFormat.getCurrencyInstance(locale)
    private val replaceable = "\\D"

    override fun afterTextChanged(text: Editable?) {
        editText.removeTextChangedListener(this)

        var cleanString = text.toString().replace(Regex(replaceable), "")
        if (cleanString.length >= EmeraldMaskedEditText.MAX_CURRENCY_LENGTH) {
            cleanString = cleanString.substring(0, EmeraldMaskedEditText.MAX_CURRENCY_LENGTH)
        }
        val parsed = if (cleanString.isNotBlank()) cleanString.toDouble() / ONE_HUNDRED else 0.0
        val formatted = numberFormat.format(parsed)

        editText.setText(formatted)
        editText.setSelection(formatted.length)
        editText.addTextChangedListener(this)

        valueListener(parsed.toString())
    }

    override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) { }

    override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) { }

    companion object {
        const val ONE_HUNDRED = 100
    }
}