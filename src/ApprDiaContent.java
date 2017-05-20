import java.awt.CardLayout;
import javax.swing.JPanel;

public class ApprDiaContent extends MenuItemContent {
	private static final long serialVersionUID = 1L;

	public ApprDiaContent(JPanel backMenuScreen, CardLayout backMenuScreenCl) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("DIALOGUE FOR APPROVAL");
		//fillComponentContent(backMenuScreen, backMenuScreenCl, "screen_content/approval_dialogue.html", "FOLLOW CUSTOMERS UPDATE TEXT", "customers_update_screen");	
		//createTextEdit();
		createTextEdit("appr_dialogue");
	}
	
	
	public ApprDiaContent(JPanel backMenuScreen, CardLayout backMenuScreenCl, DBConnect connect) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("DIALOGUE FOR APPROVAL");
		//fillComponentContent(backMenuScreen, backMenuScreenCl, "screen_content/approval_dialogue.html", "FOLLOW CUSTOMERS UPDATE TEXT", "customers_update_screen");	
		//createTextEdit();
		createTextEdit("appr_dialogue", connect);
	}
}
