package com.lstu.durak.model

import com.badlogic.gdx.Gdx
import java.util.Collections.shuffle

class Deck {
    var cards: ArrayList<Card>? = null

    init{
        cards = ArrayList()
        reset()
        shuffle(cards!!)
    }

    private fun reset(){
        cards!!.clear()
        cards = ArrayList()
        for(s in Suit.values()){
            for(v in CardsValue.values()){
                cards!!.add(Card(s, v))
            }
        }
    }


}