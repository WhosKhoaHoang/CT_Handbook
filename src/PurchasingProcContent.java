import java.awt.CardLayout;
import javax.swing.JPanel;

public class PurchasingProcContent extends MenuItemContent {
	private static final long serialVersionUID = 1L;

	public PurchasingProcContent(JPanel backMenuScreen, CardLayout backMenuScreenCl) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("Purchasing Procedures");
		//fillBasicContent("screen_content/purchasing_procedures.html");	
		//createTextEdit();
		createTextEdit("purchase_proc");
	}
	
	public PurchasingProcContent(JPanel backMenuScreen, CardLayout backMenuScreenCl, DBConnect connect) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("Purchasing Procedures");
		//fillBasicContent("screen_content/purchasing_procedures.html");	
		//createTextEdit();
		createTextEdit("purchase_proc", connect);
	}
}
