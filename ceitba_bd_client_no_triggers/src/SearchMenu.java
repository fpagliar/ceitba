import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class SearchMenu extends JMenu{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SearchMenu(final JFrame window, final CeitbaConnection con){
		super("Buscar usuario");
		setMnemonic('B');

		JMenuItem legacy = new JMenuItem("Por Legajo");
		legacy.setMnemonic('L');
		legacy.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{ 
						new SearchLegacyWindow(window, con);
					}
				}
				);
		add(legacy);

		JMenuItem name = new JMenuItem("Por Nombre");
		name.setMnemonic('N');
		name.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{ 
						new SearchNameWindow(window, con);
					}
				}
				);
		add(name);
	}

}
