import java.awt.CardLayout;

import javax.swing.JPanel;


public class CompanyWOContent extends MenuItemContent {
	private static final long serialVersionUID = 1L;

	public CompanyWOContent(JPanel backMenuScreen, CardLayout backMenuScreenCl) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("COMPANY WORK ORDER TEMPLATE");
		fillBasicContent("screen_content/company_WO.html");
		addTextToCBBtn();
	}

}
