package inscriptions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

public class ShowTable {
	Connection connection = null;
	public static void AfficherTablePersonne(JTable tableDesPersonnes, Connection connection)
	{
		try
		{
			String query = "select nom, prenom, mail from personne";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableDesPersonnes.setModel(DbUtils.resultSetToTableModel(rs));
			rs.close();
			pst.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void AfficherTableCompetitions(JTable tableCompetitions, Connection connection)
	{
		try
		{
			String query = "select * from competition";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableCompetitions.setModel(DbUtils.resultSetToTableModel(rs));
			rs.close();
			pst.close();
			
		}
		catch(Exception ek)
		{
			ek.printStackTrace();
		}
	}

	public static void AfficherTableEquipes(JTable tableEquipes, Connection connection)
	{
		try
		{
			String query = "select id_candE, nom from equipe";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableEquipes.setModel(DbUtils.resultSetToTableModel(rs));
			rs.close();
			pst.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void AfficherLaListeDesMembres(Connection connection, Object selectedValue, JTable tableListeDesMembres)
	{
		try
		{
				String query = "SELECT prenom, personne.nom, mail FROM appartenir, personne, equipe WHERE appartenir.id_candE = equipe.id_candE AND appartenir.id_candP = personne.id_candP AND equipe.nom = '"+ selectedValue +"'";
				PreparedStatement pst = connection.prepareStatement(query);
				ResultSet rs = pst.executeQuery();
				tableListeDesMembres.setModel(DbUtils.resultSetToTableModel(rs));
				rs.close();
				pst.close();
		}
		catch(Exception erty)
		{
			erty.printStackTrace();
		}
	}
	
	public static void AfficherLaListeDesInscrits(Connection connection, Object selectedValue, JTable tableListeDesInscrits)
	{
		try
		{
				String query = "SELECT IF (candidat.id_cand = personne.id_candP && candidat.id_cand = inscrire.id_cand, personne.nom, IF (candidat.id_cand = equipe.id_candE && candidat.id_cand = inscrire.id_cand, equipe.nom, '')) AS 'nom' FROM candidat, equipe, personne, inscrire, competition WHERE inscrire.id_comp = competition.id_comp AND competition.nom = '"+ selectedValue +"' GROUP BY nom LIMIT 100 OFFSET 1";
				PreparedStatement pst = connection.prepareStatement(query);
				ResultSet rs = pst.executeQuery();
				tableListeDesInscrits.setModel(DbUtils.resultSetToTableModel(rs));
				rs.close();
				pst.close();
		}
		catch(Exception erty)
		{
			erty.printStackTrace();
		}
	}
}
