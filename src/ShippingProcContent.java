import java.awt.CardLayout;
import javax.swing.JPanel;


public class ShippingProcContent extends MenuItemContent {
	private static final long serialVersionUID = 1L;

	public ShippingProcContent(JPanel backMenuScreen, CardLayout backMenuScreenCl) {
		super(backMenuScreen, backMenuScreenCl);
		createHeader("Shipping Procedures");
		//fillBasicContent("screen_content/shipping_procedures.html");
		createTextEdit();
	}
	
}
