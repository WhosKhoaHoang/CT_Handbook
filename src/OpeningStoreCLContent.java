import java.awt.CardLayout;
import javax.swing.JPanel;

public class OpeningStoreCLContent extends MenuItemContent {
	private static final long serialVersionUID = 1L;

	public OpeningStoreCLContent(final JPanel backMenuScreen, final CardLayout backMenuScreenCl) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("Opening Store Checklist");
		fillBasicContent("screen_content/opening_store_checklist.html");
	}
}
