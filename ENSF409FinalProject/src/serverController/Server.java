package serverController;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import serverModel.AdminList;
import serverModel.CourseCatalogue;
import serverModel.StudentList;


/**
 * This class runs the server and connects the course catalogue, student list and admin list to the data base.
 * @author Desiree Leal, Hoang Truong, Rohit Yeast
 * @since April 17, 2020
 * @version 1.8
 */
public class Server {
	
	/**
	 * The course catalogue from database
	 */
	private CourseCatalogue catalogue;
	
	/**
	 * The list of students
	 */
	private StudentList studentList;
	
	/**
	 * The list of admin
	 */
	private AdminList adminList;
	
	/**
	 * The server socket used to host clients.
	 */
	private ServerSocket serverSocket;	
	/**
	 * A thread pool used to start multiple games.
	 */
	private ExecutorService pool;		
		
	/**														
	 * Constructor for the server initializes a server socket, makes the thread pool and initializes the 
	 * lists of students, admin and courses in the database.
	 * @param port the port number to connect with the client
	 */
	public Server (int port) {
		
		this.studentList = new StudentList();
		this.adminList = new AdminList();
		this.catalogue = new CourseCatalogue();
		try {
			serverSocket = new ServerSocket (port);
			pool = Executors.newCachedThreadPool();
			System.out.println("Server running");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets input from client and calls method menu.
	 * @throws IOException
	 */
	public void connect() throws IOException {
			
		while (true) {
			
			Controller control = new Controller(serverSocket.accept(), studentList, catalogue, adminList); 
			System.out.println("Connection To Client");
			pool.execute(control);
		}
	}	
	
	public static void main (String [] args) throws IOException {
		
		Server s = new Server (9090);
		s.connect();
	}
}
