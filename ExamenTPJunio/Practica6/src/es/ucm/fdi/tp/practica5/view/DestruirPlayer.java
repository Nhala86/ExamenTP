package es.ucm.fdi.tp.practica5.view;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class DestruirPlayer extends Player{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer radio;

	@Override
	public GameMove requestMove(Piece p, Board board, List<Piece> pieces, GameRules rules) {
		return new DestruirMove(p, radio);
	}
	
	public void setRadioAction(Integer radio){
		this.radio = radio;		
	}

}
