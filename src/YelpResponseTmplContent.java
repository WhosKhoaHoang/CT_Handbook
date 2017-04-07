import java.awt.CardLayout;
import javax.swing.JPanel;


public class YelpResponseTmplContent extends MenuItemContent {
	private static final long serialVersionUID = 1L;

	public YelpResponseTmplContent(JPanel backMenuScreen, CardLayout backMenuScreenCl) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("YELP RESPONSE TEMPLATE");
		fillBasicContent("screen_content/yelp_response_template.html");	}

}
