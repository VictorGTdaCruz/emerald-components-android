package br.com.stone.emeraldcomponents.basic.button

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.StateListDrawable
import android.support.v4.content.ContextCompat
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.extension.colorRes
import br.com.stone.emeraldcomponents.extension.dimen

/**
 * Created by renan.silva on 19/12/2018.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
class ButtonStateHelper(private val context: Context) {

    fun getBackgroundDrawable(color: Int, radius: Float): StateListDrawable {
        val states = StateListDrawable()
        val colorRes = context.colorRes(color)

        addOutlinePressedState(colorRes, states, radius)
        addTextState(states)
        addOutlineState(colorRes, states, radius)
        addFilledPressedState(states, color, radius)
        addFilledState(states, color, radius)

        return states
    }

    private fun addFilledState(states: StateListDrawable, color: Int, radius: Float) {
        val drawable = getFilledDrawable(color, radius)
        states.addState(intArrayOf(), drawable)
    }

    private fun addFilledPressedState(states: StateListDrawable, color: Int, radius: Float) {
        val colorDrawable = getFilledDrawable(color, radius)
        val blackLayer = getFilledDrawable(R.color.emerald_button_transparent_20percent_opacity, radius)

        val layers = LayerDrawable(arrayOf(colorDrawable, blackLayer))
        states.addState(intArrayOf(android.R.attr.state_pressed), layers)
    }

    private fun getFilledDrawable(color: Int, radius: Float): GradientDrawable {
        val colorRes = context.colorRes(color)
        val drawable = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, intArrayOf(colorRes, colorRes))
        drawable.cornerRadius = radius
        return drawable
    }

    private fun addOutlineState(colorRes: Int, states: StateListDrawable, radius: Float) {
        val drawable = getOutlineDrawable(radius, colorRes)
        states.addState(intArrayOf(R.attr.state_outline), drawable)
    }

    private fun addOutlinePressedState(colorRes: Int, states: StateListDrawable, radius: Float) {
        val drawable = getOutlineDrawable(radius, colorRes)
        drawable?.setColor(context.colorRes(R.color.emerald_button_transparent_10percent_opacity))
        states.addState(intArrayOf(android.R.attr.state_pressed, R.attr.state_outline), drawable)
    }

    private fun getOutlineDrawable(radius: Float, colorRes: Int): GradientDrawable? {
        val drawable = ContextCompat.getDrawable(context, R.drawable.button_border)
                ?.mutate() as? GradientDrawable
        drawable?.cornerRadius = radius
        drawable?.setStroke(context.dimen(R.dimen.button_border_width).toInt(), colorRes)
        return drawable
    }

    private fun addTextState(states: StateListDrawable) {
        states.addState(intArrayOf(R.attr.state_text),
                ContextCompat.getDrawable(context, android.R.color.transparent))
    }
}