package org.sample.chessgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ChessAdapter.OnChessBoardListener {

    private val TAG: String = MainActivity::class.java.simpleName

    private var currenSelectedChessBoardBlockModel:ChessBoardBlockModel? = null

    private val blocksList: ArrayList<ChessBoardBlockModel> = ArrayList()

    private lateinit var adapter: ChessAdapter

    private val dupBlocksList: ArrayList<ChessBoardBlockModel> = ArrayList()

    override fun onClickChessBlock(chessBoardBlockModel: ChessBoardBlockModel) {

        if (chessBoardBlockModel.shouldHighlight) {

            val selectedBlockPos = chessBoardBlockModel.blockPosition

            val movesList = getPieceAvailableMoves(currenSelectedChessBoardBlockModel!!)

            if(movesList.contains(selectedBlockPos))
            {
                moveChessPiece(chessBoardBlockModel)
            }else {
                showToast("Invalid position")
            }

        } else if(chessBoardBlockModel.cpType!=-1){
            currenSelectedChessBoardBlockModel = chessBoardBlockModel
            blocksList.forEach { it.shouldHighlight = false }
            adapter.notifyDataSetChanged()
            val movesList = getPieceAvailableMoves(chessBoardBlockModel)

            for (i in movesList) {
                val obj = blocksList.find { it.blockPosition == i } as ChessBoardBlockModel

                if (obj.cpType == -1) {
                    obj.shouldHighlight = true
                }
            }
            adapter.notifyDataSetChanged()
        }else if(!chessBoardBlockModel.shouldHighlight)
        {
            showToast("Invalid position")
        }


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prepareChessBoardObjects()
        setupChessBoard()
        clear.setOnClickListener {
           resetChessBoard()
        }
    }

    private fun resetChessBoard(){
        blocksList.clear()
        blocksList.addAll(dupBlocksList)
        adapter.notifyDataSetChanged()
    }

    private fun moveChessPiece(chessBoardBlockModel: ChessBoardBlockModel) {
        // val old = chessBoardBlockModel

        currenSelectedChessBoardBlockModel?.let { chessBoardBlockModel1 ->
            chessBoardBlockModel.cpType = chessBoardBlockModel1.cpType
            chessBoardBlockModel.cpColor = chessBoardBlockModel1.cpColor
            chessBoardBlockModel1.cpType = -1
            chessBoardBlockModel1.cpColor = -1
            blocksList.forEach { it.shouldHighlight = false }
            adapter.notifyDataSetChanged()
        }

    }

    private fun setupChessBoard() {
        adapter = ChessAdapter(blocksList, this@MainActivity)
        val lp = GridLayoutManager(this@MainActivity, 8)

        lp.spanCount
        recBoard.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 8)
            setHasFixedSize(true)
            adapter = this@MainActivity.adapter

        }
    }

    private fun prepareChessBoardObjects() {
        for (i in 0..63) {
            val title = getBlockTitle(getRowIndex(i))
            val cpType = getDefaultChessPieceTypeAndColorByPosition(this, i).first
            val cpColorType = getDefaultChessPieceTypeAndColorByPosition(this, i).second
            blocksList.add(ChessBoardBlockModel(title, i, false, cpType, cpColorType))
            dupBlocksList.add(ChessBoardBlockModel(title, i, false, cpType, cpColorType))
        }
    }


}
