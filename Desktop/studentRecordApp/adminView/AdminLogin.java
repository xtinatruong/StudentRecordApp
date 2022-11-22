package adminView;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import clientController.GUIControl;
import serverModel.StartUp;

/**
 * Displays a window so the user can log in as an administrator.  This displays
 * a GUI window to the user.
 * @author Desiree Leal, Hoang Truong, Rohit Yeast
 * @since April 16 2020
 * @version 1.8
 */
public class AdminLogin extends JFrame{

	/**
	 * The serilizable ID of the class.
	 */
	private static final long serialVersionUID = -6471574685941483534L;
	
	/**
	 * The main panel of the frame.
	 */
	private JPanel panel;
	
	/**
	 * Constructs an AdminLogin frame and requests data from the GUI till
	 * the correct data is used to log in.
	 * @param label The label of the frame.
	 * @param g The GUIController that controls the client side.
	 */
	public AdminLogin(String label, GUIControl g) {
		super(label);
		
		String read = "", response = "";
		
		do {
			read = getAdminInfo();
			if (read != null) {
				response = g.requestFromServer("1", read);
				JOptionPane.showMessageDialog(panel, response);
			}
		}while(response.contains(" ID. Please tr"));
		
		if(read != null) {
			new AdminFrame("Main Window", g);
		}else {
			dispose();
			new StartUp("Select Role", g);
		}
	}
	
	/**
	 * Requests login info from the user with a pop up window.
	 * @return The data inputed by the user as a String.
	 */
	private String getAdminInfo() {
		
		String studentName;
		String studentNumber;
		
		panel = new JPanel (new GridLayout(2, 2));
		JTextField sNumber = new JTextField(10);
		JTextField sName = new JTextField(10);
		
		panel.add(new JLabel("Enter Administrator Name:"));
		panel.add(sName);
		panel.add(new JLabel("Enter Administrator Number:"));
		panel.add(sNumber);
		
		int result = JOptionPane.showConfirmDialog(null, panel, "Input Administrator Information", JOptionPane.OK_CANCEL_OPTION);
		
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
