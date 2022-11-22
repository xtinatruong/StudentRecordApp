package adminView;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import clientController.GUIControl;
import clientView.RequestData;
import clientView.SearchListener;
import clientView.ViewCatalogueListener;

/**
 * Displaces the options that an administrator can choose for the application.  This class
 * is a front end GUI that displays all the buttons for options on a single frame.
 * @author Desiree Leal, Hoang Truong, Rohit Yeast
 * @since April 16, 2020
 * @version 1.8
 */
public class AdminFrame extends JFrame implements RequestData{
	
	
	/**
	 * The serializable ID of the class.
	 */
	private static final long serialVersionUID = -3706576791017216240L;
	
	/**
	 * The controller of the client side.
	 */
	private GUIControl gui;
	
	/**
	 * The center panel of the frame.
	 */
	private JPanel panel;
	
	/**
	 * Buttons for the user to click.
	 */
	private JButton searchButton, addCourse, removeCourse, addStudent, removeStudent; 
	
	/**
	 * Buttons for the user to click.
	 */
	private JButton catalogueButton, studentList, quitButton;
	
	/**
	 * Listens for when the search button is pressed.
	 */
	private SearchListener searchListen;
	
	/**
	 * Listens for when the add or remove course buttons are pressed.
	 */
	private EditCourseListener addCourseListen, removeCourseListen;
	
	/**
	 * Listens for when the the add or remove student buttons are pressed.
	 */
	private EditStudentListener addStudentListen, removeStudentListen;
	
	/**
	 * Listens for when the view catalogue button is pressed. 
	 */
	private ViewCatalogueListener viewCatalogueListen;
	
	/**
	 * Listens for when the view studentList button is pressed. 
	 */
	private StudentListListener studentListListen;
	
	/**
	 * Constructs and formats an AdminFrame, instantiates all buttons and listeners and 
	 * displays the frame.
	 * @param label The main label of the frame.
	 * @param g The GUI controller that information is passed through.
	 */
	public AdminFrame(String label, GUIControl g) {
		super(label);
		
		this.gui = g;
		
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 275);
		
		add("North", new JLabel("Administrator Account", SwingConstants.CENTER));
		
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
		addCourse = new JButton("Add a Course to Catalogue");			
		removeCourse = new JButton("Remove a Course from Catalogue");	
		addStudent = new JButton("Add Student to System");				
		removeStudent = new JButton("Remove Student from Directory");	
		catalogueButton = new JButton("View all Courses in Catalogue");
		studentList = new JButton("View all Students in Directory");
		quitButton = new JButton("Quit");
		
		searchButton.addActionListener(searchListen);
		addCourse.addActionListener(addCourseListen);
		removeCourse.addActionListener(removeCourseListen);
		addStudent.addActionListener(addStudentListen);
		removeStudent.addActionListener(removeStudentListen);
		catalogueButton.addActionListener(viewCatalogueListen);
		studentList.addActionListener(studentListListen);
		quitButton.addActionListener(new QuitListener());
	}
	
	/**
	 * Initializes all listeners.
	 */
	private void initializeListeners() {
		
		searchListen = new SearchListener(this);
		addCourseListen = new EditCourseListener(this, "6");
		removeCourseListen = new EditCourseListener(this, "7");
		addStudentListen = new EditStudentListener(this, "8");
		removeStudentListen = new EditStudentListener(this, "9");
		viewCatalogueListen = new ViewCatalogueListener(this);
		studentListListen = new StudentListListener(this);
	}
	
	/**
	 * Configures the layout of the frame.
	 */
	private void configurePanelLayout(){
		
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		
		layout.setHorizontalGroup(
				
			layout.createSequentialGroup()	
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(searchButton)
				.addComponent(addCourse)
				.addComponent(removeCourse)
				.addComponent(addStudent)
				.addComponent(removeStudent)
				.addComponent(catalogueButton)
				.addComponent(studentList)
				.addComponent(quitButton)
			)
		);
		
		layout.setVerticalGroup(
				
			layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(searchButton))
			.addComponent(addCourse)
			.addComponent(removeCourse)
			.addComponent(addStudent)
			.addComponent(removeStudent)
			.addComponent(catalogueButton)
			.addComponent(studentList)
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
