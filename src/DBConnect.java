import java.io.InputStream;
import java.sql.*;

public class DBConnect {
	private Connection con;
	private Statement st; //your SQL query
	//private ResultSet rs;
	
	public DBConnect() {
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", ""); //For local
			//^Things don't seem to work if I use port 3306?
			//Paste the con for GoDaddy here (see DBConnect.java in Text Editor With Database)
			//con = DriverManager.getConnection("jdbc:mysql://107.180.58.30:3306/CT_Handbook", "wutdaheck", "scwutdaheck2000"); //For GoDaddy
			//System.out.println("YOOOOOO");
			
			st = con.createStatement();
			
		}catch(Exception e) {
			System.out.println("Error: " + e);
		}
	}
	
	
	//TEST VERSION
	public ResultSet getData() {
		
		ResultSet rs = null;
		
		try {
			
			String query = "SELECT * FROM panel_content_html WHERE id=42";
			//^HARD-CODED ID (42 for local, 5 for GoDaddy)
			//String query = "SELECT * FROM panel_content";

			rs = st.executeQuery(query);

		} catch(Exception e) {
			System.out.println(e);
		}
		
		return rs;
	}
	
	
	//FOR GETTING PARTICULAR RECORDS
	public ResultSet getData(String contentCategory) {
		
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM panel_content_html WHERE category='"+contentCategory+"'";
			rs = st.executeQuery(query);
			
			//PreparedStatement pstmt = con.prepareStatement("SELECT * FROM panel_content WHERE category=?");
			//pstmt.setString(1, contentCategory);
			//pstmt.executeUpdate();
			//Note that you can't actually executeUpdate() for SELECT statements lol...

		} catch(Exception e) {
			System.out.println(e);
		}
		
		return rs;
	}
	
	
	//TEST VERSION
	public void updateData(InputStream is) {
		try {
			PreparedStatement pstmt = con.prepareStatement("UPDATE panel_content_html SET content=? WHERE id=42"); 
			//^HARD-CODED ID (42 for local, 5 for GoDaddy)
			
		    pstmt.setBlob(1, is);
		    pstmt.executeUpdate();
					    
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	
	//FOR UPDATING SPECIFIC RECORDS
	public void updateData(InputStream is, String contentCategory) {
		try {
			PreparedStatement pstmt = con.prepareStatement("UPDATE panel_content_html SET content=? WHERE category=?"); 
			//^HARD-CODED ID (42 for local, 5 for GoDaddy)
			
		    pstmt.setBlob(1, is);
		    pstmt.setString(2, contentCategory);
		    
		    pstmt.executeUpdate();
					    
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	
	public void insertData(InputStream is) {
		try {
			
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO panel_content_html (category, content) VALUES (?, ?)");
			//^Just leave id out of the attributes tuple. Since it's set auto-increment in the database, it'll be taken care of automatically.
						
		    pstmt.setString(1, "Testing123");
		    pstmt.setBlob(2, is);
		    pstmt.executeUpdate();
			
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	
	public void close() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
