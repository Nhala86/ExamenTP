package es.ucm.fdi.tp.practica4.cuatroEnRaya;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class RayaMove extends GameMove{	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected int col;
	
	public RayaMove(){}
	
	public RayaMove(int col, Piece p){
		super(p);
		this.col = col;
	}

	@Override
	public void execute(Board board, List<Piece> pieces) {
		Piece p = getPiece();
		this.checkMove(board, p);		
		int row = 1;
		
		while(row + 1 < board.getRows() && board.getPosition(row +1, this.col) == null){
			row++;
		}
		board.setPosition(row, this.col, p);			
	}
	
	
	
	private void checkMove(Board board, Piece p) throws GameError{		
		if(board.getPosition(1, this.col) != null){
			throw new GameError("Esta columna esta llena, no hay sitio");
		}
	}
		

	@Override
	public GameMove fromString(Piece p, String str) {
		String[] palabra = str.split(" ");
		if(palabra.length != 1){
			return null;
		}
		
		try{
			int col;			
			col = Integer.parseInt(palabra[0]);
			return createMove(col, p);
		}catch(NumberFormatException e){
			return null;
		}		
	}

	private GameMove createMove(int col, Piece p) {
		return new RayaMove(col, p);
	}

	@Override
	public String help() {
		return "'col', to place/create a piece at the corresponding position.";
	}

}
