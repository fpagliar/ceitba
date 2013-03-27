import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;


public class NewPaymentWindow extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextField legacyField;
	private TextField ammountField;
	private CeitbaConnection connection;
	
	/** Constructor to setup the GUI */
	public NewPaymentWindow(CeitbaConnection con) {
		this.connection = con;
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		Label legacyLabel = new Label("Legajo (Obligatorio): "); // construct Label
		add(legacyLabel);               // "this" Frame adds Label
		this.legacyField = new TextField(30); // construct TextField
		add(legacyField);                // "this" Frame adds TextField
		legacyField.addActionListener(this);

		Label ammountLabel = new Label("Monto (Obligatorio): "); 
		add(ammountLabel);               
		this.ammountField = new TextField(30);
		add(ammountField);                
		ammountField.addActionListener(this);

		setSize(290, 200);  // "this" Frame sets initial window size
		setVisible(true);   // "this" Frame shows
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		setVisible(false);
		
		Double ammount = 0.0;
		try{
			ammount = Double.parseDouble(ammountField.getText()); 
		}catch(Exception e){
			new ErrorWindow("Monto invalido");
			return;
		}
		
		Integer legacy = 0;
		try{
			legacy = Integer.parseInt(legacyField.getText()); 
		}catch(Exception e){
			new ErrorWindow("Legajo invalido");
			return;
		}
		
		String queryString = "INSERT INTO payments(id, ammount) values ( (SELECT id FROM users WHERE legacy = "+ legacy + "), " + ammount + ");";
		SqlQuery query = new SqlInsertQuery(queryString, null, connection);
		query.run();		
	};
}