import java.awt.CardLayout;
import javax.swing.JPanel;


public class YelpResponseTmplContent extends MenuItemContent {
	private static final long serialVersionUID = 1L;

	public YelpResponseTmplContent(JPanel backMenuScreen, CardLayout backMenuScreenCl) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("Yelp Response Template");
		//fillBasicContent("screen_content/yelp_response_template.html");	
		//createTextEdit();
		createTextEdit("yelp_resp");
	}
	
	public YelpResponseTmplContent(JPanel backMenuScreen, CardLayout backMenuScreenCl, DBConnect connect) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("Yelp Response Template");
		//fillBasicContent("screen_content/yelp_response_template.html");	
		//createTextEdit();
		createTextEdit("yelp_resp", connect);
	}

}
