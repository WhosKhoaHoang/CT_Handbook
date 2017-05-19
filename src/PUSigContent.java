import java.awt.CardLayout;

import javax.swing.JPanel;


public class PUSigContent extends MenuItemContent {
	private static final long serialVersionUID = 1L;

	public PUSigContent(JPanel backMenuScreen, CardLayout backMenuScreenCl) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("PICK-UP SIGNATURE");
		//fillBasicContent("screen_content/pickup_sig.html");
		//createTextEdit();
		createTextEdit("PU_sig");
		//addTextToCBBtn();	
		
	}

}
