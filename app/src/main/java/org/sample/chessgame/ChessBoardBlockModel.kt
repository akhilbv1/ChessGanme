package org.sample.chessgame

data class ChessBoardBlockModel(var blockTitle:String= "", var blockPosition:Int=-1, var shouldHighlight:Boolean=false, var cpType:Int=-1, var cpColor:Int = -1)