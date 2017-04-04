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

	private JFrame frame = new JFrame(); //Serves as the "root" window
	private JPanel mainScreen = new JPanel(); //Put this on frame
	
	
	private JPanel logo = new CTLogo();
	private JPanel mainMenu = new JPanel();
	//private SalesBarManual sbm = new SalesBarManual();
	//You're not going to transition from the main menu directly to the sales bar manual...
	private JPanel managerMenu = new JPanel();  //Will ultimately be a class?
	private JPanel salesBarMenu = new JPanel(); //Will ultimately be a class?
	private JPanel technicianMenu = new JPanel(); //Will ultimately be a class?
	
	
	
	
	private JButton managerBtn = new JButton("Manager");
	private JButton salesBarBtn = new JButton("Sales Bar");
	private JButton technicianBtn = new JButton("Technician");
	
	
	private CardLayout mainScreenCl = new CardLayout(); 
	
	
	public CTHandbook() {
		//For proper looking buttons:
		try { UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() ); } 
		catch (Exception e) { e.printStackTrace(); }
		
		
		
		
		mainScreen.setLayout(mainScreenCl);
		//Now put a JPanel onto mainScreen that will have a MigLayout	
		mainScreen.add(mainMenu, "main_menu");
		//mainScreen.add(sbm, "sales_bar_manual"); 
		//You're not going to transition from the main menu directly to the sales bar manual...
		mainScreen.add(managerMenu, "manager_menu");
		mainScreen.add(salesBarMenu, "sales_bar_menu");
		mainScreen.add(technicianMenu, "technician_menu");
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
		//. The first 50% after "align" establishes horizontal centering and the second 50% establishes vertical centering
		//   for the group of all components.
		//. "gapy" establishes vertical spacing between components
		
		mainMenu.add(logo, "wrap, center");		
		//mainMenu.add(managerBtn);
		//mainMenu.add(salesBarBtn);
		//mainMenu.add(techBtn);
		//^Adding the buttons like this looks like crap. Use a button area...
		
		JPanel btnArea = new JPanel();
		btnArea.setBackground(Color.WHITE);
		btnArea.setLayout(new MigLayout());
		btnArea.add(managerBtn);
		btnArea.add(salesBarBtn);
		btnArea.add(technicianBtn);
		mainMenu.add(btnArea);
		
		
		// ################## FOCUS HERE!!!!!!!! ##################
		
		
		// === MANAGER MENU CONFIGRATIONS ===
		managerMenu.setBackground(Color.WHITE);
		managerMenu.setLayout(new MigLayout());
		createMenuHeader("MANAGER OPERATIONS", managerMenu);
		//. Perhaps a ManagerOps constructor could take header name and an arraylist of buttons. You would add this ManagerOps
		//    object to mainScreen. 
		//. ManagerOps can inherit from an OpsMenu. OpsMenu gives a general layout and ManagerOps will fill out the specifics.
		
		JPanel managerBtnArea = new JPanel();
		managerBtnArea.setBackground(Color.WHITE);
		managerBtnArea.setLayout(new MigLayout("align 50%"));
		
		/*
		//Testing
		managerBtnArea.add(new JButton("1. OPENING STORE CHECKLIST "), "wrap, width " + String.valueOf(700/1.5) + ", height " + String.valueOf(300/10));
		managerBtnArea.add(new JButton("HELLO"), "wrap");
		managerBtnArea.add(new JButton("HELLO"), "wrap");
		managerBtnArea.add(new JButton("HELLO"), "wrap");
		managerBtnArea.add(new JButton("HELLO"), "wrap");
		*/
		
		JScrollPane managerBtnAreaScroll = new JScrollPane(managerBtnArea);
		managerMenu.add(managerBtnAreaScroll, "push, grow, wrap");
		
		
		
		
		
		
		
		
		// ===  SALES BAR MENU CONFIGRATIONS ===
		salesBarMenu.setBackground(Color.WHITE);
		salesBarMenu.setLayout(new MigLayout());
		createMenuHeader("SALES BAR OPERATIONS", salesBarMenu);
		
		
		
		
		
		
		
		
		
		// === TECHNICIAN MENU CONFIGRATIONS ===
		technicianMenu.setBackground(Color.WHITE);
		technicianMenu.setLayout(new MigLayout());
		createMenuHeader("TECHNICIAN OPERATIONS", technicianMenu);
		
		
		
		
		
		
		
		
		// ################## FOCUS HERE!!!!!!!!!! ##################

		
		
		// === JFRAME CONFIGRATIONS ===
		frame.setResizable(false); //Resizing destroys the layout. Disable for now.
	    frame.getContentPane().setPreferredSize(new Dimension(800, 500)); //HARD-CODED DIMENSIONS
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
	
	
	
	/**
	 * A helper method that creates the header for a content screen.
	 * @param headerTxt The text to put on the header
	 * @param panel The JPanel representing the content screen (must be set with MigLayout)
	 */
	private void createMenuHeader(String headerTxt, JPanel panel) {
		JPanel header = new JPanel();
		header.setBackground(Color.decode("0x026937"));
		header.setLayout(new MigLayout("align 50% 50%"));
		
		JLabel headerLbl = new JLabel(headerTxt); //String is variable
		headerLbl.setFont(new Font("Helvetica", Font.BOLD, 25));
		headerLbl.setForeground(Color.WHITE);
		
		JButton backBtn = new JButton("Back to Top Menu");
		configButton(backBtn);
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainScreenCl.show(mainScreen, "main_menu");
			}
		});
		
		header.add(headerLbl, "wrap"); //HARD-CODED VALUE
		header.add(backBtn, "align center");
		panel.add(header, "height 100, pushx, growx, wrap"); //HARD-CODED VALUE. Setting values for Header here.
	}
}
