import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class UnsubscriptionMenu extends JMenu{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnsubscriptionMenu(final JFrame window, final CeitbaConnection con){
		super("Desinscribir");
		setMnemonic('D');

		JMenuItem alumno = new JMenuItem("Ceitba");
		alumno.setMnemonic('C');
		alumno.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{ 
						new UnsubscribeCeitbaWindow(con);
					}
				}
				);
		add(alumno);

		JMenuItem service = new JMenuItem("Servicio (deporte, curso, etc)");
		service.setMnemonic('S');
		service.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{ 
						new UnsubscribeServiceWindow(con);
					}
				}
				);
		add(service);
		
		JMenuItem locker = new JMenuItem("Locker");
		locker.setMnemonic('L');
		locker.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{ 
						new UnsubscribeLockerWindow(con);
					}
				}
				);
		add(locker);
	}
}