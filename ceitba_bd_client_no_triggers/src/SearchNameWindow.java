import java.awt.*;        // using AWT containers and components
import java.awt.event.*;  // using AWT events and listener interfaces

import javax.swing.JFrame;

public class SearchNameWindow extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextField textField;
	private JFrame window;
	private CeitbaConnection connection;
	
	/** Constructor to setup the GUI */
	public SearchNameWindow(JFrame window, CeitbaConnection con) {
		this.connection = con;
		this.window = window;
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// "this" Frame sets layout to FlowLayout, which arranges the components
		//  from left-to-right, and flow to next row from top-to-bottom.

		Label fieldLabel = new Label("Nombre o Apellido: "); // construct Label
		add(fieldLabel);               // "this" Frame adds Label

		this.textField = new TextField(30); // construct TextField
		add(textField);                // "this" Frame adds TextField
		textField.addActionListener(this);

		setSize(350, 120);  // "this" Frame sets initial window size
		setVisible(true);   // "this" Frame shows
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		setVisible(false);
		String[] columnNames = {"legacy", "first_name", "last_name", "phone", "cellphone", "email"};
		String name = textField.getText().toLowerCase();

		if ( ! name.matches("[a-z\\s]+")){
			new ErrorWindow("Nombre invalido, solo puede contener letras y espacios");
			return;
		}
		
		String queryString = "SELECT legacy, first_name, last_name, phone, cellphone, email FROM users WHERE first_name like '%" + name + "%' or last_name like '%" + name + "%'";

		Object[][] results = connection.executeSelectQuery(queryString, columnNames);

		TableData output = new TableData(columnNames, results);
		CeitbaWindow newContentPane = new CeitbaWindow(new TableData());
		if (output != null){
			newContentPane = new CeitbaWindow(output);
		}
		newContentPane.setOpaque(true); //content panes must be opaque
		window.setContentPane(newContentPane);
		window.pack();
		window.setVisible(true);		
	};
}