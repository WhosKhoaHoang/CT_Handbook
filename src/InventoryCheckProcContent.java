import java.awt.CardLayout;

import javax.swing.JPanel;


public class InventoryCheckProcContent extends MenuItemContent {
	private static final long serialVersionUID = 1L;

	public InventoryCheckProcContent(JPanel backMenuScreen, CardLayout backMenuScreenCl) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("Inventory Check Procedures");
		fillBasicContent("screen_content/inventory_check_procedures.html");
	}

}