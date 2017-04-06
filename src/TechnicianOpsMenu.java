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
	private JButton evRprOpBtn = new JButton("Technician Repair Procedures");
	private JButton cusProcsBtn = new JButton("Customer Procedures");
	private JButton closingCLBtn = new JButton("Closing Store Checklist");
	private JButton invenCheckProcBtn = new JButton("Inventory Check Procedure");
	private List<JButton> btnsLst = new ArrayList<JButton>(Arrays.asList(evRprOpBtn, cusProcsBtn,
			closingCLBtn, invenCheckProcBtn));	
	
	//=========== Needed for ActionListeners ===========
	OpsMenu curPage = this;
	
	public TechnicianOpsMenu(JPanel mainScreen, CardLayout mainScreenCl) {
		super("TECHNICIAN OPERATIONS", mainScreen, mainScreenCl);
		addMenuBtns(btnsLst);
		addBtnALs();
		
		// =========== The different MenuItemContents that this SalesBarOpsMenu will transition to ===========
		MenuItemContent techRepairProcContent = new TechRepairProcContent(this, menuScreenCl);
		SalesBarManual salesBarManual = new SalesBarManual(this, menuScreenCl);
		MenuItemContent closingStoreCL = new ClosingStoreCL(this, menuScreenCl);
		MenuItemContent inventoryCheckProcContent = new InventoryCheckProcContent(this, menuScreenCl);
		
		// =========== Add the JPanels as cards ===========
		add(techRepairProcContent, "technician_repair_procedures");
		add(salesBarManual, "customer_procedures");
		add(closingStoreCL, "closing_store_checklist");
		add(inventoryCheckProcContent, "inventory_check_procedures");
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
	}
}