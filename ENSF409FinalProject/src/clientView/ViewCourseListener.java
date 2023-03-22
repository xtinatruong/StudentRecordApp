package clientView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Listens for when the user presses a button, requests data from the frame
 * and displays the result from the frame on a GUI panel.
 * @author Desiree Leal, Hoang Truong, Rohit Yeast
 * @since April 16, 2020
 * @version 1.8
 */
public class ViewCourseListener implements ActionListener {

	/**
	 * The frame that created the listener.
	 */
	private StudentFrame frame;
	
	/**
	 * Creates a new ViewCourseListener and initializes the frame.
	 * @param f The frame that created the listener.
	 */
	public ViewCourseListener(StudentFrame f) {
		this.frame = f;
	}
	
	/**
	 * Requests data from the frame with user input and displays the results.
	 * @param e The even listened for.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		showData(frame.requestData("5"));
	}
	
	/**
	 * Displays the data on a GUI window.
	 * @param str The data to be displayed.
	 */
	private void showData(String str) {
		
		JPanel viewPanel = new JPanel();
		JOptionPane.showMessageDialog(viewPanel, str);
	}
}
