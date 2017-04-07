import java.awt.CardLayout;

import javax.swing.JPanel;

public class CustomersUpdateContent extends MenuItemContent {
	private static final long serialVersionUID = 1L;

	public CustomersUpdateContent(JPanel backMenuScreen, CardLayout backMenuScreenCl) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("CUSTOMERS UPDATE TEXT");
		fillBasicContent("screen_content/customers_update_text.html");
	}
}
