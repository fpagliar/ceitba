import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class SubscriptionMenu extends JMenu{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SubscriptionMenu(final JFrame window, final CeitbaConnection con){
		super("Inscribir");
		setMnemonic('I');

		JMenuItem alumno = new JMenuItem("Ceitba");
		alumno.setMnemonic('C');
		alumno.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{ 
						new SubscribeCeitbaWindow(con);
					}
				}
				);
		add(alumno);

		JMenuItem curso = new JMenuItem("Servicio (deporte, curso, etc)");
		curso.setMnemonic('S');
		curso.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{ 
						new SubscribeServiceWindow(con);
					}
				}
				);
		add(curso);
		
		JMenuItem locker = new JMenuItem("Locker");
		locker.setMnemonic('L');
		locker.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{ 
						new SubscribeLockerWindow(con);
					}
				}
				);
		add(locker);
	}
}
