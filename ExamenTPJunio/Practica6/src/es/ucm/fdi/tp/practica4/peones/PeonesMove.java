package es.ucm.fdi.tp.practica4.peones;

import java.util.List;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica4.ataxx.AtaxxMove;

public class PeonesMove extends GameMove{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The row where the piece is return return by {@link GameMove#getPiece()}.
	 * <p>
	 * Fila en la que se encuentra la ficha devuelta por
	 * {@link GameMove#getPiece()}.
	 */
	protected int originRow;

	/**
	 * The column where the piece is return by {@link GameMove#getPiece()}
	 * .
	 * <p>
	 * Columna en la que se encuentra la ficha devuelta por
	 * {@link GameMove#getPiece()}.
	 */
	protected int originCol;
	/**
	 * The row where to place the piece return by {@link GameMove#getPiece()}.
	 * <p>
	 * Fila en la que se coloca la ficha devuelta por
	 * {@link GameMove#getPiece()}.
	 */
	protected int destinyRow;

	/**
	 * The column where to place the piece return by {@link GameMove#getPiece()}
	 * .
	 * <p>
	 * Columna en la que se coloca la ficha devuelta por
	 * {@link GameMove#getPiece()}.
	 */
	protected int destinyCol;

	public PeonesMove(){}
	
	public PeonesMove(int originRow, int originCol, int destinyRow, int destinyCol, Piece p) {
		super(p);
		this.originRow = originRow;
		this.originCol = originCol;
		this.destinyRow = destinyRow;
		this.destinyCol = destinyCol;
	}

	@Override
	public void execute(Board board, List<Piece> pieces) {	
		
		Piece p = getPiece();
		int distance = Math.max(Math.abs(this.originRow - this.destinyRow), Math.abs(this.originCol - this.destinyCol));
		this.checkMove(board, p, distance);
		
		if(p.equals(pieces.get(0))){
			if(this.destinyRow-this.originRow <  0) //¿muevo arriba?
				throw new GameError("the pieces cannot go back");
			else{
				if(this.destinyCol - this.originCol != 0){
					if(board.getPosition(this.destinyRow, this.destinyCol).equals(pieces.get(1))){
						board.setPosition(this.destinyRow, this.destinyCol, p);
						board.setPosition(this.originRow, this.originCol, null);
						board.setPieceCount(pieces.get(1), board.getPieceCount(pieces.get(1))- 1);
					}
				}
				board.setPosition(this.destinyRow, this.destinyCol, p);
				board.setPosition(this.originRow, this.originCol, null);
			}
		}
		
		
		else if(p.equals(pieces.get(1))){
			if(this.destinyRow-this.originRow >  0) //¿muevo abajo?
				throw new GameError("the pieces cannot go back");
			else{
				if(this.destinyCol - this.originCol != 0){
					if(board.getPosition(this.destinyRow, this.destinyCol).equals(pieces.get(0))){
						board.setPosition(this.destinyRow, this.destinyCol, p);
						board.setPosition(this.originRow, this.originCol, null);
						board.setPieceCount(pieces.get(0), board.getPieceCount(pieces.get(0))- 1);
					}
				}
				board.setPosition(this.destinyRow, this.destinyCol, p);
				board.setPosition(this.originRow, this.originCol, null);
				
			}
		}
		
		
	}
	
	private void checkMove(Board board, Piece p, int distance) throws GameError{
		
		if (board.getPieceCount(p) <= 0)
			throw new GameError("There are no pieces of type " + p + " available");
		else if (board.getPosition(this.originRow, this.originCol) == null) 
			throw new GameError("Position (" + this.originRow + "," + this.originCol + ") there is not piece there.");
		else if (board.getPosition(this.originRow, this.originCol).equals(board.getPosition(this.destinyRow, this.destinyCol))) 
			throw new GameError("Position (" + this.destinyRow + "," + this.destinyCol + ") is already occupied for one of your peons");
		else if(distance > 1)
			throw new GameError("Position (" + this.destinyRow + "," + this.destinyCol + ") distance is larger than 1 from the origin");
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
	
	protected GameMove createMove(int originRow, int originCol, int destinyRow, int destinyCol, Piece p) {
		return new PeonesMove(originRow, originCol, destinyRow, destinyCol, p);
	}

	@Override
	public String help() {
		return "'originRow originCol destinyRow destinyCol', to place/create a piece at the corresponding position.";
	}

}
