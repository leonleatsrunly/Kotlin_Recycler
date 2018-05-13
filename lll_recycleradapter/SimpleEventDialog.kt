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

class SimpleEventDialog
    (
            val activity: Activity,

            title: String = "" ,

            eventIds: List<Paire>,

            val callback: (allOccurrences: Int) -> Unit) : AlertDialog.Builder(activity) {

    var dialog: AlertDialog? = null

    var radioGroup: RadioGroup? = null

    var mLinearLayout: ScrollView? = null

    var deck_event_description: TextView? = null

    var radioButtonSelected = -1

    init {
        val  nbRadios = eventIds.size
        Log.v(TAG, String.format("%d",nbRadios))
        val view = activity.layoutInflater.inflate(R.layout.dialog_deck_event, null).apply {
            mLinearLayout = this.findViewById(R.id.tab2_LinearLayout_Button_x) as ScrollView
            mLinearLayout!!.removeAllViews()
            radioGroup = RadioGroup(this.context)
            mLinearLayout!!.addView(radioGroup, RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
            eventIds.forEach {
                addRadioButton(radioGroup!! , it)
            }
            deck_event_description = this.findViewById(R.id.deck_event_description) as TextView
            deck_event_description!!.text = title
        }
        dialog = AlertDialog.Builder(activity)
                //.setPositiveButton("Show", { dialog, which -> dialogConfirmed(view as ViewGroup, true) })
                .setNegativeButton("Cancel", null)
                .create().apply {
            setView(view)
            show()
        }
    }

    private fun addRadioButton(view : View, type: Paire) {
        val mRadioButton = RadioButton(view.context).apply {
            text = type.name
            id = type.position
            setOnClickListener {
                radioButtonSelected = it.id
                dialogConfirmed(view as ViewGroup, true)
            }
        };
        radioGroup!!.addView(mRadioButton, RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT) )
    }

    private fun dialogConfirmed(view: ViewGroup, hasRepeatableEvent: Boolean) {
        val deleteAllOccurrences = radioButtonSelected
        dialog?.dismiss()
        callback.invoke(deleteAllOccurrences)
    }
}

/*


 */