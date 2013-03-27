import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;


public class NewUserWindow extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextField legacyField;
	private TextField nameField;
	private TextField lastNameField;
	private TextField emailField;
	private TextField phoneField;
	private TextField cellPhoneField;
	private CeitbaConnection connection;
	
	/** Constructor to setup the GUI */
	public NewUserWindow(CeitbaConnection con) {
		this.connection = con;
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// "this" Frame sets layout to FlowLayout, which arranges the components
		//  from left-to-right, and flow to next row from top-to-bottom.

		Label legacyLabel = new Label("Numero de legajo (Obligatorio): "); // construct Label
		add(legacyLabel);               // "this" Frame adds Label
		this.legacyField = new TextField(30); // construct TextField
		add(legacyField);                // "this" Frame adds TextField
		legacyField.addActionListener(this);
		
		Label nameLabel = new Label("Nombre: "); 
		add(nameLabel);               
		this.nameField = new TextField(30);
		add(nameField);                
		nameField.addActionListener(this);

		Label lastNameLabel = new Label("Apellido: "); 
		add(lastNameLabel);               
		this.lastNameField = new TextField(30);
		add(lastNameField);                
		lastNameField.addActionListener(this);

		Label emailLabel = new Label("Email: "); 
		add(emailLabel);               
		this.emailField = new TextField(30);
		add(emailField);                
		emailField.addActionListener(this);

		Label phoneLabel = new Label("Telefono: "); 
		add(phoneLabel);               
		this.phoneField = new TextField(30);
		add(phoneField);                
		phoneField.addActionListener(this);

		Label cellPhoneLabel = new Label("Celular: "); 
		add(cellPhoneLabel);               
		this.cellPhoneField = new TextField(30);
		add(cellPhoneField);                
		cellPhoneField.addActionListener(this);
		setSize(290, 400);  // "this" Frame sets initial window size
		setVisible(true);   // "this" Frame shows
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		setVisible(false);
		
		Integer legacy = 0;
		try{
			legacy = Integer.parseInt(legacyField.getText()); 
		}catch(Exception e){
			new ErrorWindow("Numero de Legajo invalido");
			return;
		}
		
		String email = emailField.getText().toLowerCase();
		String name = nameField.getText().toLowerCase();
		String lastName = lastNameField.getText().toLowerCase();
		String phone = phoneField.getText().toLowerCase();
		String cellPhone = cellPhoneField.getText().toLowerCase();
		
		if ( ! name.matches("[a-z\\s]*") ){
			new ErrorWindow("Nombre invalido");
			return;			
		}
		if (name.length() == 0){
			name = "NULL";
		}else{
			name = "'" + name + "'";
		}
			
		if ( ! lastName.matches("[a-z\\s]*") ){
			new ErrorWindow("Apellido invalido");
			return;			
		}
		if (name.length() == 0){
			lastName = "NULL";
		}else{
			lastName = "'" + lastName + "'";
		}

		if (email.length() == 0){
			email = "NULL";
		}else{
			if ( ! email.matches("[a-z0-9._%+-]+@[a-z.-]+") ){
				new ErrorWindow("Email invalido");
				return;			
			}
			email = "'" + email + "'";
		}
		
		if ( ! phone.matches("[0-9\\s-]*") ){
			new ErrorWindow("Telefono invalido");
			return;			
		}
		if (phone.length() == 0){
			phone = "NULL";
		}else{
			phone = "'" + phone + "'";
		}
		
		if ( ! cellPhone.matches("[0-9\\s-]*") ){
			new ErrorWindow("Celular invalido");
			return;			
		}
		if (cellPhone.length() == 0){
			cellPhone = "NULL";
		}else{
			cellPhone = "'" + cellPhone + "'";
		}
		String queryString = "INSERT INTO users(legacy, first_name, last_name, email, phone, cellphone) VALUES ("+ legacy + ", " + name + ", "+ lastName + ", " + email + ", " + phone + ", " + cellPhone + ")";
		SqlQuery query = new SqlInsertQuery(queryString, null, connection);
		query.run();
		
	};
}
