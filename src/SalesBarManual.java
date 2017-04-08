/*
 * A class that represents a CleverTech Handbook
 * @author Khoa Hoang
 */

import javax.swing.*;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.miginfocom.swing.MigLayout;


public class SalesBarManual extends OpsMenu {  //Should this inherit from MenuItemContent instead?
	
	//java.net.URL url = ClassLoader.getSystemResource("images/ct_logo.png"); //Code for changing desktop icon?
	private static final long serialVersionUID = 1L; //Eclipses suggested this...

	//private JFrame frame = new JFrame();
	//private JPanel mainScreen = new JPanel(); //Should I just use "this" instead of mainScreen? YEZ.
	private SalesBarManual curPage = this;
	
	//private JPanel mainMenu = new JPanel(); //Don't need anymore
	private JPanel btnArea = new JPanel();
	
	//Main Menu Buttons (have individual variables for them for when we need to add action listeners):
	private JButton newRepDiaBtn = new JButton("DIALOGUE FOR NEW REPAIR");
	private JButton walkinWOBtn= new JButton("WALK-IN WORK ORDER");
	private JButton workCmplBtn = new JButton("WORK COMPLETED TEXT");
	private JButton pickUpDiaBtn = new JButton("DIALOGUE FOR PICK-UP");
	private JButton cmpnyWOBtn = new JButton("COMPANY WORK ORDER");
	private JButton cusUpdateBtn = new JButton("CUSTOMERS UPDATE TEXT");
	private JButton apprDiaBtn = new JButton("DIALOGUE FOR APPROVAL");
	private JButton dOSigBtn = new JButton("DROP-OFF SIGNATURE");
	private JButton cmplRprBtn = new JButton("COMPLETE REPAIR TEXT");
	private JButton repComDiaBtn = new JButton("DIALOGUE REPAIR COMPLETE");
	private JButton pUSigBtn = new JButton("PICK-UP SIGNATURE");
	private List<JButton> mainMenuBtns = new ArrayList<JButton>(Arrays.asList(newRepDiaBtn, walkinWOBtn,
			workCmplBtn, pickUpDiaBtn, cmpnyWOBtn, cusUpdateBtn, apprDiaBtn, dOSigBtn, cmplRprBtn,
			repComDiaBtn, pUSigBtn));
	
