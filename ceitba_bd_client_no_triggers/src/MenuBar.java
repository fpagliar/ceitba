import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class MenuBar extends JMenuBar{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MenuBar(final JFrame window, final CeitbaConnection con){

		add(new SearchMenu(window, con));
		add(new NewMenu(window, con));
		add(new SeeMenu(window, con));
		add(new SubscriptionMenu(window, con));
		add(new UnsubscriptionMenu(window, con));
		
		JMenuItem payments = new JMenuItem("Facturacion");
		payments.setMnemonic('F');
		payments.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{ 
						String[] columnNames = {"legacy", "first_name", "last_name", "name", "price"};
						String queryString = "SELECT users.legacy, users.first_name, users.last_name, services.name, services.price FROM subscriptions AS s JOIN services ON s.service_id = services.id JOIN users ON s.user_id = users.id ORDER BY users.legacy";
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
		add(payments);
	}

}
