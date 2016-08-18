package es.ucm.fdi.tp.practica4.foxy;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class FoxyMove extends GameMove{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int row;
	private int col;
	private int destinyRow;
	private int destinyCol;

	public FoxyMove(){}
	
	public FoxyMove(int row, int col, int destinyRow, int destinyCol, Piece p){
		super(p);
		this.row = row;
		this.col = col;
		this.destinyRow = destinyRow;
		this.destinyCol = destinyCol;
	}
	@Override
	public void execute(Board board, List<Piece> pieces) {
		Piece p = getPiece();
		int distance = Math.max(Math.abs(this.row - this.destinyRow), Math.abs(this.col - this.destinyCol));
		this.checkMove(board, p, distance);
		
		if(this.destinyRow != this.row && this.destinyCol != this.col && board.getPosition(this.row, this.col).equals(pieces.get(0))){
			board.setPosition(this.destinyRow, this.destinyCol, pieces.get(0));
			board.setPosition(this.row, this.col, null);
		}
		else if(board.getPosition(this.row, this.col).equals(pieces.get(1)) && (this.destinyRow - this.row) != -1){
			board.setPosition(this.destinyRow, this.destinyCol, pieces.get(1));
			board.setPosition(this.row, this.col, null);
		}
		else{
			throw new GameError("Movimiento no valido: " + this.destinyRow + " " + this.destinyCol + " los perros en diagonal hacia delante, el zorro en diagonal, alante o atras");
		}
		
		
	}
	
	private void checkMove(Board board, Piece p, int distance) throws GameError{		
		if (board.getPosition(this.destinyRow, this.destinyCol) != null) 
			throw new GameError("Position (" + this.destinyRow + "," + this.destinyCol + ") is already occupied");
		else if(board.getPosition(this.row, this.col) != p)
			throw new GameError("Position (" + this.row + "," + this.col + ") is a piece from other player!!");
		else if(distance > 1)
			throw new GameError("Position (" + this.destinyRow + "," + this.destinyCol + ") distance is larger than 1 from the origin");		
		else if(destinyRow >= board.getRows() && destinyCol >= board.getCols()){
			throw new GameError("No se puede mover fuera del tablero");
		}
		else if(destinyRow < 0 && destinyCol < 0){
			throw new GameError("No se puede mover fuera del tablero");
		}
			
	}
		

	@Override
	public GameMove fromString(Piece p, String str) {
		String[] words = str.split(" ");
		if (words.length != 4) {
			return null;
		}

		try {
			int originRow, originCol, destinyRow, destinyCol;
			originRow = Integer.parseInt(words[0]);
			originCol = Integer.parseInt(words[1]);
			destinyRow = Integer.parseInt(words[2]);
			destinyCol = Integer.parseInt(words[3]);
			return createMove(originRow, originCol, destinyRow, destinyCol, p);
		} catch (NumberFormatException e) {
			return null;
		}
	}
	
	protected GameMove createMove(int row, int col, int destinyRow, int destinyCol, Piece p) {
		return new FoxyMove(row, col, destinyRow, destinyCol, p);
	}

	@Override
	public String help() {
		return "'row col destinyRow destinyCol', to place/create a piece at the corresponding position.";
	}

}
