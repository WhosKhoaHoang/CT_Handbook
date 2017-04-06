import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
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
}
