import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;

public class CTLogo extends JPanel {
	private static final long serialVersionUID = 1L; //Eclipse suggested this
	ImageIcon image;
	
	public CTLogo() {
		String pathToLogo = "images/ct_logo.png"; //images is a directory that must also be in the src directory
		image = new ImageIcon(getClass().getClassLoader().getResource(pathToLogo));
	}
	
	public Dimension getPreferredSize() {
	    return (new Dimension(422, 247));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.drawImage(image, 0, 0, null);
		image.paintIcon(this, g, 0, 0);
	}
}
