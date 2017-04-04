import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;


public class SalesBarOps extends OpsMenu {
	
	private static final long serialVersionUID = 1L; //Eclipses suggested this...
	private JButton openingStoreCLBtn = new JButton("Opening Store Check List");
	private JButton phoneCallBtn = new JButton("Phone Call");
	private JButton SBManualBtn = new JButton("Sales Bar Manual");
	private JButton cusProcBtn = new JButton("Customer Procedures");
	private JButton recMailOps = new JButton("Receiving Mail Operations");
	private JButton dropOffUpdates = new JButton("Drop-off Updates");
	private List<JButton> btnsLst = new ArrayList<JButton>(Arrays.asList(openingStoreCLBtn, phoneCallBtn,
			SBManualBtn, cusProcBtn, recMailOps, dropOffUpdates));	
	
	public SalesBarOps(JPanel mainScreen, CardLayout mainScreenCl) {
		super("SALES BAR OPERATIONS", mainScreen, mainScreenCl);
		
		addMenuBtns(btnsLst);
		addBtnALs();

	}
	
	
	/**
	 * A helper method that adds ActionListerns to each button
	 */
	private void addBtnALs() {
		
		openingStoreCLBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}			
		});
		
		phoneCallBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		SBManualBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	
		cusProcBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		recMailOps.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		dropOffUpdates.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
