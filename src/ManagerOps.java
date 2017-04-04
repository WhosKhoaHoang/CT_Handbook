import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;


public class ManagerOps extends OpsMenu {

	private static final long serialVersionUID = 1L; //Eclipse suggested this...
	private JButton openStoreCLBtn = new JButton("Opening Store Checklist");
	private JButton shippingProcBtn = new JButton("Shipping Procedures");
	private JButton pickUpDropOffProcBtn = new JButton("Pick-Up/Drop-Off Procedure");
	private JButton onsitePhoneRprsBtn = new JButton("Onsite Phone Repairs");
	private JButton CusProcBtn = new JButton("Customer Procedures");
	private JButton purchOpsBtn = new JButton("Purchasing Operations");
	private JButton invenCheckProcBtn = new JButton("Inventory Check Procedure");
	private JButton orderingOffSupBtn = new JButton("Ordering Office Supplies");
	private JButton onlineMrktingBtn = new JButton("Online Marketing");
	private List<JButton> btnsLst = new ArrayList<JButton>(Arrays.asList(openStoreCLBtn, shippingProcBtn,
			pickUpDropOffProcBtn, onsitePhoneRprsBtn, CusProcBtn, purchOpsBtn, invenCheckProcBtn, orderingOffSupBtn, 
			onlineMrktingBtn));	
	
	public ManagerOps(JPanel mainScreen, CardLayout mainScreenCl) {
		super("MANAGER OPERATIONS", mainScreen, mainScreenCl);
		//For proper-looking buttons:
		//try { UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() ); } 
		//catch (Exception e) { e.printStackTrace(); }
		//^Actually, don't think I need this since the top-level container already includes this...
		
		addMenuBtns(btnsLst);
		addBtnALs();
	}
	
	
	/**
	 * A helper method that adds ActionListerns to each button
	 */
	private void addBtnALs() {
		
		openStoreCLBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}			
		});
		
		shippingProcBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		pickUpDropOffProcBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	
		onsitePhoneRprsBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		CusProcBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		purchOpsBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		invenCheckProcBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		orderingOffSupBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		onlineMrktingBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
