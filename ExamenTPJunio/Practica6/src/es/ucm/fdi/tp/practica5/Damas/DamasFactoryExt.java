package es.ucm.fdi.tp.practica5.Damas;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica4.damas.DamasFactory;

public class DamasFactoryExt extends DamasFactory{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Metodo constructor por defecto de la clase instanciado a vacio que llama al super()
	 */
	public DamasFactoryExt() {
		super();
	}
	
	
	/**
	 * Metodo constructor de la clase al que se le pasa un parametro de entrada
	 * @param dimension entero positivo que indica la dimension del tablero
	 */
	public DamasFactoryExt(int dimension) {
		super(dimension);
	}
	
	@Override
	public void createSwingView(Observable<GameObserver> game, Controller ctrl, Piece viewPiece, Player randPlayer, Player aiPlayer) {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					new DamasSwingView(game, ctrl, viewPiece, randPlayer, aiPlayer);					
					}
				});
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
