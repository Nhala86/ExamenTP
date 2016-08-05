package es.ucm.fdi.tp.practica5.cuatroEnRaya;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica4.cuatroEnRaya.RayaMove;

public class RayaSwingPlayer extends Player{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int col;
	
	public RayaSwingPlayer(){}
	
	public void setMoveValue(int col){
		this.col = col;
	}
	
	@Override
	public GameMove requestMove(Piece p, Board board, List<Piece> pieces, GameRules rules) {
		return this.GameCreateMove(this.col, p);
	}
	
	protected GameMove GameCreateMove(int col, Piece p){
		return new RayaMove(col, p) ;
	}	


}
