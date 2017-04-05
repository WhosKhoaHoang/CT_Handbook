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


public class SalesBarOps extends OpsMenu {  //Note that OpsMenu is set with a CardLayout, which makes "this" set with CardLayout
	
	private static final long serialVersionUID = 1L; //Eclipses suggested this...
	private JButton openingStoreCLBtn = new JButton("Opening Store Check List");
	private JButton phoneCallBtn = new JButton("Phone Call");
	private JButton SBManualBtn = new JButton("Sales Bar Manual");
	private JButton cusProcBtn = new JButton("Customer Procedures");
	private JButton recMailOpsBtn = new JButton("Receiving Mail Operations");
	private JButton dropOffUpdatesBtn = new JButton("Drop-off Updates");
	private List<JButton> btnsLst = new ArrayList<JButton>(Arrays.asList(openingStoreCLBtn, phoneCallBtn,
			SBManualBtn, cusProcBtn, recMailOpsBtn, dropOffUpdatesBtn));	
	
	
	//=========== Needed for ActionListeners ===========
	OpsMenu curPage = this;

	
	public SalesBarOps(JPanel homeScreen, CardLayout homeScreenCl) {
		super("SALES BAR OPERATIONS", homeScreen, homeScreenCl);
		
		
		addMenuBtns(btnsLst);
		addBtnALs();

		
		// =========== The different JPanels that this SalesBarOps will transition to ===========
		JPanel openingStoreCL = new JPanel();
		JPanel phoneCall = new JPanel();
		//SalesBarManual SBManual = new SalesBarManual(homeScreen, homeScreenCl); 
		SalesBarManual SBManual = new SalesBarManual(this, menuScreenCl); 
		//^Perhaps I need to pass homeScreenCl to this so I can go back to the sales bar screen? No. I think a card can transition
		//to another component only if that card was added by the component that had CardLayout set on it! homeScreen never added
		//the SBManual as a card, so the transition can't take place. However, this added SBManual as a card!!!
		JPanel cusProc = new JPanel();
		JPanel recMailOps = new JPanel();
		JPanel dropOffUpdates = new JPanel();
		
		
		// =========== Add the JPanels as cards ===========
		add(openingStoreCL, "opening_store_checklist");
		add(phoneCall, "phone_call");
		add(SBManual, "sales_bar_manual");
		add(cusProc, "customer_procedures");
		add(recMailOps, "receving_mail_ops");
		add(dropOffUpdates, "dropoff_updates");		
	}
	
	
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
				menuScreenCl.show(curPage, "sales_bar_manual");								
			}
		});
	
		cusProcBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuScreenCl.show(curPage, "customer_procedures");								
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
	}
}
