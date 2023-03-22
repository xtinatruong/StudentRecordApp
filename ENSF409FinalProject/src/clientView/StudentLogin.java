package clientView;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clientController.GUIControl;

/**
 * Displays a window so the user can log in as a student.  Diplays a GUI window to the
 * user.
 * @author Desiree Leal, Hoang Truong, Rohit Yeast
 * @since April 16 2020
 * @version 1.8
 */
public class StudentLogin extends JFrame {
		
	/**
	 * The serilizable ID of the class.
	 */
	private static final long serialVersionUID = 6827963213402945489L;
	
	/**
	 * The main panel of the frame.
	 */
	private JPanel panel;
	
	/**
	 * Constructs a StudentLogin frame and requests data from the GUI till
	 * the correct data is used to log in.
	 * @param label The label of the frame.
	 * @param g The GUIController that controls the client side.
	 */
	public StudentLogin (String label, GUIControl g) {
		super (label);
		
		String read = "", response = "";
		
		do {
			read = getStudentInfo();
			if (read != null) {
				
				response = g.requestFromServer("0", read);
				JOptionPane.showMessageDialog(panel, response);
			}
		}while(response.contains("dent ID. Please tr"));
		
		if(read != null) {
			new StudentFrame("Main Window", g);
		}else {

			dispose();
			new StartUp("Select Role", g);
		}
	}

	/**
	 * Requests login info from the user with a pop up window.
	 * @return The data inputed by the user as a String.
	 */
	private String getStudentInfo () {
		
		String studentName;
		String studentNumber;
		
		panel = new JPanel (new GridLayout(2, 2));
		JTextField sNumber = new JTextField(10);
		JTextField sName = new JTextField(10);
		
		panel.add(new JLabel("Enter Student Name:"));
		panel.add(sName);
		panel.add(new JLabel("Enter Student Number:"));
		panel.add(sNumber);
		
		int result = JOptionPane.showConfirmDialog(null, panel, "Input Student Information", JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.CANCEL_OPTION)
		{
			return null;
		} else {
			studentName = sName.getText();
			studentNumber = sNumber.getText();
			
			return studentNumber + "\t" + studentName;
		}
	}
}
