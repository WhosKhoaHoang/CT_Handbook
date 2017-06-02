import javax.swing.*;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.jdesktop.swingx.border.DropShadowBorder;
import net.miginfocom.swing.MigLayout;

//TODO: Consider changing hard-coded values!
//TODO: Establish Event Listener for selecting and copying text
//TODO: Add Event Listeners for keyboard navigation
//TODO: Find way to make Handbook editable and have instances of the software be able to check for these updates
//TODO: Change the program's desktop and dock (for Macs) icon
//TODO: Work on Technician Knowledge Base for Technician Operations
//TODO: Implement search functionality
//TODO: Allow manager to compare various prices for a certain item on Ebay and choose the best one
//TODO: Determine if it'd be beneficial each menu page should have a distinctive label, not just "ops_main_screen"
//TODO: Fix issue of not being able to put two buttons in a single JTextPane
//TODO: Allow a user to add new menu items + content for those menu items

//THINK: A search feature will just look through the TextEditor text of all MenuItemContents?


public class CTHandbook {
	final int PREF_WIDTH = 800;
	final int PREF_HEIGHT = 600;
	
	private JFrame frame = new JFrame(); //Serves as the "root" window
	private JPanel homeScreen = new JPanel(); //Put this on frame
	
	private JPanel logo = new CTLogo();
	private JLabel subtitle = new JLabel("DIGITAL HANDBOOK");
	private JPanel homeMenu = new JPanel();
	
	private JButton managerBtn = new JButton("Manager");
	private JButton salesBarBtn = new JButton("Sales Bar");
	private JButton technicianBtn = new JButton("Technician");
	
	private CardLayout homeScreenCl = new CardLayout(); 
	
	public CTHandbook() {
		frame.setTitle("CleverTech Handbook");
		
        DropShadowBorder shadow = new DropShadowBorder();
        shadow.setShadowColor(Color.BLACK);
        shadow.setShowLeftShadow(true);
        shadow.setShowRightShadow(true);
        shadow.setShowBottomShadow(true);
        shadow.setShowTopShadow(true);
        frame.getRootPane().setBorder(shadow);
        
		//For proper looking buttons:
		try { UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() ); } 
		catch (Exception e) { e.printStackTrace(); }
		
		homeScreen.setLayout(homeScreenCl);
		homeScreen.add(homeMenu, "home_menu");
		// ====== Perhaps pass a DBConnect object that each MenuItem uses to pull content from the database here to INITIALIZE
		//	      MENU ITEM CONTENTS? ======
		// - Ultimately, you could try loading the database version first? If the database connection fails...
		//    HMMMM, think: How to handle no database connection when you first start the program? How about during program execution?
		//    Perhaps you can have an argument that serves as a flag telling each specific MenuItem constructor (e.g., ClosingStoreCL) 
		//	  to call createTextEdit without a contentCategory string so that nothing gets initialized to the TextEditor...
		/*
		homeScreen.add(new ManagerOpsMenu(homeScreen, homeScreenCl), "manager_menu");
		homeScreen.add(new SalesBarOpsMenu(homeScreen, homeScreenCl), "sales_bar_menu");
		homeScreen.add(new TechnicianOpsMenu(homeScreen, homeScreenCl), "technician_menu");
		*/
		DBConnect connect = new DBConnect();
		homeScreen.add(new ManagerOpsMenu(homeScreen, homeScreenCl, connect), "manager_menu");
		homeScreen.add(new SalesBarOpsMenu(homeScreen, homeScreenCl, connect), "sales_bar_menu");
		homeScreen.add(new TechnicianOpsMenu(homeScreen, homeScreenCl, connect), "technician_menu");
		homeScreenCl.show(homeScreen, "main_menu");
		connect.close();
		
		// === CLEVERTECH LOGO CONFIGURATIONS ===
		logo.setBackground(Color.WHITE);
		
		
		// === DIGITAL HANDBOOK LABEL CONFIGURATIONS ===
		subtitle.setFont(new Font("Arial", Font.BOLD, 20));
		subtitle.setForeground(Color.decode("0x242424"));
		
		
		// === BUTTON CONFIGURATIONS ===
		configButton(managerBtn);
		configButton(salesBarBtn);
		configButton(technicianBtn);
		managerBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				homeScreenCl.show(homeScreen, "manager_menu");
			}
		});
		salesBarBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				homeScreenCl.show(homeScreen, "sales_bar_menu");
			}
		});
		technicianBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				homeScreenCl.show(homeScreen, "technician_menu");
			}
		});
		
		
		// === MAIN MENU CONFIGURATIONS ===
		homeMenu.setBackground(Color.WHITE);
		homeMenu.setLayout(new MigLayout("align 50% 50%, gapy 10"));
		homeMenu.add(logo, "wrap, center");		
		homeMenu.add(subtitle, "wrap, center");
		JPanel btnArea = new JPanel();
		btnArea.setBackground(Color.WHITE);
		btnArea.setLayout(new MigLayout());
		btnArea.add(managerBtn, "gapright 5");
		btnArea.add(salesBarBtn, "gapright 5");
		btnArea.add(technicianBtn);
		homeMenu.add(btnArea, "wrap, center");
		
		
		// === JFRAME CONFIGRATIONS ===
	    frame.getContentPane().setPreferredSize(new Dimension(PREF_WIDTH, PREF_HEIGHT)); //HARD-CODED DIMENSIONS
		//Boiler-plate Code:
		frame.add(homeScreen);
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
		btn.setFont(new Font("Arial", Font.BOLD, 15));
		btn.setMargin(new Insets(7, 7, 7, 7)); //Perhaps inset values should NOT be hard coded?
		btn.setForeground(Color.WHITE);
		btn.setBackground(Color.decode("0x026937"));
		btn.setOpaque(true); 
		//Need to say this so that the button's color shows up and you don't get a whacky
		//silver button on top of a colored square.
	}

}
