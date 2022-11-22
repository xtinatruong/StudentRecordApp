package serverModel;

/**
 * 
 * @author Desiree Leal, Hoang Truong, Rohit Yeast
 * @since 
 * @version 
 */
public abstract class Account {

	protected String name;
	protected int id;
	
	protected Account(String n, int i) {
		name = n;
		id = i;
	}
	
	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public void setId(int i) {
		id = i;
	}
	
	
}
