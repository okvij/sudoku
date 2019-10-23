import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel implements ActionListener {
	private ButtonListener btnListener;
	private JButton validateButton;
	private JButton clearButton;
	
	public ButtonPanel() {
		
		validateButton = new JButton("Validate");
		validateButton.addActionListener(this);
		
		clearButton = new JButton("Clear");
		clearButton.setPreferredSize(validateButton.getPreferredSize());
		clearButton.addActionListener(this);
	
		add(validateButton);
		add(clearButton);
		setBorder(BorderFactory.createTitledBorder("Button Panel"));
	}
	
	public void setButtonListener(ButtonListener listener) {
		this.btnListener = listener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton)e.getSource();
		
		if (clicked == clearButton) {
			btnListener.buttonPressed("clear");
		} else if (clicked == validateButton) {
			btnListener.buttonPressed("validate");
		}
		
	}
}
