package com.example.leonly.lll_recycleradapter

import android.content.Context
import android.support.annotation.StringRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso


fun Context.toast(text: CharSequence, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.toast(@StringRes resId: Int, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show()
}

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(this.context).inflate(layoutRes, this, false)
}

fun ImageView.loadUrl (url:String){
    try {
        Picasso.with(this.context).setLoggingEnabled(false)
        Picasso.with(this.context).load(url).into(this)
    }
    catch (e: Exception){
        Picasso.with(this.context).setLoggingEnabled(false)
    }
}

fun ImageView.load (res:Int){
    try {
        Picasso.with(this.context).setLoggingEnabled(false)
        Picasso.with(this.context).load(res).into(this)
    }
    catch (e: Exception){
        Picasso.with(this.context).setLoggingEnabled(false)
    }
}