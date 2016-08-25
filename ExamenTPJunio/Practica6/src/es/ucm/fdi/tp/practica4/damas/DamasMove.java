package es.ucm.fdi.tp.practica4.damas;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class DamasMove extends GameMove{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int origenRow;
	private int origenCol;
	private int destinyRow;
	private int destinyCol;
	
	public DamasMove(){}
	
	public DamasMove(int origenRow, int origenCol, int destinyRow, int destinyCol, Piece p){
		super(p);
		this.origenRow = origenRow;
		this.origenCol = origenCol;
		this.destinyRow = destinyRow;
		this.destinyCol = destinyCol;
	}

	@Override
	public void execute(Board board, List<Piece> pieces) {
		Piece p = getPiece();
		int distance = Math.max(Math.abs(this.origenRow - this.destinyRow), Math.abs(this.origenCol - this.destinyCol));
		this.checkMove(board, p, distance);
		
		if(p.equals(pieces.get(0))){
			if(this.destinyRow-this.origenRow <=  0) //¿muevo arriba?
				throw new GameError("the pieces cannot go back");
			else{
				if(this.destinyCol - this.origenCol != 0){
					if((pieces.get(1)).equals(board.getPosition(this.destinyRow, this.destinyCol))){
						board.setPosition(this.destinyRow, this.destinyCol, p);
						board.setPosition(this.origenRow, this.origenCol, null);
						board.setPieceCount(pieces.get(1), board.getPieceCount(pieces.get(1))- 1);
					}
					else{
						throw new GameError("Solo puedes mover en diagonal para comer al contrario");
					}
				}				
				board.setPosition(this.destinyRow, this.destinyCol, p);
				board.setPosition(this.origenRow, this.origenCol, null);
			}
		}
		
		
		else if(p.equals(pieces.get(1))){
			if(this.destinyRow-this.origenRow >=  0) //¿muevo abajo?
				throw new GameError("the pieces cannot go back");
			else{
				if(this.destinyCol - this.origenCol != 0){
					if((pieces.get(0)).equals(board.getPosition(this.destinyRow, this.destinyCol))){
						board.setPosition(this.destinyRow, this.destinyCol, p);
						board.setPosition(this.origenRow, this.origenCol, null);
						board.setPieceCount(pieces.get(0), board.getPieceCount(pieces.get(0))- 1);
					}
					else{
						throw new GameError("Solo puedes mover en diagonal para comer al contrario");
					}
				}
				board.setPosition(this.destinyRow, this.destinyCol, p);
				board.setPosition(this.origenRow, this.origenCol, null);
				
			}
		}
		
	}
	
	private void checkMove(Board board, Piece p, int distance) throws GameError{		
		if (board.getPieceCount(p) <= 0)
			throw new GameError("There are no pieces of type " + p + " available");
		else if (board.getPosition(this.origenRow, this.origenCol) == null) 
			throw new GameError("Position (" + this.origenRow + "," + this.origenCol + ") there is not piece there.");		
		else if (board.getPosition(this.origenRow, this.origenCol).equals(board.getPosition(this.destinyRow, this.destinyCol))) 
			throw new GameError("Position (" + this.destinyRow + "," + this.destinyCol + ") is already occupied for one of your peons");
		else if(distance > 1)
			throw new GameError("Position (" + this.destinyRow + "," + this.destinyCol + ") distance is larger than 2 from the origin");
	}

	@Override
	public GameMove fromString(Piece p, String str) {
		String[] words = str.split(" ");
		if (words.length != 4) {
			return null;
		}

		try {
			int origenRow, origenCol, destinyRow, destinyCol;
			origenRow = Integer.parseInt(words[0]);
			origenCol = Integer.parseInt(words[1]);
			destinyRow = Integer.parseInt(words[2]);
			destinyCol = Integer.parseInt(words[3]);
			return createMove(origenRow, origenCol, destinyRow, destinyCol, p);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	private GameMove createMove(int originRow, int originCol, int destinyRow, int destinyCol, Piece p) {
		return new DamasMove(originRow, originCol, destinyRow, destinyCol, p);
	}

	@Override
	public String help() {
		return "'origenRow origenCol destinyRow destinyCol', to place/create a piece at the corresponding position.";
	}

}
