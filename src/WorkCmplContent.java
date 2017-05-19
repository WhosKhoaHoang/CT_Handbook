import java.awt.CardLayout;
import javax.swing.JPanel;

public class WorkCmplContent extends MenuItemContent {
	private static final long serialVersionUID = 1L;

	public WorkCmplContent(JPanel backMenuScreen, CardLayout backMenuScreenCl) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("WORK COMPLETED TEXT");
		//fillComponentContent(backMenuScreen, backMenuScreenCl,"screen_content/work_completed_text.html", "FOLLOW DIALOGUE FOR APPROVAL", "approval_dialogue_screen");	
		//createTextEdit();
		createTextEdit("work_cmpl");
	}

}
