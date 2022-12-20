package com.lstu.durak

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.FillViewport
import com.lstu.durak.model.Card
import com.lstu.durak.model.CardsValue
import com.lstu.durak.model.Deck
import com.lstu.durak.model.Suit

class Durak() : Game() {
    lateinit var batch: SpriteBatch
    lateinit var menuScreen: MainMenuScreen

    override fun create() {
        batch = SpriteBatch()
        menuScreen = MainMenuScreen(this, this.batch)
        setScreen(menuScreen)
    }

    override fun render() {
        super.render()
    }

    override fun dispose() {
    }
}