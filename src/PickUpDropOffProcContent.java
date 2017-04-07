import java.awt.CardLayout;
import javax.swing.JPanel;

public class PickUpDropOffProcContent extends MenuItemContent {
	private static final long serialVersionUID = 1L;

	public PickUpDropOffProcContent(JPanel backMenuScreen, CardLayout backMenuScreenCl) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("Pick-Up/Drop-Off Procedures");
		fillBasicContent("screen_content/pickup_and_dropoff_procedures.html");
	}
}
