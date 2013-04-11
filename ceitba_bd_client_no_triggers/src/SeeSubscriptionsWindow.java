import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;


public class SeeSubscriptionsWindow extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextField legacyField;
	private TextField serviceField;
	private JFrame window;
	private JButton submitButton;
	private CeitbaConnection connection;

	/** Constructor to setup the GUI */
	public SeeSubscriptionsWindow(JFrame window, CeitbaConnection con) {
		this.connection = con;
		this.window = window;
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// "this" Frame sets layout to FlowLayout, which arranges the components
		//  from left-to-right, and flow to next row from top-to-bottom.

		JRadioButton legacyRButton = new JRadioButton("Legajo:");
		legacyRButton.setMnemonic(KeyEvent.VK_L);
		legacyRButton.setActionCommand("legacy");
		legacyRButton.addActionListener(this);
		legacyRButton.setToolTipText("Buscar todas las subscripciones para un usuario");
		add(legacyRButton);

		//		Label fieldLabel = new Label("Numero de legajo: "); // construct Label
		//		add(fieldLabel);               // "this" Frame adds Label

		this.legacyField = new TextField(30); // construct TextField
		add(legacyField);                // "this" Frame adds TextField
		legacyField.addActionListener(this);
		legacyField.setEnabled(false);

		JRadioButton serviceRButton = new JRadioButton("Nombre del servicio:");
		serviceRButton.setMnemonic(KeyEvent.VK_C);
		serviceRButton.setActionCommand("service");
		serviceRButton.addActionListener(this);
		serviceRButton.setToolTipText("Buscar todos los usuarios subscriptos a un servicio");
		add(serviceRButton);

		this.serviceField = new TextField(30); // construct TextField
		add(serviceField);                // "this" Frame adds TextField
		serviceField.addActionListener(this);
		serviceField.setEnabled(false);

		ButtonGroup group = new ButtonGroup();
		group.add(legacyRButton);
		group.add(serviceRButton);

		this.submitButton = new JButton("Submit");
		submitButton.setMnemonic(KeyEvent.VK_ENTER);
		//	    submitButton.setActionCommand("submit");
		submitButton.addActionListener(this);
		submitButton.setEnabled(false);
		add(submitButton);

		setSize(450, 130);  // "this" Frame sets initial window size
		setVisible(true);   // "this" Frame shows
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand() == "legacy"){
			legacyField.setEnabled(true);
			serviceField.setEnabled(false);
			serviceField.setText("");
			submitButton.setEnabled(true);
		}else if(arg0.getActionCommand() == "service"){
			legacyField.setEnabled(false);
			legacyField.setText("");
			serviceField.setEnabled(true);
			submitButton.setEnabled(true);
		}else{
			setVisible(false);
			if(legacyField.isEnabled()){
				String[] columnNames = {"id", "name", "legacy", "first_name", "last_name", "price"};
				Integer legacy = 0;
				try{
					legacy = Integer.parseInt(legacyField.getText()); 
				}catch(Exception e){
					new ErrorWindow("Numero de legajo invalido!");
					return;
				}
				String queryString = "SELECT id, name, legacy, first_name, last_name, price FROM subscriptions_view WHERE legacy = " + legacy;
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
			}else if(serviceField.isEnabled()){
				String[] columnNames = {"legacy", "first_name", "last_name"};
				String service = serviceField.getText().toLowerCase();
				if ( ! service.matches("[a-z\\s]+") ){
					new ErrorWindow("Servicio invalido, solo puede contener letras y espacios");
					return;
				}
				service = "'" + service + "'";
				String queryString = "SELECT legacy, first_name, last_name FROM subscriptions JOIN users ON subscriptions.user_id = users.id WHERE subscriptions.service_id = (SELECT id FROM services WHERE name = " + service + ")";
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
			}else{
				return;
			}
		}
	};
}
