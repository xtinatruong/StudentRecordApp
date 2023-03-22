package adminView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 * Listens for when the user presses a button, requests data from the frame
 * and displays the result from the frame on a GUI panel.
 * @author Desiree Leal, Hoang Truong, Rohit Yeast
 * @since April 16, 2020
 * @version 1.8
 */
public class StudentListListener implements ActionListener {

	/**
	 * The frame that created the listener.
	 */
	private AdminFrame frame;
	
	/**
	 * Creates a new StudentListListener and initializes the frame.
	 * @param f The frame that created the listener.
	 */
	public StudentListListener(AdminFrame f) {
		this.frame = f;
	}
	
	/**
	 * Requests data from the frame with user input and displays the results.
	 * @param e The event listened for.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		showData(frame.requestData("0"));
	}
	
	/**
	 * Creates a new frame to show the list of all students
	 * @param str String with all the students
	 */
	private void showData(String str) {
				
		JFrame frame = new JFrame();
		frame.setSize(300, 300);
		
		frame.add("North", new JLabel("Student Directory", SwingConstants.CENTER));
		
		JTextArea textArea = new JTextArea();
		JScrollPane scroll = new JScrollPane(textArea);
		frame.add("Center", scroll);
		textArea.setText(str);
		frame.setVisible(true);
		
	}
	
}

