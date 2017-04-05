import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import net.miginfocom.swing.MigLayout;


public class SalesBarOps extends OpsMenu {  //Note that OpsMenu is set with a CardLayout, which makes "this" set with CardLayout
	
	private static final long serialVersionUID = 1L; //Eclipses suggested this...
	private JButton openingStoreCLBtn = new JButton("Opening Store Check List");
	private JButton phoneCallBtn = new JButton("Phone Call Procedures");
	private JButton SBManualBtn = new JButton("Customer Procedures");
	//private JButton cusProcBtn = new JButton("Customer Procedures");
	private JButton recMailOpsBtn = new JButton("Receiving Mail Operations");
	private JButton dropOffUpdatesBtn = new JButton("Drop-off Updates");
	private List<JButton> btnsLst = new ArrayList<JButton>(Arrays.asList(openingStoreCLBtn, phoneCallBtn,
			SBManualBtn, recMailOpsBtn, dropOffUpdatesBtn));	
	
	
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
		//JPanel cusProc = new JPanel();
		JPanel recMailOps = new JPanel();
		JPanel dropOffUpdates = new JPanel();
		
		
		// =========== Add the JPanels as cards ===========
		add(openingStoreCL, "opening_store_checklist");
		add(phoneCall, "phone_call");
		add(SBManual, "sales_bar_manual");
		//add(cusProc, "customer_procedures");
		add(recMailOps, "receiving_mail_ops");
		add(dropOffUpdates, "dropoff_updates");

		
		
		
		
		// ALL OF THIS COULD BE TAKEN OUT IF YOU WENT THE CLASS ROUTE!!!
		// =========== Begin filling the JPanels with content ===========
		
		// === Opening Store Checklist ===
		//You could probably make a Content Class of some sort!!!! Think: all of the stuff here could be put in the constructor for
		//a Content class...Base: OpsItemContent, Sub: (particular name) + Content
		openingStoreCL.setBackground(Color.WHITE);
		openingStoreCL.setLayout(new MigLayout());
		createHeader("Opening Store Checklist", openingStoreCL); //Could be a method of the Content class
		fillBasicContent("screen_content/opening_store_checklist.html", openingStoreCL); //Could be a method of the Content class

		
		// === Phone Call Procedures ===
		phoneCall.setBackground(Color.WHITE);
		phoneCall.setLayout(new MigLayout());
		createHeader("Phone Call Procedures", phoneCall);
		fillBasicContent("screen_content/phone_call_procedures.html", phoneCall);

		
		// === Sales Bar Manual ===
		// - DONE!!! Consider how easy that was. You simply had to instantiate a SalesBarManual and add it to this SalesBarOps as a card!
		//   Work to do this for the other content screens!
		
		
		// === Receiving Mail Operations ===
		recMailOps.setBackground(Color.WHITE);
		recMailOps.setLayout(new MigLayout());
		createHeader("Receiving Mail Operations", recMailOps);
		fillBasicContent("screen_content/receiving_mail_operations.html", recMailOps);
		
		// === Drop-off Updates ===
		dropOffUpdates.setBackground(Color.WHITE);
		dropOffUpdates.setLayout(new MigLayout());
		createHeader("Drop-Off Updates", dropOffUpdates);
		fillBasicContent("screen_content/dropoff_updates.html", dropOffUpdates);


	}
	
	
	/**
	 * A helper method that fills in the content for a content screen.
	 * @param fileName Name of the file containing the screen's content
	 * @param panel The JPanel representing the content screen (must be set with a MigLayout)
	 */
	private void fillBasicContent(String fileName, JPanel panel) {
		JTextPane content = new JTextPane();		
		content.setEditable(false);
		content.setContentType("text/html");
		
        try {
            //BufferedReader br = new BufferedReader(new FileReader(fileName));
        	//It seems that FileReader doesn't look inside the file structure WITHIN the src folder...
            BufferedReader br = new BufferedReader(new InputStreamReader(
            		Main.class.getResourceAsStream(fileName), StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            
            while (line != null) {
            	sb.append(line);
            	line = br.readLine();
            }
            
            String everything = sb.toString();
            content.setText(everything);
            br.close();         
        }
        catch(FileNotFoundException e) { e.printStackTrace(); }
        catch(IOException e) {e.printStackTrace();}
		
		final JScrollPane contentScroll = new JScrollPane(content);
		//contentScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		SwingUtilities.invokeLater(new Runnable() { //This is the only way I can get the JScrollPane to default to a 0 value...
			   public void run() { 
				   contentScroll.getVerticalScrollBar().setValue(0);
			   }
			});
		panel.add(contentScroll, "push, grow, wrap");
	}
	
	
	/**
	 * A helper method that creates the header for a content screen.
	 * @param headerTxt The text to put on the header
	 * @param panel The JPanel representing the content screen (must be set with MigLayout)
	 */
	private void createHeader(String headerTxt, JPanel panel) {
		JPanel header = new JPanel();
		header.setBackground(Color.decode("0x026937"));
		header.setLayout(new MigLayout());
		
		JLabel headerLbl = new JLabel(headerTxt); //String is variable
		headerLbl.setFont(new Font("Helvetica", Font.BOLD, 25));
		headerLbl.setForeground(Color.WHITE);
		
		JButton backBtn = new JButton("Back to Main Menu");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuScreenCl.show(curPage, "ops_main_screen");
			}
		});
		
		header.add(backBtn, "wrap");
		header.add(headerLbl, "gaptop 10"); //HARD-CODED VALUE
		panel.add(header, "height 100, pushx, growx, wrap"); //HARD-CODED VALUE. Setting values for Header here.
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
	
		/*
		cusProcBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuScreenCl.show(curPage, "customer_procedures");								
			}
		});
		*/
		
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
