import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ManagerOpsMenu extends OpsMenu {

	private static final long serialVersionUID = 1L;
	private JButton openStoreCLBtn = new JButton("Opening Store Checklist");
	private JButton shippingProcBtn = new JButton("Shipping Procedures");
	private JButton pickUpDropOffProcBtn = new JButton("Pick-Up/Drop-Off Procedure");
	private JButton onsitePhoneRprsBtn = new JButton("Onsite Phone Repairs");
	private JButton CusProcBtn = new JButton("Customer Procedures");
	private JButton purchOpsBtn = new JButton("Purchasing Procedures");
	private JButton invenCheckProcBtn = new JButton("Inventory Check Procedure");
	private JButton orderingOffSupBtn = new JButton("Ordering Office Supplies");
	private JButton onlineMrktingBtn = new JButton("Online Marketing");
	private List<JButton> btnsLst = new ArrayList<JButton>(Arrays.asList(openStoreCLBtn, shippingProcBtn,
			pickUpDropOffProcBtn, onsitePhoneRprsBtn, CusProcBtn, purchOpsBtn, invenCheckProcBtn, orderingOffSupBtn, 
			onlineMrktingBtn));	
	
	//=========== Needed for ActionListeners ===========
	OpsMenu curPage = this;
	
	public ManagerOpsMenu(JPanel mainScreen, CardLayout mainScreenCl) {
		super("MANAGER OPERATIONS", mainScreen, mainScreenCl);
		addMenuBtns(btnsLst);
		addBtnALs();
		
		// =========== The different MenuItemContents that this SalesBarOpsMenu will transition to ===========
		MenuItemContent openingStoreCLContent = new OpeningStoreCLContent(this, menuScreenCl);
		MenuItemContent shippingProcContent = new ShippingProcContent(this, menuScreenCl);
		MenuItemContent pickUpDropOffContent = new PickUpDropOffProcContent(this, menuScreenCl);
		MenuItemContent onsitePhoneRepContent = new OnsitePhoneRepContent(this, menuScreenCl);
		SalesBarManual salesBarManual = new SalesBarManual(this, menuScreenCl);
		MenuItemContent purchasingProcContent = new PurchasingProcContent(this, menuScreenCl);
		MenuItemContent inventoryCheckProcContent = new InventoryCheckProcContent(this, menuScreenCl);
		MenuItemContent officeSuppliesCLContent = new OfficeSuppliesCLContent(this, menuScreenCl);
		MenuItemContent onlineMarkertingContent = new OnlineMarketingProcContent(this, menuScreenCl);
		
		// =========== Add the JPanels as cards ===========
		add(openingStoreCLContent, "opening_store_checklist");
		add(shippingProcContent, "shipping_procedures");
		add(pickUpDropOffContent, "pickup_dropoff_procedures");
		add(onsitePhoneRepContent, "onsite_phone_repairs");
		add(salesBarManual, "customer_procedures");
		add(purchasingProcContent, "purchasing_procedures");
		add(inventoryCheckProcContent, "inventory_check_procedures");
		add(officeSuppliesCLContent, "office_supplies_checklist");
		add(onlineMarkertingContent, "online_marketing_procedures");		
	}
	
	
	/**
	 * A helper method that adds ActionListerns to each button
	 */
	private void addBtnALs() {
		
		openStoreCLBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuScreenCl.show(curPage, "opening_store_checklist");
			}			
		});
		
		shippingProcBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuScreenCl.show(curPage, "shipping_procedures");				
			}
		});
		
		pickUpDropOffProcBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuScreenCl.show(curPage, "pickup_dropoff_procedures");								
			}
		});
	
		onsitePhoneRprsBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuScreenCl.show(curPage, "onsite_phone_repairs");												
			}
		});
		
		CusProcBtn.addActionListener(new ActionListener() {
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
		
		invenCheckProcBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuScreenCl.show(curPage, "inventory_check_procedures");				
			}
		});
		
		orderingOffSupBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuScreenCl.show(curPage, "office_supplies_checklist");								
			}
		});
		
		onlineMrktingBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuScreenCl.show(curPage, "online_marketing_procedures");												
			}
		});
	}
}