package lab3;

import java.util.ArrayList;

/**
 * This class holds all the data regarding a Supplier to the Shop.
 * @author Desiree Leal
 * @version 1.0
 * @since February 11, 2020
 */
public class Supplier {

	/**
	 * The identification number of the supplier.
	 */
	private int supID;
	
	/**
	 * The name of the supplier.
	 */
	private String supName;
	
	/**
	 * The address of the supplier.
	 */
	private String address;
	
	/**
	 * The contact of the supplier.
	 */
	private String salesContact;
	
	/**
	 * A list of the items that the supplier supplied.
	 */
	private ArrayList <Item> itemList;
	
	/**
	 * Constructs and empty Supplier.
	 */
	public Supplier () {
		
	}
	
	/**
	 * Constructs a Supplier and initializes all of its data fields with parameters.
	 * @param id the new ID number of the supplier.
	 * @param name the new name of the supplier.
	 * @param addy the new address of the supplier.
	 * @param contact the new contact of the supplier.
	 */
	public Supplier (int id, String name, String addy, String contact) {
		this.supID = id;
		this.supName = name;
		this.address = addy;
		this.salesContact = contact;
	}
	
	/**
	 * Gets the list of items supplied by the supplier.
	 * @return the list of items supplied by the supplier.
	 */
	public ArrayList <Item> getItemList() {
		return itemList;
	}

	/**
	 * Gets the ID number of supplier.
	 * @return the supplier ID number.
	 */
	public int getSupID() {
		return supID;
	}

	/**
	 * Gets the name of the Supplier.
	 * @return the name of the Supplier.
	 */
	public String getSupName () {
		return supName;
	}
}
