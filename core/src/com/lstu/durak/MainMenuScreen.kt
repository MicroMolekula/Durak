package com.lstu.durak

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FillViewport
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport

class MainMenuScreen(val game: Durak): Screen {
    var stage: Stage? = null

    constructor(game: Durak, batch: Batch): this(game){
        stage = Stage(FillViewport(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat()), batch)
//        val bgTexture = Texture("bgmenu.png")
//        val bg = BackGround(bgTexture)
//        bg.setPosition(0f, 0f)
//        bg.width = Gdx.graphics.width.toFloat()
//        bg.height = Gdx.graphics.height.toFloat()
//        stage!!.addActor(bg)
        // Проверка
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

    class BackGround(val bg: Texture):Actor(){
        override fun draw(batch: Batch?, parentAlpha: Float) {
            super.draw(batch, parentAlpha)
            batch!!.draw(bg, x, y, width, height)
        }
    }
}