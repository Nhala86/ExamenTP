package es.ucm.fdi.tp.practica5.foxy;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.view.RectBoardSwingView;

public class FoxySwingView extends RectBoardSwingView{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int row;
	private int col;
	private int destinyRow;
	private int destinyCol;
	private FoxySwingPlayer player;
	private boolean secondClick;
	
	

	public FoxySwingView(Observable<GameObserver> game, Controller c, Piece localPiece, Player randPlayer, Player aiPlayer) {
		super(game, c, localPiece, randPlayer, aiPlayer);
		this.player = new FoxySwingPlayer();
		this.secondClick = false;
	}

	@Override
	protected void handelMouseClick(int row, int col, int clickcounter, int mouseButton) {
		if(!secondClick){
			this.row = row;
			this.col = col;
			this.secondClick = true;
			
		}
		else{
			this.destinyRow = row;
			this.destinyCol = col;
			this.secondClick = false;
			
			this.player.setMoveValue(this.row, this.col, this.destinyRow, this.destinyCol);
			if(mouseButton == 1)
			this.caseMakeManualMove(this.player);
		}		
		
	}

}
