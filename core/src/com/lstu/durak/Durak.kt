package com.lstu.durak

import com.badlogic.gdx.Game
import com.badlogic.gdx.Screen
import com.badlogic.gdx.utils.ScreenUtils
import com.lstu.durak.actors.CardActor
import com.lstu.durak.model.Card
import com.lstu.durak.model.CardsValue
import com.lstu.durak.model.Deck
import com.lstu.durak.model.Suit

class Durak() : Game() {
//    init{
//        val card = Card(Suit.CLUBS, CardsValue.ACE);
//        val act: CardActor = CardActor(card, this.game)
//        val deck: Deck = Deck()
//        game.stage?.addActor(act)
//    }

    override fun create(){

    }

    override fun render() {
        ScreenUtils.clear(1f, 0f, 0f, 1f)

    }

    override fun dispose() {

    }
}