package br.com.stone.emeraldcomponents.basics.input

import android.text.SpannableStringBuilder
import android.widget.EditText
import br.com.stone.emeraldcomponents.basic.input.CurrencyTextWatcher
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import java.util.Locale

/**
 * Created by renan.silva on 17/07/2018.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
@RunWith(RobolectricTestRunner::class)
class CurrencyTextWatcherTest {

    private lateinit var textWatcher: CurrencyTextWatcher

    private lateinit var editText: EditText

    @Before
    fun setup() {
        editText = EditText(RuntimeEnvironment.application)
        textWatcher = CurrencyTextWatcher(editText, Locale("pt", "BR"))
    }

    @Test
    fun testDefaultLocale() {
        val textWatcher = CurrencyTextWatcher(editText)
        textWatcher.afterTextChanged(SpannableStringBuilder("100"))
        assertEquals('0', editText.text.last())
    }

    @Test
    fun testDefaultLocaleInvalidFormat() {
        val textWatcher = CurrencyTextWatcher(editText)
        textWatcher.afterTextChanged(SpannableStringBuilder(""))
        assertEquals('0', editText.text.last())
    }

    @Test
    fun testAfterTextChanged() {
        textWatcher = CurrencyTextWatcher(editText, Locale("pt", "BR"))
        textWatcher.afterTextChanged(SpannableStringBuilder("1"))
        assertEquals("R$ 0,01", editText.text.toString())
    }

    @Test
    fun testAfterTextChangedWithInvalidNumber() {
        textWatcher = CurrencyTextWatcher(editText, Locale("pt", "BR"))
        textWatcher.afterTextChanged(SpannableStringBuilder("invalid"))
        assertEquals("R$ 0,00", editText.text.toString())
    }

    @Test
    fun testAfterTextChangedWithMaximumLength() {
        textWatcher = CurrencyTextWatcher(editText, Locale("pt", "BR"))
        textWatcher.afterTextChanged(SpannableStringBuilder("111111111111111111"))
        assertEquals("R$ 111.111.111.111,11", editText.text.toString())
    }

    @Test
    fun testBeforeTextChanged() {
        textWatcher.beforeTextChanged("", 0, 0, 0)
    }

    @Test
    fun testOnTextChanged() {
        textWatcher.onTextChanged("", 0, 0, 0)
    }
}