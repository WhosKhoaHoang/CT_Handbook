import java.awt.CardLayout;
import javax.swing.JPanel;

public class PickUpDiaContent extends MenuItemContent {
	private static final long serialVersionUID = 1L;

	public PickUpDiaContent(JPanel backMenuScreen, CardLayout backMenuScreenCl) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("DIALOGUE FOR PICK-UP");	
		//fillComponentContent(backMenuScreen, backMenuScreenCl, "screen_content/pickup_dialogue.html", "FOLLOW PICK-UP SIGNATURE", "pickup_signature_screen");
		createTextEdit();
	}
}
