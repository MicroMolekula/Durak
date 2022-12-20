package com.lstu.durak

import com.badlogic.gdx.Screen
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.FillViewport
import com.lstu.durak.model.Card
import com.lstu.durak.model.CardsValue
import com.lstu.durak.model.Deck
import com.lstu.durak.model.Suit

class Durak(val game: Drop) : Screen {
    init{
        val card = Card(Suit.CLUBS, CardsValue.ACE);
        val act: CardActor = CardActor(card, this.game)
        val deck: Deck = Deck()
        game.stage?.addActor(act)
    }

    override fun render(delta: Float) {
        ScreenUtils.clear(1f, 0f, 0f, delta)
        game.batch.begin()
        game.batch!!.draw(game.textureRegion, 0f, 0f)
        game.batch.end()
        game.stage?.act(delta)
        game.stage?.draw()
    }

    override fun dispose() {

    }

    override fun show() {

    }

    override fun resize(width: Int, height: Int) {

    }

    override fun pause() {

    }

    override fun resume() {

    }

    override fun hide() {

    }
}