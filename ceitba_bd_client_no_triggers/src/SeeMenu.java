import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class SeeMenu extends JMenu{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SeeMenu(final JFrame window, final CeitbaConnection con){
		super("Ver");
		setMnemonic('V');

		JMenuItem alumno = new JMenuItem("Usuarios");
		alumno.setMnemonic('U');
		alumno.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{ 						
						String[] columnNames = {"legacy", "first_name", "last_name", "phone", "cellphone", "email"};
						String queryString = "SELECT legacy, first_name, last_name, phone, cellphone, email from users order by legacy";
//						SqlQuery query = new SqlSelectQuery(queryString, columnNames, con);
//						TableData output = query.run();
						Object[][] results = con.executeSelectQuery(queryString, columnNames);

						TableData output = new TableData(columnNames, results);

						CeitbaWindow newContentPane = new CeitbaWindow(new TableData());
						if (output != null){
							newContentPane = new CeitbaWindow(output);
						}
						newContentPane.setOpaque(true); //content panes must be opaque
						window.setContentPane(newContentPane);
						window.pack();
						window.setVisible(true);
					}
				}
				);
		add(alumno);

		JMenuItem curso = new JMenuItem("Cursos");
		curso.setMnemonic('C');
		curso.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{ 
						String[] columnNames = {"id", "name", "description", "duration", "price"};
						String queryString = "SELECT id, name, description, duration, price FROM courses_view";
						Object[][] results = con.executeSelectQuery(queryString, columnNames);

						TableData output = new TableData(columnNames, results);
						CeitbaWindow newContentPane = new CeitbaWindow(new TableData());
						if (output != null){
							newContentPane = new CeitbaWindow(output);
						}
						newContentPane.setOpaque(true); //content panes must be opaque
						window.setContentPane(newContentPane);
						window.pack();
						window.setVisible(true);
					}
				}
				);
		add(curso);

		JMenuItem deporte = new JMenuItem("Deportes");
		deporte.setMnemonic('D');
		deporte.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{ 
						String[] columnNames = {"id", "name", "description", "duration", "price"};
						String queryString = "SELECT id, name, description, duration, price FROM sports_view";
						Object[][] results = con.executeSelectQuery(queryString, columnNames);

						TableData output = new TableData(columnNames, results);
						CeitbaWindow newContentPane = new CeitbaWindow(new TableData());
						if (output != null){
							newContentPane = new CeitbaWindow(output);
						}
						newContentPane.setOpaque(true); //content panes must be opaque
						window.setContentPane(newContentPane);
						window.pack();
						window.setVisible(true);					}
				}
				);
		add(deporte);

		JMenuItem servicio = new JMenuItem("Servicios");
		servicio.setMnemonic('S');
		servicio.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{ 
						String[] columnNames = {"id", "name", "description", "duration", "price"};
						String queryString = "SELECT id, name, description, duration, price FROM other_services_view";
						Object[][] results = con.executeSelectQuery(queryString, columnNames);

						TableData output = new TableData(columnNames, results);
						CeitbaWindow newContentPane = new CeitbaWindow(new TableData());
						if (output != null){
							newContentPane = new CeitbaWindow(output);
						}
						newContentPane.setOpaque(true); //content panes must be opaque
						window.setContentPane(newContentPane);
						window.pack();
						window.setVisible(true);					}
				}
				);
		add(servicio);

		JMenuItem freeLocker = new JMenuItem("Lockers libres");
		freeLocker.setMnemonic('L');
		freeLocker.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{ 
						String[] columnNames = {"id", "condition"};
						String queryString = "SELECT id, condition FROM lockers WHERE owner_id is NULL ORDER BY id";
						Object[][] results = con.executeSelectQuery(queryString, columnNames);

						TableData output = new TableData(columnNames, results);
						CeitbaWindow newContentPane = new CeitbaWindow(new TableData());
						if (output != null){
							newContentPane = new CeitbaWindow(output);
						}
						newContentPane.setOpaque(true); //content panes must be opaque
						window.setContentPane(newContentPane);
						window.pack();
						window.setVisible(true);					}
				}
				);
		add(freeLocker);

		JMenuItem locker = new JMenuItem("Lockers usados");
		locker.setMnemonic('L');
		locker.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{ 
						String[] columnNames = {"id", "legacy", "first_name", "last_name", "condition"};
						String queryString = "SELECT id, legacy, first_name, last_name, condition FROM lockers_subscriptions ORDER BY id";
						Object[][] results = con.executeSelectQuery(queryString, columnNames);

						TableData output = new TableData(columnNames, results);
						CeitbaWindow newContentPane = new CeitbaWindow(new TableData());
						if (output != null){
							newContentPane = new CeitbaWindow(output);
						}
						newContentPane.setOpaque(true); //content panes must be opaque
						window.setContentPane(newContentPane);
						window.pack();
						window.setVisible(true);					}
				}
				);
		add(locker);

		JMenuItem payments = new JMenuItem("Pagos");
		payments.setMnemonic('P');
		payments.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{ 
						String[] columnNames = {"legacy", "ammount", "date"};
						String queryString = "SELECT legacy, ammount, date FROM payments_view";
						Object[][] results = con.executeSelectQuery(queryString, columnNames);

						TableData output = new TableData(columnNames, results);
						CeitbaWindow newContentPane = new CeitbaWindow(new TableData());
						if (output != null){
							newContentPane = new CeitbaWindow(output);
						}
						newContentPane.setOpaque(true); //content panes must be opaque
						window.setContentPane(newContentPane);
						window.pack();
						window.setVisible(true);					}
				}
				);
		add(payments);
		
		JMenuItem subscriptions = new JMenuItem("Subscripciones");
		subscriptions.setMnemonic('S');
		subscriptions.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{ 
						new SeeSubscriptionsWindow(window, con);
						//						String[] columnNames = {"id", "name", "legacy", "first_name", "last_name", "price"};
//						String queryString = "SELECT id, name, legacy, first_name, last_name, price FROM subscriptions_view";
//						SqlQuery query = new SqlSelectQuery(queryString, columnNames, con);
//						TableData output = query.run();
//						CeitbaWindow newContentPane = new CeitbaWindow(new TableData());
//						if (output != null){
//							newContentPane = new CeitbaWindow(output);
//						}
//						newContentPane.setOpaque(true); //content panes must be opaque
//						window.setContentPane(newContentPane);
//						window.pack();
//						window.setVisible(true);					
						}
				}
				);
		add(subscriptions);

	}

}
