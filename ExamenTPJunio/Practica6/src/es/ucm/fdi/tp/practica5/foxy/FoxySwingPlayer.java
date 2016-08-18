package es.ucm.fdi.tp.practica5.foxy;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica4.foxy.FoxyMove;

public class FoxySwingPlayer extends Player{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int row;
	private int col;
	private int destinyRow;
	private int destinyCol;
	
	public FoxySwingPlayer(){}
	
	public void setMoveValue(int row, int col, int destinyRow, int destinyCol){
		this.row = row;
		this.col = col;
		this.destinyCol = destinyCol;
		this.destinyRow = destinyRow;
	}

	@Override
	public GameMove requestMove(Piece p, Board board, List<Piece> pieces, GameRules rules) {
		return this.GameCreateMove(this.row, this.col, this.destinyRow, this.destinyCol, p);
	}

	private GameMove GameCreateMove(int row, int col, int destinyRow, int destinyCol, Piece p) {
		return new FoxyMove(row, col, destinyRow, destinyCol, p);
	}

}
