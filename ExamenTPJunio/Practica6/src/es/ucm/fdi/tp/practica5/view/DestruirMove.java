package es.ucm.fdi.tp.practica5.view;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class DestruirMove extends GameMove{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer radio;
	
	public DestruirMove(Piece p){
		super(p);
	}

	public DestruirMove(Piece p, Integer radio) {
		super(p);
		this.radio = radio;
	}

	@Override
	public void execute(Board board, List<Piece> pieces) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GameMove fromString(Piece p, String str) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String help() {
		// TODO Auto-generated method stub
		return null;
	}

}
