import javax.swing.SwingUtilities;

//TASK:
//. Find out how to properly transition with CardLayout to a SalesBarManual

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new CTHandbook();
			}
		});
	}
}
