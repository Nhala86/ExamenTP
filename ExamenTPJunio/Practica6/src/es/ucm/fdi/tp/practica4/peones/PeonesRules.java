package es.ucm.fdi.tp.practica4.peones;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.Utils;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.FiniteRectBoard;
import es.ucm.fdi.tp.basecode.bgame.model.Game.State;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Pair;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class PeonesRules implements GameRules{
	
	private int dimension;
	
	public PeonesRules(int dimension){
		this.dimension = dimension;
	}

	@Override
	public String gameDesc() {
		return "Peones " + this.dimension + "X" + this.dimension;
	}

	@Override
	public Board createBoard(List<Piece> pieces) {
		int cont = 0;
		Board board = new  FiniteRectBoard(this.dimension, this.dimension);
		
		Piece p1 = pieces.get(0);
		Piece p2 = pieces.get(1);
		
		for(int i = 0; i < board.getCols(); i++){
			cont++;
			board.setPosition(0, i, p1);
			board.setPosition(board.getRows()-1, i, p2);
			board.setPieceCount(p1, cont);
			board.setPieceCount(p2, cont);
		}			
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
			if(this.allPlayersBlocked(board, pieces))
				result = new Pair<State, Piece>(State.Draw, null);
		}
		else{
			Piece winnerPiece = foundPiece(board, pieces);
			if(winnerPiece.equals(pieces.get(0)) || winnerPiece.equals(pieces.get(1))){
				result = new Pair<State, Piece>(State.Won, winnerPiece);
			}
		}		
		return result;
	}
	
	private Piece foundPiece(Board board, List<Piece> pieces){
		Piece winnerPiece = new Piece();
	
		for(int i= 0; i < board.getCols(); i++){
			if((pieces.get(1)).equals(board.getPosition(0, i)))
				winnerPiece = pieces.get(1);
			else if(pieces.get(0).equals(board.getPosition(board.getRows()-1, i)))
				winnerPiece = pieces.get(0);
		}
		return winnerPiece;
		
	}
	
	/**
	 * Metodo que determina si todos los jugadores de la partida excepto uno se han quedado sin fichas
	 * 
	 * @param board tablero donde se desarrolla la partida
	 * @param pieces lista de piezas de los jugadores
	 * @return
	 */
	protected boolean allPlayersBlocked(Board board, List<Piece> pieces){
		boolean blocked = false;
		int counter = 0;
		
		for(int i = 0; i < pieces.size(); i++){
			if(this.validMoves(board, pieces, pieces.get(i)).size() <= 0)
				counter++;
		}
		if(counter == pieces.size() - 1)
			blocked = true;
		return blocked;
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
		int row = 0;
		int col = 0;
		Piece piece = turn;
		List<GameMove> validMoves = new ArrayList<GameMove>();
		for(int r= 0; r < board.getRows(); r++)
			for(int c = 0; c < board.getCols(); c++){
				if(board.getPosition(r, c) == piece){
					validMoves.add(new PeonesMove(row, col, r, c, piece));
				}
			}
		return validMoves;
	}

}
