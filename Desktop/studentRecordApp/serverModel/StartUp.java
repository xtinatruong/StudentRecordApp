package serverModel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import adminView.AdminLogin;
import clientController.GUIControl;
import clientView.StudentLogin;

/**
 * The first GUI frame of the program that asks the user to choose a role.
 * It starts either an admin frame of a student frame depending on the choice.
 * @author Desiree Leal, Hoang Truong, Rohit Yeast
 * @since April 16, 2020
 * @version 1.8
 */
public class StartUp extends JFrame{

	/**
	 * The serilizable ID of the class.
	 */
	private static final long serialVersionUID = 3468142464946467036L;
	
	/**
	 * The main panel of on the frame.
	 */
	private JPanel panel;
	
	/**
	 * The buttons on the panel.
	 */
	private JButton admin, student, cancel;
	
	/**
	 * The GUIControl that is passed to subsequent frames created.
	 */
	private GUIControl gui;
	
	/**
	 * Creates the frame and initializes all components on it.
	 * @param label The label of the frame.
	 * @param g The GUI controller passed to the next frame.
	 */
	public StartUp(String label, GUIControl g) {	
		super(label);
		
		gui = g;
		
		panel = new JPanel(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 60);
		
		admin = new JButton("Administrator");
		student = new JButton("Student");
		cancel = new JButton("Cancel");
		
		admin.addActionListener(new AdminListener());
		student.addActionListener(new StudentListener());
		cancel.addActionListener(new CancelListener());
		
		panel.add(admin);
		panel.add(student);
		panel.add(cancel);
		
		add("Center", panel);
		setVisible(true);
	}
	
	/**
	 * Listens for when the cancel button is pressed.
	 * @author Desiree Leal, Hoang Truong, Rohit Yeast
	 * @since April 16, 2020
 	 * @version 1.8
 	 */
	class CancelListener implements ActionListener {

		/**
		 * Calls the cancel method.
		 * @param e The even listened for.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			cancel();
		}	
	}
	
	/**
	 * Disposes of the frame and ends the program.
	 */
	public void cancel() {
		dispose();
		System.exit(1);
	}
	
	/**
	 * Listens for when the admin button is pressed.
	 * @author Desiree Leal, Hoang Truong, Rohit Yeast
	 * @since April 16, 2020
 	 * @version 1.8
 	 */
	class AdminListener implements ActionListener {

		/**
		 * Calls the newAdmin method.
		 * @param e The even listened for.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			newAdmin();
		}	
	}
	
	/**
	 * Disposes of this frame and starts a new adminLogin frame.
	 */
	public void newAdmin() {
		dispose();
		new AdminLogin("Main Window", gui);
	}
	
	/**
	 * Listens for when the student button is pressed.
	 * @author Desiree Leal, Hoang Truong, Rohit Yeast
	 * @since April 16, 2020
 	 * @version 1.8
 	 */
	class StudentListener implements ActionListener {

		/**
		 * Calls the newStudent method.
		 * @param e The even listened for.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			newStudent();
		}
	}
	
	/**
	 * Disposes of this frame and starts a new studentLogin frame.
	 */
	public void newStudent() {
		dispose();
		System.out.println("Properly disposed");
		new StudentLogin("Main Window", gui);
	}
	
}
