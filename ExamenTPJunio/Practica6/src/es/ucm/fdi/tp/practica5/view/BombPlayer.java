package es.ucm.fdi.tp.practica5.view;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.Utils;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class BombPlayer extends Player {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer radius;

	@Override
	public GameMove requestMove(Piece p, Board board, List<Piece> pieces, GameRules rules) {
		return new BombMove(p, radius);
	}

	public void setRadius(Integer radius) {
		this.radius = radius;
	}

}
