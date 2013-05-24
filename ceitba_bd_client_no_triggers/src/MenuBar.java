import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MenuBar(final JFrame window, final CeitbaConnection con) {

		add(new SearchMenu(window, con));
		add(new NewMenu(window, con));
		add(new SeeMenu(window, con));
		add(new SubscriptionMenu(window, con));
		add(new UnsubscriptionMenu(window, con));

		JMenuItem ceitba = new JMenuItem("F-CEITBA");
		ceitba.setMnemonic('C');
		ceitba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] columnNames = { "legacy", "first_name", "last_name",
						"name", "price" };
				String queryString = "SELECT users.legacy, users.first_name, users.last_name, services.name, services.price"
						+ " FROM subscriptions AS s JOIN services ON s.service_id = services.id JOIN users ON s.user_id = users.id "
						+ "WHERE services.name = 'ceitba'"
						+ "ORDER BY users.legacy ";
				Object[][] results = con.executeSelectQuery(queryString,
						columnNames);
				TableData output = new TableData(columnNames, results);

				CeitbaWindow newContentPane = new CeitbaWindow(new TableData());
				if (output != null) {
					newContentPane = new CeitbaWindow(output);
				}
				newContentPane.setOpaque(true); // content panes must be opaque
				window.setContentPane(newContentPane);
				window.pack();
				window.setVisible(true);
			}
		});
		add(ceitba);

		JMenuItem ymca = new JMenuItem("F-YMCA");
		ymca.setMnemonic('Y');
		ymca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] columnNames = { "legacy", "first_name", "last_name",
						"name", "price" };
				String queryString = "SELECT users.legacy, users.first_name, users.last_name, services.name, services.price"
						+ " FROM subscriptions AS s JOIN services ON s.service_id = services.id JOIN users ON s.user_id = users.id "
						+ "WHERE services.name = 'ymca'"
						+ "ORDER BY users.legacy ";
				Object[][] results = con.executeSelectQuery(queryString,
						columnNames);
				TableData output = new TableData(columnNames, results);

				CeitbaWindow newContentPane = new CeitbaWindow(new TableData());
				if (output != null) {
					newContentPane = new CeitbaWindow(output);
				}
				newContentPane.setOpaque(true); // content panes must be opaque
				window.setContentPane(newContentPane);
				window.pack();
				window.setVisible(true);
			}
		});
		add(ymca);

		JMenuItem servicios = new JMenuItem("F-Otros Servicios");
		servicios.setMnemonic('O');
		servicios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] columnNames = { "legacy", "first_name", "last_name",
						"name", "price" };
				String queryString = "SELECT users.legacy, users.first_name, users.last_name, services.name, services.price"
						+ " FROM subscriptions AS s JOIN services ON s.service_id = services.id JOIN users ON s.user_id = users.id "
						+ "WHERE services.name!='ceitba' AND services.name!='ymca'"
						+ "ORDER BY users.legacy ";
				Object[][] results = con.executeSelectQuery(queryString,
						columnNames);
				TableData output = new TableData(columnNames, results);

				CeitbaWindow newContentPane = new CeitbaWindow(new TableData());
				if (output != null) {
					newContentPane = new CeitbaWindow(output);
				}
				newContentPane.setOpaque(true); // content panes must be opaque
				window.setContentPane(newContentPane);
				window.pack();
				window.setVisible(true);
			}
		});
		add(servicios);

		JMenuItem altas = new JMenuItem("F-Altas");
		altas.setMnemonic('A');
		altas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AltasServicio(window, con);
			}
		});
		add(altas);

		JMenuItem bajas = new JMenuItem("F-Bajas");
		bajas.setMnemonic('B');
		bajas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BajasServicio(window, con);
			}
		});
		add(bajas);
		// altas.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent e) {
		// String[] columnNames = { "legacy", "first_name", "last_name",
		// "name", "price" };
		// String queryString =
		// "SELECT users.legacy, users.first_name, users.last_name, services.name, services.price "
		// +
		// "FROM subscriptions AS s JOIN services ON s.service_id = services.id JOIN users ON s.user_id = users.id "
		// +
		// "WHERE service.name='ceitba' and s.date - " +
		// "ORDER BY users.legacy ";
		// Object[][] results = con.executeSelectQuery(queryString,
		// columnNames);
		// TableData output = new TableData(columnNames, results);
		//
		// CeitbaWindow newContentPane = new CeitbaWindow(new TableData());
		// if (output != null) {
		// newContentPane = new CeitbaWindow(output);
		// }
		// newContentPane.setOpaque(true); // content panes must be opaque
		// window.setContentPane(newContentPane);
		// window.pack();
		// window.setVisible(true);
		// }
		// });
		// add(altas);

		// JMenuItem remo = new JMenuItem("F-Remo");
		// altas.setMnemonic('A');
		// altas.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent e) {
		// String[] columnNames = { "legacy", "first_name", "last_name",
		// "name", "price" };
		// String queryString =
		// "SELECT users.legacy, users.first_name, users.last_name, services.name, services.price "
		// +
		// "FROM subscriptions AS s JOIN services ON s.service_id = services.id JOIN users ON s.user_id = users.id "
		// +
		// "WHERE service.name='ceitba' and s.date - " +
		// "ORDER BY users.legacy ";
		// Object[][] results = con.executeSelectQuery(queryString,
		// columnNames);
		// TableData output = new TableData(columnNames, results);
		//
		// CeitbaWindow newContentPane = new CeitbaWindow(new TableData());
		// if (output != null) {
		// newContentPane = new CeitbaWindow(output);
		// }
		// newContentPane.setOpaque(true); // content panes must be opaque
		// window.setContentPane(newContentPane);
		// window.pack();
		// window.setVisible(true);
		// }
		// });
		// add(altas);
	}

}