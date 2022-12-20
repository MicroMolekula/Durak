package com.lstu.durak

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.viewport.FillViewport
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport

class MainMenuScreen(val game: Durak): Screen {
    var stage: Stage? = null

    constructor(game: Durak, batch: Batch): this(game){
        stage = Stage(FillViewport(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat()), batch)
        val bgTexture = Texture("bgmenu.png")
        val btnTexture = Texture("btnPlay.png")
        val bg = TextureActor(bgTexture)
        bg.setPosition(0f, 0f)
        bg.width = Gdx.graphics.width.toFloat()
        bg.height = Gdx.graphics.height.toFloat()

        val btnPlay = Button(btnTexture)
        btnPlay.setScale(0.3f)
        btnPlay.width = btnPlay.texture.width.toFloat()
        btnPlay.height = btnPlay.texture.height.toFloat()
        btnPlay.setPosition(Gdx.graphics.width.toFloat()/2 - btnPlay.width*btnPlay.scaleX/2 , Gdx.graphics.height.toFloat()/2 - btnPlay.height*btnPlay.scaleY/2)
        btnPlay.addListener(BtnPlay(game))


        stage!!.addActor(bg)
        stage!!.addActor(btnPlay)
        // Проверка
    }

    class BtnPlay(val game: Durak): ClickListener(){
        override fun clicked(event: InputEvent?, x: Float, y: Float) {
            game.showGame()
        }
    }


    override fun render(delta: Float) {
        stage!!.draw()
    }

    override fun resume() {
    }

    override fun hide() {
    }

    override fun show() {
        Gdx.input.setInputProcessor(stage)
    }

    override fun pause() {
    }

    override fun dispose() {
    }

    override fun resize(width: Int, height: Int) {
    }

    class TextureActor(val bg: Texture):Actor(){
        override fun draw(batch: Batch?, parentAlpha: Float) {
            super.draw(batch, parentAlpha)
            batch!!.draw(bg, x, y, width, height)
        }
    }

    class Button(val texture: Texture):Actor(){
        override fun draw(batch: Batch?, parentAlpha: Float) {
            super.draw(batch, parentAlpha)
            batch!!.draw(texture, x, y, originX, originY, width, height, scaleX, scaleY, 0f, 0, 0, width.toInt(), height.toInt(), false, false)
        }
    }
}