package com.lstu.durak

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FillViewport
import com.lstu.durak.actors.CardActor
import com.lstu.durak.model.Card
import com.lstu.durak.model.CardsValue
import com.lstu.durak.model.Suit

class GameScreen(val game: Durak): Screen{
    var stage: Stage? = null;
    constructor(game: Durak, batch: Batch): this(game){
        stage = Stage(FillViewport(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat()), batch)
        val bgGame = Texture("fonGame.png")
        val bg = TextureActor(bgGame)
        bg.width = Gdx.graphics.width.toFloat()
        bg.height = Gdx.graphics.height.toFloat()
        bg.setPosition(0f, 0f)

        val card = Card(Suit.SPADES, CardsValue.KING)
        val cardAct: CardActor = CardActor(card, game, batch)
        cardAct.setPosition(0f, 0f)

        cardAct.setScale(0.5f)


        stage!!.addActor(bg)
        stage!!.addActor(cardAct)
    }

    override fun render(delta: Float) {
        stage!!.draw()
    }

    override fun resume() {

    }

    override fun hide() {

    }

    override fun show() {

    }

    override fun pause() {

    }

    override fun dispose() {

    }

    override fun resize(width: Int, height: Int) {

    }
    class TextureActor(val bg: Texture): Actor(){
        override fun draw(batch: Batch?, parentAlpha: Float) {
            super.draw(batch, parentAlpha)
            batch!!.draw(bg, x, y, width, height)
        }
    }
}