import java.awt.CardLayout;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class OfficeSuppliesCLContent extends MenuItemContent{
	private static final long serialVersionUID = 1L;

	public OfficeSuppliesCLContent(JPanel backMenuScreen, CardLayout backMenuScreenCl) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("Office Supplies Checklist");
		//fillBasicContent("screen_content/office_supplies_checklist.html");
		//createTextEdit();
		createTextEdit("office_supp_CL");
	}
	
	public OfficeSuppliesCLContent(JPanel backMenuScreen, CardLayout backMenuScreenCl, DBConnect connect) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("Office Supplies Checklist");
		//fillBasicContent("screen_content/office_supplies_checklist.html");
		//createTextEdit();
		createTextEdit("office_supp_CL", connect);
	}
	
	
	@Override
	//Got code from http://stackoverflow.com/questions/11753042/jeditorpane-hyperlink-swing-html for hyperlink listener stuff
	protected void fillBasicContent(String fileName) {
		JTextPane content = new JTextPane();	
		content.setEditorKit(JTextPane.createEditorKitForContentType("text/html")); //Set the EditorKit
		content.setEditable(false);
		content.setContentType("text/html");
		
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
            		Main.class.getResourceAsStream(fileName), StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder(); //More memory-efficient to use StringBuilders
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
		contentScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		SwingUtilities.invokeLater(new Runnable() { //This is the only way I can get the JScrollPane to default to a 0 value...
			   public void run() { 
				   contentScroll.getVerticalScrollBar().setValue(0);
			   }
			});
		add(contentScroll, "push, grow, wrap");
	}
}
