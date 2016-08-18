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
		int contador = 0;
		
		 for(int r = 1; r < this.dimension; r++){
			 for(int x = 0; x < this.dimension; x++){
				 if(board.getPosition(r, x) != null){
					 contador++;
					 if(contador == ((this.dimension * this.dimension) - this.dimension)){
						 result = new Pair<State, Piece>(State.Draw, null);
					 }
				 }
				 else{
					 contador = 0;
				 }
			 }
		 }
		 
		//Check row
		for(int i= 1; i < this.dimension; i++ ){ 
			counter = 0; 
			for(int j = 0; j < this.dimension; j++){ 
				if(counter == 4){ 
					result = new Pair<State, Piece>(State.Won, turn); 
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
		for(int k = 0; k < this.dimension; k++){ 
			counter = 0; 
			for(int d = 0; d < this.dimension; d++){ 
				if(turn.equals(board.getPosition(d, k)))
					counter++; 
			 	else 
			 		counter = 0;
				
				if(counter == 4){ 
			 		result = new Pair<State, Piece>(State.Won, turn); 
			 	}
			 } 
		}
		
		
		//check diagonal
		for(int r = 1; r < this.dimension; r++){
			counter = 0;
			for(int c = 0; c < this.dimension; c++){				
				if(board.getPosition(r, c).equals(turn)){
					counter++;
					if(board.getPosition(r + 1, c - 1).equals(turn)){
						counter++;
					}
					else if(board.getPosition(r + 1, c + 1).equals(turn)){
						counter++;
					}
					else{
						counter = 0;
					}
				}
				
				if(counter == 4){
					result = new Pair<State, Piece>(State.Won, turn);
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
		List<GameMove> validMoves = new ArrayList<GameMove>();
		for(int r= 0; r < board.getCols(); r++)
			validMoves.add(new RayaMove(r, turn));			
		return validMoves;
	}

}
