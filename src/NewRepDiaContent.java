import java.awt.CardLayout;
import javax.swing.JPanel;

public class NewRepDiaContent extends MenuItemContent {
	private static final long serialVersionUID = 1L;

	public NewRepDiaContent(JPanel backMenuScreen, CardLayout backMenuScreenCl) {
		//super(backMenuScreen, backMenuScreenCl, "ops_main_screen"); //Do I even need ops_main_screen here? Isn't this always gonna be the case?
		super(backMenuScreen, backMenuScreenCl); //Do I even need ops_main_screen here? Isn't this always gonna be the case?
		createHeader("NEW REPAIR DIALOGUE");
		fillComponentContent(backMenuScreen, backMenuScreenCl,
				"screen_content/new_repair_dialogue.html", "FOLLOW WALK-IN-TEMPLATE", "walkin_work_order_screen");
	}
}
