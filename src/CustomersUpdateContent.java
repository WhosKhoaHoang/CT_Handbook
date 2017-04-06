import java.awt.CardLayout;

import javax.swing.JPanel;

//TESTING FOR NOW
public class CustomersUpdateContent extends MenuItemContent {

	public CustomersUpdateContent(JPanel backMenuScreen, CardLayout backMenuScreenCl) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("CUSTOMERS UPDATE TEXT");
		fillBasicContent("screen_content/customers_update_text.html");
	}

}
