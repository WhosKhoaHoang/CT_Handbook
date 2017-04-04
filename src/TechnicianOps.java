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


public class TechnicianOps extends OpsMenu {

	private JButton evRprOpBtn = new JButton("Every Repair Operation");
	private JButton cusProcsBtn = new JButton("Customer Procedures");
	private JButton closingCLBtn = new JButton("Closing Store Checklist");
	private JButton invenCheckProcBtn = new JButton("Inventory Check Procedure");
	private List<JButton> btnsLst = new ArrayList<JButton>(Arrays.asList(evRprOpBtn, cusProcsBtn,
			closingCLBtn, invenCheckProcBtn));	
	
	public TechnicianOps(JPanel mainScreen, CardLayout mainScreenCl) {
		super("TECHNICIAN OPERATIONS", mainScreen, mainScreenCl);
		addMenuBtns(btnsLst);
		addBtnALs();

	}
	
	
	/**
	 * A helper method that adds ActionListerns to each button
	 */
	private void addBtnALs() {
		
		evRprOpBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}			
		});
		
		cusProcsBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		closingCLBtn.addActionListener(new ActionListener() {
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
	}
}
