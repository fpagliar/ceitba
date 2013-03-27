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
						
					}
				}
				);
		add(payments);
	}

}
