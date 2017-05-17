//Credit: http://www.javaquizplayer.com/examples/text-editor-using-java-example.html

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;


public class TextEditorLite extends JPanel {

	
	//private JPanel backdrop__;
	private JTextPane editor__;
	
	private static final String MAIN_TITLE = "My Editor 1";
	private static final String DEFAULT_FONT_FAMILY = "SansSerif";
	private static final int DEFAULT_FONT_SIZE = 18;

	//DEFINE THE CONSTRUCTOR!
	public TextEditorLite() {
		
		//Get rid of backdrop. "this" itself is already a JPanel...
		//backdrop__ = new JPanel();
		editor__ = new JTextPane();
		JScrollPane editorScrollPane = new JScrollPane(editor__);

		editor__.setDocument(new DefaultStyledDocument());

		add(editorScrollPane, BorderLayout.CENTER);		
		setSize(900, 500);
		
		//setLocation(150, 80);
		//backdrop__.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		//editor__.requestFocusInWindow();
	}
	
	/*
	private void createAndShowGUI() {
	
		backdrop__ = new JPanel();
		editor__ = new JTextPane();
		JScrollPane editorScrollPane = new JScrollPane(editor__);

		editor__.setDocument(new DefaultStyledDocument());

		backdrop__.add(editorScrollPane, BorderLayout.CENTER);		
		backdrop__.setSize(900, 500);
		backdrop__.setLocation(150, 80);
		//backdrop__.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		backdrop__.setVisible(true);
		
		editor__.requestFocusInWindow();
	}
	*/
}