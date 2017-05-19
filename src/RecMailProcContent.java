import java.awt.CardLayout;

import javax.swing.JPanel;

public class RecMailProcContent extends MenuItemContent {
	private static final long serialVersionUID = 1L;

	public RecMailProcContent(JPanel backMenuScreen, CardLayout backMenuScreenCl) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("Receiving Mail Operations");
		//fillBasicContent("screen_content/receiving_mail_operations.html");
		//createTextEdit();
		createTextEdit("rec_mail_ops");
	}

}
