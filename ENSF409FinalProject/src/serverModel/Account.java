package serverModel;

/**
 * Abstract class that is used by Student and Admin to initialize and set their name and ID.
 * @author Desiree Leal, Hoang Truong, Rohit Yeast
 * @since April 16, 2020
 * @version 1.8
 */
public abstract class Account {

	/**
	 * Name of the account holder
	 */
	protected String name;
	/**
	 * ID of the account holder
	 */
	protected int id;
	
	/**
	 * Constructor initializes name and ID
	 * @param n name
	 * @param i ID
	 */
	protected Account(String n, int i) {
		name = n;
		id = i;
	}
	
	/**
	 * Returns name of account holder
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the ID of account holder
	 * @return ID
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * sets the name of the account holder
	 * @param n name
	 */
	public void setName(String n) {
		name = n;
	}
	
	/**
	 * Sets the ID of the account holder
	 * @param i ID
	 */
	public void setId(int i) {
		id = i;
	}
	
	
}
