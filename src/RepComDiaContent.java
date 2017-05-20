import java.awt.CardLayout;

import javax.swing.JPanel;


public class RepComDiaContent extends MenuItemContent {
	private static final long serialVersionUID = 1L;

	public RepComDiaContent(JPanel backMenuScreen, CardLayout backMenuScreenCl) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("DIALOGUE REPAIR COMPLETE");
		//fillComponentContent(backMenuScreen, backMenuScreenCl, "screen_content/repair_completion_dialogue.html", "FOLLOW COMPLETE REPAIR", "complete_repair_screen");	
		//createTextEdit();	
		createTextEdit("rpr_cmpl_dialogue");
	}
	
	public RepComDiaContent(JPanel backMenuScreen, CardLayout backMenuScreenCl, DBConnect connect) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("DIALOGUE REPAIR COMPLETE");
		//fillComponentContent(backMenuScreen, backMenuScreenCl, "screen_content/repair_completion_dialogue.html", "FOLLOW COMPLETE REPAIR", "complete_repair_screen");	
		//createTextEdit();	
		createTextEdit("rpr_cmpl_dialogue", connect);
	}
}
