package es.ucm.fdi.tp.practica5.view;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.Utils;
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
		int rows = board.getRows();
		int cols = board.getCols();
		int randomRows = Utils.randomInt(rows);
		int randomCols = Utils.randomInt(cols);
		int initRow = randomRows;
		int initCol = randomCols;
		Piece p = getPiece();
		do{
			if(p.equals(board.getPosition(initRow, initCol))){
				Destruir(board, pieces, initRow, initCol, radio, p);
				return;
			}
			initCol = (initCol + 1)%cols;
			if(initCol == 0){
				initRow = (initRow + 1)%rows;
			}
		}while(initRow != randomRows || initCol != randomCols);
		
	}

	private void Destruir(Board board, List<Piece> pieces, int initRow, int initCol, Integer radio, Piece p) {
		if(p.equals(board.getPosition(initRow, initCol))){
			for(int i = initRow; i < 6; i++){
				for(int j = initCol; j < 6; j++){
					if(p.equals(board.getPosition(i, j)) && enTablero(board, initRow, initCol) && noObstaculo(board, pieces, initRow, initCol, radio)){
						board.setPosition(i, j, null);
						board.setPieceCount(p, pieces.size() - 1);
					}
				}
			}
		}
		
	}
	
	private boolean enTablero(Board board, int rows, int cols){
		return (rows >= 0 && cols >= 0 && rows < board.getRows() && cols < board.getCols());
	}
	
	private boolean noObstaculo(Board board, List<Piece>pieces, int row, int col, int radio){
		return (enTablero(board, row, col) && (board.getPosition(row, col) == null || pieces.contains(board.getPosition(row, col))));
	}

	@Override
	public GameMove fromString(Piece p, String str) {
		String[] palabras = str.split(" ");
		if(palabras.length != 2 || !palabras[0].equalsIgnoreCase("full")){
			return null;
		}
		try{
			int radio = Integer.parseInt(palabras[1]);
			return new DestruirMove(p, radio);
		}catch(NumberFormatException e){
			return null;
		}
	}

	@Override
	public String help() {
		return "full radio";
	}
	
	@Override
	public String toString() {
		return "Destruccion en la posicion de " + getPiece();
	}

}
