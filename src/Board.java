import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Board extends JPanel {
	private List<BigSquare> squares;
	private List<Cell> cells;
	private Random rand = new Random();

	public Board() {

		squares = new ArrayList<BigSquare>();
		cells = new ArrayList<Cell>();
		rand = new Random();

		Dimension dim = getPreferredSize();
		dim.width = 450;
		dim.height = 450;
		setMinimumSize(dim);
		setBorder(BorderFactory.createTitledBorder("Board"));

		setLayout(new GridLayout(3, 3, 0, 0));

		for (int i = 0; i < 9; i++) {
			BigSquare bigSquare = new BigSquare(i);
			squares.add(bigSquare);
			add(bigSquare);

			cells.addAll(bigSquare.getCells());
		}
		
		setSquare1();
		solve();
		updateCells();
		clearRandomCells(60);
		
		
	}


	
	
	public void clearSquares() {
		for (BigSquare bigSquare : squares) {
			bigSquare.clearCells();
		}
	}

	public boolean check(boolean paint) {

		boolean valid = true;

		for (Cell cell : cells) {
			cell.setBackground(null);
		}

		for (int i = 0; i < cells.size() - 1; i++) {
			for (int j = i + 1; j < cells.size(); j++) {
				if (conflict(cells.get(i), cells.get(j))) {
					if (paint) {
						cells.get(i).setBackground(Color.RED);
						cells.get(j).setBackground(Color.RED);
					}
					valid = false;
				}
			}
		}

		return valid;
	}

	public boolean conflict(Cell c1, Cell c2) {
		if (((c1.getRow() == c2.getRow()) || (c1.getColumn() == c2.getColumn()) || (c1.getSquare() == c2.getSquare()))
				&& c1.getValue() != 0 & c1.getValue() == c2.getValue()) {
			return true;
		} else {
			return false;
		}
	}

	private void clearRandomCells(int numberOfCells) {
		List<Cell> randomCells = new ArrayList<Cell>();

		while (randomCells.size() < numberOfCells) {
			Cell c = cells.get(rand.nextInt(80));
			if (!randomCells.contains(c)) {
				randomCells.add(c);
				c.clickable = true;
				c.setValue(0);
				c.setBorder(new LineBorder(Color.BLACK, 1));
			}
		}

	}


	
	private static int[][] GRID_TO_SOLVE = { 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
		};

	private static final int EMPTY = 0;
	private static final int SIZE = 9;

	private void setSquare1() {
		
		List<Integer> values = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
		Random rand = new Random();
		
		for (int i = 0; i<=2; i++) {
				for (int j = 0; j <=2; j++) {
			
					int randIndex = values.size()>1 ? rand.nextInt(values.size()-1) : 0;
					GRID_TO_SOLVE[i][j] = values.get(randIndex);
					values.remove(randIndex);		
				}
		}		
	}
	


	private static boolean isInRow(int row, int number) {
		for (int i = 0; i < SIZE; i++)
			if (GRID_TO_SOLVE[row][i] == number)
				return true;

		return false;
	}

	private static boolean isInCol(int col, int number) {
		for (int i = 0; i < SIZE; i++)
			if (GRID_TO_SOLVE[i][col] == number)
				return true;

		return false;
	}

	private static boolean isInBox(int row, int col, int number) {
		int r = row - row % 3;
		int c = col - col % 3;

		for (int i = r; i < r + 3; i++)
			for (int j = c; j < c + 3; j++)
				if (GRID_TO_SOLVE[i][j] == number)
					return true;

		return false;
	}

	private static boolean isOk(int row, int col, int number) {
		return !isInRow(row, number) && !isInCol(col, number) && !isInBox(row, col, number);
	}

	private boolean solve() {
		for (int row = 0; row < SIZE; row++) {
			
			for (int col = 0; col < SIZE; col++) {

				if (GRID_TO_SOLVE[row][col] == EMPTY) {

					for (int number = 1; number <= SIZE; number++) {
						if (isOk(row, col, number)) {

							GRID_TO_SOLVE[row][col] = number;

							if (solve()) { // we start backtracking recursively
								return true;
							} else { // if not a solution, we empty the cell and we continue
								GRID_TO_SOLVE[row][col] = EMPTY;
							}
						}
					}

					return false;
				}
			}
		}

		return true;
	}

	private static void display() {

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				System.out.print(" " + GRID_TO_SOLVE[i][j]);
			}

			System.out.println();
		}

		System.out.println();
	}
	
	private void updateCells() {
		
		for (Cell c: cells) {
			c.setValue(GRID_TO_SOLVE[c.getRow()][c.getColumn()]);
		}
		
		
	}
}
