import java.awt.CardLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;


public class WalkinWOContent extends MenuItemContent {
	private static final long serialVersionUID = 1L;

	public WalkinWOContent(final JPanel backMenuScreen, final CardLayout backMenuScreenCl) {
		super(backMenuScreen, backMenuScreenCl);
		
		createHeader("WALKIN-IN WORK ORDER TEMPLATE");	
		fillBasicContent("screen_content/walkinWO.html");
		addTextToCBBtn();
		JPanel walkinWOheader = (JPanel)getComponent(0);
		JButton backToNewRepDia = new JButton("Back to New Repairs Dialogue");
		backToNewRepDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backMenuScreenCl.show(backMenuScreen, "new_repair_dialogue_screen");
			}
		});
		walkinWOheader.add(backToNewRepDia, "cell 0 0");
		
	}

	@Override
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
		
        //Add a hyperlink listener!
        content.addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    if (Desktop.isDesktopSupported()) {
                        try {
                            Desktop.getDesktop().browse(e.getURL().toURI());
                        } 
                        catch (IOException e1) { e1.printStackTrace(); } 
                        catch (URISyntaxException e1) { e1.printStackTrace();}
                    }
                }
            }
        });
        
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

}
