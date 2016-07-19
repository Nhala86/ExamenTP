package es.ucm.fdi.tp.practica5.view;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.Utils;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class BombMove extends GameMove {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer radius;

	public BombMove(Piece p) {
		super(p);
	}

	public BombMove(Piece p, Integer radius) {
		super(p);
		this.radius = radius;
	}

	@Override
	public void execute(Board board, List<Piece> pieces) {

		int rows = board.getRows();
		int cols = board.getCols();

		// pick an initial random position
		int initRow = Utils.randomInt(rows);
		int initCol = Utils.randomInt(cols);
		int currRow = initRow;
		int currCol = initCol;
		Piece p = getPiece();

		do {
			if (p.equals(board.getPosition(currRow, currCol))) {
				bomb(board, pieces, currRow, currCol, radius);
				return;
			}
			currCol = (currCol + 1) % cols;
			if (currCol == 0) {
				currRow = (currRow + 1) % rows;
			}
		} while (currRow != initRow || currCol != initCol);

		throw new GameError("Could not find any piece of type " + p);

	}

	private void bomb(Board board, List<Piece> pieces, int row, int col, Integer d) {
		for (int i = 0; i < 10; i++) {
			int col1 = col + Utils.randomInt(2 * d + 1) - d;
			int row1 = row + Utils.randomInt(2 * d + 1) - d;
			int col2 = col + Utils.randomInt(2 * d + 1) - d;
			int row2 = row + Utils.randomInt(2 * d + 1) - d;

			if (inBoard(board, row1, col1) && inBoard(board, row2, col2) && notObstacle(board, pieces, row1, col1)
					&& notObstacle(board, pieces, row2, col2)) {
				Piece p = board.getPosition(row1, col1);
				Piece q = board.getPosition(row2, col2);
				board.setPosition(row1, col1, q);
				board.setPosition(row2, col2, p);
			}
		}
	}

	private boolean notObstacle(Board board, List<Piece> pieces, int row, int col) {
		return inBoard(board, row, col)
				&& (board.getPosition(row, col) == null || pieces.contains(board.getPosition(row, col)));
	}

	private boolean inBoard(Board board, int row, int col) {
		return row >= 0 && col >= 0 && row < board.getCols() && col < board.getCols();
	}

	@Override
	public GameMove fromString(Piece p, String str) {
		String[] words = str.split(" ");
		if (words.length != 2 || !words[0].equalsIgnoreCase("chaos")) {
			return null;
		}

		try {
			int radius = Integer.parseInt(words[1]);
			return new BombMove(p, radius);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	@Override
	public String help() {
		return "chaos radius";
	}

	@Override
	public String toString() {
		return "Bomb a position with " + getPiece();
	}
}
