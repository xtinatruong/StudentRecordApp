package clientView;
/**
 * This interface ensures that a class has the requestData methods.
 * @author Desiree Leal, Hoang Truong, Rohit Yeast
 * @since April 16, 2020
 * @version 1.8
 */
public interface RequestData {

	/**
	 * Requests data from the GUIController.
	 * @param code The code representing the data requested.
	 * @param data Relevant data required by the GUIController.
	 * @return A string of the requested data.
	 */
	public String requestData(String code, String data);
	
	/**
	 * Requests data from the GUIController.
	 * @param code The code representing the data requested.
	 * @return A string of the requested data.
	 */
	public String requestData(String code);
}
