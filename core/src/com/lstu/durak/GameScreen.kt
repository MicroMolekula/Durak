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
    lateinit var handP1: Group
    lateinit var handP2: Group
    lateinit var trump: CardActor
    lateinit var field: Group
    lateinit var backCard: TextureActor



    constructor(game: Durak, batch: Batch): this(game){
        // Основная инициализация сцены
        stage = Stage(FillViewport(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat()), batch)
        val bgGame = Texture("fonGame.png")
        val bg = TextureActor(bgGame)
        backCard = TextureActor(Texture("back.jpg"))
        backCard.width = 225f
        backCard.height = 315f
        backCard.setPosition(-backCard.width/1.5f, Gdx.graphics.height.toFloat()/2 - backCard.height/2)
        bg.width = Gdx.graphics.width.toFloat()
        bg.height = Gdx.graphics.height.toFloat()
        bg.setPosition(0f, 0f)
        p1 = Player(0)
        p2 = Player(1)
        deck = Deck()
        widthCard = 225f* Gdx.graphics.width/480f
        heightCard = 315f*Gdx.graphics.height/854f
        stage!!.addActor(bg)

        takeTrump(batch)

        //Раздача кард
        initCardPlayer(p1, p2, deck, batch)
        printHand1()
        printHand2()

        Gdx.app.log("size", "${deck.cards!!.size}")
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

    private fun printHand1(){
        p1.hand[0].width = widthCard
        p1.hand[0].height = heightCard
        p1.hand[0].setScale(0.5f)
        p1.hand[0].setPosition( 0f, 0f)
        for(i in 1 until p1.hand.size){
            p1.hand[i].width = widthCard
            p1.hand[i].height = heightCard
            p1.hand[i].setScale(0.5f)
            p1.hand[i].setPosition(p1.hand[i-1].x + p1.hand[i].width*0.2f , 0f)
        }

        handP1 = Group()
        handP1.setSize(widthCard*p1.hand.size, heightCard)
        handP1.setPosition(0f, 0f)

        for(i in p1.hand){
            handP1.addActor(i)
        }
        stage!!.addActor(handP1)
    }

    private fun printHand2(){
        p2.hand[0].width = widthCard
        p2.hand[0].height = heightCard
        p2.hand[0].setScale(0.5f)
        p2.hand[0].setPosition( 0f, 0f)
        for(i in 1 until p2.hand.size){
            p2.hand[i].width = widthCard
            p2.hand[i].height = heightCard
            p2.hand[i].setScale(0.5f)
            p2.hand[i].setPosition(p2.hand[i-1].x + p2.hand[i].width*0.2f , 0f)
        }

        handP2 = Group()
        handP2.setSize(widthCard*p2.hand.size, heightCard)
        handP2.setPosition(0f, Gdx.graphics.height.toFloat() - p2.hand[0].height * 0.7f)

        for(i in p2.hand){
            handP2.addActor(i)
        }
        stage!!.addActor(handP2)
    }

    private fun takeTrump(batch: Batch){
        trump = CardActor(deck.cards?.get(deck.cards!!.size-1)!!, game, batch)
        deck.cards?.removeAt(deck.cards!!.size-1)
        trump.width = widthCard
        trump.height = heightCard
        trump.setScale(0.5f)
        trump.rotation = 90f
        trump.setPosition(90f, Gdx.graphics.height.toFloat()/2 -  trump.height/3.5f)
        stage!!.addActor(trump)
        stage!!.addActor(backCard)
    }

}