package br.com.stone.emeraldcomponents.basics.button

import br.com.stone.emeraldcomponents.basic.button.ButtonStateHelper
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

/**
 * Created by renan.silva on 19/12/2018.
 * Copyright (c) Stone Co. All rights reserved.
 * renan.silva@stone.com.br
 */
@RunWith(RobolectricTestRunner::class)
class ButtonStateHelperTest {

    @Test
    fun testGetBackgroundDrawable() {
        val states = ButtonStateHelper(RuntimeEnvironment.application)
                .getBackgroundDrawable(android.R.color.white, 1f)
        assertNotNull(states)
    }
}