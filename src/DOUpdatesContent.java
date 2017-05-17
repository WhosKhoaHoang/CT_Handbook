import java.awt.CardLayout;
import javax.swing.JPanel;

public class DOUpdatesContent extends MenuItemContent {
	private static final long serialVersionUID = 1L;

	public DOUpdatesContent(JPanel backMenuScreen, CardLayout backMenuScreenCl) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("Drop-Off Updates");
		//fillBasicContent("screen_content/dropoff_updates.html");
		createTextEdit();
	}
}
