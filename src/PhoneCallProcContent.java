import java.awt.CardLayout;
import javax.swing.JPanel;

public class PhoneCallProcContent extends MenuItemContent {
	private static final long serialVersionUID = 1L;

	public PhoneCallProcContent(final JPanel backMenuScreen, final CardLayout backMenuScreenCl) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("Phone Call Procedures");
		//fillBasicContent("screen_content/phone_call_procedures.html");
		
		//FOR TESTING
		//fillComponentContent(backMenuScreen, backMenuScreenCl,"screen_content/phone_call_procedures2.html", "FOLLOW YELP RESPONSE TEMPLATES", "yelp_response_template");
		
		//createTextEdit();
		createTextEdit("phone_call_proc");

	}
}
