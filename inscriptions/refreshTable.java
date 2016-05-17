package inscriptions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

/*METHODES POUR RAFRAICHIR LES TABLES AUTOMATIQUEMENT A CHAQUE CREATION, MODIFICATION OU SUPPRESSION*/
public class refreshTable {
	Connection connection = null;
	public static void refreshTablePersonnes(JTable table_1, Connection connection)
	{
		try
		{
			String query = "select nom, prenom, mail from personne";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
			rs.close();
			pst.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void refreshTableEquipes(JTable table, Connection connection)
	{
		try
		{
			String query = "select id_candE, nom from equipe";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			rs.close();
			pst.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void refreshTableCompetitions(JTable table_2, Connection connection)
	{
		try
		{
			String query = "select * from competition";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table_2.setModel(DbUtils.resultSetToTableModel(rs));
			rs.close();
			pst.close();
			
		}
		catch(Exception ek)
		{
			ek.printStackTrace();
		}
	}

}
