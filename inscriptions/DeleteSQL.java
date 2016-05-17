package inscriptions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DeleteSQL {
	Connection connection = null;
	public static void SupprimerUneEquipe(Connection connection, JTextField textFieldIdEquipe)
	{
		int action = JOptionPane.showConfirmDialog(null, "Do you really want to delete?", "Delete", JOptionPane.YES_NO_OPTION);
		if(action == 0)
		{
			try
			{
				String query = "delete from candidat where id_cand = '"+textFieldIdEquipe.getText()+"' ";
				PreparedStatement pst = connection.prepareStatement(query);
				pst.execute();
				JOptionPane.showMessageDialog(null, "Data deleted");
				pst.close();
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}
	
	public static void RetirerUnCdansI(Connection connection, Object row, Object row1)
	{
		try
		{
			int n = 0;
			int n4= 0;
				String query = "SELECT id_candP FROM personne WHERE prenom = '"+ row +"'";
				PreparedStatement pst = connection.prepareStatement(query);
				ResultSet idcand = pst.executeQuery();
				if(idcand.next())
				{
					n = idcand.getInt("id_candP");
				}
				else
				{
					
					String query2 = "SELECT id_candE FROM equipe WHERE nom = '"+ row +"'";
					PreparedStatement pst1 = connection.prepareStatement(query2);
					ResultSet idcand2 = pst1.executeQuery();
					idcand2.next();
					n = idcand2.getInt("id_candE");
				}
				String query3 = "SELECT id_comp FROM competition WHERE nom = '"+ row1 +"'";
				PreparedStatement pst2 = connection.prepareStatement(query3);
				ResultSet idcand3 = pst2.executeQuery();
				idcand3.next();
				n4 = idcand3.getInt("id_comp");
				
				String query1 = "DELETE FROM inscrire WHERE id_cand ='"+ n + "' AND id_comp = '"+n4+"'";
				Statement s = connection.createStatement();
				s.executeUpdate(query1);
				pst.close();
				s.close();
				JOptionPane.showMessageDialog(null, "Data Saved");
		}
		catch(Exception erty)
		{
			erty.printStackTrace();
		}
	}
	
	public static void SupprimerUneCompetition(Connection connection, JTextField textFieldNomComp, JTextField textFieldIdComp)
	{
		int action = JOptionPane.showConfirmDialog(null, "Do you really want to delete?", "Delete", JOptionPane.YES_NO_OPTION);
		if(action == 0)
		{
			try
			{
				String query = "delete from Competition where nom = '"+textFieldNomComp.getText()+"' OR id_comp = '"+textFieldIdComp.getText()+"'";
				PreparedStatement pst = connection.prepareStatement(query);
				pst.execute();
				JOptionPane.showMessageDialog(null, "Data deleted");
				pst.close();
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
			
		}
	}
	
	public static void RetirerUnePdeE(Connection connection, Object row, Object row1)
	{
 		try
 		{
 			int n = 0;
 			int n2= 0;
 			String query = "SELECT id_candP FROM personne WHERE prenom = '"+ row +"'";
 			PreparedStatement pst = connection.prepareStatement(query);
 			ResultSet idcand = pst.executeQuery();
 			idcand.next();
 			n = idcand.getInt("id_candP");
 			
 			String query2 = "SELECT id_candE FROM equipe WHERE nom = '"+ row1 +"'";
 			PreparedStatement pst1 = connection.prepareStatement(query2);
 			ResultSet idcand2 = pst1.executeQuery();
 			idcand2.next();
 			n2 = idcand2.getInt("id_candE");
 		
 			String query1 = "DELETE FROM appartenir WHERE id_candP = '"+n +"' and id_candE = '"+n2+"' ";
 			Statement s = connection.createStatement();
 			s.executeUpdate(query1);

 			pst.close();
 			s.close();
 			JOptionPane.showMessageDialog(null, "Data deleted");
 		}
 		catch(Exception e1)
 		{
 			e1.printStackTrace();
 		}
	}
	
	public static void SupprimerUnePersonne(Connection connection, JTextField textFieldNomPersonne)
	{
		int action = JOptionPane.showConfirmDialog(null, "Do you really want to delete?", "Delete", JOptionPane.YES_NO_OPTION);
		if(action == 0)
		{
			try
			{
				String query = "delete from Personne where nom = '"+textFieldNomPersonne.getText()+"' ";
				PreparedStatement pst = connection.prepareStatement(query);
				pst.execute();
				JOptionPane.showMessageDialog(null, "Data deleted");
				pst.close();
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
			
		}
	}

}
