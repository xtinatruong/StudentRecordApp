package clientController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * The class responsible for communicating with the server.
 * @author Desiree Leal, Hoang Truong, Rohit Yeast
 * @since April 16, 2020
 * @version 1.8
 */
public class Client {

	/**
	 * The writer that writes data to the server.
	 */
	private PrintWriter socketOut;
	
	/**
	 * The socket that enables communication.
	 */
	private Socket palinSocket;
	
	/**
	 * The reader that reads data from the server.
	 */
	private BufferedReader socketIn;
	
	/**
	 * Constructs a Client, initializes all data fields and connects to server.
	 * @param host The name of the host.
	 * @param portNumber The port number that the client connects to.
	 */
	public Client(String host, int portNumber) {
		try {
			palinSocket = new Socket(host, portNumber);
			socketIn = new BufferedReader(new InputStreamReader(palinSocket.getInputStream()));
			socketOut = new PrintWriter((palinSocket.getOutputStream()), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sends a request to the server and receives the data resulting from the request.
	 * @param str The request sent to the server.
	 * @return The data received from the server.
	 */
	public String communicate (String str) {
		
		socketOut.println(str);
		String response = "";
		try {
			while(!response.contains("\0")) {
				response += socketIn.readLine() + "\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return response;
	}
}
