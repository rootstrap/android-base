package com.rootstrap.android.utils

/**
 * @author Amaury Ricardo Miranda 2019
 * */

import com.google.gson.Gson
import org.joda.time.DateTime
import org.joda.time.DateTimeZone

fun Long.getDate(): DateTime = DateTime(this, DateTimeZone.UTC)
fun DateTime.getLong(): Long = this.millis
fun Any.toGson(): String = Gson().toJson(this)
inline fun <reified T> String.fromGson() : T = Gson().fromJson(this,T :: class.java)

/**
 * Add more convert functions
 * */
