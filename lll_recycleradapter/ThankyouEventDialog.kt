package com.example.leonly.lll_recycleradapter

import android.app.Activity
import android.content.ContentValues.TAG
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*

class ThankyouEventDialog
    (
            val activity: Activity,

            title: String = ""

            ) : AlertDialog.Builder(activity) {

    var dialog: AlertDialog? = null

    var radioGroup: RadioGroup? = null

    var mLinearLayout: ScrollView? = null

    var deck_event_description: TextView? = null

    init {

        val view = activity.layoutInflater.inflate(R.layout.dialog_thankyou_event, null).apply {
            deck_event_description = this.findViewById(R.id.deck_event_description) as TextView
            deck_event_description!!.text = title
        }
        dialog = AlertDialog.Builder(activity)
                .create().apply {
            setView(view)
            show()
        }
    }

    private fun dialogConfirmed(view: ViewGroup, hasRepeatableEvent: Boolean) {
        dialog?.dismiss()
    }
}

