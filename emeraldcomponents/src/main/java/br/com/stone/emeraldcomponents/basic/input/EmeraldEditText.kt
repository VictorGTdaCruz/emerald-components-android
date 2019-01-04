package br.com.stone.emeraldcomponents.basic.input

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import br.com.stone.emeraldcomponents.R
import kotlinx.android.synthetic.main.widget_edittextview.view.*

/**
 * Created by renan.silva on 18/06/2018.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
class EmeraldEditText : EmeraldBaseEditText {

    val unmaskedText: String
        get() = (editText as EmeraldMaskedEditText).unmaskedText

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setAttributes(attrs)
    }

    init {
        val editText = inflate(context, R.layout.widget_edittextview, null) as EditText
        editText.inputType = inputType
        editText.setText(textAttribute)
        addView(editText)
    }

    fun setMaskType(type: EmeraldMaskedEditText.MaskTypes) {
        emeraldMaskedEditText.type = type
    }

    fun setMaskType(mask: String?) {
        emeraldMaskedEditText.defineMask(mask)
    }

    override fun validateEditText(): Pair<Boolean, String> {
        return Pair(emeraldMaskedEditText.isValid(), emeraldMaskedEditText.errorMessage)
    }

    private fun setAttributes(attrs: AttributeSet) {
        val args = context.theme.obtainStyledAttributes(attrs, R.styleable.EmeraldEditText, 0, 0)

        val type = EmeraldMaskedEditText.MaskTypes.getById(
                args.getInt(R.styleable.EmeraldEditText_maskType, EmeraldMaskedEditText.MaskTypes.NONE.id))

        val mask = args.getString(R.styleable.EmeraldEditText_mask) ?: type.mask

        if (type == EmeraldMaskedEditText.MaskTypes.NONE) {
            setMaskType(mask)
        } else {
            setMaskType(type)
        }

        args.recycle()
    }
}