import javax.swing.*;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import net.miginfocom.swing.MigLayout;


public class OpsMenu extends JPanel {

	private JPanel mainScreen;
	private CardLayout mainScreenCl;
	protected JPanel btnArea = new JPanel(); //should this be protected?
	
	//private JPanel content = new JPanel();
	
	
	public OpsMenu(String headerTitle, JPanel mainScreen, CardLayout mainScreenCl) {
		
		super(); //Establish functionalities of a JPanel...
		this.mainScreen = mainScreen;
		this.mainScreenCl = mainScreenCl;
		setBackground(Color.WHITE);
		setLayout(new MigLayout());
		createMenuHeader(headerTitle);
		createBtnArea();
	}
	
	
	/**
	 * A helper method that creates the button area for an OpsMenu
	 */
	private void createBtnArea() {
		btnArea.setBackground(Color.WHITE);
		btnArea.setLayout(new MigLayout("align 50%"));
		JScrollPane btnAreaScroll = new JScrollPane(btnArea);
		add(btnAreaScroll, "push, grow, wrap");
	}
	
	
	/**
	 * A helper method that creates the header for a content screen.
	 * @param headerTxt The text to put on the header
	 * @param panel The JPanel representing the content screen (must be set with MigLayout)
	 */
	private void createMenuHeader(String headerTxt) {
		JPanel header = new JPanel();
		header.setBackground(Color.decode("0x026937"));
		header.setLayout(new MigLayout("align 50% 50%"));
		
		JLabel headerLbl = new JLabel(headerTxt); //String is variable
		headerLbl.setFont(new Font("Helvetica", Font.BOLD, 30));
		headerLbl.setForeground(Color.WHITE);
		
		JButton backBtn = new JButton("Back to Top Menu"); //Stlye this button differently!!!
		//configButton(backBtn);
		backBtn.setFont(new Font("Arial", Font.BOLD, 12));
		backBtn.setForeground(Color.WHITE);
		backBtn.setBackground(Color.decode("0x026937"));
		backBtn.setOpaque(true); //Need to say this so that the button's color shows up
		
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainScreenCl.show(mainScreen, "main_menu");
			}
		});
		
		header.add(headerLbl, "wrap");
		header.add(backBtn, "align center");
		add(header, "height 100, pushx, growx, wrap"); //HARD-CODED VALUE. Setting values for Header here.
	}

	
	/**
	 * A helper method that adds buttons to the operations menu.
	 */
	protected void addMenuBtns(List<JButton> btnsLst) {
		
		for (int i = 0; i < btnsLst.size(); i++) {
			
			configButton(btnsLst.get(i));
			JLabel num = new JLabel(String.valueOf(i+1));
			num.setFont(new Font("Arial", Font.BOLD, 20));	
			num.setForeground(Color.decode("0x929497"));
			btnArea.add(num);
			btnArea.add(btnsLst.get(i), "width " + String.valueOf(700/1.5) + ", height " + String.valueOf(300/5) + 
					", center, wrap, gaptop 10");
			
		}
	}
	
	
	/**
	 * A helper method that configures a Button for placement on the main menu.
	 * @param btn The button to be placed
	 */
	private void configButton(JButton btn) {
		btn.setFont(new Font("Arial", Font.BOLD, 15));
		btn.setMargin(new Insets(7, 7, 7, 7)); //Perhaps inset values should NOT be hard coded?
		btn.setForeground(Color.WHITE);
		btn.setBackground(Color.decode("0x026937"));
		btn.setOpaque(true); //Need to say this so that the button's color shows up
		
	}
}