	/**
	 * Creates a new SalesBarManual.
	 */
	public SalesBarManual(final JPanel backMenuScreen, final CardLayout backMenuScreenCl) {	
		super("CUSTOMER PROCEDURES", backMenuScreen, backMenuScreenCl, "ops_main_screen");		
		MenuItemContent newRepDiaContent = new NewRepDiaContent(this, menuScreenCl);
		MenuItemContent walkinWOContent = new WalkinWOContent(this, menuScreenCl);
		MenuItemContent workCmplContent = new WorkCmplContent(this, menuScreenCl);
		MenuItemContent pickUpDiaContent = new PickUpDiaContent(this, menuScreenCl);
		MenuItemContent cmpnyWOContent = new CompanyWOContent(this, menuScreenCl);
		MenuItemContent cusUpdateContent = new CusUpdateContent(this, menuScreenCl);
		MenuItemContent apprDiaContent = new ApprDiaContent(this, menuScreenCl);
		MenuItemContent dOSigContent = new DOSigContent(this, menuScreenCl);
		MenuItemContent cmplRprContent = new CmplRprContent(this, menuScreenCl);
		MenuItemContent repComDiaContent = new RepComDiaContent(this, menuScreenCl);
		MenuItemContent pUSigContent = new PUSigContent(this, menuScreenCl);
		
		add(newRepDiaContent, "new_repair_dialogue_screen");
		add(walkinWOContent, "walkin_work_order_screen");
		add(workCmplContent, "work_completed_screen");
		add(pickUpDiaContent, "pickup_dialogue_screen");
		add(cmpnyWOContent, "company_work_order_screen");
		add(cusUpdateContent, "customers_update_screen");
		add(apprDiaContent, "approval_dialogue_screen");
		add(dOSigContent, "drop_off_signature_screen");
		add(cmplRprContent, "complete_repair_screen");
		add(repComDiaContent, "repair_complete_dialogue_screen");
		add(pUSigContent, "pickup_signature_screen");
		
		// === HEADER CONFIGURATIONS ===
		JPanel header = new JPanel();
		header.setBackground(Color.decode("0x026937"));
		header.setLayout(new MigLayout());
		
		JLabel headerLbl = new JLabel("Customer Procedures");
		headerLbl.setFont(new Font("Helvetica", Font.BOLD, 25));
		headerLbl.setForeground(Color.WHITE);
		
		JButton backBtn = new JButton("Back to Main Menu");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backMenuScreenCl.show(backMenuScreen, "ops_main_screen");
			}
		});
		
		header.add(backBtn, "wrap");
		header.add(headerLbl, "gaptop 10"); //HARD-CODED VALUE
		content.add(header, "height 100, pushx, growx, wrap"); //HARD-CODED VALUE. Setting values for Header here.
		
		
		// === BUTTON AREA CONFIGURATIONS ===
		btnArea.setLayout(new MigLayout());
		btnArea.setBackground(Color.WHITE);
		content.add(btnArea, "center, push, growy");		
		addMainMenuBtns();
		JScrollPane scrollPane = new JScrollPane(btnArea);
		content.add(scrollPane, "push, grow");
		
		
		//addMenuBtns(mainMenuBtns); 
		//addMenuBtns() is a method found in the OpsMenu Base class. That layout looks different from
		//what you want for "this"...so don't write this statement!
		addMainMenuBtnALs();
		
	}

	
	
	/**
	 * A helper method that adds buttons to the main menu in groups of three columns.
	 */
	private void addMainMenuBtns() {
		for (int i = 0, curCol = 0; i < mainMenuBtns.size(); i++, curCol++) {
			configButton(mainMenuBtns.get(i));
			if (curCol == 0) { 
				if (i == 0) {
					btnArea.add(mainMenuBtns.get(i), "width " + String.valueOf(700/3) + ", height " + String.valueOf(300/10));
				}
				else {
					btnArea.add(mainMenuBtns.get(i), "width " + String.valueOf(700/3) + ", height " + String.valueOf(300/10) + ", gaptop " + String.valueOf(700/70));
				}
			}
			else if (curCol == 1) { 
				btnArea.add(mainMenuBtns.get(i), "width " + String.valueOf(700/3) + ", height " + String.valueOf(300/10) + ", gap " + String.valueOf(700/70));
			}
			else if (curCol == 2) { 
				btnArea.add(mainMenuBtns.get(i), "width " + String.valueOf(700/3) + ", height " + String.valueOf(300/10) + ", gap " + String.valueOf(700/70) + ", wrap");
				curCol = -1; //Don't reset to 0 because curCol is incremented at the end of the iteration!!!
			}
		}	
	}
	
	
	
	/**
	 * A helper method that adds Action Listeners to the main menu's buttons.
	 */
	private void addMainMenuBtnALs() {
		newRepDiaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuScreenCl.show(curPage, "new_repair_dialogue_screen");
				//^Read this as: For mainScreen, show the (JPanel) component labeled as "new_repair_dialogue_screen"
			}
		});
		
		
		walkinWOBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuScreenCl.show(curPage, "walkin_work_order_screen");
			}
		});
		
		
		workCmplBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuScreenCl.show(curPage, "work_completed_screen");
			}
		});
		
		
		pickUpDiaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuScreenCl.show(curPage, "pickup_dialogue_screen");
			}
		});
		
		
		cmpnyWOBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuScreenCl.show(curPage, "company_work_order_screen");
			}
		});
		
		cusUpdateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuScreenCl.show(curPage, "customers_update_screen");
			}
		});
		
		
		apprDiaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuScreenCl.show(curPage, "approval_dialogue_screen");
			}
		});

		
		dOSigBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuScreenCl.show(curPage, "drop_off_signature_screen");
			}
		});
		
		
		cmplRprBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuScreenCl.show(curPage, "complete_repair_screen");
			}
		});
		
		
		repComDiaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuScreenCl.show(curPage, "repair_complete_dialogue_screen");
			}
		});
		
		pUSigBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuScreenCl.show(curPage, "pickup_signature_screen");
			}
		});
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
		//btn.setBorderPainted(false); 
		//^I would have needed to say this if I didn't write the "look and feel" try/catch block code situated
		//near the beginning of the Handbook constructor. I.e., this:
		//	try { UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() ); } 
		//	catch (Exception e) { e.printStackTrace(); }
		//In general, you should ^INCLUDE THIS!!!
	}

}