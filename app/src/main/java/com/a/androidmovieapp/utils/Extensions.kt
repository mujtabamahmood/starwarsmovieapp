package com.a.androidmovieapp

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.a.androidmovieapp.utils.UNDEFINED
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.lang.Math.floor
import kotlin.math.roundToInt


inline fun <reified VM: ViewModel> FragmentActivity.viewModelProvider(
    provider: ViewModelProvider.Factory
) =ViewModelProvider(this, provider).get(VM::class.java)

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide(){
    visibility = View.INVISIBLE
}

fun String.convertUrlToHttps() = if (this.isNotEmpty()){
    substring(0,4)+ 's' + substring(4)
}else{
    UNDEFINED
}


fun String.hasValue() = this != UNDEFINED

fun Context.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()


@Throws(NumberFormatException::class)
fun String.convertCmToFeet(): String {
    val feet = floor(toDouble()/30.48).toInt()
    val inches = (toDouble()/2.54 - feet *12).roundToInt()
    return String.format("%d' %d'' ", feet, inches)
}

inline fun <reified T> Gson.fromJsonToObjectType(json: String): T =
    fromJson(json, object: TypeToken<T>() {}.type)

fun EditText.getTextChangeStateFlow(): StateFlow<String> {
    val query = MutableStateFlow("")
    addTextChangedListener {
        query.value = it.toString()
    }
    return query
}