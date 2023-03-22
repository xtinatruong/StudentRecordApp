package adminView;

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
public class EditCourseListener implements ActionListener {
	
	/**
	 * The frame which created the listener.
	 */
	private AdminFrame frame;
	
	/**
	 * The panel info is displayed on.
	 */
	private JPanel panel;
	
	/**
	 * The code for what message to display and pass to the frame.
	 */
	private String code;
	
	/**
	 * Constructs an EditCourseListener and initializes the data fields.
	 * @param af The AdminFrame that data is passed through.
	 * @param c The code for the listener.
	 */
	public EditCourseListener(AdminFrame af, String c) {
		
		this.frame = af;
		this.code = c;	
	}
	
	/**
	 * Gets user input and request return data from the frame using the user input.
	 * Displays the return from the frame
	 * @param e The event listened for.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String read = readCourseInfo();
		

		if(read != null) {
			String str = frame.requestData(code, read);
			JOptionPane.showMessageDialog(panel, str);
		}
		
	}
	
	/**
	 * Reads the user input with a GUI window. 
	 * @return Null if the cancel button is pressed and the user input otherwise.
	 */
	private String readCourseInfo() {
		
		String courseName;
		String courseNumber;
		
		panel = new JPanel (new GridLayout(3, 2));
		JTextField cName = new JTextField(10);
		JTextField cNumber = new JTextField(10);
		
		if(code.equals("6")) {
			panel.add(new JLabel("Add new course", SwingConstants.CENTER));
		} else {
			panel.add(new JLabel("Remove new course", SwingConstants.CENTER));
		}
		
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
