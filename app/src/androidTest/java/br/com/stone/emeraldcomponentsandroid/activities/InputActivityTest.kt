package br.com.stone.emeraldcomponentsandroid.activities

import android.content.pm.ActivityInfo
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.clearText
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.closeSoftKeyboard
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.action.ViewActions.scrollTo
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import br.com.stone.emeraldcomponentsandroid.BaseScreenshotTest
import br.com.stone.emeraldcomponentsandroid.CustomMatcher
import br.com.stone.emeraldcomponentsandroid.R
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat
import java.util.Locale

/**
 * Created by lucasdiego on 11/09/18.
 * Copyright (c) Stone Co. All rights reserved.
 * lucas.amaral@stone.com.br
 */
class InputActivityTest : BaseScreenshotTest() {

    override val activity: AppCompatActivity
        get() = activityRule.activity

    @get:Rule
    val activityRule = activityTestRule<InputActivity>()

    private fun onEditText(matcher: Matcher<View>) =
            onView(allOf(
                    isDescendantOfA(matcher),
                    isAssignableFrom(EditText::class.java)))

    @Test
    fun shouldCurrencyEditBeAbleToValidateInvalidAndValidInput() {
        val numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
        listOf(
                "-12" to numberFormat.format("0.12".toDouble()),
                "12.12.12" to numberFormat.format("1212.12".toDouble()),
                "" to numberFormat.format(0)
        ).forEach {
            onEditText(withId(R.id.currencyEditText))
                    .perform(closeSoftKeyboard())
                    .perform(scrollTo(), click())
                    .perform(clearText())
                    .perform(scrollTo(), typeText(it.first))
                    .perform(closeSoftKeyboard())
                    .check(matches(withText(it.second)))
        }

        screenShot("currency-edittext")
    }

    @Test
    fun shouldCNPJEditBeAbleToValidateInvalidAndValidInput() {
        onEditText(withId(R.id.cnpjEditText))
                .perform(closeSoftKeyboard())
                .perform(scrollTo(), typeText("000"))

        onEditText(withId(R.id.phoneEditText))
                .perform(closeSoftKeyboard())
                .perform(scrollTo())
                .perform(click())

        onEditText(withId(R.id.cnpjEditText))
                .perform(scrollTo())
                .check(matches(CustomMatcher.hasTextInputLayoutErrorText("Valor inválido")))

        onEditText(withId(R.id.cnpjEditText))
                .perform(closeSoftKeyboard())
                .perform(scrollTo(), replaceText("00000000000000"))
                .check(matches(withText("00.000.000/0000-00")))

        screenShot("cnpj-edittext")
    }

    @Test
    fun shouldCPFEditBeAbleToValidateInvalidAndValidInput() {
        onEditText(withId(R.id.cpfEditText))
                .perform(closeSoftKeyboard())
                .perform(scrollTo(), typeText("0000"))

        onEditText(withId(R.id.cnpjEditText))
                .perform(scrollTo())
                .perform(click())

        onEditText(withId(R.id.cpfEditText))
                .perform(closeSoftKeyboard())
                .perform(scrollTo())
                .check(matches(CustomMatcher.hasTextInputLayoutErrorText("Valor inválido")))
                .perform(scrollTo(), replaceText("00000000000"))
                .check(matches(withText("000.000.000-00")))

        screenShot("cpf-edittext")
    }

    @Test
    fun shouldCellPhoneEditBeAbleToValidateInvalidAndValidInput() {
        onEditText(withId(R.id.cellPhoneEditText))
                .perform(closeSoftKeyboard())
                .perform(scrollTo(), typeText("0000"))

        onEditText(withId(R.id.emeraldEditText))
                .perform(click())

        onEditText(withId(R.id.cellPhoneEditText))
                .check(matches(CustomMatcher.hasTextInputLayoutErrorText("Valor inválido")))
                .perform(replaceText("00000000000"))
                .check(matches(withText("(00) 00000-0000")))

        screenShot("cellphone-edittext")
    }

    @Test
    fun shouldPhoneEditBeAbleToValidateInvalidAndValidInput() {
        onEditText(withId(R.id.phoneEditText))
                .perform(closeSoftKeyboard())
                .perform(scrollTo(), typeText("0000"))

        onEditText(withId(R.id.cellPhoneEditText))
                .perform(scrollTo(), click())

        onEditText(withId(R.id.phoneEditText))
                .check(matches(CustomMatcher.hasTextInputLayoutErrorText("Valor inválido")))
                .perform(replaceText("0000000000"))
                .check(matches(withText("(00) 0000-0000")))

        screenShot("phone-edittext")
    }

    @Test
    fun shouldEmailEditBeAbleToValidateInvalidAndValidInput() {
        onEditText(withId(R.id.emailEditText))
                .perform(closeSoftKeyboard())
                .perform(scrollTo(), typeText("aaa"))

        onEditText(withId(R.id.currencyEditText))
                .perform(scrollTo(), click())

        onEditText(withId(R.id.emailEditText))
                .check(matches(CustomMatcher.hasTextInputLayoutErrorText("Email inválido")))
                .perform(scrollTo(), replaceText("email@email.com"))
                .check(matches(withText("email@email.com")))

        screenShot("email-edittext")
    }

    @Test
    fun shouldCepEditBeAbleToValidateInvalidAndValidInput() {
        onEditText(withId(R.id.cepEditText))
                .perform(closeSoftKeyboard())
                .perform(scrollTo(), typeText("0000"), closeSoftKeyboard())

        onEditText(withId(R.id.emailEditText))
                .perform(scrollTo(), click(), closeSoftKeyboard())

        onEditText(withId(R.id.cepEditText))
                .check(matches(CustomMatcher.hasTextInputLayoutErrorText("Valor inválido")))
                .perform(scrollTo(), replaceText("21000000"))
                .check(matches(withText("21000-000")))

        screenShot("cep-edittext")
    }


    @Test
    fun shouldPasswordEditNotCrashWhenToggleIsClickedAndOrientationChanges(){
        onView(withId((R.id.text_input_password_toggle)))
                .perform(click())

        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
}