package br.com.stone.emeraldcomponents.basics

import android.graphics.drawable.StateListDrawable
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.basic.EmeraldButton
import br.com.stone.emeraldcomponents.extension.colorRes
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

/**
 * Created on 25/05/2018
 *
 * @author Victor Cruz
 * @email victor.cruz@stone.com.br
 */

@RunWith(RobolectricTestRunner::class)
class EmeraldButtonTest {

    lateinit var button: EmeraldButton

    @Before
    fun setup() {
        button = EmeraldButton(RuntimeEnvironment.application)
    }

    @Test
    fun testInstanceWithContext() {
        val view = EmeraldButton(RuntimeEnvironment.application)
        Assert.assertNotNull(view)
    }

    @Test
    fun testInstanceWithAttributeSet() {
        val attrs = Robolectric.buildAttributeSet()
                .addAttribute(R.attr.emeraldButtonStyle, EmeraldButton.ButtonStyle.FILLED.ordinal.toString())
                .addAttribute(R.attr.emeraldButtonType, EmeraldButton.ButtonType.CONFIRM.ordinal.toString())
                .addAttribute(R.attr.emeraldButtonRadius, "10")
                .build()

        val view = EmeraldButton(RuntimeEnvironment.application, attrs)

        Assert.assertEquals(view.type, EmeraldButton.ButtonType.CONFIRM)
        Assert.assertEquals(view.style, EmeraldButton.ButtonStyle.FILLED)
        Assert.assertNotNull(view)
    }

    @Test
    fun testInstanceWithAttributeSetInvalid() {
        val attrs = Robolectric.buildAttributeSet()
                .addAttribute(R.attr.emeraldButtonStyle, "teste")
                .addAttribute(R.attr.emeraldButtonType, "valor invalido")
                .build()

        val view = EmeraldButton(RuntimeEnvironment.application, attrs)

        Assert.assertEquals(view.type, EmeraldButton.ButtonType.PRIMARY)
        Assert.assertEquals(view.style, EmeraldButton.ButtonStyle.FILLED)
        Assert.assertNotNull(view)
    }

    @Test
    fun testTypePrimary() {
        button.type = EmeraldButton.ButtonType.PRIMARY
        assertEquals(button.currentTextColor, button.context.colorRes(android.R.color.white))
        assertEquals((button.background as StateListDrawable).state.size, 4)
    }

    @Test
    fun testStyleFilled() {
        button.style = EmeraldButton.ButtonStyle.FILLED
        assertEquals(button.currentTextColor, button.context.colorRes(android.R.color.white))
    }

    @Test
    fun testStyleOutline() {
        button.style = EmeraldButton.ButtonStyle.OUTLINE
        assertEquals(button.currentTextColor, button.context.colorRes(R.color.emerald_button_primary))
    }

    @Test
    fun testStyleText() {
        button.style = EmeraldButton.ButtonStyle.TEXT
        assertEquals(button.currentTextColor, button.context.colorRes(R.color.emerald_button_primary))
    }

    @Test
    fun testSetStyleProperties() {
        val textColor = R.color.emerald_white_1
        button.setStyleProperties(R.color.emerald_black_1, textColor)
        assertEquals(button.currentTextColor, button.context.colorRes(textColor))
        assertEquals((button.background as StateListDrawable).state.size, 4)
    }
}