package com.lstu.durak.model

enum class CardsValue(val value: Int){
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(11),
    QUEEN(12),
    KING(13),
    ACE(14);
}

enum class Suit(val value: Int){
    HEARTS(0),
    SPADES(1),
    CLUBS(2),
    DIAMS(3);
}

data class Card(val suit: Suit, val value: CardsValue)