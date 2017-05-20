import java.awt.CardLayout;

import javax.swing.JPanel;


public class TechToolsContent extends MenuItemContent {
	private static final long serialVersionUID = 1L;

	public TechToolsContent(JPanel backMenuScreen, CardLayout backMenuScreenCl) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("Tech Tools");
		//fillBasicContent("screen_content/tech_tools.html");	
		//createTextEdit();

		createTextEdit("tech_tools");
	}
	
	public TechToolsContent(JPanel backMenuScreen, CardLayout backMenuScreenCl, DBConnect connect) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("Tech Tools");
		//fillBasicContent("screen_content/tech_tools.html");	
		//createTextEdit();

		createTextEdit("tech_tools", connect);
	}

}
