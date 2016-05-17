package inscriptions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ControleSaisie {
	Connection connection = null;
	public static void ControleSaisieCreerCompetition(Connection connection, JRadioButton rdbtnOuiEquipe, JRadioButton rdbtnNonEquipe, JTextField textFieldNomComp, JTextField textFieldDateComp)
	{
		if (rdbtnOuiEquipe.isSelected() || rdbtnNonEquipe.isSelected() && !textFieldNomComp.getText().isEmpty() && !textFieldDateComp.getText().isEmpty())
		{
					try
					{
						String query = "SELECT * FROM competition WHERE nom = '"+textFieldNomComp.getText()+"' AND datecloture ='"+textFieldDateComp.getText()+"'";
						PreparedStatement pst = connection.prepareStatement(query);
						ResultSet rs = pst.executeQuery();	
						
						if(rs.next())
							JOptionPane.showMessageDialog(null, "Cette compétition existe déjà");
						else
							InsertSQL.CreerUneCompetition(connection, rdbtnOuiEquipe, textFieldNomComp, textFieldDateComp);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}	
		}
		else
			JOptionPane.showMessageDialog(null, "Champ vide", "Erreur", JOptionPane.ERROR_MESSAGE);	
	}
	
	public static void ControleSaisieModifierCompetition(Connection connection, JTextField textFieldIdComp, JRadioButton rdbtnOuiEquipe, JRadioButton rdbtnNonEquipe, JTextField textFieldNomComp, JTextField textFieldDateComp)
	{
		if (rdbtnOuiEquipe.isSelected() || rdbtnNonEquipe.isSelected() && !textFieldNomComp.getText().isEmpty() && !textFieldDateComp.getText().isEmpty())
		{
					try
					{
						String query = "SELECT * FROM competition WHERE nom = '"+textFieldNomComp.getText()+"' AND datecloture ='"+textFieldDateComp.getText()+"'";
						PreparedStatement pst = connection.prepareStatement(query);
						ResultSet rs = pst.executeQuery();	
						
						if(rs.next())
							JOptionPane.showMessageDialog(null, "Aucune modification effectué");
						else
							UpdateSQL.ModifierUneCompetition(connection, textFieldNomComp, textFieldDateComp, textFieldIdComp);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}	
		}
		else
			JOptionPane.showMessageDialog(null, "Champ vide", "Erreur", JOptionPane.ERROR_MESSAGE);	
	}
	
	public static void ControleSaisieCreerPersonne(Connection connection, JTextField textFieldNomPersonne, JTextField textFieldPrenomPersonne, JTextField textFieldMailPersonne)
	{
		if (!textFieldNomPersonne.getText().isEmpty() && !textFieldPrenomPersonne.getText().isEmpty() && !textFieldMailPersonne.getText().isEmpty())
		{
					try
					{
						String query = "SELECT * FROM personne WHERE nom = '"+textFieldNomPersonne.getText()+"' AND prenom ='"+textFieldPrenomPersonne.getText()+"'";
						PreparedStatement pst = connection.prepareStatement(query);
						ResultSet rs = pst.executeQuery();	
						
						if(rs.next())
							JOptionPane.showMessageDialog(null, "Cette personne existe déjà");
						else
							InsertSQL.CreerUnePersonne(connection, textFieldNomPersonne, textFieldPrenomPersonne, textFieldMailPersonne);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}	
		}
		else
			JOptionPane.showMessageDialog(null, "Champ vide", "Erreur", JOptionPane.ERROR_MESSAGE);	
	}
	
	public static void ControleSaisieModifierPersonne(Connection connection, JTextField textFieldNomPersonne, JTextField textFieldPrenomPersonne, JTextField textFieldMailPersonne)
	{
		if (!textFieldNomPersonne.getText().isEmpty() && !textFieldPrenomPersonne.getText().isEmpty() && !textFieldMailPersonne.getText().isEmpty())
		{
					try
					{
						String query = "SELECT * FROM personne WHERE nom = '"+textFieldNomPersonne.getText()+"' AND prenom ='"+textFieldPrenomPersonne.getText()+"' AND mail = '"+textFieldMailPersonne.getText()+"'";
						PreparedStatement pst = connection.prepareStatement(query);
						ResultSet rs = pst.executeQuery();	
						
						if(rs.next())
							JOptionPane.showMessageDialog(null, "Aucune information modifié");
						else
							UpdateSQL.ModifierUnePersonne(connection, textFieldMailPersonne, textFieldNomPersonne);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}	
		}
		else
			JOptionPane.showMessageDialog(null, "Champ vide", "Erreur", JOptionPane.ERROR_MESSAGE);	
	}
	
	public static void ControleSaisieCreerEquipe(Connection connection, JTextField textFieldNomEquipe)
	{
		if (!textFieldNomEquipe.getText().isEmpty())
		{
					try
					{
						String query = "SELECT * FROM equipe WHERE nom = '"+textFieldNomEquipe.getText()+"'";
						PreparedStatement pst = connection.prepareStatement(query);
						ResultSet rs = pst.executeQuery();	
						
						if(rs.next())
							JOptionPane.showMessageDialog(null, "Cette équipe existe déjà");
						else
							InsertSQL.CreerUneEquipe(connection, textFieldNomEquipe);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}	
		}
		else
			JOptionPane.showMessageDialog(null, "Champ vide", "Erreur", JOptionPane.ERROR_MESSAGE);	
	}
	
	public static void ControleSaisieModifierEquipe(Connection connection, JTextField textFieldNomEquipe,JTextField textFieldIdEquipe)
	{
		if (!textFieldNomEquipe.getText().isEmpty())
		{
					try
					{
						String query = "SELECT * FROM equipe WHERE id_candE = '"+textFieldIdEquipe.getText()+"' AND nom = '"+textFieldNomEquipe.getText()+"'";
						PreparedStatement pst = connection.prepareStatement(query);
						ResultSet rs = pst.executeQuery();	
						
						if(rs.next())
							JOptionPane.showMessageDialog(null, "Cette équipe existe déjà");
						else
							UpdateSQL.ModifierUneEquipe(connection, textFieldNomEquipe, textFieldIdEquipe);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}	
		}
		else
			JOptionPane.showMessageDialog(null, "Champ vide", "Erreur", JOptionPane.ERROR_MESSAGE);	
	}
	
	public static void ControleSaisieAjouterUnCdansI(Connection connection, Object row,Object row1)
	{
		try
		{
			
			int id_cand = 0;
			int id_comp= 0;
				String query = "SELECT id_candP FROM personne WHERE prenom = '"+ row +"'";
				PreparedStatement pst = connection.prepareStatement(query);
				ResultSet idcand = pst.executeQuery();
				if(idcand.next())
				{
					id_cand = idcand.getInt("id_candP");
				}
				else
				{
					
					String query2 = "SELECT id_candE FROM equipe WHERE nom = '"+ row +"'";
					PreparedStatement pst1 = connection.prepareStatement(query2);
					ResultSet idcand2 = pst1.executeQuery();
					idcand2.next();
					id_cand = idcand2.getInt("id_candE");
				}
				
				String query3 = "SELECT id_comp FROM competition WHERE nom = '"+ row1 +"'";
				PreparedStatement pst2 = connection.prepareStatement(query3);
				ResultSet idcand3 = pst2.executeQuery();
				idcand3.next();
				id_comp = idcand3.getInt("id_comp");
				
			String query4 = "SELECT * FROM inscrire WHERE id_cand = '"+id_cand+"' AND id_comp = '"+id_comp+"'";
			PreparedStatement pst3 = connection.prepareStatement(query4);
			ResultSet rs = pst3.executeQuery();	
			
			if(rs.next())
				JOptionPane.showMessageDialog(null, "Ce candidat est déjà inscrit à cette compétition");
			else
				InsertSQL.AjouterUnCdansI(connection, row, row1);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}	
	}
	
	public static void RetirerUnCdansI(Connection connection, Object row, Object row1)
	{
		try
		{
			int id_cand = 0;
			int id_comp= 0;
				String query = "SELECT id_candP FROM personne WHERE prenom = '"+ row +"'";
				PreparedStatement pst = connection.prepareStatement(query);
				ResultSet idcand = pst.executeQuery();
				if(idcand.next())
				{
					id_cand = idcand.getInt("id_candP");
				}
				else
				{
					
					String query2 = "SELECT id_candE FROM equipe WHERE nom = '"+ row +"'";
					PreparedStatement pst1 = connection.prepareStatement(query2);
					ResultSet idcand2 = pst1.executeQuery();
					idcand2.next();
					id_cand = idcand2.getInt("id_candE");
				}
				
				String query3 = "SELECT id_comp FROM competition WHERE nom = '"+ row1 +"'";
				PreparedStatement pst2 = connection.prepareStatement(query3);
				ResultSet idcand3 = pst2.executeQuery();
				idcand3.next();
				id_comp = idcand3.getInt("id_comp");
				
			String query4 = "SELECT * FROM inscrire WHERE id_cand = '"+id_cand+"' AND id_comp = '"+id_comp+"'";
			PreparedStatement pst3 = connection.prepareStatement(query4);
			ResultSet rs = pst3.executeQuery();	
			
			if(!rs.next())
				JOptionPane.showMessageDialog(null, "Ce candidat n'est pas inscrit à cette compétition");
			else
				DeleteSQL.RetirerUnCdansI(connection, row, row1);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void ControleSaisieAjouterUnePdansE(Connection connection, Object row, Object row1)
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

 			String query4 = "SELECT * FROM appartenir WHERE id_candP = '"+n+"' AND id_candE = '"+n2+"'";
			PreparedStatement pst3 = connection.prepareStatement(query4);
			ResultSet rs = pst3.executeQuery();	
			
			if(rs.next())
				JOptionPane.showMessageDialog(null, "Ce candidat appartient déjà à cette équipe");
			else
				InsertSQL.AjouterUnePdansE(connection, row, row1);
 		}
 		catch(Exception e1)
 		{
 			e1.printStackTrace();
 		}
	}
	
	public static void ControleSaisieRetirerUnePdeE(Connection connection, Object row, Object row1)
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

 			String query4 = "SELECT * FROM appartenir WHERE id_candP = '"+n+"' AND id_candE = '"+n2+"'";
			PreparedStatement pst3 = connection.prepareStatement(query4);
			ResultSet rs = pst3.executeQuery();	
			
			if(!rs.next())
				JOptionPane.showMessageDialog(null, "Ce candidat n'appartient pas à cette équipe");
			else
				DeleteSQL.RetirerUnePdeE(connection, row, row1);
 		}
 		catch(Exception e1)
 		{
 			e1.printStackTrace();
 		}
	}
}
