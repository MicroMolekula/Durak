package com.lstu.durak

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FillViewport
import com.lstu.durak.model.Card
import com.lstu.durak.model.CardsValue
import com.lstu.durak.model.Deck
import com.lstu.durak.model.Suit

class Drop: Game() {
    private val menu: Durak = Durak(this)
    var img: Texture? = null
    lateinit var batch: SpriteBatch
    var textureRegion: TextureRegion? = null
    var stage: Stage? = null

    override fun create() {
        batch = SpriteBatch()
        stage = Stage(FillViewport(800f, 600f))
        img = Texture("cardsImg.png")
        val card = Card(Suit.SPADES, CardsValue.SEVEN);
        textureRegion = TextureRegion(img, if (card.value.value != 14) 225*(card.value.value - 1) else 0, 315*(card.suit.value), 225, 315)
        val deck: Deck = Deck()
        this.setScreen(menu)
    }

    override fun render() {
        super.render()
    }

    override fun dispose() {
        menu.dispose()
    }
}