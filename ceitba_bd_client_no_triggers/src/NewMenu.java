import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class NewMenu extends JMenu{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NewMenu(final JFrame window, final CeitbaConnection con){
		super("Nuevo");
		setMnemonic('N');

		JMenuItem alumno = new JMenuItem("Usuario");
		alumno.setMnemonic('U');
		alumno.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{ 
						new NewUserWindow(con);
					}
				}
				);
		add(alumno);

		JMenuItem curso = new JMenuItem("Curso");
		curso.setMnemonic('C');
		curso.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{ 
						new NewCourseWindow(con);
					}
				}
				);
		add(curso);

		JMenuItem deporte = new JMenuItem("Deporte");
		deporte.setMnemonic('D');
		deporte.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{ 
						new NewSportWindow(con);
					}
				}
				);
		add(deporte);

		JMenuItem servicio = new JMenuItem("Servicio");
		servicio.setMnemonic('S');
		servicio.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{ 
						new NewServiceWindow(con);
					}
				}
				);
		add(servicio);
		
		JMenuItem locker  = new JMenuItem("Locker");
		locker.setMnemonic('L');
		locker.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{ 
						new NewLockerWindow(con);
					}
				}
				);
		add(locker);
		
		JMenuItem payment  = new JMenuItem("Pago");
		payment.setMnemonic('P');
		payment.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{ 
						new NewPaymentWindow(con);
					}
				}
				);
		add(payment);
	}

}
