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
public class EditStudentListener implements ActionListener {

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
	 * Constructs an EditStudentListener and initializes the data fields.
	 * @param af The AdminFrame that data is passed through.
	 * @param c The code for the listener.
	 */
	public EditStudentListener(AdminFrame af, String c) {
		
		this.frame = af;
		this.code = c;
	}
	
	/**
	 * Gets user input and request return data from the frame using the user input.
	 * Displays the return from the frame
	 * @param e The even listened for.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String read = readStudentInfo();
		
		if(read != null) {
			String str = frame.requestData(code, read);
			JOptionPane.showMessageDialog(panel, str);
		}
		
	}
	
	/**
	 * Reads the user input with a GUI window. 
	 * @return Null if the cancel button is pressed and the user input otherwise.
	 */
	private String readStudentInfo() {
		
		String studentName;
		String studentNumber;
		
		panel = new JPanel (new GridLayout(3, 2));
		JTextField cName = new JTextField(10);
		JTextField cNumber = new JTextField(10);
		if(code.equals("8")) {
			panel.add(new JLabel("Add new student", SwingConstants.CENTER));
		}else {
			panel.add(new JLabel("Remove student", SwingConstants.CENTER));
		}
		panel.add(new JLabel(""));
		
		panel.add(new JLabel("Enter Student Name: "));
		panel.add(cName);
		panel.add(new JLabel("Enter Student Number:"));
		panel.add(cNumber);
		
		int result = JOptionPane.showConfirmDialog(null, panel, "Input Student Information", JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.OK_OPTION)
		{
			studentName = cName.getText();
			studentNumber = cNumber.getText();
			return studentName + "\t" + studentNumber;
		}
		return null;
	}
	
}
