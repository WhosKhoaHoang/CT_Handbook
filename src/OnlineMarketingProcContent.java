import java.awt.CardLayout;
import javax.swing.JPanel;

public class OnlineMarketingProcContent extends MenuItemContent {
	private static final long serialVersionUID = 1L;

	public OnlineMarketingProcContent(JPanel backMenuScreen, CardLayout backMenuScreenCl) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("Online Marketing Procedures");
		//fillBasicContent("screen_content/online_marketing.html");
		//createTextEdit();
		createTextEdit("online_marketing_proc");
	}

}
