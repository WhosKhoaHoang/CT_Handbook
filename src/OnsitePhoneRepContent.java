import java.awt.CardLayout;
import javax.swing.JPanel;

public class OnsitePhoneRepContent extends MenuItemContent {
	private static final long serialVersionUID = 1L;

	public OnsitePhoneRepContent(JPanel backMenuScreen, CardLayout backMenuScreenCl) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("On-Site Phone Repairs");
		//fillBasicContent("screen_content/onsite_phone_repairs.html");
		//createTextEdit();
		createTextEdit("onsite_phone_rpr");
	}
	
	public OnsitePhoneRepContent(JPanel backMenuScreen, CardLayout backMenuScreenCl, DBConnect connect) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("On-Site Phone Repairs");
		//fillBasicContent("screen_content/onsite_phone_repairs.html");
		//createTextEdit();
		createTextEdit("onsite_phone_rpr", connect);
	}
}
