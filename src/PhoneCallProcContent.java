import java.awt.CardLayout;
import javax.swing.JPanel;

public class PhoneCallProcContent extends MenuItemContent {
	private static final long serialVersionUID = 1L;

	public PhoneCallProcContent(JPanel backMenuScreen, CardLayout backMenuScreenCl) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("Phone Call Procedures");
		fillBasicContent("screen_content/phone_call_procedures.html");
	}
}
