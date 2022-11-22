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
public class RemoveListener implements ActionListener {

	/**
	 * The frame that created the listener.
	 */
	private StudentFrame frame;
	
	/**
	 * The panel where information is displayed.
	 */
	private JPanel insertPanel;
	
	/**
	 * Creates a new RemoveListener and initializes the frame.
	 * @param f The frame that created the listener.
	 */
	public RemoveListener(StudentFrame f) {
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
			String str = frame.requestData("3", read);
			JOptionPane.showMessageDialog(insertPanel, str);
		}
	}
	
	/**
	 * Creates a GUI window to get user input.
	 * @return The user input as one string.
	 */
	private String readCourseInfo() {
		
		String courseName;
		String courseNumber;
		
		insertPanel = new JPanel (new GridLayout(3, 2));
		JTextField cName = new JTextField(10);
		JTextField cNumber = new JTextField(10);
		
		insertPanel.add(new JLabel("Insert new data", SwingConstants.CENTER));
		insertPanel.add(new JLabel(""));
		
		insertPanel.add(new JLabel("Enter Course Name: "));
		insertPanel.add(cName);
		insertPanel.add(new JLabel("Enter Course Number: \n"));
		insertPanel.add(cNumber);
		
		int result = JOptionPane.showConfirmDialog(null, insertPanel, "Input Course Information", JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.OK_OPTION)
		{
			courseName = cName.getText();
			courseNumber = cNumber.getText();

			return courseName + "\t" + courseNumber;
		}
		return null;
	}
}
