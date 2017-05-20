import java.awt.CardLayout;
import javax.swing.JPanel;

public class PickUpDropOffProcContent extends MenuItemContent {
	private static final long serialVersionUID = 1L;

	public PickUpDropOffProcContent(JPanel backMenuScreen, CardLayout backMenuScreenCl) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("Pick-Up/Drop-Off Procedures");
		//fillBasicContent("screen_content/pickup_and_dropoff_procedures.html");
		//createTextEdit();
		createTextEdit("pickup_dropoff");
	}
	
	public PickUpDropOffProcContent(JPanel backMenuScreen, CardLayout backMenuScreenCl, DBConnect connect) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("Pick-Up/Drop-Off Procedures");
		//fillBasicContent("screen_content/pickup_and_dropoff_procedures.html");
		//createTextEdit();
		//createTextEdit("pickup_dropoff");
		createTextEdit("pickup_dropoff", connect);
	}
}
