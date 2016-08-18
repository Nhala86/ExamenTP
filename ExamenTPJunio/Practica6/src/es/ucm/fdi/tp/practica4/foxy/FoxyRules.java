package es.ucm.fdi.tp.practica4.foxy;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.Utils;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.FiniteRectBoard;
import es.ucm.fdi.tp.basecode.bgame.model.Game.State;
import es.ucm.fdi.tp.practica4.ataxx.AtaxxMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Pair;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class FoxyRules implements GameRules{

	private int dimension;
	
	public FoxyRules(int dimension){
		if(dimension < 5){
			System.out.println("La dimension del tablero no es correcta, al menos debe ser de 5x5");
		}
		else{
			this.dimension = dimension;
		}
	}
	
	@Override
	public String gameDesc() {
		return "Foxy " + this.dimension + "X" + this.dimension;
	}

	@Override
	public Board createBoard(List<Piece> pieces) {
		
		Board board = new  FiniteRectBoard(this.dimension, this.dimension);
		
		Piece p1 = pieces.get(0);
		board.setPosition(this.dimension - 1, 0, p1);		
		board.setPieceCount(p1, 1);
		
		Piece p2 = pieces.get(1);
		int contador = 0;
		for(int i = 1; i < board.getCols(); i++){
			int j = (0 + i)% 2;
			if(j == 1){
				board.setPosition(0, i, p2);
				contador++;				
			}			
		}		
		board.setPieceCount(p2, contador);
		
		return board;
	}	

	@Override
	public Piece initialPlayer(Board board, List<Piece> pieces) {
		int x = Utils.randomInt(pieces.size());
		return pieces.get(x);
	}

	@Override
	public int minPlayers() {
		return 2;
	}

	@Override
	public int maxPlayers() {
		return 2;
	}

	@Override
	public Pair<State, Piece> updateState(Board board, List<Piece> pieces, Piece turn) {
		Pair<State, Piece> result = new Pair<State, Piece>(State.InPlay, null) ;
		
		if(this.nextPlayer(board, pieces, turn).equals(turn)){
			result = new Pair<State, Piece>(State.Won, turn);
		}		
		else{
			Piece PiezaGanadora = encontrarPieza(board, pieces);
			if(PiezaGanadora.equals(pieces.get(0))){
				result = new Pair<State, Piece>(State.Won, PiezaGanadora);
			}
		}
		
		return result;
	}
	

	private Piece encontrarPieza(Board board, List<Piece>pieces){
		Piece PiezaGanadora = new Piece();
		for(int i = 0; i < board.getCols(); i++){
			if(pieces.get(0).equals(board.getPosition(0, i))){
				PiezaGanadora = pieces.get(0);
			}
		}
		return PiezaGanadora;
	}

	@Override
	public Piece nextPlayer(Board board, List<Piece> pieces, Piece turn) {
		Piece piece;
		int numPlayers = pieces.size();
		int i = pieces.indexOf(turn);
		int cantidad, counter = 1;

		do{
			piece	= pieces.get((i + counter) % numPlayers);
			cantidad = this.validMoves(board, pieces, piece).size();
			counter++;
		}while(cantidad <= 0 && counter <= numPlayers);

		return piece;
	}

	@Override
	public double evaluate(Board board, List<Piece> pieces, Piece turn, Piece p) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<GameMove> validMoves(Board board, List<Piece> pieces, Piece turn) {
		Piece piece = turn;
		List<GameMove> validMoves = new ArrayList<GameMove>();
		for(int r= 0; r < board.getRows(); r++)
			for(int c = 0; c < board.getCols(); c++){
				if(board.getPosition(r, c) == piece){
					validMoves.addAll(validMoveOfPiece(board, turn, r, c));
				}
			}
		return validMoves;
	}
	
	protected List<GameMove> validMoveOfPiece(Board board, Piece piece, int row, int col){
		int rows = board.getRows();
		int cols = board.getCols();				
		
		List<GameMove> validMoves = new ArrayList<GameMove>();
		for(int r = Math.max(row - 1, 0); r <= Math.min(row + 1, rows - 1); r++) 
			for (int c = Math.max(col - 1, 0); c <= Math.min(col + 1, cols - 1); c++)
				if(board.getPosition(r, c) == null){
					if((row - r) != 0 || (col - c) != 0){
						if(CasillasValidas(row, r, col, c)){
							if(piece.getId().equals("P") && validaRows(row, r)){
							validMoves.add(new FoxyMove(row, col, r, c, piece));
							}
						else{
							validMoves.add(new FoxyMove(row, col, r, c, piece));
						}
					}
				}
			}
		return validMoves;		
	}
	
	private boolean validaRows(int origenRow, int destinyRow){
		boolean ok = true;
		int rowsOk = destinyRow - origenRow;
		if(rowsOk < 1){
			ok = false;
		}
		return ok;
	}
	
	
	private boolean CasillasValidas(int origenRow, int destinyRow, int origenCol, int destinyCol ){
		boolean casillaOk = true;
		int rowOk = origenRow - destinyRow;
		int colOk = origenCol - destinyCol;
		if(rowOk == 0 || colOk == 0){
			casillaOk = false;
		}
		return casillaOk;
	}
	
	

}
