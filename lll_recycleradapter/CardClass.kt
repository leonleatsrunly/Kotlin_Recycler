package com.example.leonly.lll_recycleradapter

import java.lang.reflect.Constructor


public class Cards (
        val cards: CardClass
)

public class CardClass (
        val imageUrlTemplate: String,
        val data: List<CardEntry>,
        val total: Int,
        val success: Boolean,
        val version_number: String,
        val last_updated: String
)

public class CardEntry (
        val code: String = ""
        , val  type: String? = ""
        , val  url: String? = ""
        , val  faction_code: String? = ""
        , val  illustrator: String? = ""
        , val  pack_code: String? = ""
        , val  side_code: String? = ""
        , val  text: String? = ""
        , val  title: String? = ""
        , var  title_1: String? = ""
        , var  title_2: String? = ""
        , val  type_code: String? = ""
        , val  keywords: String? = ""
        , val  flavor: String? = ""

        , val  minimum_deck_size: Int? = 0
        , val  trash_cost: Int? = 0
        , val  advancement_cost: Int? = 0
        , val  cost: Int? = 0
        , val  deck_limit: Int? = 0
        , val  faction_cost: Int? = 0
        , val  position: Int? = 0
        , val  strength: Int? = 0
        , val  quantity: Int? = 0
        , val  uniqueness: Boolean? = false


        , val  size: Int? = 0
        , val  rotated: Boolean? = false
        , val  name: String? = ""

        , val  color: String? = ""
        , val  is_mini: Boolean? = false

        , val  id: Int? = 0
        , val  active: Boolean? = false
        , val  global_penalty: Boolean? = false

        , val  date_creation: String? = ""
        , val  date_start: String? = ""
        , val  date_update: String? = ""

        , val  cycle_code: String? = ""
        , val  date_release: String? = ""


        , val  base_link: Int? = 0
        , val  influence_limit: Int? = 0
        , val  memory_cost: Int? = 0
        , val  agenda_points: Int? = 0
        , var order_points: Int? = 0
        , var  allCost: String? = ""
        , var nb_cards: Int? = 0
)
