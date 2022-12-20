package com.lstu.durak.actors

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import com.lstu.durak.Durak
import com.lstu.durak.model.Card

class CardActor(val value: Card, private val game: Durak) : Actor(){
    private var xImg = 0
    private var yImg = 0
    private var widthImg = 225
    private var heightImg = 315

    private lateinit var face: TextureRegion


    constructor(value: Card, game: Durak, batch: Batch): this(value, game){
        face = TextureRegion(
            Texture("cardsImg.png"),
            if(value.value.value != 14) widthImg*(value.value.value-1) else 0,
            heightImg*value.suit.value,
            widthImg,
            heightImg
        )
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
        batch!!.draw(face, x, y, originX, originY, widthImg.toFloat(), heightImg.toFloat(), scaleX, scaleY, rotation)
    }
}