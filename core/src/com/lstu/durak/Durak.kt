package com.lstu.durak

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.SpriteBatch


class Durak() : Game() {
    lateinit var batch: SpriteBatch
    lateinit var menuScreen: MainMenuScreen
    lateinit var gameScreen: GameScreen

    override fun create() {
        batch = SpriteBatch()
        menuScreen = MainMenuScreen(this, this.batch)
        gameScreen = GameScreen(this, this.batch)
        setScreen(menuScreen)
    }

    override fun render() {
        super.render()
    }

    fun showGame(){
        setScreen(GameScreen(this, batch))
    }

    fun showMenu(){
        setScreen(MainMenuScreen(this, batch))
    }

    override fun dispose() {
        batch.dispose()
        menuScreen.dispose()
        gameScreen.dispose()
    }
}