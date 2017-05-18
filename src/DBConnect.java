import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.sql.*;

public class DBConnect {
	private Connection con;
	private Statement st; //your SQL query
	private ResultSet rs;
	
	public DBConnect() {
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", ""); //For local
			//^Things don't seem to work if I use port 3306?
			//Paste the con for GoDaddy here (see DBConnect.java in Text Editor With Database)
			st = con.createStatement();
			
		}catch(Exception e) {
			System.out.println("Error: " + e);
		}
	}
	
	
	public ResultSet getData() {
		
		ResultSet rs = null;
		
		try {
			
			String query = "SELECT * FROM panel_content WHERE id=42";
			//^HARD-CODED ID (42 for local, 5 for GoDaddy)
			//String query = "SELECT * FROM panel_content";

			rs = st.executeQuery(query);

		} catch(Exception e) {
			System.out.println(e);
		}
		
		return rs;
	}

	
	
	public void updateData(InputStream is) {
		try {
			PreparedStatement pstmt = con.prepareStatement("UPDATE panel_content SET content=? WHERE id=42"); 
			//^HARD-CODED ID (42 for local, 5 for GoDaddy)
			
		    pstmt.setBlob(1, is);
		    pstmt.executeUpdate();
					    
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	
	public void insertData(InputStream is) {
		try {
			
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO panel_content (category, content) VALUES (?, ?)");
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
