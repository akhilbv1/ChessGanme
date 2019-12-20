package org.sample.chessgame

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView

class ChessAdapter(
    val blocksList: ArrayList<ChessBoardBlockModel>,
    val listener: OnChessBoardListener
) : RecyclerView.Adapter<ChessAdapter.ViewHolder>() {

    interface OnChessBoardListener {
        fun onClickChessBlock(chessBoardBlockModel: ChessBoardBlockModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChessAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_item_chess, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = blocksList.size

    override fun onBindViewHolder(holder: ChessAdapter.ViewHolder, position: Int) {
        holder.updateUi(position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        override fun onClick(v: View?) {
            listener.onClickChessBlock(blocksList[adapterPosition])
        }

        init {
            itemView.setOnClickListener(this)
        }

        val view: View = itemView.findViewById(R.id.chess_block)
        val chessPiece: AppCompatImageView = itemView.findViewById(R.id.chess_piece)
        val highlight: AppCompatImageView = itemView.findViewById(R.id.highlight)
        val tvBlockNum: TextView = itemView.findViewById(R.id.tvBlockNum)
        fun updateUi(position: Int) {
            tvBlockNum.text = "${position}"
            val colorPair = determineColorOfBlock(itemView.context, getColumnIndex(position))
            val evenColor = colorPair.first
            val oddColor = colorPair.second
            val cPos = (position / 8) + 1
            if (cPos % 2 == 0) {
                view.setBackgroundColor(oddColor)
            } else {
                view.setBackgroundColor(evenColor)
            }
            if (blocksList[position].cpColor != -1 && blocksList[position].cpType != -1) {
                chessPiece.visibility = View.VISIBLE
                chessPiece.setImageResource(
                    getChessDrawableResource(
                        blocksList[position].cpType,
                        blocksList[position].cpColor
                    )
                )
            } else {
                chessPiece.visibility = View.GONE
            }

            if (blocksList[position].shouldHighlight) {
                highlight.visibility = View.VISIBLE
            } else {
                highlight.visibility = View.GONE
            }
        }
    }


}