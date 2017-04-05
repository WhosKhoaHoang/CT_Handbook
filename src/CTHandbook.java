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

public class CTHandbook {
	final int PREF_WIDTH = 800;
	final int PREF_HEIGHT = 500;
	
	private JFrame frame = new JFrame(); //Serves as the "root" window
	private JPanel homeScreen = new JPanel(); //Put this on frame
	
	private JPanel logo = new CTLogo();
	private JPanel homeMenu = new JPanel();
	
	private JButton managerBtn = new JButton("Manager");
	private JButton salesBarBtn = new JButton("Sales Bar");
	private JButton technicianBtn = new JButton("Technician");
	
	private CardLayout homeScreenCl = new CardLayout(); 
	
	
	public CTHandbook() {
		
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
		homeScreen.add(new ManagerOps(homeScreen, homeScreenCl), "manager_menu");
		homeScreen.add(new SalesBarOps(homeScreen, homeScreenCl), "sales_bar_menu");
		homeScreen.add(new TechnicianOps(homeScreen, homeScreenCl), "technician_menu");
		homeScreenCl.show(homeScreen, "main_menu");

		
		
		// === CLEVERTECH LOGO CONFIGURATIONS ===
		logo.setBackground(Color.WHITE);
		

		
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
		JPanel btnArea = new JPanel();
		btnArea.setBackground(Color.WHITE);
		btnArea.setLayout(new MigLayout());
		btnArea.add(managerBtn);
		btnArea.add(salesBarBtn);
		btnArea.add(technicianBtn);
		homeMenu.add(btnArea, "wrap");
		//3-Point Team???
		JButton tPt = new JButton("3-Point Team");
		homeMenu.add(tPt, "center");
		
		
		
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
		btn.setFont(new Font("Arial", Font.BOLD, 13));
		btn.setMargin(new Insets(7, 7, 7, 7)); //Perhaps inset values should NOT be hard coded?
		btn.setForeground(Color.WHITE);
		btn.setBackground(Color.decode("0x026937"));
		btn.setOpaque(true); //Need to say this so that the button's color shows up
		
	}

}
