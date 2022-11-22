package serverController;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 
 * @author Desiree Leal, Hoang Truong, Rohit Yeast
 * @since 
 * @version 
 */
public class Server {
		
	/**
	 * The server socket used to host clients.
	 */
	private ServerSocket serverSocket;	
	/**
	 * A thread pool used to start multiple games.
	 */
	private ExecutorService pool;		
	
															//TODO add database
	
	/**														TODO connect to database in the constructor
	 * 
	 * @param port the port number to connect with the client
	 */
	public Server (int port) {
		
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
			
			Controller control = new Controller(serverSocket.accept());  //TODO add arguments for database
			System.out.println("Connection To Client");
			pool.execute(control);
		}
	}	
	
	public static void main (String [] args) throws IOException {
		
		Server s = new Server (9090);
		s.connect();
	}
}
