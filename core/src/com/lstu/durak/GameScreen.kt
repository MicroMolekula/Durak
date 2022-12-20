package com.lstu.durak

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FillViewport
import com.lstu.durak.actors.CardActor
import com.lstu.durak.model.*
import kotlin.properties.Delegates

class GameScreen(val game: Durak): Screen{
    var stage: Stage? = null;
    var widthCard by Delegates.notNull<Float>()
    var heightCard by Delegates.notNull<Float>()
    lateinit var p1: Player
    lateinit var p2: Player
    lateinit var deck: Deck



    constructor(game: Durak, batch: Batch): this(game){
        // Основная инициализация сцены
        stage = Stage(FillViewport(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat()), batch)
        val bgGame = Texture("fonGame.png")
        val bg = TextureActor(bgGame)
        bg.width = Gdx.graphics.width.toFloat()
        bg.height = Gdx.graphics.height.toFloat()
        bg.setPosition(0f, 0f)
        p1 = Player(0)
        p2 = Player(1)
        deck = Deck()
        widthCard = 225f
        heightCard = 135f

        //Раздача кард
        initCardPlayer(p1, p2, deck, batch)

        p1.hand[0].width = widthCard
        p1.hand[0].height = heightCard
        p1.hand[0].setScale(0.6f)
        p1.hand[0].setPosition( p1.hand[0].width*0.1f, heightCard/2)
        for(i in 1 until p1.hand.size){
            p1.hand[i].width = widthCard
            p1.hand[i].height = heightCard
            p1.hand[i].setScale(0.6f)
            p1.hand[i].setPosition(p1.hand[i-1].x + p1.hand[i].width*0.3f , heightCard/2)
        }

        val handP1 = Group()
        handP1.setSize(widthCard*p1.hand.size, heightCard)

        for(i in p1.hand){
            handP1.addActor(i)
        }

        stage!!.addActor(bg)
        stage!!.addActor(handP1)
    }

    override fun render(delta: Float) {
        stage!!.act(delta)
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

    private fun initCardActor(arr: ArrayList<Card>){

    }

    private fun initCardPlayer(p1: Player, p2: Player, deck: Deck, batch: Batch){
        for(i in 0 until 12){
            if(i % 2 == 0){
                p1.hand.add(CardActor(deck.cards!![i+p1.id], game, batch))
                deck.cards!!.removeAt(i+p1.id)
            }
            else{
                p2.hand.add(CardActor(deck.cards!![i+p2.id], game, batch))
                deck.cards!!.removeAt(i+p2.id)
            }

        }
    }

}