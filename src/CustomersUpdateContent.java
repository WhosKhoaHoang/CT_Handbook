import java.awt.CardLayout;

import javax.swing.JPanel;

//Did I accidentally create this? I already have CusUpdateContent.java...
public class CustomersUpdateContent extends MenuItemContent {
	private static final long serialVersionUID = 1L;

	public CustomersUpdateContent(JPanel backMenuScreen, CardLayout backMenuScreenCl) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("CUSTOMERS UPDATE TEXT");
		//fillBasicContent("screen_content/customers_update_text.html");
		createTextEdit("cus_update");
	}
}
