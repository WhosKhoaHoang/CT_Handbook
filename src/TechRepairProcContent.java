import java.awt.CardLayout;
import javax.swing.JPanel;

public class TechRepairProcContent extends MenuItemContent {
	private static final long serialVersionUID = 1L;

	public TechRepairProcContent(JPanel backMenuScreen, CardLayout backMenuScreenCl) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("Technician Repair Procedures");
		fillBasicContent("screen_content/technician_repair_procedures.html");
	}

}
