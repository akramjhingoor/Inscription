package inscriptions;

import java.sql.*;
import javax.swing.*;

/*METHODES POUR CHARGER LES LISTES AUTOMATIQUEMENT A PARTIR DE LA BASE DE DONNEES*/
public class LoadList {
	Connection connection = null;
	public static void loadlistPersonne(JList<String> listPersonne, Connection connection)
	{
		try
		{
			String query = "select * from personne";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			DefaultListModel<String> DLM = new DefaultListModel<String>();
			
			while(rs.next())
			{
				DLM.addElement(rs.getString("prenom"));
				
			}
			listPersonne.setModel(DLM);
			rs.close();
			pst.close();
		}
		catch(Exception er)
		{
			er.printStackTrace();
		}
	}
	
	public static void loadlistEquipe(JList<String> listEquipe, Connection connection)
	{
		try
		{
			String query = "select * from equipe";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			DefaultListModel<String> DLM = new DefaultListModel<String>();
			
			while(rs.next())
			{
				DLM.addElement(rs.getString("nom"));
				
			}
			listEquipe.setModel(DLM);
			rs.close();
			pst.close();
		}
		catch(Exception ert)
		{
			ert.printStackTrace();
		}
	}
	public static void loadListDesCompetitions(JList<String> listDesCompetitions, Connection connection)
	{
		try
		{
			String query = "select * from competition";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			DefaultListModel<String> DLM = new DefaultListModel<String>();
			
			while(rs.next())
			{
				DLM.addElement(rs.getString("nom"));
				
			}
			listDesCompetitions.setModel(DLM);
			rs.close();
			pst.close();
		}
		catch(Exception ert)
		{
			ert.printStackTrace();
		}
	}
	
	public static void loadListDesEquipes(JList<String> listDesEquipes, Connection connection)
	{
		try
		{
			String query = "select * from equipe";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			DefaultListModel<String> DLM = new DefaultListModel<String>();
			
			while(rs.next())
			{
				DLM.addElement(rs.getString("nom"));
				
			}
			listDesEquipes.setModel(DLM);
			rs.close();
			pst.close();
		}
		catch(Exception ert)
		{
			ert.printStackTrace();
		}
	}
	
	public static void loadlistCompetition(JList<String> listCompetition, Connection connection)
	{
		try
		{	
			String query = "select * from competition ";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			DefaultListModel<String> DLM = new DefaultListModel<String>();
			
			while(rs.next())
			{
				DLM.addElement(rs.getString("nom"));

			} 
			listCompetition.setModel(DLM);
			rs.close();
			pst.close(); 
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
	}
	
	public static void loadlistDesCandidats(JList<String> listCandidat, Connection connection)
	{
		try
		{	
			
			String query1 = "select * from personne ";
			PreparedStatement pst1 = connection.prepareStatement(query1);
			ResultSet rs1 = pst1.executeQuery();
			
			String query2 = "select * from equipe ";
			PreparedStatement pst2 = connection.prepareStatement(query2);
			ResultSet rs2 = pst2.executeQuery();
			
			DefaultListModel<String> DLM = new DefaultListModel<String>();
			
			while(rs1.next())
			{
				DLM.addElement(rs1.getString("prenom"));
			} 
			
			while(rs2.next())
			{
				DLM.addElement(rs2.getString("nom"));
			}
			
			listCandidat.setModel(DLM);
			rs1.close();
			pst1.close(); 
			rs2.close();
			pst2.close(); 
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
	}

}
