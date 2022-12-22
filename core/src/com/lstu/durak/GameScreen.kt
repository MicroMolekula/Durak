package com.lstu.durak

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.*
import com.badlogic.gdx.utils.viewport.FillViewport
import com.lstu.durak.actors.CardActor
import com.lstu.durak.model.*
import java.lang.Math.abs
import java.sql.DriverManager.println
import kotlin.properties.Delegates
import kotlin.random.Random


class GameScreen(val game: Durak): Screen{

    var stage: Stage? = null;
    val batch: SpriteBatch = SpriteBatch()
    var widthCard by Delegates.notNull<Float>()
    var heightCard by Delegates.notNull<Float>()
    lateinit var p1: Player
    lateinit var p2: Player
    lateinit var deck: Deck
    lateinit var handP1: Group
    lateinit var handP2: Group
    lateinit var trump: CardActor
    lateinit var backCard: TextureActor
    lateinit var bgGame: Texture
    lateinit var fieldAttack: Field
    lateinit var fieldDef: Field
    lateinit var sizeDeck: BitmapFont
    lateinit var sizeHandP1: BitmapFont
    lateinit var sizeHandP2: BitmapFont
    lateinit var textTurn: BitmapFont
    lateinit var gameState: GameState
    private var flag: Boolean = true
    private lateinit var fieldAttackArray: ArrayList<CardActor>
    private lateinit var fieldDefArray: ArrayList<CardActor>


    var turn: Int? = null


    enum class GameState{
        ATTACKP1,
        ATTACKP2,
        DEFP1,
        DEFP2
    }



    constructor(game: Durak, batch: Batch): this(game){
        // Основная инициализация сцены
        stage = Stage(FillViewport(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat()), batch)
        Gdx.input.inputProcessor = stage
        bgGame = Texture("fonGame.png")
        val bg = TextureActor(bgGame)
        turn = Random.nextInt(0, 2)

        backCard = TextureActor(Texture("back.jpg"))
        backCard.width = 225f * Gdx.graphics.width/480f * 0.5f
        backCard.height = 315f * Gdx.graphics.height/854f * 0.5f
        backCard.setPosition(-backCard.width, Gdx.graphics.height.toFloat()/2 - backCard.height/6)
        bg.width = Gdx.graphics.width.toFloat()
        bg.height = Gdx.graphics.height.toFloat()
        bg.setPosition(0f, 0f)
        p1 = Player(0)
        p2 = Player(1)
        deck = Deck()
        initText()
        widthCard = 225f* Gdx.graphics.width/480f
        heightCard = 315f * Gdx.graphics.height/854f
        stage!!.addActor(bg)
        fieldAttackArray = ArrayList()
        fieldDefArray = ArrayList()

        takeTrump(batch)

        fieldAttack = Field(widthCard, heightCard, true)
        fieldDef = Field(widthCard, heightCard, false)
        gameState = GameState.ATTACKP1

        //Раздача кард
        initCardPlayer(p1, p2, deck, batch)
        printHand1()
        printHand2()

        // Определение хода игрока
        for (card in handP1.children) {
            initMoveCard(card, handP1, p1)
        }
        for (card in handP2.children) {
            initMoveCard(card, handP2, p2)
        }

        stage!!.addActor(fieldAttack.group)
        stage!!.addActor(fieldDef.group)

//        stage!!.addListener(object : InputListener(){
//            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
//                for(card in getHand(turn!!).children) {
//                    if ((x < card.x || x > card.x + card.width) && (y < card.y || y > card.y + card.height)) {
//                        turn = abs(turn!! - 1)
//                        return true
//                    }
//                }
//                if(getHand(turn!!).children.size == 0){
//                    turn = abs(turn!! - 1)
//                    return true
//                }
//                return false
//            }
//        })

    }

    override fun render(delta: Float) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
//        when(gameState){
//            GameState.ATTACKP1 -> {
//                var flag = false
//                for(cardF in fieldAttackArray){
//                    for(cardP in p1.hand){
//                        if(cardF.value.value != cardP.value.value)
//                            flag = true
//                    }
//                }
//                if(flag) gameState = GameState.DEFP2
//            }
//            GameState.ATTACKP2 -> {
//                var flag = false
//                for(cardF in fieldAttackArray){
//                    for(cardP in p2.hand){
//                        if(cardF.value.value != cardP.value.value)
//                            flag = true
//                    }
//                }
//                if(flag) gameState = GameState.DEFP1
//            }
//            GameState.DEFP2 -> {
//
//            }
//            GameState.DEFP1 -> {
//
//            }
//        }
        stage!!.act(delta)
        stage!!.draw()
        batch.begin()
        sizeDeck.draw(batch, "${deck.cards!!.size}", 0f, Gdx.graphics.height.toFloat()/2 + widthCard*0.2f)
        sizeHandP1.draw(batch, "${handP1.children.size}", 0f, heightCard*0.6f)
        sizeHandP2.draw(batch, "${p2.hand.size}", 0f, Gdx.graphics.height.toFloat() - heightCard*0.6f)
        if(turn!! == 0) textTurn.draw(batch, "Turn: your", Gdx.graphics.width.toFloat() - widthCard, heightCard*0.6f)
        else textTurn.draw(batch, "Turn: enemy", Gdx.graphics.width.toFloat() - widthCard, heightCard*0.6f)
        batch.end()
        println("${gameState}")
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

    private fun initText(){
        sizeDeck = BitmapFont()
        sizeDeck.getData().setScale(1.3f)
        sizeHandP1 = BitmapFont()
        sizeHandP1.getData().setScale(1.3f)
        sizeHandP2 = BitmapFont()
        sizeHandP2.getData().setScale(1.3f)
        textTurn = BitmapFont()
        textTurn.getData().setScale(1.3f)

    }

    private fun initMoveCard(cardActor: Actor, handP: Group, p: Player){
        cardActor.addListener(object : InputListener(){
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                val field: Field
                val fieldArr: ArrayList<CardActor>
                if(p.id == turn) field = fieldAttack
                else field = fieldDef
                handP.removeActor(cardActor)
                field.group.addActor(cardActor)
                //fieldArr.add(p.hand[p.hand.indexOf(cardActor)])
                for(j in field.mesh.indices){
                    if (field.meshBool[j]) {
                        cardActor.setPosition(field.mesh[j][0], field.mesh[j][1])
                        field.meshBool[j] = false
                        p.hand.removeAt(p.hand.indexOf(cardActor))
                        break
                    }
                    else continue
                }
                return true
            }
        })
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
            p1.hand[i].setPosition(p1.hand[i-1].x + p1.hand[i].width*0.3f , 0f)
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
            p2.hand[i].setPosition(p2.hand[i-1].x + p2.hand[i].width*0.3f , 0f)
        }

        handP2 = Group()
        handP2.setSize(widthCard*p2.hand.size, heightCard)
        handP2.setPosition(0f, Gdx.graphics.height.toFloat() - p2.hand[0].height * p2.hand[0].scaleY)

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
        trump.setPosition(heightCard*0.15f, Gdx.graphics.height.toFloat()/2 -  trump.height/3.5f)
        stage!!.addActor(trump)
        stage!!.addActor(backCard)
    }

    private fun getPlayer(id: Int): Player{
        if (id == 0) return p1
        return p2
    }

    private fun getHand(id: Int): Group{
        if(id == 0) return handP1
        return handP2
    }

}