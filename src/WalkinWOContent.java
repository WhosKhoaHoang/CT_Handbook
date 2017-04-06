import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class WalkinWOContent extends MenuItemContent {
	private static final long serialVersionUID = 1L;

	public WalkinWOContent(final JPanel backMenuScreen, final CardLayout backMenuScreenCl) {
		super(backMenuScreen, backMenuScreenCl);
		
		createHeader("WALKIN-IN WORK ORDER TEMPLATE");	
		fillBasicContent("screen_content/walkinWO.html");
		addTextToCBBtn();
		JPanel walkinWOheader = (JPanel)getComponent(0);
		JButton backToNewRepDia = new JButton("Back to New Repairs Dialogue");
		backToNewRepDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backMenuScreenCl.show(backMenuScreen, "new_repair_dialogue_screen");
			}
		});
		walkinWOheader.add(backToNewRepDia, "cell 0 0");
		
	}


}
