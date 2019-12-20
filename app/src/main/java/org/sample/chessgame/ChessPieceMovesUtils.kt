package org.sample.chessgame


class ChessPieceMovesUtils {
    companion object {

        fun getMovesList(chessBoardBlockModel: ChessBoardBlockModel): ArrayList<Int> {
            val movesList: ArrayList<Int> = ArrayList()
            when (chessBoardBlockModel.cpType) {
                KING -> {
                    movesList.addAll(getKingMovesList(chessBoardBlockModel))
                }
                QUEEN -> {
                    movesList.addAll(getQueenMovesList(chessBoardBlockModel))

                }
                ROOK -> {
                    movesList.addAll(getRookMovesList(chessBoardBlockModel))

                }
                HORSE -> {
                    movesList.addAll(getHorseMovesList(chessBoardBlockModel))
                }
                KNIGHT -> {
                    movesList.addAll(getKnightMovesList(chessBoardBlockModel))
                }
                PAWN -> {
                    movesList.addAll(getPawnMovesList(chessBoardBlockModel))
                }
            }
            return movesList
        }


        private fun getKingMovesList(chessBoardBlockModel: ChessBoardBlockModel): ArrayList<Int> {
            val movesList: ArrayList<Int> = ArrayList()
            val row = getRowIndex(chessBoardBlockModel.blockPosition)
            movesList.add(chessBoardBlockModel.blockPosition + 1)
            movesList.add(chessBoardBlockModel.blockPosition - 1)

            if (row == 1) {
                movesList.add(chessBoardBlockModel.blockPosition + 8)
                movesList.add(chessBoardBlockModel.blockPosition + 9)
                movesList.add(chessBoardBlockModel.blockPosition + 7)
            } else {
                movesList.add(chessBoardBlockModel.blockPosition - 8)
                movesList.add(chessBoardBlockModel.blockPosition - 9)
                movesList.add(chessBoardBlockModel.blockPosition - 7)
            }
            return movesList
        }

        private fun getQueenMovesList(chessBoardBlockModel: ChessBoardBlockModel): ArrayList<Int> {
            val allMovesList: ArrayList<Int> = ArrayList()
            val movesList: ArrayList<Int> = ArrayList()
            var pos1 = chessBoardBlockModel.blockPosition
            var pos2 = chessBoardBlockModel.blockPosition
            var pos3 = chessBoardBlockModel.blockPosition
            var pos4 = chessBoardBlockModel.blockPosition

            for (i in 1..7) {

                pos1 += 8
                pos2 -= 8
                pos3 += 1
                pos4 -= 1
                allMovesList.add(pos1)
                allMovesList.add(pos2)
                allMovesList.add(pos3)
                allMovesList.add(pos4)
            }

            val posRow = getRowIndex(chessBoardBlockModel.blockPosition)
            val posColumn = getColumnIndex(chessBoardBlockModel.blockPosition)

            allMovesList.forEach {

                if ((it in 0..63) && (getRowIndex(it) == posRow || getColumnIndex(it) == posColumn)) {
                    movesList.add(it)
                }

            }
            allMovesList.clear()
            val currentColumn = getColumnIndex(chessBoardBlockModel.blockPosition)
            var counter = chessBoardBlockModel.blockPosition
            var tempCounter = chessBoardBlockModel.blockPosition

            for (i in currentColumn downTo 2) {
                counter += 7
                allMovesList.add(counter)

                tempCounter -= 9
                allMovesList.add(tempCounter)
            }

            counter = chessBoardBlockModel.blockPosition
            tempCounter = chessBoardBlockModel.blockPosition

            for (i in currentColumn..7) {
                counter -= 7
                allMovesList.add(counter)
                tempCounter += 9
                allMovesList.add(tempCounter)
            }



            allMovesList.forEach {
                if ((it in 0..63)) {
                    movesList.add(it)
                }

            }

            return movesList
        }

        private fun getRookMovesList(chessBoardBlockModel: ChessBoardBlockModel): ArrayList<Int> {
            val allMovesList: ArrayList<Int> = ArrayList()
            val movesList: ArrayList<Int> = ArrayList()
            var pos1 = chessBoardBlockModel.blockPosition
            var pos2 = chessBoardBlockModel.blockPosition
            var pos3 = chessBoardBlockModel.blockPosition
            var pos4 = chessBoardBlockModel.blockPosition
            for (i in 1..7) {
                pos1 += 8
                pos2 -= 8
                pos3 += 1
                pos4 -= 1
                allMovesList.add(pos1)
                allMovesList.add(pos2)
                allMovesList.add(pos3)
                allMovesList.add(pos4)
            }

            val posRow = getRowIndex(chessBoardBlockModel.blockPosition)
            val posColumn = getColumnIndex(chessBoardBlockModel.blockPosition)

            allMovesList.forEach {

                if ((it in 0..63) && (getRowIndex(it) == posRow || getColumnIndex(it) == posColumn)) {
                    movesList.add(it)
                }

            }

            return movesList
        }


        private fun getHorseMovesList(chessBoardBlockModel: ChessBoardBlockModel): ArrayList<Int> {
            val currentRowIndex = getRowIndex(chessBoardBlockModel.blockPosition)
            val allMovesList: ArrayList<Int> = ArrayList()
            val movesList: ArrayList<Int> = ArrayList()
            val currentColIndex = getColumnIndex(chessBoardBlockModel.blockPosition)

            if (getDiffBottomAndRight(currentRowIndex) >= 3) {
                allMovesList.add((chessBoardBlockModel.blockPosition + (2 * 8)) + 1)
                allMovesList.add((chessBoardBlockModel.blockPosition + (2 * 8)) - 1)
            }

            if (getDiffTopAndLeft(currentRowIndex) >= 3) {
                allMovesList.add((chessBoardBlockModel.blockPosition - (2 * 8)) + 1)
                allMovesList.add((chessBoardBlockModel.blockPosition - (2 * 8)) - 1)
            }

            if (getDiffTopAndLeft(currentColIndex) >= 3) {
                allMovesList.add((chessBoardBlockModel.blockPosition + (1 * 8)) - 2)
                allMovesList.add((chessBoardBlockModel.blockPosition - (1 * 8)) - 2)

            }

            if (getDiffBottomAndRight(currentColIndex) >= 3) {
                allMovesList.add((chessBoardBlockModel.blockPosition + (1 * 8)) + 2)
                allMovesList.add((chessBoardBlockModel.blockPosition - (1 * 8)) + 2)
            }


            allMovesList.forEach {
                if ((it in 0..63) && currentRowIndex != getRowIndex(it)) {
                    movesList.add(it)
                }

            }

            return movesList
        }

        private fun getKnightMovesList(chessBoardBlockModel: ChessBoardBlockModel): ArrayList<Int> {
            val allMovesList: ArrayList<Int> = ArrayList()
            val movesList: ArrayList<Int> = ArrayList()
            val currentColumn = getColumnIndex(chessBoardBlockModel.blockPosition)
            var counter = chessBoardBlockModel.blockPosition
            var tempCounter = chessBoardBlockModel.blockPosition

            for (i in currentColumn downTo 2) {
                counter += 7
                allMovesList.add(counter)

                tempCounter -= 9
                allMovesList.add(tempCounter)
            }

            counter = chessBoardBlockModel.blockPosition
            tempCounter = chessBoardBlockModel.blockPosition

            for (i in currentColumn..7) {
                counter -= 7
                allMovesList.add(counter)
                tempCounter += 9
                allMovesList.add(tempCounter)
            }

            allMovesList.forEach {
                if (it in 0..63) {
                    movesList.add(it)
                }
            }
            return movesList
        }

        private fun getPawnMovesList(chessBoardBlockModel: ChessBoardBlockModel): ArrayList<Int> {
            val movesList: ArrayList<Int> = ArrayList()
            val rowInd = getRowIndex(chessBoardBlockModel.blockPosition)
            if (chessBoardBlockModel.cpColor == WHITE) {

                if (rowInd == 2) {
                    movesList.add(chessBoardBlockModel.blockPosition + 2 * 8)
                }
                movesList.add(chessBoardBlockModel.blockPosition + 8)
            } else {
                if (rowInd == 7) {
                    movesList.add(chessBoardBlockModel.blockPosition - 2 * 8)
                }
                movesList.add(chessBoardBlockModel.blockPosition - 8)
            }
            return movesList
        }
    }
}