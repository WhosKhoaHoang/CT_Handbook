import java.awt.CardLayout;
import javax.swing.JPanel;

public class TechRepairProcContent extends MenuItemContent {
	private static final long serialVersionUID = 1L;

	public TechRepairProcContent(JPanel backMenuScreen, CardLayout backMenuScreenCl) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("All Technician Repair Procedures");
		//fillBasicContent("screen_content/technician_repair_procedures.html");
		//createTextEdit();
		createTextEdit("tech_rpr_procs");
	}

	public TechRepairProcContent(JPanel backMenuScreen, CardLayout backMenuScreenCl, DBConnect connect) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("All Technician Repair Procedures");
		//fillBasicContent("screen_content/technician_repair_procedures.html");
		//createTextEdit();
		createTextEdit("tech_rpr_procs", connect);
	}
}
