package com.lstu.durak

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.actions.Actions.hide
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.viewport.FillViewport
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import java.awt.Font

class MainMenuScreen(val game: Durak): Screen {
    var stage: Stage? = null
    lateinit var btnPlay: Button
    lateinit var bgTexture: Texture
    lateinit var btnTexture: Texture
    lateinit var bg: TextureActor

    constructor(game: Durak, batch: Batch): this(game){
        stage = Stage(FillViewport(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat()), batch)
        bgTexture = Texture("bgmenu.png")
        btnTexture = Texture("btnPlay.png")
        bg = TextureActor(bgTexture)
        bg.setPosition(0f, 0f)
        bg.width = Gdx.graphics.width.toFloat()
        bg.height = Gdx.graphics.height.toFloat()

        btnPlay = Button(btnTexture)
        btnPlay.setScale(0.3f)
        btnPlay.width = btnPlay.texture.width.toFloat()
        btnPlay.height = btnPlay.texture.height.toFloat()
        btnPlay.setPosition(Gdx.graphics.width.toFloat()/2 - btnPlay.width*btnPlay.scaleX/2 , Gdx.graphics.height.toFloat()/2 + btnPlay.height*btnPlay.scaleY/2)
        btnPlay.addListener(BtnPlay(game, game::showGame))


        stage!!.addActor(bg)
        stage!!.addActor(btnPlay)
        // Проверка
    }

    class BtnPlay(val game: Durak, val func: () -> Unit): ClickListener(){
        override fun clicked(event: InputEvent?, x: Float, y: Float) {
            func()
        }
    }



    override fun render(delta: Float) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        stage!!.act()
        stage!!.draw()
    }

    override fun resume() {
    }

    override fun hide() {
        btnPlay.removeListener(BtnPlay(game, game::showGame))
    }

    override fun show() {
        Gdx.input.setInputProcessor(stage)
    }

    override fun pause() {
    }

    override fun dispose() {
        stage!!.dispose()
        bgTexture.dispose()
        bgTexture.dispose()
    }

    override fun resize(width: Int, height: Int) {
    }

    class TextureActor(val bg: Texture):Actor(){
        override fun draw(batch: Batch?, parentAlpha: Float) {
            batch!!.draw(bg, x, y, width, height)
        }
    }

    class Button(val texture: Texture):Actor(){
        override fun draw(batch: Batch?, parentAlpha: Float) {
            super.draw(batch, parentAlpha)
            batch!!.draw(texture, x, y, originX, originY, width.toFloat()* Gdx.graphics.width/480f, height.toFloat()*Gdx.graphics.height/854f, scaleX, scaleY, 0f, 0, 0, width.toInt(), height.toInt(), false, false)
        }
    }
}