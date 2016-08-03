package es.ucm.fdi.tp.practica5.view;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class BombaPlayer extends Player{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int radio;
	
	

	@Override
	public GameMove requestMove(Piece p, Board board, List<Piece> pieces, GameRules rules) {
		return new BombaMove(p, radio);
	}
	
	public void setRadio(int radio){
		this.radio = radio;
	}

}
