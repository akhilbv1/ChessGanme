package org.sample.chessgame

import android.content.Context
import androidx.core.content.ContextCompat

fun getBlockTitle(rIndex: Int): String {
    /* val index = if(rIndex==0) 0 else rIndex-1*/
    val titles = arrayListOf("a", "b", "c", "d", "e", "f", "g", "h")
    return titles[rIndex - 1]
}

fun determineColorOfBlock(context: Context, colIndex: Int): Pair<Int, Int> {
    var evenColor = -1
    var oddColor = -1

    if (colIndex % 2 == 0) {
        evenColor = ContextCompat.getColor(context, R.color.block_selected_color)
        oddColor = ContextCompat.getColor(context, R.color.light_white)
    } else if (colIndex % 2 == 1) {
        evenColor = ContextCompat.getColor(context, R.color.light_white)
        oddColor = ContextCompat.getColor(context, R.color.block_selected_color)
    }

    return Pair(evenColor, oddColor)
}

fun determineColorOfBlock(blockPosition: Int): Int {
    val blockColorType = -1
    val bp = (blockPosition + 1) % 2

    if (bp == 1) {
        return WHITE
    } else if (bp == 0) {
        return BLACK
    }

    return -1
}


fun getColumnPositionsListByColumnIndex(cIndex: Int): ArrayList<Int> {
    val colPosList: ArrayList<Int> = ArrayList()

    var counterValue = cIndex - 1
    colPosList.add(counterValue)
    for (i in 1..7) {
        counterValue += 8
        colPosList.add(counterValue)

    }
    return colPosList
}

fun getRowPositionsListByRowIndex(rIndex: Int): ArrayList<Int> {
    val rowPosList: ArrayList<Int> = ArrayList()
    val tempRowIndex = rIndex - 1

    var counterValue = tempRowIndex * 8
    rowPosList.add(counterValue)
    for (i in 1..7) {
        counterValue += 1
        rowPosList.add(counterValue)
    }

    return rowPosList

}

fun getRowIndex(position: Int): Int {
    // return (position % 8) + 1

    when (position) {
        in 0..7 -> {
            return 1
        }

        in 8..15 -> {
            return 2
        }

        in 16..23 -> {
            return 3
        }
        in 24..31 -> {
            return 4
        }

        in 32..39 -> {
            return 5
        }

        in 40..47 -> {
            return 6
        }

        in 48..55 -> {
            return 7
        }

        in 56..63 -> {
            return 8
        }
    }
    return -1
}


fun getColumnIndex(position: Int): Int {
    //   return (position / 8) + 1
    when (position) {
        0, 8, 16, 24, 32, 40, 48, 56 -> {
            return 1
        }
        1, 9, 17, 25, 33, 41, 49, 57 -> {
            return 2
        }
        2, 10, 18, 26, 34, 42, 50, 58 -> {
            return 3
        }
        3, 11, 19, 27, 35, 43, 51, 59 -> {
            return 4
        }
        4, 12, 20, 28, 36, 44, 52, 60 -> {
            return 5
        }
        5, 13, 21, 29, 37, 45, 53, 61 -> {
            return 6
        }
        6, 14, 22, 30, 38, 46, 54, 62 -> {
            return 7
        }
        7, 15, 23, 31, 39, 47, 55, 63 -> {
            return 8
        }
    }
    return -1
}


fun getPieceAvailableMoves(chessBoardBlockModel: ChessBoardBlockModel): ArrayList<Int> {
    val movesList: ArrayList<Int> = ArrayList()
    movesList.addAll(ChessPieceMovesUtils.getMovesList(chessBoardBlockModel))
    return movesList
}


fun getChessDrawableResource(cpType: Int, cpColor: Int): Int {

    var resourceId = -1
    val blackPieces = arrayListOf(
        R.drawable.ic_rb,
        R.drawable.ic_nb,
        R.drawable.ic_bishop_black,
        R.drawable.ic_qb,
        R.drawable.ic_kb,
        R.drawable.ic_bishop_black,
        R.drawable.ic_nb,
        R.drawable.ic_rb
    )
    val whitePieces = arrayListOf(
        R.drawable.ic_rw,
        R.drawable.ic_nw,
        R.drawable.ic_bw,
        R.drawable.ic_qw,
        R.drawable.ic_kw,
        R.drawable.ic_bw,
        R.drawable.ic_nw,
        R.drawable.ic_rw
    )

    when (cpType) {
        ROOK -> {
            resourceId = if (cpColor == WHITE) {
                R.drawable.ic_rw
            } else {
                R.drawable.ic_rb
            }
        }
        HORSE -> {
            resourceId = if (cpColor == WHITE) {
                R.drawable.ic_nw
            } else {
                R.drawable.ic_nb
            }
        }
        KNIGHT -> {
            resourceId = if (cpColor == WHITE) {
                R.drawable.ic_bw
            } else {
                R.drawable.ic_bishop_black
            }
        }
        QUEEN -> {
            resourceId = if (cpColor == WHITE) {
                R.drawable.ic_qw
            } else {
                R.drawable.ic_qb
            }
        }
        KING -> {
            resourceId = if (cpColor == WHITE) {
                R.drawable.ic_kw
            } else {
                R.drawable.ic_kb
            }
        }
        PAWN -> {
            resourceId = if (cpColor == WHITE) {
                R.drawable.ic_pw
            } else {
                R.drawable.ic_pb
            }
        }
    }
    return resourceId
}

fun getDiffTopAndLeft(currentIndex: Int): Int =
    if (currentIndex == -1) -1 else currentIndex - MIN_COUNT

fun getDiffBottomAndRight(currentIndex: Int): Int =
    if (currentIndex == -1) -1 else MAX_COUNT - currentIndex

fun getDefaultChessPieceTypeAndColorByPosition(context: Context, position: Int): Pair<Int, Int> {
    val piecesList = arrayListOf(ROOK, HORSE, KNIGHT, QUEEN, KING, KNIGHT, HORSE, ROOK)
    val colorsList = arrayListOf(WHITE, BLACK)

    var cpType = -1
    var colorType = -1

    val rIndex = getRowIndex(position)
    val cIndex = getColumnIndex(position)

    if (rIndex <= 2) {
        colorType = colorsList[0]
    } else if (rIndex >= 7) {
        colorType = colorsList[1]
    }

    if (rIndex == 2 || rIndex == 7) {
        cpType = PAWN
    } else if (rIndex == 1) {
        cpType = piecesList[position]
    } else if (rIndex == 8) {
        val arrayPos = (position % 8)
        cpType = piecesList[arrayPos]
    }

    return Pair(cpType, colorType)
}