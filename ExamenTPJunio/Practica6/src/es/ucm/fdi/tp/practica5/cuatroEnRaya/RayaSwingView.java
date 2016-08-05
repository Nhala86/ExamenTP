package es.ucm.fdi.tp.practica5.cuatroEnRaya;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.view.RectBoardSwingView;

public class RayaSwingView extends RectBoardSwingView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int col;
	
	/**
	 * Parametro de jugador de la SwingPlayer
	 */
	private RayaSwingPlayer player;
	

	public RayaSwingView(Observable<GameObserver> game, Controller c, Piece localPiece, Player randPlayer, Player aiPlayer) {
		super(game, c, localPiece, randPlayer, aiPlayer);				
		this.player = new RayaSwingPlayer();
		
	}

	@Override
	protected void handelMouseClick(int row, int col, int clickcounter, int mouseButton) {
		this.col = col;
		this.player.setMoveValue(this.col);
		if(mouseButton == 1)
			this.caseMakeManualMove(this.player);
	}

}
