package org.sample.chessgame

import android.content.Context

class ChessRulesValidation(val context: Context)
{

    fun getValidChessMovesList(blocksList: ArrayList<ChessBoardBlockModel>,chessBoardBlockModel:ChessBoardBlockModel){
        val allMovesList = getPieceAvailableMoves(chessBoardBlockModel = chessBoardBlockModel)
        val validMovesList:ArrayList<Int> = ArrayList()
        blocksList.forEachIndexed { index, obj ->
            if(obj.cpType!= HORSE)
            {

            }
        }
    }

    fun validateChessMove(cpModel:ChessBoardBlockModel,chessBoardBlockModel: ChessBoardBlockModel){
        val selectesBlockPos = chessBoardBlockModel.blockPosition
        val movesList = getPieceAvailableMoves(cpModel)

        if(!movesList.contains(selectesBlockPos))
        {
            context.showToast("Invalid Position")
        }else if(chessBoardBlockModel.cpType!=-1 && cpModel.cpColor==chessBoardBlockModel.cpColor)
        {
            context.showToast("Cannot move over the chess piece")
        }
    }





}