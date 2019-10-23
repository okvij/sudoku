import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class Cell extends JButton {

	private int row;
	private int column;
	private int square;
	private int value = 0;
	public boolean clickable;
	
	public Cell(int row, int column, int square) {
		this.row = row;
		this.column = column;
		this.square = square;
		this.clickable = false;
	
		setFont(new Font(this.getFont().toString(),Font.BOLD,30));
		setBorder(new LineBorder(Color.BLACK, 2));
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getSquare() {
		return square;
	}

	public void setSquare(int square) {
		this.square = square;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
		
		if (value ==0) {
			setText(null);
		} else {
			setText(Integer.toString(value));
		}
	}

	@Override
	public String toString() {
		return "R" + row + "C" + column + "S" + square + "V" + value;
	}
	
	

}
