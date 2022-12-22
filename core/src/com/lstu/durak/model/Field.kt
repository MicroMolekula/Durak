package com.lstu.durak.model

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Group

class Field(val heightCard: Float,  val widthCard: Float, val attack: Boolean){

    val group = Group()

    var meshBool = Array(6, {true})
    val mesh = arrayOf(
        arrayOf(0f, heightCard*0.9f),
        arrayOf(widthCard*0.4f, heightCard*0.9f),
        arrayOf(2*widthCard*0.4f, heightCard*0.9f),
        arrayOf(0f, 0f),
        arrayOf(widthCard*0.4f, 0f),
        arrayOf(2*widthCard*0.4f, 0f)
    )

    init{
        if(attack){
            group.setPosition(0.2f*widthCard, Gdx.graphics.height.toFloat()/3)
            group.setSize(widthCard*3, heightCard*2)
        }
        else{
            group.setPosition(0.25f*widthCard, Gdx.graphics.height.toFloat()/3 - 0.05f*heightCard)
            group.setSize(widthCard*3, heightCard*2)
        }
    }
}