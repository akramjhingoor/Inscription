package inscriptions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

public class RemplirBarreSaisie {
	Connection connection = null;
	
	public static void RemplirCompetition(Connection connection, JTextField textFieldIdComp, JTextField textFieldNomComp, JTextField textFieldDateComp, JRadioButton rdbtnOuiEquipe, JRadioButton rdbtnNonEquipe, JTable tableCompetitions)
	{
		try
		{
			int row = tableCompetitions.getSelectedRow();
			String id_comp = (tableCompetitions.getModel().getValueAt(row, 0).toString());
			String query = "select * from competition where id_comp ='"+id_comp+"'";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				textFieldIdComp.setText(rs.getString("id_comp"));
				textFieldNomComp.setText(rs.getString("nom"));
				textFieldDateComp.setText(rs.getString("datecloture"));
				if(rs.getInt("enEquipe") == 1)
				{
					rdbtnOuiEquipe.setSelected(true);
					rdbtnNonEquipe.setSelected(false);
				}
				else
				{
					rdbtnOuiEquipe.setSelected(false);
					rdbtnNonEquipe.setSelected(true);
				}
			}
			pst.close();
			rs.close();
		}
		catch (Exception ej)
		{
			ej.printStackTrace();
		}
	}
	
	public static void RemplirEquipe(Connection connection, JTable tableEquipes, JTextField textFieldIdEquipe, JTextField textFieldNomEquipe)
	{
		try
		{
			int row = tableEquipes.getSelectedRow();
			String id_candE = (tableEquipes.getModel().getValueAt(row, 0).toString());
			String query = "select * from equipe where id_candE ='"+id_candE+"'";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				textFieldIdEquipe.setText(rs.getString("id_candE"));
				textFieldNomEquipe.setText(rs.getString("nom"));
			}
			pst.close();
			rs.close();
		}
		catch (Exception ej)
		{
			ej.printStackTrace();
		}
	}
	
	public static void RemplirPersonne(Connection connection, JTable tableDesPersonnes, JTextField textFieldNomPersonne, JTextField textFieldPrenomPersonne, JTextField textFieldMailPersonne)
	{
		try
		{
			int row = tableDesPersonnes.getSelectedRow();
			String Nom_ = (tableDesPersonnes.getModel().getValueAt(row, 0).toString());
			String query = "select * from personne where nom ='"+Nom_+"'";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				textFieldNomPersonne.setText(rs.getString("nom"));
				textFieldPrenomPersonne.setText(rs.getString("prenom"));
				textFieldMailPersonne.setText(rs.getString("mail"));
			}
			pst.close();
			rs.close();
		}
		catch (Exception ej)
		{
			ej.printStackTrace();
		}
	}
	

}
