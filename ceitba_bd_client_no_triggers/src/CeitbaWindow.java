/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
  
/*
 * SimpleTableDemo.java requires no other files.
 */

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CeitbaWindow extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CeitbaWindow(TableData data) {
		super(new GridLayout(1,0));

		final JTable table = new JTable(data.getInfo(), data.getColumnNames());
		table.setPreferredScrollableViewportSize(new Dimension(1000, 200));
		table.setFillsViewportHeight(true);
		table.setEnabled(false); // to avoid editing cells

		//Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);

		//Add the scroll pane to this panel.
		add(scrollPane);

	}

	/**
	 * Create the GUI and show it.  For thread safety,
	 * this method should be invoked from the
	 * event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		//Create and set up the main window.
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		CeitbaConnection con = new CeitbaConnection("fpagliar", "crhtxb7o");
		CeitbaConnection con = new CeitbaConnection("fpagliar", "36806557");

		//Create and set up the content pane.
		CeitbaWindow newContentPane = new CeitbaWindow(new TableData()); //the initial table has no data
		newContentPane.setOpaque(true); //content panes must be opaque
		frame.setContentPane(newContentPane); //sets the empty table

		JMenuBar bar = new MenuBar(frame, con);
		frame.setJMenuBar(bar);

		//Display the window.
		frame.pack();
		frame.setVisible(true);


	}

	public static void main(String[] args) {
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		try{
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					createAndShowGUI();
				}
			});
		}catch(Exception e){
			try{
				FileWriter fstream = new FileWriter("cetiba_db_error_log.txt");
				BufferedWriter out = new BufferedWriter(fstream);
				out.write("\n\n ERROR FOUND AT: " + new Date() + "\n");
				out.close();
				FileOutputStream fos = new FileOutputStream(new File("cetiba_db_error_log.txt"), true);  
				PrintStream ps = new PrintStream(fos);  
				e.printStackTrace(ps);
				ps.close();
				fos.close();
			}catch(Exception e1){
				System.exit(ABORT);
			}
		}
	}
}