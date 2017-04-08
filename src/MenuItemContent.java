import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import net.miginfocom.swing.MigLayout;

public class MenuItemContent extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel backMenuScreen;
	private CardLayout backMenuScreenCl;

	public MenuItemContent(final JPanel backMenuScreen, final CardLayout backMenuScreenCl) {
		this.backMenuScreen = backMenuScreen;
		this.backMenuScreenCl = backMenuScreenCl;
		setBackground(Color.WHITE);
		setLayout(new MigLayout());
	}
	
	
	/**
	 * A helper method that creates the header for a content screen.
	 * @param headerTxt The text to put on the header
	 * @param panel The JPanel representing the content screen (must be set with MigLayout)
	 */
	protected void createHeader(String headerTxt) {
		JPanel header = new JPanel();
		header.setBackground(Color.decode("0x026937"));
		header.setLayout(new MigLayout());
		
		JLabel headerLbl = new JLabel(headerTxt); //String is variable
		headerLbl.setFont(new Font("Helvetica", Font.BOLD, 25));
		headerLbl.setForeground(Color.WHITE);
		
		JButton backBtn = new JButton("Back to Main Menu");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backMenuScreenCl.show(backMenuScreen, "ops_main_screen");
			}
		});
		
		header.add(backBtn, "wrap");
		header.add(headerLbl, "gaptop 10"); //HARD-CODED VALUE
		add(header, "height 100, pushx, growx, wrap"); //HARD-CODED VALUE. Setting values for Header here.
	}
	
	
	/**
	 * A helper method that fills in the content for a content screen.
	 * @param fileName Name of the file containing the screen's content
	 * @param panel The JPanel representing the content screen (must be set with a MigLayout)
	 */
	protected void fillBasicContent(String fileName) {
		JTextPane content = new JTextPane();		
		content.setEditable(false);
		content.setContentType("text/html");
		
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
            		Main.class.getResourceAsStream(fileName), StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            
            while (line != null) {
            	sb.append(line);
            	line = br.readLine();
            }
            
            String everything = sb.toString();
            content.setText(everything);
            br.close();         
        }
        catch(FileNotFoundException e) { e.printStackTrace(); }
        catch(IOException e) {e.printStackTrace();}
		
		final JScrollPane contentScroll = new JScrollPane(content);
		//contentScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		SwingUtilities.invokeLater(new Runnable() { //This is the only way I can get the JScrollPane to default to a 0 value...
			   public void run() { 
				   contentScroll.getVerticalScrollBar().setValue(0);
			   }
			});
		add(contentScroll, "push, grow, wrap");
	}
	
	
	/**
	 * A helper method that fills in the content, which contains a button component, for a content screen.
	 * @param fileName Name of the file containing the screen's content
	 * @param panel The JPanel representing the content screen (must be set with a MigLayout)
	 * @param btnTxt The text to be put on the button
	 * @param jumpToPgLbl The label for the screen that the button jumps to
	 */
	protected void fillComponentContent(final JPanel backMenuScreen, final CardLayout backMenuScreenCl, 
			String fileName, String btnTxt, final String jumpToPgLbl) {		
		JTextPane content = new JTextPane();			
		content.setEditable(false);
		content.setContentType("text/html");
		
		/*
		//If componentLst were an ArrayList of pairs...(Note that you'd have to make a Pair class of your own! Java doesn't have one)
		for (int i = 0; i < componentLst.size(); i++) {
			componentLst.get(i).first.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainScreenCl.show(mainScreen, componentLst.get(i).second); 
					//Hmm, this second argument needs to be declared with final. How am I gonna do that with contents in an ArrayList?
				}
			}
		}
		*/
		
		JButton button = new JButton(btnTxt);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backMenuScreenCl.show(backMenuScreen, jumpToPgLbl);  //mainScreenCl and mainScreen must be referred to in this actionListener
			}
		});
		
		//Read each line in an HTML file		
        String line = null;
        List<String> lineLst = new ArrayList<String>();
        try {

            //BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        	//It seems that FileReader doesn't look inside the file structure within the src folder...
            BufferedReader br = new BufferedReader(new InputStreamReader(
            		Main.class.getResourceAsStream(fileName), StandardCharsets.UTF_8));

            while((line = br.readLine()) != null) {
            	lineLst.add(line);
            }
            
            br.close();         
        }
        catch(FileNotFoundException e) { e.printStackTrace(); }
        catch(IOException e) { e.printStackTrace();}
		
        // ============================================ FOCUS HERE!!!!! ============================================
        
		//Make HTMLDocument and HTMLEditorKit
		HTMLDocument contentDoc = (HTMLDocument)content.getDocument(); //Think: The content JPanel as an HTMLDocument
		HTMLEditorKit contentEK = (HTMLEditorKit)content.getEditorKit(); //Think: The object that modifies the HTMLDocument. The "pen".
		
		//Insert HTML into HTMLDocument, which is then rendered on the content JPanel
		for (int i = 0; i < lineLst.size(); i++) {
			if (lineLst.get(i).equals("<!--INSERT-->")) {
				content.insertComponent(button);
				//If you wanted to go the componentLst route, you can have a j index variable and say something like:
				//content.insertComponent(componentLst.get(j)); j++;
			}
			else {
				try {
					contentEK.insertHTML(contentDoc, contentDoc.getLength(), lineLst.get(i), 0, 0, null);
					//Seems like inserting a single opening tag and the corresponding closing tag later in another iteration won't do anything...
					// - If that's the case, you're gonna have to be a bit verbose on how you write your HTML tags in the HTML file. See
					//    work_completed_text.html as an example.
				} 
				catch (BadLocationException e1) { e1.printStackTrace(); } 
				catch (IOException e1) { e1.printStackTrace(); }
			}
		}
		
        // ============================================ FOCUS HERE!!!!! ============================================

		
		
		//Make JScrollPane to contain content
		final JScrollPane contentScroll = new JScrollPane(content);
		contentScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		SwingUtilities.invokeLater(new Runnable() { //This is the only way I can get the JScrollPane to default to a 0 value...
			   public void run() { 
				   contentScroll.getVerticalScrollBar().setValue(0);
			   }
			});
		//Add JScrollPant onto JPanel for this screen
		add(contentScroll, "push, grow");		
	}
	
	
	/**
	 * A helper method that adds a button to a panel for copying the panel's content text to
	 * the OS clip board.
	 * @param panel The panel with the content pane containing the text of interest
	 */
	protected void addTextToCBBtn() {
		JButton copyTxtBtn = new JButton("Copy text to clipboard");
		add(copyTxtBtn);
		
		final int JSCROLLPANE_COMPONENT_INDEX = 1;
		copyTxtBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HTMLToText parser = new HTMLToText();
				try {
					JScrollPane sPane = (JScrollPane)getComponent(JSCROLLPANE_COMPONENT_INDEX);
					JViewport vPort = sPane.getViewport();
					JTextPane tPane = (JTextPane)vPort.getView();
				    parser.parse(new StringReader(tPane.getText()));
				    
					StringSelection stringSelection = new StringSelection(parser.getText());
					Clipboard clpBrd = Toolkit.getDefaultToolkit().getSystemClipboard();
					clpBrd.setContents(stringSelection, null);
				} catch (IOException ee) { ee.printStackTrace(); }
			}
		});
	}
}

