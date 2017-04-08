import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

public class TechnicianOpsMenu extends OpsMenu {

	private static final long serialVersionUID = 1L;
	private JButton evRprOpBtn = new JButton("All Technician Repair Procedures");
	private JButton cusProcsBtn = new JButton("Customer Procedures");
	private JButton donorBoardBtn = new JButton("Donor Board Procedures");
	private JButton closingCLBtn = new JButton("Closing Store Checklist");
	private JButton invenCheckProcBtn = new JButton("Inventory Check Procedures");
	private JButton techToolsBtn = new JButton("Tech Tools");
	private JButton technicianKBBtn = new JButton("Technician Knowledge Base");
	private List<JButton> btnsLst = new ArrayList<JButton>(Arrays.asList(evRprOpBtn, cusProcsBtn,
			donorBoardBtn, closingCLBtn, invenCheckProcBtn, techToolsBtn, technicianKBBtn));	
	
	//=========== Needed for ActionListeners ===========
	OpsMenu curPage = this;
	
	public TechnicianOpsMenu(JPanel mainScreen, CardLayout mainScreenCl) {
		super("TECHNICIAN OPERATIONS", mainScreen, mainScreenCl, "home_menu");
		
		createMenuHeader("TECHNICIAN OPERATIONS"); //Should this be specific to each OpsMenu instead?
		createBtnArea(); //Should this be specific to each OpsMenu instead?
		
		addMenuBtns(btnsLst);
		addBtnALs();
		
		// =========== The different MenuItemContents that this SalesBarOpsMenu will transition to ===========
		MenuItemContent techRepairProcContent = new TechRepairProcContent(this, menuScreenCl);
		SalesBarManual salesBarManual = new SalesBarManual(this, menuScreenCl);
		MenuItemContent donorBoardContent = new DonorBoardContent(this, menuScreenCl);
		MenuItemContent closingStoreCL = new ClosingStoreCL(this, menuScreenCl);
		MenuItemContent inventoryCheckProcContent = new InventoryCheckProcContent(this, menuScreenCl);
		MenuItemContent techToolsContent = new TechToolsContent(this, menuScreenCl);
		MenuItemContent technicianKBContent = new TechnicianKBContent(this, menuScreenCl);
		
		// =========== Add the JPanels as cards ===========
		add(techRepairProcContent, "technician_repair_procedures");
		add(salesBarManual, "customer_procedures");
		add(donorBoardContent, "donor_board_procedures");
		add(closingStoreCL, "closing_store_checklist");
		add(inventoryCheckProcContent, "inventory_check_procedures");
		add(techToolsContent, "tech_tools");
		add(technicianKBContent, "technician_knowledge_base");
	}
	
	
	/**
	 * A helper method that adds ActionListerns to each button
	 */
	private void addBtnALs() {
		
		evRprOpBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuScreenCl.show(curPage, "technician_repair_procedures");
			}			
		});
		
		cusProcsBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuScreenCl.show(curPage, "customer_procedures");				
			}
		});
		
		donorBoardBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuScreenCl.show(curPage, "donor_board_procedures");				
			}
		});
		
		closingCLBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuScreenCl.show(curPage, "closing_store_checklist");								
			}
		});
	
		invenCheckProcBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuScreenCl.show(curPage, "inventory_check_procedures");												
			}
		});
		
		techToolsBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuScreenCl.show(curPage, "tech_tools");												
			}
		});
		
		technicianKBBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuScreenCl.show(curPage, "technician_knowledge_base");												
			}
		});
	}
}
