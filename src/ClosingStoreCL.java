import java.awt.CardLayout;
import javax.swing.JPanel;

public class ClosingStoreCL extends MenuItemContent {
	private static final long serialVersionUID = 1L;

	public ClosingStoreCL(JPanel backMenuScreen, CardLayout backMenuScreenCl) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("Closing Store Checklist");
		//fillBasicContent("screen_content/closing_store_checklist.html");
		//createTextEdit();
		createTextEdit("close_store_CL");
	}
	
	public ClosingStoreCL(JPanel backMenuScreen, CardLayout backMenuScreenCl, DBConnect connect) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("Closing Store Checklist");
		//fillBasicContent("screen_content/closing_store_checklist.html");
		//createTextEdit();
		createTextEdit("close_store_CL", connect);
	}
}
