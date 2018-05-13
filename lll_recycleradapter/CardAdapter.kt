package com.example.leonly.lll_recycleradapter

import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.leonly.lll_recycleradapter.MainActivity.Companion.FILE_DIR
import com.example.leonly.lll_recycleradapter.MainActivity.Companion.ASSET_DIR
import kotlinx.android.synthetic.main.card_list_row.view.*
import java.io.File
import java.io.IOException

class CardAdapter( private val cardsList: MutableList<CardEntry> , val listener: (CardEntry, position: Int, button: Int) -> Unit ) :
        RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    var recyclerView: RecyclerView? = null
    var expandedPosition = -1

    var allVisible = false

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.card_list_row), listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = with(holder.itemView) {
        val card: CardEntry = cardsList[position]
        setOnClickListener { listener(card, position, 0) }
        title_1.text = card.title_1
        title_2.text = card.title_2
        holder.bindCard(cardsList[position], position)
        try {
            val bitmap_indicator = BitmapFactory.decodeStream(context.getResources().getAssets().open(
                    "round_1" + File.separator + card.code + ".png"))
            thumbnail_ImageView.setImageBitmap(bitmap_indicator)
        } catch (e: IOException) {
        }
        if (position == expandedPosition) {
            cardview_LinearLayout.setVisibility(View.VISIBLE)
            card_ImageView.loadUrl(FILE_DIR + card.code + ".png")
            try {
                val bitmap_indicator = BitmapFactory.decodeStream(context.getResources().getAssets().open(
                        "round_4" + File.separator + card.code + ".png"))
                thumbnail_ImageView.setImageBitmap(bitmap_indicator)
            } catch (e: IOException) {
            }

        } else {
            cardview_LinearLayout.setVisibility(View.GONE)
        }

        if (allVisible == true) {
            cardview_LinearLayout.setVisibility(View.VISIBLE)
            card_ImageView.loadUrl(FILE_DIR + card.code + ".png")
        }
    }

    override fun getItemCount() = cardsList.size

    class ViewHolder(view: View, val listener: (CardEntry, Int, Int) -> Unit) :
            RecyclerView.ViewHolder(view) {
        fun bindCard(card: CardEntry, position: Int, iButton: Int = 0) = with(itemView) {
            itemView.setOnClickListener { listener(card, position, iButton) }
            imageButton1.setOnClickListener{  listener(card, position, 1) }
            imageButton2.setOnClickListener{  listener(card, position, 2) }
            imageButton3.setOnClickListener{  listener(card, position, 3) }
            imageButton4.setOnClickListener{  listener(card, position, 4) }
            imageButton5.setOnClickListener{  listener(card, position, 5) }
            imageButton6.setOnClickListener{  listener(card, position, 6) }
            imageButton7.setOnClickListener{  listener(card, position, 7) }
            imageButton8.setOnClickListener{  listener(card, position, 8) }
            imageButton.setOnClickListener{  listener(card, position, 9) }
        }
    }

    fun updateHolderCardExpanded(position: Int, visibility: Int){
        if (position < 0) return
        try {
            val holder = recyclerView?.findViewHolderForAdapterPosition(position)
            if (holder == null) {
            }else{
                holder.itemView.cardview_LinearLayout.setVisibility(visibility)
            }
        }
        catch (e : TypeCastException){
        }

    }

    fun setHolderCardExpanded(position: Int, visibility: Int) {
        val holder = recyclerView?.findViewHolderForAdapterPosition(position)
        if (holder == null) {
        }else{
            holder.itemView.cardview_LinearLayout.setVisibility(View.VISIBLE)
        }
    }

    fun updateExpandedCardPosition( newExpandedPosition: Int){
        if (newExpandedPosition >= 0) {
            updateHolderCardExpanded(expandedPosition, View.GONE)
            expandedPosition = newExpandedPosition
            updateHolderCardExpanded(expandedPosition, View.VISIBLE)
        }
    }

    fun toggleExpandedCardPosition( newExpandedPosition: Int){
        if (newExpandedPosition >= 0) {
            try {
                val holder = recyclerView?.findViewHolderForAdapterPosition(newExpandedPosition)
                if (holder != null) {
                    holder.itemView.cardview_LinearLayout.visibility =  when {
                        holder.itemView.cardview_LinearLayout.visibility == View.VISIBLE -> View.GONE
                        else -> View.VISIBLE
                    }
                    expandedPosition = -1
                }
            }
            catch (e : TypeCastException){
            }
        }
    }

}

/*


 */