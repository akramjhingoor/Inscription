package inscriptions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class InsertSQL {
	Connection connection = null;
	public static void CreerUnePersonne(Connection connection, JTextField textFieldNomPersonne, JTextField textFieldPrenomPersonne, JTextField textFieldMailPersonne)
	{
		try
		{
			LocalDateTime datecreation =  LocalDateTime.now();
			String query = "insert into Candidat (datecreation) values (?)";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, datecreation.toString());
			pst.execute();
			pst.close();
			
			int id = 0;
			String query2 ="SELECT id_cand FROM candidat WHERE datecreation = '"+datecreation+"'";
			PreparedStatement pst2 = connection.prepareStatement(query2);
			ResultSet rs = pst2.executeQuery(); 
			rs.next();
			id = rs.getInt("id_cand");
			
			String query1 = "insert into Personne (id_candP, nom, prenom, mail) values (?, ?, ?, ?)";
			PreparedStatement pst1 = connection.prepareStatement(query1);
			pst1.setInt(1, id);
			pst1.setString(2, textFieldNomPersonne.getText());
			pst1.setString(3, textFieldPrenomPersonne.getText());
			pst1.setString(4, textFieldMailPersonne.getText());
			pst1.execute();
			JOptionPane.showMessageDialog(null, "Data Saved");
			pst1.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void CreerUneEquipe(Connection connection, JTextField textFieldNomEquipe)
	{
		try
		{
			LocalDateTime datecreation =  LocalDateTime.now();
			String query = "insert into Candidat (datecreation) values (?)";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, datecreation.toString());
			pst.execute();
			pst.close();
			
			int id = 0;
			String query2 ="SELECT id_cand FROM candidat WHERE datecreation = '"+datecreation+"'";
			PreparedStatement pst2 = connection.prepareStatement(query2);
			ResultSet rs = pst2.executeQuery(); 
			rs.next();
			id = rs.getInt("id_cand");
			
			String query1 = "insert into Equipe (id_candE, nom) values (?, ?)";
			PreparedStatement pst1 = connection.prepareStatement(query1);
			pst1.setInt(1, id);
			pst1.setString(2, textFieldNomEquipe.getText());
			pst1.execute();
			JOptionPane.showMessageDialog(null, "Data Saved");
			pst1.close();
		}
		catch (Exception ea)
		{
			ea.printStackTrace();
		}
	}
	
	public static void CreerUneCompetition(Connection connection, JRadioButton rdbtnOuiEquipe, JTextField textFieldNomComp, JTextField textFieldDateComp)
	{
		try
		{
			String enEquipe = "";
			if (rdbtnOuiEquipe.isSelected())
			{
				enEquipe = "1";
			}
			else
			{
				enEquipe = "0";
			}
			String query1 = "insert into Competition (nom, datecloture, enEquipe) values (?, ?, ?)";
			PreparedStatement pst1 = connection.prepareStatement(query1);
			pst1.setString(1, textFieldNomComp.getText());
			pst1.setString(2, textFieldDateComp.getText());
			pst1.setString(3, enEquipe.toString());
			pst1.execute();
			JOptionPane.showMessageDialog(null, "Data Saved");
			pst1.close();
		}
		catch (Exception ea)
		{
			ea.printStackTrace();
		}
	}
	
	public static void AjouterUnCdansI(Connection connection, Object row, Object row1)
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
				
				String query1 = "INSERT INTO inscrire(id_cand, id_comp) VALUES ('"+ n + "', '"+n4+"')";
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
	
	public static void AjouterUnePdansE(Connection connection, Object row, Object row1)
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
 		
 			String query1 = "INSERT INTO appartenir(id_candP, id_candE) VALUES ('"+ n + "', '"+n2+"')";
 			Statement s = connection.createStatement();
 			s.executeUpdate(query1);

 			pst.close();
 			s.close();
 			JOptionPane.showMessageDialog(null, "Data Saved");
 		}
 		catch(Exception e1)
 		{
 			e1.printStackTrace();
 		}
	}

}
