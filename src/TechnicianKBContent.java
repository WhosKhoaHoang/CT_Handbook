import java.awt.CardLayout;
import javax.swing.JPanel;

public class TechnicianKBContent extends MenuItemContent {
	private static final long serialVersionUID = 1L;

	public TechnicianKBContent(JPanel backMenuScreen, CardLayout backMenuScreenCl) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("TECHNICIAN KNOWLEDGE BASE UNDER CONSTRUCTION");
	}
	
	public TechnicianKBContent(JPanel backMenuScreen, CardLayout backMenuScreenCl, DBConnect connect) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("TECHNICIAN KNOWLEDGE BASE UNDER CONSTRUCTION");
	}
}
