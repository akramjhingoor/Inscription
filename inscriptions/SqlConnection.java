package inscriptions;
import java.sql.*;
import javax.swing.*;
public class SqlConnection {
	Connection conn = null;
	public static Connection dbConnector()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String way = "jdbc:mysql://localhost/inscription";
			String user = "root";
			String mdp = "root";
			Connection conn = DriverManager.getConnection(way, user, mdp);
			JOptionPane.showMessageDialog(null, "Connexion réussite ! Bienvenue !");
			return conn;
		} catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}

}
