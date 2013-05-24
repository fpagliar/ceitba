import java.awt.*; // using AWT containers and components
import java.awt.event.*; // using AWT events and listener interfaces
import java.util.Date;

import javax.swing.JFrame;

public class AltasServicio extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private TextField startD;
	private TextField startM;
	private TextField startY;
	private TextField endD;
	private TextField endM;
	private TextField endY;
	private TextField name;
	private JFrame window;
	private CeitbaConnection connection;

	/** Constructor to setup the GUI */
	public AltasServicio(JFrame window, CeitbaConnection con) {
		this.connection = con;
		this.window = window;
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// "this" Frame sets layout to FlowLayout, which arranges the components
		// from left-to-right, and flow to next row from top-to-bottom.
		Label fieldLabel7 = new Label("Nombre del servicio: "); // construct // Label
		add(fieldLabel7); // "this" Frame adds Label
		this.name = new TextField(30); // construct TextField
		add(name); // "this" Frame adds TextField

		Label fieldLabel = new Label("Comienzo: "); // construct // Label
		add(fieldLabel); // "this" Frame adds Label

		this.startD = new TextField(2); // construct TextField
		add(startD); // "this" Frame adds TextField
		Label fieldLabel3 = new Label("/"); // construct // Label
		add(fieldLabel3); // "this" Frame adds Label
		this.startM = new TextField(2); // construct TextField
		add(startM); // "this" Frame adds TextField
		Label fieldLabel4 = new Label("/"); // construct // Label
		add(fieldLabel4); // "this" Frame adds Label
		this.startY = new TextField(4); // construct TextField
		add(startY); // "this" Frame adds TextField
		startD.addActionListener(this);
		startM.addActionListener(this);
		startY.addActionListener(this);

		Label fieldLabel2 = new Label("Fin: "); // construct // Label
		add(fieldLabel2); // "this" Frame adds Label

		this.endD = new TextField(2); // construct TextField
		add(endD); // "this" Frame adds TextField
		Label fieldLabel5 = new Label("/"); // construct // Label
		add(fieldLabel5); // "this" Frame adds Label
		this.endM = new TextField(2); // construct TextField
		add(endM); // "this" Frame adds TextField
		Label fieldLabel6 = new Label("/"); // construct // Label
		add(fieldLabel6); // "this" Frame adds Label
		this.endY = new TextField(4); // construct TextField
		add(endY); // "this" Frame adds TextField
		endD.addActionListener(this);
		endM.addActionListener(this);
		endY.addActionListener(this);

		setSize(420, 120); // "this" Frame sets initial window size
		setVisible(true); // "this" Frame shows
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		setVisible(false);
		String[] columnNames = { "legacy", "first_name", "last_name", "name",
				"price", "date" };
		String startD = this.startD.getText();
		String startM = this.startM.getText();
		String startY = this.startY.getText();
		String endD = this.endD.getText();
		String endM = this.endM.getText();
		String endY = this.endY.getText();
		String name = this.name.getText();

		if ( ! name.matches("[a-z\\s]+")){
			new ErrorWindow("Nombre invalido, solo puede contener letras y espacios");
			return;
		}
		
		if (!(startD.matches("[0-9][0-9]") && startM.matches("[0-9][0-9]")
				&& endD.matches("[0-9][0-9]") && endM.matches("[0-9][0-9]")
				&& startY.matches("[0-9][0-9][0-9][0-9]") && endY
					.matches("[0-9][0-9][0-9][0-9]"))) {
			new ErrorWindow(
					"Fecha invalida, tiene que estar en formato dd/mm/aaaa");
			return;
		}
		String queryString = "SELECT users.legacy, users.first_name, users.last_name, services.name, services.price, subscriptions.date "
				+ "FROM subscriptions JOIN services ON subscriptions.service_id = services.id JOIN users ON subscriptions.user_id = users.id "
				+ "WHERE services.name='" + name + "' and subscriptions.date >= '"
				+ startY
				+ "-"
				+ startM
				+ "-"
				+ startD
				+ "'::date AND subscriptions.date <= '"
				+ endY
				+ "-"
				+ endM
				+ "-" + endD + "'::date " + "ORDER BY users.legacy ";
		Object[][] results = connection.executeSelectQuery(queryString,
				columnNames);

		TableData output = new TableData(columnNames, results);
		try{
			Date date = new Date();
			ExcelExport export = new ExcelExport(date.getDay() + "-" + date.getMonth() + "-" + date.getYear() + "_Altas_entre_" + startD + "-" + startM + "-" + startY + "_y_" + endD + "-" + endM + "-" + endY + "_de_" + name);
			export.createExcel(output);
		}catch(Exception e){
			new ErrorWindow("Error al cargar los datos en el archivo!");
		}
		CeitbaWindow newContentPane = new CeitbaWindow(new TableData());
		if (output != null) {
			newContentPane = new CeitbaWindow(output);
		}
		newContentPane.setOpaque(true); // content panes must be opaque
		window.setContentPane(newContentPane);
		window.pack();
		window.setVisible(true);
	};
}
