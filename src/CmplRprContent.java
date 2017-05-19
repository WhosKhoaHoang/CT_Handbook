import java.awt.CardLayout;
import javax.swing.JPanel;

public class CmplRprContent extends MenuItemContent {
	private static final long serialVersionUID = 1L;

	public CmplRprContent(JPanel backMenuScreen, CardLayout backMenuScreenCl) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("COMPLETE REPAIR TEXT");
		//fillComponentContent(backMenuScreen, backMenuScreenCl, "screen_content/complete_repair_text.html", "FOLLOW DIALOGUE FOR REPAIR COMPLETION UPDATE", "repair_complete_dialogue_screen");
		//createTextEdit();
		createTextEdit("cmpl_rpr_txt");
	}
}
