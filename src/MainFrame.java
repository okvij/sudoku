import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
	private Board board;
	private ButtonPanel buttonPanel;
	
	public MainFrame () {
		super("Sudoku 1.0");
		
		board = new Board();
		buttonPanel = new ButtonPanel();
		
		buttonPanel.setButtonListener(new ButtonListener() {
			public void buttonPressed(String btn) {
				if (btn.equals("clear")) {
					board.clearSquares();
				} else if (btn.equals("validate")) {
					board.check(true);
				}
			}
		});
		
		setLayout(new BorderLayout());

		add(buttonPanel, BorderLayout.NORTH);
		add(board, BorderLayout.CENTER);

		setMinimumSize(new Dimension(500, 600));
		setSize(500, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
