import java.awt.CardLayout;
import javax.swing.JPanel;

public class DonorBoardContent extends MenuItemContent {
	private static final long serialVersionUID = 1L;

	public DonorBoardContent(JPanel backMenuScreen, CardLayout backMenuScreenCl) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("Donor Board Procedures");
		//fillBasicContent("screen_content/donor_board_procedures.html");
		//createTextEdit();
		createTextEdit("donor_board");
	}
	
	public DonorBoardContent(JPanel backMenuScreen, CardLayout backMenuScreenCl, DBConnect connect) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("Donor Board Procedures");
		//fillBasicContent("screen_content/donor_board_procedures.html");
		//createTextEdit();
		createTextEdit("donor_board", connect);
	}

}
