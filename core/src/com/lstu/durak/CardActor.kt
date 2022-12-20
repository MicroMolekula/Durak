package com.lstu.durak

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import com.lstu.durak.model.Card

class CardActor(val value: Card, private val game: Drop) : Actor(){
    var xImg = 0
    var yImg = 0
    var widthImg = 225
    var heightImg = 315
    var face = game.textureRegion

    init{
        imgCards(value.value.value, value.suit.value)
    }
    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
        batch!!.draw(face, 0f, 0f)
    }

    private fun imgCards(x:Int, y:Int){
        face?.regionX = widthImg*x
        face?.regionY = heightImg*y
    }
}