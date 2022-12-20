package com.lstu.durak

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FillViewport
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport

class MainMenuScreen(val game: Durak): Screen {

    constructor(game: Durak, batch: Batch): this(game){
        val stage: Stage = Stage(FillViewport(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat()), batch)
        val bg = Texture("")
    }

    override fun render(delta: Float) {

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
}