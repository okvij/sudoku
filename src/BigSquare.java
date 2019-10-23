import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.apache.commons.lang3.ArrayUtils;

public class BigSquare extends JPanel {
	private int index;




	private List<Cell> cells;

	public BigSquare(int index) {

		this.index = index;
		cells = new ArrayList<Cell>();

		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		setLayout(new GridLayout(3, 3, 0, 0));

		for (int i = 0; i < 9; i++) {
			int row = getCoordinates(i)[0];
			int column = getCoordinates(i)[1];

			Cell cell = new Cell(row, column, index);

			cell.addMouseListener(new MouseListener() {

				
				
				public void mouseReleased(MouseEvent me) {
					
					Cell clicked = (Cell) me.getSource();
					if (!clicked.clickable) return;

					int label = clicked.getText() == null ? 0 : Integer.parseInt(clicked.getText());
					
					if (me.getButton() == MouseEvent.BUTTON1) {
						label = label == 9 ? 1 : ++label;
					} else if (me.getButton() == MouseEvent.BUTTON3) {
						label = label <= 1 ? 9 : --label;
					}
					
					
					clicked.setValue(label);
					clicked.setBackground(null);
					
				}

				public void mouseClicked(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
				public void mousePressed(MouseEvent e) {}
				
			});
			
			

			cells.add(cell);
			add(cell);
		}
	}

	public void clearCells() {
		for (Cell cell : cells) {
			if (cell.clickable) {
				cell.setText("");
				cell.setValue(0);
			}
			cell.setBackground(null);
		}
	}


	public List<Cell> getCells() {
		return cells;
	}



	private int[] getCoordinates(int cellIndex) {
		int row = 0;
		int column = 0;

		if (index >= 0 && index <= 2) {
			if (cellIndex >= 0 && cellIndex <= 2) {
				row = 0;
				column = cellIndex;
			} else if (cellIndex >= 3 && cellIndex <= 5) {
				row = 1;
				column = cellIndex - 3;
			} else if (cellIndex >= 6 && cellIndex <= 8) {
				row = 2;
				column = cellIndex - 6;
			}

		} else if (index >= 3 && index <= 5) {
			if (cellIndex >= 0 && cellIndex <= 2) {
				row = 3;
				column = cellIndex + 3;
			} else if (cellIndex >= 3 && cellIndex <= 5) {
				row = 4;
				column = cellIndex + 3;
			} else if (cellIndex >= 6 && cellIndex <= 8) {
				row = 5;
				column = cellIndex + 3;
			}
		} else if (index >= 6 && index <= 8) {
			if (cellIndex >= 0 && cellIndex <= 2) {
				row = 6;
				column = cellIndex + 6;
			} else if (cellIndex >= 3 && cellIndex <= 5) {
				row = 7;
				column = cellIndex + 6;
			} else if (cellIndex >= 6 && cellIndex <= 8) {
				row = 8;
				column = cellIndex + 6;
			}
		}

		if (ArrayUtils.contains(new int[] { 0, 3, 6 }, index)) {
			if (cellIndex >= 0 && cellIndex <= 2) {
				column = cellIndex;
			} else if (cellIndex >= 3 && cellIndex <= 5) {
				column = cellIndex - 3;
			} else if (cellIndex >= 6 && cellIndex <= 8) {
				column = cellIndex - 6;
			}

		} else if (ArrayUtils.contains(new int[] { 1, 4, 7 }, index)) {
			if (cellIndex >= 0 && cellIndex <= 2) {
				column = cellIndex + 3;
			} else if (cellIndex >= 3 && cellIndex <= 5) {
				column = cellIndex;
			} else if (cellIndex >= 6 && cellIndex <= 8) {
				column = cellIndex - 3;
			}
		} else if (ArrayUtils.contains(new int[] { 2, 5, 8 }, index)) {
			if (cellIndex >= 0 && cellIndex <= 2) {
				column = cellIndex + 6;
			} else if (cellIndex >= 3 && cellIndex <= 5) {
				column = cellIndex + 3;
			} else if (cellIndex >= 6 && cellIndex <= 8) {
				column = cellIndex;
			}
		}

		return new int[] {row, column};
	}
	
	public int getIndex() {
		return index;
	}
}
