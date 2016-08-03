package es.ucm.fdi.tp.practica5.view;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.Utils;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class BombaMove extends GameMove{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int radio;
	
	public BombaMove(Piece p){
		super(p);
	}
	
	public BombaMove(Piece p, int radio){
		super(p);
		this.radio = radio;
	}

	@Override
	public void execute(Board board, List<Piece> pieces) {
		int rows = board.getRows();
		int cols = board.getCols();
		int randomRows = Utils.randomInt(rows);
		int randomCols = Utils.randomInt(cols);
		int iniRow = randomRows;
		int iniCol = randomCols;
		Piece p = getPiece();
		do{
			if(p.equals(board.getPosition(iniRow, iniCol))){
				bomba(board, pieces, iniRow, iniCol, radio);
				return;
			}
			iniCol = (iniCol + 1)% cols;
			if(iniCol == 0){
				iniRow = (iniRow + 1)% rows;
			}
		}while(iniRow != randomRows || iniCol != randomCols);
		
		throw new GameError("No se ha encontrado piezas de este tipo" + p);
		
	}
	
	private boolean enTablero(Board board, int rows, int cols){
		return rows >= 0 && cols >= 0 && rows < board.getRows() && cols < board.getCols();
	}
	
	private boolean noObstaculo(Board board, List<Piece> p, int rows, int cols){
		return enTablero(board, rows, cols) && (board.getPosition(rows, cols) == null || p.contains(board.getPosition(rows, cols)));
	}

	private void bomba(Board board, List<Piece> pieces, int row, int col, int radio) {
		for(int i = 0; i < 10; i++){
			int rows1 = row + Utils.randomInt(2 * radio + 1) - radio;
			int cols1 = col + Utils.randomInt(2 * radio + 1) - radio;
			int rows2 = row + Utils.randomInt(2 * radio + 1) - radio;
			int cols2 = col + Utils.randomInt(2 * radio + 1) - radio;
			
			if(enTablero(board, rows1, cols1) && enTablero(board, rows2, cols2) && noObstaculo(board, pieces, rows1, cols1) && noObstaculo(board, pieces, rows2, cols2)){
				Piece p = board.getPosition(rows1, cols1);
				Piece q = board.getPosition(rows2, cols2);
				board.setPosition(rows1, cols1, q);
				board.setPosition(rows2, cols2, p);
			}
		}
		
	}

	@Override
	public GameMove fromString(Piece p, String str) {
		String[] palabras = str.split(" ");
		if(palabras.length != 2 || !palabras[0].equalsIgnoreCase("chaos")){
			return null;
		}
		try{
			int radio = Integer.parseInt(palabras[1]);
			return new BombaMove(p, radio);
		}catch(NumberFormatException e){
			return null;
		}
	}

	@Override
	public String help() {
		return "chaos radio";
	}
	
	@Override
	public String toString() {
		return "Bomb a position with " + getPiece();
	}

}
