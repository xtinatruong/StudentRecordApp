package clientController;

import clientView.StartUp;


/**
 * The main class of the client side that connects the GUI and client.
 * This method only starts the GUI by creating a new StartUp.
 * @author Desiree Leal, Hoang Truong, Rohit Yeast
 * @since April 16, 2020
 * @version 1.8
 */
public class GUIControl {

	/**
	 * The client that communicates to the server.
	 */
	private Client client;
	
	/**
	 * This class aggregates the Client(s) of the program.
	 * @param c the client that will use the frame.
	 */
	public GUIControl (Client c) {
		
		this.client = c;
	}
	
	/**
	 * Requests data from the client and returns that data.
	 * @param code Indicates what choice the user selected.
	 * @param data A string with the user input.
	 * @return The data from the client.
	 */
	public String requestFromServer(String code, String data) {
		
		return client.communicate(code + "\t" + data);
	}
	
	/**
	 * Requests data from the client and returns that data.
	 * @param code Indicates what choice the user selected.
	 * @return The data from the client.
	 */
	public String requestFromServer(String code) {
		
		String r;
		r = client.communicate(code);
		return r;
	}
	

	public static void main (String [] args) {
		
		GUIControl gc = new GUIControl (new Client("localhost", 9090));
		new StartUp("Select Role", gc);
	}
}
