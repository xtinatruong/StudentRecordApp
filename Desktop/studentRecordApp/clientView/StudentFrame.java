package clientView;

import javax.swing.JFrame;

import clientController.GUIControl;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * This method implements the front end of the program that the user interacts with.
 * It manages various GUI components and displays them to the user.
 * @author Desiree Leal, Hoang Truong, Rohit Yeast
 * @since April 11, 2020
 * @version 2.0
 */
public class StudentFrame extends JFrame implements RequestData{

	/**
	 * The serializable ID of the class.
	 */
	private static final long serialVersionUID = -840222079440263878L;

	/**
	 * The controller of the client side.
	 */
	private GUIControl gui;
	
	/**
	 * The center panel of the main window.
	 */
	private JPanel panel;
	
	/**
	 * The buttons on the main panel.
	 */
	private JButton searchButton, addButton, catalogueButton, removeButton, viewButton, quitButton;
	
	/**
	 * Listens for the add course to student button.
	 */
	private AddListener addListen;
	
	/**
	 * Listens for the remove course from student button.
	 */
	private RemoveListener removeListen;
	
	/**
	 * Listens for when the search button is pressed.
	 */
	private SearchListener searchListen;
	
	/**
	 * Listens for when the view catalogue button is pressed. 
	 */
	private ViewCatalogueListener viewCatalogueListen;
	
	/**
	 * Listens for when the view student courses button is pressed.
	 */
	private ViewCourseListener viewCourseListen;
	
	/**
	 * Constructs a frame for the client to see.
	 * @param label the label of the frame
	 */
	public StudentFrame (String label, GUIControl g) {
		super(label);
		
		this.gui = g;
		
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 225);
		
		add("North", new JLabel("Student Account", SwingConstants.CENTER));
		
		initializeListeners();
		connectButtons();
		
		panel = new JPanel();
		configurePanelLayout();
		
		add("Center", panel);
		
		setVisible(true);
		
	}
	
	/**
	 * Initializes all buttons and connects them to listeners.
	 */
	private void connectButtons() {
		
		searchButton = new JButton("Search Catalogue");
		addButton = new JButton("Add Course to Student Courses");
		removeButton = new JButton("Remove Course from Student Courses");
		catalogueButton = new JButton("View all Courses in Catalogue");
		viewButton = new JButton ("View all Courses Taken by Student");
		quitButton = new JButton ("Quit");
		
		searchButton.addActionListener(searchListen);
		addButton.addActionListener(addListen);
		removeButton.addActionListener(removeListen);
		catalogueButton.addActionListener(viewCatalogueListen);
		viewButton.addActionListener(viewCourseListen);
		quitButton.addActionListener(new QuitListener());
	}
	
	/**
	 * Initializes all listeners.
	 */
	private void initializeListeners() {
		
		searchListen = new SearchListener(this);
		addListen = new AddListener(this);
		removeListen = new RemoveListener(this);
		viewCatalogueListen = new ViewCatalogueListener(this);
		viewCourseListen = new ViewCourseListener(this);
	}
	
	/**
	 * Configures the layout of the frame.
	 */
	private void configurePanelLayout() {
		
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		
		layout.setHorizontalGroup(
				
			layout.createSequentialGroup()	
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(searchButton)
				.addComponent(addButton)
				.addComponent(removeButton)
				.addComponent(catalogueButton)
				.addComponent(viewButton)
				.addComponent(quitButton)
			)
		);
			
		layout.setVerticalGroup(
					
			layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(searchButton))
			.addComponent(addButton)
			.addComponent(removeButton)
			.addComponent(catalogueButton)
			.addComponent(viewButton)
			.addComponent(quitButton)
		);
		
	}
	
	/**
	 * Requests data from the GUIController.
	 * @param code The code representing the data requested.
	 * @param data Relevant data required by the GUIController.
	 * @return A string of the requested data.
	 */
	public String requestData(String code, String data) {
		
		String str = gui.requestFromServer(code, data);
		return str;
	}
	
	/**
	 * Requests data from the GUIController.
	 * @param code The code representing the data requested.
	 * @return A string of the requested data.
	 */
	public String requestData(String code) {
		
		String str = gui.requestFromServer(code);
		return str;
	}
	
	/**
	 * Listens for when the quit button is pressed. 
	 * @author Desiree Leal, Hoang Truong, Rohit Yeast
	 * @since April 16, 2020
	 * @version 1.8
	 */
	class QuitListener implements ActionListener {

		/**
		 * Performs calls the quit method when the event is heard. 
		 * @param e The even listened for.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			quit();
		}	
	}
	
	/**
	 * Disposes of the frame and ends the program.
	 */
	public void quit() {
		this.dispose();
		System.exit(1);
	}
	
}
