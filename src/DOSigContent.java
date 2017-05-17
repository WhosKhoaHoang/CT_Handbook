import java.awt.CardLayout;
import javax.swing.JPanel;

public class DOSigContent extends MenuItemContent {
	private static final long serialVersionUID = 1L;

	public DOSigContent(JPanel backMenuScreen, CardLayout backMenuScreenCl) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("DROP-OFF SIGNATURE");
		//fillBasicContent("screen_content/drop_off_sig.html");
		/*
		fillComponentContent(backMenuScreen, backMenuScreenCl,
				"screen_content/drop_off_sig.html", "FOLLOW CUSTOMERS UPDATE TEXT", "customers_update_screen");
		*/
		createTextEdit();
		addTextToCBBtn();
	}
}
