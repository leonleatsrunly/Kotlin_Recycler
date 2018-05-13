package com.example.leonly.lll_recycleradapter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.Gson
import java.util.ArrayList
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        open_JsonRaw_data()
        init_recyclerView_2()
        init_recyclerView()
    }

    //region <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    lateinit private var netList: MutableList<CardEntry>
    lateinit private var deckList: MutableList<CardEntry>

    //region variables <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    var all_cards: List<CardEntry> = listOf()
    var deck_cards: List<CardEntry> = listOf()
    var cards_selected : ArrayList<CardEntry> = arrayListOf()
    var cardList : ArrayList<CardEntry> = arrayListOf()
    var cards_selected_deck: List<CardEntry> = listOf()

    var cards_runner: List<CardEntry> = listOf()
    var cards_corp: List<CardEntry> = listOf()
    var cards_identity: List<CardEntry> = listOf()
    var cards_identity_corp: List<CardEntry> = listOf()
    var cards_identity_runner: List<CardEntry> = listOf()

    var cards_corp_agenda: List<CardEntry> = listOf()
    var cards_corp_barrier: List<CardEntry> = listOf()
    var cards_corp_codegate: List<CardEntry> = listOf()
    var cards_corp_sentry: List<CardEntry> = listOf()
    var cards_corp_asset: List<CardEntry> = listOf()
    var cards_corp_operation: List<CardEntry> = listOf()
    var cards_corp_upgrade: List<CardEntry> = listOf()
    var cards_corp_haas_bioroid: List<CardEntry> = listOf()
    var cards_corp_jinteki: List<CardEntry> = listOf()
    var cards_corp_nbn: List<CardEntry> = listOf()
    var cards_corp_weyland_consortium: List<CardEntry> = listOf()
    var cards_corp_neutral_corp: List<CardEntry> = listOf()
    var cards_corp_ice: List<CardEntry> = listOf()

    var cards_runner_icebreaker: List<CardEntry> = listOf()
    var cards_runner_event: List<CardEntry> = listOf()
    var cards_runner_hardware: List<CardEntry> = listOf()
    var cards_runner_program: List<CardEntry> = listOf()
    var cards_runner_resource: List<CardEntry> = listOf()
    var cards_runner_event_run: List<CardEntry> = listOf()
    var cards_runner_anarch: List<CardEntry> = listOf()
    var cards_runner_criminal: List<CardEntry> = listOf()
    var cards_runner_shaper: List<CardEntry> = listOf()
    var cards_runner_neutral_runner: List<CardEntry> = listOf()
    var cards_runner_apex: List<CardEntry> = listOf()
    var cards_runner_adam: List<CardEntry> = listOf()
    var cards_runner_sunny_lebeau: List<CardEntry> = listOf()

    var cards_runner_pack: List<CardEntry> = listOf()
    var cards_corp_pack: List<CardEntry> = listOf()

    var cards_filtered: List<CardEntry> = listOf()

    //endregion

    fun open_JsonRaw_data(){
        val inputStream = resources.openRawResource(R.raw.cards)
        val jCards = Gson().fromJson(inputStream.reader(), Cards::class.java)

        var theIndex = 0
        jCards.cards.data.forEach {
            theIndex = it.title!!.indexOf(':')
            if( theIndex > 0){
                var theTitle = it.title
                val theTitle_1 = theTitle.subSequence(0, theIndex)
                it.title_1 = theTitle_1 as String
                val theTitle_2 = theTitle.subSequence(theIndex + 1, theTitle.length)
                it.title_2 = theTitle_2 as String
            }else
            {
                it.title_1 = it.title
            }
        }

        all_cards = jCards.cards.data.toList().sortedBy {it.title}


        cards_identity = jCards.cards.data.toList().filter { it.pack_code != "draft" && it.type_code == "identity" }.sortedBy {it.title}
        cards_identity_corp = cards_identity.filter { it.side_code == "corp" }.sortedBy {it.title}
        cards_identity_runner = cards_identity.filter { it.side_code == "runner" }.sortedBy {it.title}

        cards_corp = jCards.cards.data.toList().filter { it.pack_code != "draft" && it.side_code == "corp" }.sortedBy {it.title}
        cards_corp_agenda = cards_corp.filter { it.type_code == "agenda" }.sortedBy {it.title}
        cards_corp_ice = cards_corp.filter { it.type_code == "ice" }.sortedBy {it.title}
        cards_corp_asset = cards_corp.filter { it.type_code == "asset" }.sortedBy {it.title}
        cards_corp_operation = cards_corp.filter { it.type_code == "operation" }.sortedBy {it.title}
        cards_corp_upgrade = cards_corp.filter { it.type_code == "upgrade" }.sortedBy {it.title}
        cards_corp_haas_bioroid = cards_corp.filter { it.faction_code!= null && it.faction_code == "haas-bioroid" }.sortedBy {it.title}
        cards_corp_jinteki = cards_corp.filter { it.faction_code!= null && it.faction_code == "jinteki" }.sortedBy {it.title}
        cards_corp_nbn = cards_corp.filter { it.faction_code!= null && it.faction_code == "nbn" }.sortedBy {it.title}
        cards_corp_weyland_consortium = cards_corp.filter { it.faction_code!= null && it.faction_code == "weyland-consortium" }.sortedBy {it.title}
        cards_corp_neutral_corp = cards_corp.filter { it.faction_code!= null && it.faction_code == "neutral-corp" }.sortedBy {it.title}
        cards_corp_pack = cards_corp.filter { it.pack_code!= null && it.pack_code == "wla" }.sortedBy {it.title}
        cards_corp_barrier = cards_corp_ice.filter { it.keywords!= null && it.keywords!!.startsWith("barrier", ignoreCase = true ) }.sortedBy {it.title}
        cards_corp_codegate = cards_corp_ice.filter { it.keywords!= null && it.keywords!!.startsWith("code gate", ignoreCase = true ) }.sortedBy {it.title}
        cards_corp_sentry = cards_corp_ice.filter { it.keywords!= null && it.keywords!!.startsWith("sentry", ignoreCase = true ) }.sortedBy {it.title}

        cards_runner = jCards.cards.data.toList().filter { it.pack_code != "draft" && it.side_code == "runner" }.sortedBy {it.title}
        cards_runner_event = cards_runner.filter { it.type_code == "event" }.sortedBy {it.title}
        cards_runner_hardware = cards_runner.filter { it.type_code == "hardware" }.sortedBy {it.title}
        cards_runner_program = cards_runner.filter { it.type_code == "program" }.sortedBy {it.title}
        cards_runner_resource = cards_runner.filter { it.type_code == "resource" }.sortedBy {it.title}
        cards_runner_anarch = cards_runner.filter { it.faction_code!= null && it.faction_code == "anarch" }.sortedBy {it.title}
        cards_runner_criminal = cards_runner.filter { it.faction_code!= null && it.faction_code == "criminal" }.sortedBy {it.title}
        cards_runner_shaper = cards_runner.filter { it.faction_code!= null && it.faction_code == "shaper" }.sortedBy {it.title}
        cards_runner_neutral_runner = cards_runner.filter { it.faction_code!= null && it.faction_code == "neutral-runner" }.sortedBy {it.title}
        cards_runner_apex = cards_runner.filter { it.faction_code!= null && it.faction_code == "apex" }.sortedBy {it.title}
        cards_runner_adam = cards_runner.filter { it.faction_code!= null && it.faction_code == "adam" }.sortedBy {it.title}
        cards_runner_sunny_lebeau = cards_runner.filter { it.faction_code!= null && it.faction_code == "sunny-lebeau" }.sortedBy {it.title}
        cards_runner_pack = cards_runner.filter { it.pack_code!= null && it.pack_code == "ta" }.sortedBy {it.title}
        cards_runner_icebreaker = cards_runner_program.filter { it.keywords!= null && it.keywords!!.startsWith("icebreaker", ignoreCase = true ) }.sortedBy {it.title}
        cards_runner_event_run = cards_runner_event.filter { it.keywords!= null && it.keywords!!.startsWith("run", ignoreCase = true ) }.sortedBy {it.title}

        println()
    }

    //endregion

    //region <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    lateinit var card_Adapter: CardAdapter
    lateinit var deck_Adapter: DeckAdapter

    fun init_recyclerView(){
        recycler_view_2.visibility = View.GONE
        recycler_view.visibility = View.VISIBLE
        val mLayoutManager = LinearLayoutManager(applicationContext)
        recycler_view.layoutManager = mLayoutManager
        recycler_view.itemAnimator = DefaultItemAnimator()
        val decoration = DividerItemDecoration(recycler_view.context, mLayoutManager.orientation)
        recycler_view.addItemDecoration(decoration)
        netList = all_cards.toMutableList()
        card_Adapter = CardAdapter( netList,
                { it: CardEntry, position: Int, iButton -> onCardClicked(it, position, iButton)})
        recycler_view.adapter = card_Adapter
        supportActionBar!!.title = "Netrunner Card Viewer"
    }

    fun init_recyclerView_2(){
        recycler_view_2.visibility = View.VISIBLE
        recycler_view.visibility = View.GONE
        val mLayoutManager = LinearLayoutManager(applicationContext)
        recycler_view_2.layoutManager = mLayoutManager
        recycler_view_2.itemAnimator = DefaultItemAnimator()
        val decoration = DividerItemDecoration(recycler_view_2.context, mLayoutManager.orientation)
        recycler_view_2.addItemDecoration(decoration)
        deckList = cards_selected.toMutableList()
        deck_Adapter = DeckAdapter( deckList,
                { it: CardEntry, position: Int, iButton -> onDeckClicked(it, position, iButton)})
        recycler_view_2.adapter = deck_Adapter
        supportActionBar!!.title = "Netrunner Deck Viewer"
    }

    companion object {

        val strPath = Environment.getExternalStorageDirectory().absolutePath
        val NET_DIR = strPath + "/netrunner/cards/hq"
        val FILE_DIR = "file:" + strPath + "/netrunner/cards/hq/"
        val ASSET_DIR = "file:///android_asset/thumbnails/"

        public var assets = "thumbs_ron"

        public var index_assets_corp = 0
        public var index_assets_runner = 1

        public var clickButton = 0

        public enum class assets_enum {
            thumbs_ron
            ,thumbs_hex_v
            ,mix
            //,round_age_ide_ide_upg
        }

        public enum class enum_cards_sortby {
            code,
            title,
            text,
            illustrator,
            type_code,
            pack_code,
            side_code,
            faction_code,
            cost,
            quantity,
            position,
            memory_cost,
            advancement_cost,
            agenda_points,
            trash_cost,
            strength
        }

        public enum class enum_cards {
            all_cards,
            cards_corp,
            cards_runner,
            cards_identity,
            cards_identity_runner,
            cards_identity_corp,
            cards_corp_agenda,
            cards_corp_ice,
            cards_corp_asset,
            cards_corp_operation,
            cards_corp_upgrade,
            cards_corp_haas_bioroid,
            cards_corp_jinteki,
            cards_corp_nbn,
            cards_corp_weyland_consortium,
            cards_corp_neutral_corp,
            cards_corp_pack,
            cards_corp_barrier,
            cards_corp_codegate,
            cards_corp_sentry,
            cards_runner_event,
            cards_runner_hardware,
            cards_runner_program,
            cards_runner_resource,
            cards_runner_anarch,
            cards_runner_criminal,
            cards_runner_shaper,
            cards_runner_neutral_runner,
            cards_runner_apex,
            cards_runner_adam,
            cards_runner_sunny_lebeau,
            cards_runner_pack,
            cards_runner_icebreaker,
            cards_runner_event_run,

            cards_selected
        }

        val TAG = "LLL"
        /**
         * Whether or not the system UI should be auto-hidden after
         * [AUTO_HIDE_DELAY_MILLIS] milliseconds.
         */
        private val AUTO_HIDE = true

        /**
         * If [AUTO_HIDE] is set, the number of milliseconds to wait after
         * user interaction before hiding the system UI.
         */
        private val AUTO_HIDE_DELAY_MILLIS = 3000

        /**
         * Some older devices needs a small delay between UI widget updates
         * and a change of the status and navigation bar.
         */
        private val UI_ANIMATION_DELAY = 300
    }

    var position_ : Int = -1
    var position_2 : Int = -1

    fun onCardClicked(card: CardEntry, position: Int, button: Int) {
        if (position_ != position)
            card_Adapter.updateExpandedCardPosition(position)
        else {
            card_Adapter.toggleExpandedCardPosition(position)
        }
        when(button){
            1 -> showCards()
            2 -> sortCards()
            5 -> addToDeck_ex(card)
            8 -> thankyou(3000)
            9 -> setAllVisible()
        }
        position_ = position
        card_Adapter.notifyDataSetChanged()
    }

    fun onDeckClicked(card: CardEntry, position: Int, button: Int) {
        if (position_2 != position)
            deck_Adapter.updateExpandedCardPosition(position)
        else {
            deck_Adapter.toggleExpandedCardPosition(position)
        }
        when(button){
            5 -> delFromDeck_ex(card)
            8 -> thankyou(3000)
        }
        position_2 = position
        deck_Adapter.notifyDataSetChanged()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(supportActionBar!!.title!!.contains("Card", true))
            onDeckSelected()
        else
            onCardSelected()
        return super.onOptionsItemSelected(item)
    }

    fun onCardSelected(){
        supportActionBar!!.title = "Netrunner Card Viewer"
        recycler_view.visibility = View.VISIBLE
        recycler_view_2.visibility = View.GONE
        card_Adapter.notifyDataSetChanged()
    }

    fun onDeckSelected(){
        supportActionBar!!.title = "Netrunner Deck Viewer"
        recycler_view_2.visibility = View.VISIBLE
        recycler_view.visibility = View.GONE
        deck_Adapter.notifyDataSetChanged()
    }

    fun showCards() {
        val showCardsList = ArrayList<Paire>()
        Companion.enum_cards.values().forEach {
            showCardsList.add( Paire(it.name, it.ordinal))
        }
        val theTitle: String = "View list"
        SimpleEventDialog(this, theTitle, showCardsList) {
            if (it >= 0) {
                val named_list = Companion.enum_cards.values().get(it).name
                Log.v(TAG, named_list + String.format(" - it: %d", it))
                when(it){
                    0 -> netList = all_cards.toMutableList()
                    1 -> netList = cards_corp.toMutableList()
                    2 -> netList = cards_runner.toMutableList()
                    3 -> netList = cards_identity.toMutableList()
                    4 -> netList = cards_identity_runner.toMutableList()
                    5 -> netList = cards_identity_corp.toMutableList()
                    6 -> netList = cards_corp_agenda.toMutableList()
                    7 -> netList = cards_corp_ice.toMutableList()
                    8 -> netList = cards_corp_asset.toMutableList()
                    9 -> netList = cards_corp_operation.toMutableList()
                    10 -> netList = cards_corp_upgrade.toMutableList()
                    11 -> netList = cards_corp_haas_bioroid.toMutableList()
                    12 -> netList = cards_corp_jinteki.toMutableList()
                    13 -> netList = cards_corp_nbn.toMutableList()
                    14 -> netList = cards_corp_weyland_consortium.toMutableList()
                    15 -> netList = cards_corp_neutral_corp.toMutableList()
                    16 -> netList = cards_corp_pack.toMutableList()
                    17 -> netList = cards_corp_barrier.toMutableList()
                    18 -> netList = cards_corp_codegate.toMutableList()
                    19 -> netList = cards_corp_sentry.toMutableList()
                    20 -> netList = cards_runner_event.toMutableList()
                    21 -> netList = cards_runner_hardware.toMutableList()
                    22 -> netList = cards_runner_program.toMutableList()
                    23 -> netList = cards_runner_resource.toMutableList()
                    24 -> netList = cards_runner_anarch.toMutableList()
                    25 -> netList = cards_runner_criminal.toMutableList()
                    26 -> netList = cards_runner_shaper.toMutableList()
                    27 -> netList = cards_runner_neutral_runner.toMutableList()
                    28 -> netList = cards_runner_apex.toMutableList()
                    29 -> netList = cards_runner_adam.toMutableList()
                    30 -> netList = cards_runner_sunny_lebeau.toMutableList()
                    31 -> netList = cards_runner_pack.toMutableList()
                    32 -> netList = cards_runner_icebreaker.toMutableList()
                    33 -> netList = cards_runner_event_run.toMutableList()
                    34 -> netList = cards_selected.toMutableList()
                }
                if(netList.size == 0) return@SimpleEventDialog
                card_Adapter = CardAdapter( netList, { it: CardEntry, position: Int, iButton -> onCardClicked(it, position, iButton)})
                recycler_view.adapter = card_Adapter
            }
        }
    }

    fun sortCards() {
        val sortCardsList = ArrayList<Paire>()
        Companion.enum_cards_sortby.values().forEach {
            sortCardsList.add( Paire(it.name, it.ordinal))
        }
        val theTitle: String = "Sort by"
        SimpleEventDialog(this, theTitle, sortCardsList) {
            if (it >= 0) {
                val named_list = Companion.enum_cards.values().get(it).name
                Log.v(TAG, named_list + String.format(" - it: %d", it))

                when (it) {
                    0 -> netList.sortBy { it.code  }
                    1 -> netList.sortBy { it.title  }
                    2 -> netList.sortBy { it.text  }
                    3 -> netList.sortBy { it.illustrator  }
                    4 -> netList.sortBy { it.type_code  }
                    5 -> netList.sortBy { it.pack_code  }
                    6 -> netList.sortBy { it.side_code  }
                    7 -> netList.sortBy { it.faction_code  }
                    8 -> netList.sortBy { it.cost  }
                    9 -> netList.sortBy { it.quantity  }
                    10 -> netList.sortBy { it.position  }
                    11 -> netList.sortBy { it.memory_cost  }
                    12 -> netList.sortBy { it.advancement_cost  }
                    13 -> netList.sortBy { it.agenda_points  }
                    14 -> netList.sortBy { it.trash_cost  }
                    15 -> netList.sortBy { it.strength  }
                }
                if(netList.size == 0) return@SimpleEventDialog
                netList.reverse()
                card_Adapter = CardAdapter( netList, { it: CardEntry, position: Int, iButton -> onCardClicked(it, position, iButton)})
                recycler_view.adapter = card_Adapter
            }
        }
    }

    fun addToDeck(card: CardEntry){
        val paireList = ArrayList<Paire>()
        paireList.add(Paire(card.title!!, card.position!!))
        val theTitle: String = "Add to deck"
        SimpleEventDialog(this, theTitle, paireList) {
            if (it > 0) {
                cards_selected.add(card)
                deckList = cards_selected.toMutableList()
                deck_Adapter = DeckAdapter( deckList,
                        { entry, position, button -> onDeckClicked(entry, position, button) })
                recycler_view_2.adapter = deck_Adapter
            }
        }
        applicationContext.toast("Added to deck: " + card.title)
    }

    fun addToDeck_ex(card: CardEntry){
        cards_selected.add(card)
        deckList = cards_selected.toMutableList()
        deck_Adapter = DeckAdapter( deckList,
                { entry, position, button -> onDeckClicked(entry, position, button) })
        recycler_view_2.adapter = deck_Adapter
        applicationContext.toast("Added to deck: " + card.title)
    }

    fun delFromDeck(card: CardEntry){
        val paireList = ArrayList<Paire>()
        paireList.add(Paire(card.title!!, card.position!!))
        val theTitle: String = "Remove card from Deck"
        SimpleEventDialog(this, theTitle, paireList) {
            if (it > 0) {
                cards_selected.remove(card)
                deckList = cards_selected.toMutableList()
                deck_Adapter = DeckAdapter( deckList,
                        { entry, position, button -> onDeckClicked(entry, position, button) })
                recycler_view_2.adapter = deck_Adapter
            }
        }
        applicationContext.toast("Removed from deck: " + card.title)
    }

    fun delFromDeck_ex(card: CardEntry){
        cards_selected.remove(card)
        deckList = cards_selected.toMutableList()
        deck_Adapter = DeckAdapter( deckList,
                { entry, position, button -> onDeckClicked(entry, position, button) })
        recycler_view_2.adapter = deck_Adapter
        applicationContext.toast("Removed from deck: " + card.title)
        deck_Adapter.notifyDataSetChanged()
    }

    private val mHideHandler = Handler()
    private val mThankyouRunnable = Runnable { thankyouRunnable() }

    private fun thankyou(delayMillis: Int) {
        val theTitle: String = "Thank You for using Netrunner"
        ThankyouEventDialog(this, theTitle)
        mHideHandler.removeCallbacks(mThankyouRunnable)
        mHideHandler.postDelayed(mThankyouRunnable, delayMillis.toLong())
    }

    fun thankyouRunnable(){
        finish()
    }

    fun setAllVisible(){
        card_Adapter.allVisible = !card_Adapter.allVisible
    }
    //endregion


}
