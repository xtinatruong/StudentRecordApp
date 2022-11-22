package clientView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 * Listens for when the user presses a button, requests data from the frame
 * and displays the result from the frame on a GUI panel.
 * @author Desiree Leal, Hoang Truong, Rohit Yeast
 * @since April 16, 2020
 * @version 1.8
 */
public class ViewCatalogueListener implements ActionListener {

	/**
	 * The frame that created the listener.
	 */
	private RequestData frame;
	
	/**
	 * Creates a new ViewCatalogueListener and initializes the frame.
	 * @param f The frame that created the listener.
	 */
	public ViewCatalogueListener(RequestData f) {
		this.frame = f;
	}
	
	/**
	 * Requests data from the frame with user input and displays the results.
	 * @param e The even listened for.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		showData(frame.requestData("4"));
	}
	
	private void showData(String str) {
				
		JFrame frame = new JFrame();
		frame.setSize(300, 300);
		
		frame.add("North", new JLabel("Course Catalogue", SwingConstants.CENTER));
		
		JTextArea textArea = new JTextArea();
		JScrollPane scroll = new JScrollPane(textArea);
		frame.add("Center", scroll);
		textArea.setText(str);
		frame.setVisible(true);
		
	}
	
}
