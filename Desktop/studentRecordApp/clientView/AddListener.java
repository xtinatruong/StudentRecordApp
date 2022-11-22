package clientView;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Listens for when the user presses a button, requests data from the frame
 * and displays the result from the frame on a GUI panel.
 * @author Desiree Leal, Hoang Truong, Rohit Yeast
 * @since April 16, 2020
 * @version 1.8
 */
public class AddListener implements ActionListener {

	/**
	 * The frame that created the listener.
	 */
	private StudentFrame frame;
	
	/**
	 * The panel where information is displayed.
	 */
	private JPanel panel;
	
	/**
	 * Creates a new AddListener and initializes the frame.
	 * @param f The frame that created the listener.
	 */
	public AddListener(StudentFrame f) {
		this.frame = f;
	}
	
	/**
	 * Requests data from the frame with user input and displays the results.
	 * @param e The even listened for.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String read = readCourseInfo();
		

		if(read != null) {
			String str = frame.requestData("2", read);
			JOptionPane.showMessageDialog(panel, str);
		}
		
	}
	
	/**
	 * Creates a GUI window to get user input.
	 * @return The user input as one string.
	 */
	private String readCourseInfo() {
	
		String courseName;
		String courseNumber;
		
		panel = new JPanel (new GridLayout(3, 2));
		JTextField cName = new JTextField(10);
		JTextField cNumber = new JTextField(10);
		
		panel.add(new JLabel("Insert new data", SwingConstants.CENTER));
		panel.add(new JLabel(""));
		
		panel.add(new JLabel("Enter Course Name:  "));
		panel.add(cName);
		panel.add(new JLabel("Enter Course Number:"));
		panel.add(cNumber);
		
		int result = JOptionPane.showConfirmDialog(null, panel, "Input Course Information", JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.OK_OPTION)
		{
			courseName = cName.getText();
			courseNumber = cNumber.getText();
			return courseName + "\t" + courseNumber;
		}
		return null;
	}
}
