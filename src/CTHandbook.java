import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

public class CTHandbook {
	final int PREF_WIDTH = 800;
	final int PREF_HEIGHT = 500;
	
	private JFrame frame = new JFrame(); //Serves as the "root" window
	private JPanel mainScreen = new JPanel(); //Put this on frame
	
	private JPanel logo = new CTLogo();
	private JPanel mainMenu = new JPanel();
	
	private JButton managerBtn = new JButton("Manager");
	private JButton salesBarBtn = new JButton("Sales Bar");
	private JButton technicianBtn = new JButton("Technician");
	
	private CardLayout mainScreenCl = new CardLayout(); 
	
	
	public CTHandbook() {
		//For proper looking buttons:
		try { UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() ); } 
		catch (Exception e) { e.printStackTrace(); }
		
		mainScreen.setLayout(mainScreenCl);
		mainScreen.add(mainMenu, "main_menu");
		mainScreen.add(new ManagerOps(mainScreen, mainScreenCl), "manager_menu");
		mainScreen.add(new SalesBarOps(mainScreen, mainScreenCl), "sales_bar_menu");
		mainScreen.add(new TechnicianOps(mainScreen, mainScreenCl), "technician_menu");
		mainScreenCl.show(mainScreen, "main_menu");

		
		
		// === CLEVERTECH LOGO CONFIGURATIONS ===
		logo.setBackground(Color.WHITE);
		

		
		// === BUTTON CONFIGURATIONS ===
		configButton(managerBtn);
		configButton(salesBarBtn);
		configButton(technicianBtn);
		managerBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainScreenCl.show(mainScreen, "manager_menu");
			}
		});
		salesBarBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainScreenCl.show(mainScreen, "sales_bar_menu");
			}
		});
		technicianBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainScreenCl.show(mainScreen, "technician_menu");
			}
		});
		
		
		
		// === MAIN MENU CONFIGURATIONS ===
		mainMenu.setBackground(Color.WHITE);
		mainMenu.setLayout(new MigLayout("align 50% 50%, gapy 10"));
		mainMenu.add(logo, "wrap, center");		
		JPanel btnArea = new JPanel();
		btnArea.setBackground(Color.WHITE);
		btnArea.setLayout(new MigLayout());
		btnArea.add(managerBtn);
		btnArea.add(salesBarBtn);
		btnArea.add(technicianBtn);
		mainMenu.add(btnArea, "wrap");
		//3-Point Team???
		JButton tPt = new JButton("3-Point Team");
		mainMenu.add(tPt, "center");
		
		
		
		// === JFRAME CONFIGRATIONS ===
	    frame.getContentPane().setPreferredSize(new Dimension(PREF_WIDTH, PREF_HEIGHT)); //HARD-CODED DIMENSIONS
		//Boiler-plate Code:
		frame.add(mainScreen);
		frame.pack();
		//Be sure to write the following two lines AFTER you've packed the frame. Writing them before packing
		//doesn't center the GUI correctly!
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}
	
	
	
	/**
	 * A helper method that configures a Button for placement on the main menu.
	 * @param btn The button to be placed
	 */
	private void configButton(JButton btn) {
		btn.setFont(new Font("Arial", Font.BOLD, 13));
		btn.setMargin(new Insets(7, 7, 7, 7)); //Perhaps inset values should NOT be hard coded?
		btn.setForeground(Color.WHITE);
		btn.setBackground(Color.decode("0x026937"));
		btn.setOpaque(true); //Need to say this so that the button's color shows up
		
	}

}
