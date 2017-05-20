import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SalesBarOpsMenu extends OpsMenu {  
	//Note that OpsMenu is set with a CardLayout, which makes "this" set with CardLayout as well
	
	private static final long serialVersionUID = 1L; //Eclipses suggested this...
	private JButton openingStoreCLBtn = new JButton("Opening Store Check List");
	private JButton phoneCallBtn = new JButton("Phone Call Procedures");
	private JButton SBManualBtn = new JButton("Customer Procedures");
	private JButton purchOpsBtn = new JButton("Purchasing Procedures");
	private JButton recMailOpsBtn = new JButton("Receiving Mail Operations");
	private JButton dropOffUpdatesBtn = new JButton("Drop-Off Updates");
	private JButton yelpResponseTmplBtn = new JButton("Yelp Response Template");
	private List<JButton> btnsLst = new ArrayList<JButton>(Arrays.asList(openingStoreCLBtn, phoneCallBtn,
			SBManualBtn, purchOpsBtn, recMailOpsBtn, dropOffUpdatesBtn, yelpResponseTmplBtn));	
	
	//=========== Needed for ActionListeners ===========
	OpsMenu curPage = this;
	
	public SalesBarOpsMenu(JPanel homeScreen, CardLayout homeScreenCl) {
		super("SALES BAR OPERATIONS", homeScreen, homeScreenCl, "home_menu");
		
		createMenuHeader("SALES BAR OPERATIONS"); //Should this be specific to each OpsMenu instead?
		createBtnArea(); //Should this be specific to each OpsMenu instead?
		
		addMenuBtns(btnsLst);
		addBtnALs();
		

		// =========== The different MenuItemContents that this SalesBarOpsMenu will transition to ===========		
		MenuItemContent openingStoreCLContent = new OpeningStoreCLContent(this, menuScreenCl);
		MenuItemContent phoneCallProcContent = new PhoneCallProcContent(this, menuScreenCl);
		SalesBarManual SBManual = new SalesBarManual(this, menuScreenCl); 
		MenuItemContent purchasingProcContent = new PurchasingProcContent(this, menuScreenCl);
		MenuItemContent menuItemContent = new RecMailProcContent(this, menuScreenCl);
		MenuItemContent dOUpdatesContent = new DOUpdatesContent(this, menuScreenCl);		
		MenuItemContent yelpResponseTmplContent = new YelpResponseTmplContent(this, menuScreenCl);
		
		// =========== Add the JPanels as cards ===========
		add(openingStoreCLContent, "opening_store_checklist");
		add(phoneCallProcContent, "phone_call");
		add(SBManual, "customer_procedures");
		add(purchasingProcContent, "purchasing_procedures");
		add(menuItemContent, "receiving_mail_ops");
		add(dOUpdatesContent, "dropoff_updates");
		add(yelpResponseTmplContent, "yelp_response_template");
	}
	

	//OVERLOAD SO CURRENT SYSTEM DOESN'T BREAK!
	public SalesBarOpsMenu(JPanel homeScreen, CardLayout homeScreenCl, DBConnect connect) {
		super("SALES BAR OPERATIONS", homeScreen, homeScreenCl, "home_menu");
		
		createMenuHeader("SALES BAR OPERATIONS"); //Should this be specific to each OpsMenu instead?
		createBtnArea(); //Should this be specific to each OpsMenu instead?
		
		addMenuBtns(btnsLst);
		addBtnALs();
		

		// =========== The different MenuItemContents that this SalesBarOpsMenu will transition to ===========
		MenuItemContent openingStoreCLContent = new OpeningStoreCLContent(this, menuScreenCl, connect);
		MenuItemContent phoneCallProcContent = new PhoneCallProcContent(this, menuScreenCl, connect);
		SalesBarManual SBManual = new SalesBarManual(this, menuScreenCl, connect);
		MenuItemContent purchasingProcContent = new PurchasingProcContent(this, menuScreenCl, connect);
		MenuItemContent menuItemContent = new RecMailProcContent(this, menuScreenCl, connect);
		MenuItemContent dOUpdatesContent = new DOUpdatesContent(this, menuScreenCl, connect);		
		MenuItemContent yelpResponseTmplContent = new YelpResponseTmplContent(this, menuScreenCl, connect);
		
		// =========== Add the JPanels as cards ===========
		add(openingStoreCLContent, "opening_store_checklist");
		add(phoneCallProcContent, "phone_call");
		add(SBManual, "customer_procedures");
		add(purchasingProcContent, "purchasing_procedures");
		add(menuItemContent, "receiving_mail_ops");
		add(dOUpdatesContent, "dropoff_updates");
		add(yelpResponseTmplContent, "yelp_response_template");
	}
	
	
	//This needs to be here and not in OpsMenu.java because this particular JPanel has access to the JPanels that
	//are to be transitioned to -- OpsMenu.java doesn't!!! Well, maybe you can just pass in this page and the card names
	//to OpsMenu? Actually, just keep this method here. The amount of buttons on a menu screen can vary. You *could* pass in
	//an ArrayList of buttons to the super() constructor, but nahhhhh.
	/**
	 * A helper method that adds ActionListerns to each button
	 */
	private void addBtnALs() {
		
		openingStoreCLBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuScreenCl.show(curPage, "opening_store_checklist");
			}			
		});
		
		phoneCallBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuScreenCl.show(curPage, "phone_call");				
			}
		});
		
		SBManualBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuScreenCl.show(curPage, "customer_procedures");								
			}
		});
		
		purchOpsBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuScreenCl.show(curPage, "purchasing_procedures");
			}
		});
		
		recMailOpsBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuScreenCl.show(curPage, "receiving_mail_ops");				
			}
		});
		
		dropOffUpdatesBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuScreenCl.show(curPage, "dropoff_updates");								
			}
		});
		
		yelpResponseTmplBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuScreenCl.show(curPage, "yelp_response_template");								
			}
		});
	}
}
