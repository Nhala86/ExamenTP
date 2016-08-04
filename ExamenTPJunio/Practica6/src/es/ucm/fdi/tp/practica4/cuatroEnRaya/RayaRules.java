package es.ucm.fdi.tp.practica4.cuatroEnRaya;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.Utils;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.FiniteRectBoard;
import es.ucm.fdi.tp.basecode.bgame.model.Game.State;
import es.ucm.fdi.tp.practica4.peones.PeonesMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Pair;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class RayaRules implements GameRules{

	private int dimension;
	
	public RayaRules(int dimension){
		if(dimension <5){
			System.out.println("La dimension del tablero es menor que 5: " + dimension);
		}
		else{
			this.dimension = dimension;
		}
	}
	@Override
	public String gameDesc() {
		return "4 en Raya " + this.dimension + "X" + this.dimension;
	}

	@Override
	public Board createBoard(List<Piece> pieces) {
		Board board = new  FiniteRectBoard(this.dimension, this.dimension);
		
		Piece p1 = pieces.get(0);		
		board.setPieceCount(p1, 0);
		
		Piece p2 = pieces.get(1);		
		board.setPieceCount(p2, 0);
		
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
		int counter;
		if(board.isFull()){
			return new Pair<State, Piece>(State.Draw, null);
		}
		
		for(int i= 1; i < this.dimension; i++ ){ 
			counter = 0; 
			for(int j = 0; j < this.dimension; j++){ 
				if(counter == 4){ 
					return new Pair<State, Piece>(State.Won, turn); 
				} 
				else{ 
					if(turn.equals(board.getPosition(i, j))) 
						counter++; 
					else 
						counter = 0; 
					} 
			 } 
		} 
					 
		//Check Cols 
		for(int i= 1; i <this.dimension ; i++ ){ 
			counter = 0; 
			for(int j = 0; j < this.dimension; j++){ 
			 	if(counter == 4){ 
			 		return new Pair<State, Piece>(State.Won, turn); 
			 	}
				else{ 
					if(turn.equals(board.getPosition(j, i))) 
						counter++; 
			 		else 
			 			counter = 0; 
			 		} 
			 } 
		}
		return result;
	}
	
	@Override
	public Piece nextPlayer(Board board, List<Piece> pieces, Piece turn) {
		int i = pieces.indexOf(turn);
		return pieces.get((i+1)% pieces.size());
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
					validMoves.add(new RayaMove(col, piece));
				}
			}
		return validMoves;
	}

}
