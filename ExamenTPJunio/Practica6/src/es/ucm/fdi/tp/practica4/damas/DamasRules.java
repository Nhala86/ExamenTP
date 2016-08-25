package es.ucm.fdi.tp.practica4.damas;

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

public class DamasRules implements GameRules{
	
	private int dimension;
	
	public DamasRules(int dimension){
		this.dimension = dimension;
	}

	@Override
	public String gameDesc() {
		return "Damas " + this.dimension + "X" + this.dimension;
	}

	@Override
	public Board createBoard(List<Piece> pieces) {
		Board board = new  FiniteRectBoard(this.dimension, this.dimension);
		
		Piece p1 = pieces.get(0);
		board.setPosition(0, 0, p1);
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < this.dimension; j++){
				if(i == 0 || i == 2){
					if(((j + 2) % 2) == 0){
						board.setPosition(i, j, p1);						
					}
				}				
				else if(i == 1){
					if(((j + 2) % 2) == 1){
						board.setPosition(i, j, p1);
					}					
				}				
				board.setPieceCount(p1, 12);
			}
		}
		
		Piece p2 = pieces.get(1);
		for(int i = this.dimension - 3; i < this.dimension; i++){
			for(int j = 0; j < this.dimension; j++){
				if(i == this.dimension - 3 || i == this.dimension - 1){
					if(((j + 2) % 2) == 1){
						board.setPosition(i, j, p2);						
					}
				}				
				else if(i == this.dimension - 2){
					if(((j + 2) % 2) == 0){
						board.setPosition(i, j, p2);
					}					
				}				
				board.setPieceCount(p2, 12);
			}
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
			result = new Pair<State, Piece>(State.Won, turn);
						
		}
		else if(allPlayersBlocked(board, pieces)){
			result = new Pair<State, Piece>(State.Draw, null);
		}
		else {
			Piece ganador = lineaOcupada(board, pieces);
			if(ganador.equals(pieces.get(1)) || ganador.equals(pieces.get(0))){
				result = new Pair<State, Piece>(State.Won, ganador);
			}
		}
		return result;
	}
	
	private Piece lineaOcupada(Board board, List<Piece> pieces){
		Piece casillaOcupada = new Piece();
		
		for(int i = 0; i < board.getRows(); i++){
			if((pieces.get(1).equals(board.getPosition(0, i)))){
				casillaOcupada = pieces.get(1);
			}
			else if((pieces.get(0).equals(board.getPosition(this.dimension - 1, i)))){
				casillaOcupada = pieces.get(0);
			}
		}
		return casillaOcupada;
	}
	
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
		Piece p = turn;
		List<GameMove> validMoves = new ArrayList<GameMove>();
		for(int r= 0; r < board.getRows(); r++)
			for(int c = 0; c < board.getCols(); c++){
				if(board.getPosition(r, c) == p){
					validMoves.add(new DamasMove(row, col, r, c, p));
				}
			}
		return validMoves;
	}
	
	

}
