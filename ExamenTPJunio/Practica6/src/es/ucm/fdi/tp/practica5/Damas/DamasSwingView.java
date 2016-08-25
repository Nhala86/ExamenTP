package es.ucm.fdi.tp.practica5.Damas;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.view.RectBoardSwingView;

public class DamasSwingView extends RectBoardSwingView{

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
	private int originRow;

	/**
	 * The column where the piece is return by {@link GameMove#getPiece()}
	 * .
	 * <p>
	 * Columna en la que se encuentra la ficha devuelta por
	 * {@link GameMove#getPiece()}.
	 */
	private int originCol;
	/**
	 * The row where to place the piece return by {@link GameMove#getPiece()}.
	 * <p>
	 * Fila en la que se coloca la ficha devuelta por
	 * {@link GameMove#getPiece()}.
	 */
	private int destinyRow;

	/**
	 * The column where to place the piece return by {@link GameMove#getPiece()}
	 * .
	 * <p>
	 * Columna en la que se coloca la ficha devuelta por
	 * {@link GameMove#getPiece()}.
	 */
	private int destinyCol;	

	/**
	 * Parametro de jugador de la SwingPlayer
	 */
	private DamasSwingPlayer player;
	
	/**
	 * Parametro booleano del segundo click del mouse
	 */
	private boolean secondClick;
	
	public DamasSwingView(Observable<GameObserver> game, Controller c, Piece localPiece, Player randPlayer, Player aiPlayer) {
		super(game, c, localPiece, randPlayer, aiPlayer);
		this.player = new DamasSwingPlayer();
		this.secondClick = false;
	}

	@Override
	protected void handelMouseClick(int row, int col, int clickcounter, int mouseButton) {
		if(!secondClick){
			this.originRow = row;
			this.originCol = col;
			this.secondClick = true;
			
		}
		else{
			this.destinyRow = row;
			this.destinyCol = col;
			this.secondClick = false;
			
			this.player.setMoveValue(this.originRow, this.originCol, this.destinyRow, this.destinyCol);
			if(mouseButton == 1)
			this.caseMakeManualMove(this.player);
		}		
		
	}

}
