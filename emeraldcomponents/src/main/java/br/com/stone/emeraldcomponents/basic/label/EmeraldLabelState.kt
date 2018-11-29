package br.com.stone.emeraldcomponents.basic.label

import android.graphics.PorterDuff
import android.graphics.drawable.GradientDrawable
import android.support.v4.content.ContextCompat
import br.com.stone.emeraldcomponents.R
import br.com.stone.emeraldcomponents.extension.colorRes
import br.com.stone.emeraldcomponents.extension.dimen
import br.com.stone.emeraldcomponents.extension.show
import kotlinx.android.synthetic.main.widget_emerald_label.view.*

/**
 * Created by renan.silva on 22/10/2018.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
enum class EmeraldLabelState : LabelStateHandler {
    FILLED {
        override fun setProperties(label: EmeraldLabel, color: Int) {
            label.emeraldLabelText.run {
                setTextColor(ContextCompat.getColor(context, R.color.emerald_white_1))
                getLabelBackgroundDrawable(label)?.apply {
                    setColor(color)
                    setStroke(context.dimen(R.dimen.label_border_width).toInt(),
                            context.colorRes(android.R.color.transparent))
                }
            }
        }
    },
    OUTLINE {
        override fun setProperties(label: EmeraldLabel, color: Int) {
            label.emeraldLabelText.run {
                setTextColor(color)
                getLabelBackgroundDrawable(label)?.apply {
                    setColor(context.colorRes(android.R.color.transparent))
                    setStroke(context.dimen(R.dimen.label_border_width).toInt(), color)
                }
            }
        }
    },
    TEXT {
        override fun setProperties(label: EmeraldLabel, color: Int) {
            label.run {
                emeraldLabelText.setTextColor(color)
                getLabelBackgroundDrawable(label)?.apply {
                    setStroke(context.dimen(R.dimen.label_border_width).toInt(),
                            context.colorRes(android.R.color.transparent))
                    setColor(context.colorRes(android.R.color.transparent))
                }
                val drawable = ContextCompat.getDrawable(context, R.drawable.dot_shape)?.mutate()
                drawable?.setColorFilter(color, PorterDuff.Mode.SRC_IN)
                emeraldLabelImage.setImageDrawable(drawable)
                emeraldLabelImage.show()
            }
        }
    };

    fun getLabelBackgroundDrawable(label: EmeraldLabel): GradientDrawable? {
        return label.background.mutate() as? GradientDrawable
    }
}