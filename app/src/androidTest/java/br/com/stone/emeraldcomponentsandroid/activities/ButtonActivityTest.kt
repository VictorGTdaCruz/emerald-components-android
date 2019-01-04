package br.com.stone.emeraldcomponentsandroid.activities

import android.support.v7.app.AppCompatActivity
import br.com.stone.emeraldcomponentsandroid.BaseScreenshotTest
import org.junit.Rule
import org.junit.Test

/**
 * Created by renan.silva on 19/12/2018.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
class ButtonActivityTest : BaseScreenshotTest() {

    override val activity: AppCompatActivity
        get() = activityRule.activity

    @get:Rule
    var activityRule = activityTestRule<ButtonActivity>()

    @Test
    fun shouldClickAllButtons() {
        screenShot("emerald-buttons")
    }
}