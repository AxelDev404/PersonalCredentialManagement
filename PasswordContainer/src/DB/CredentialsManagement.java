package DB;

import Credentials.Credentials;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CredentialsManagement 
{
    public void addCredentials(Credentials credentials , int id)
    {
        try 
        {
         Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/credentials_management" , "root" , "root2003A03");
         String sql = "INSERT INTO credentials (id_user_credentials , pwd , username , email , product) VALUES (?, ?, ?, ?, ?)";

         PreparedStatement ps = connection.prepareStatement(sql);
         
         ps.setInt(1, id);
         ps.setString(2,credentials.getPasswordCredentials());
         ps.setString(3, credentials.getUsernameCredentials());
         ps.setString(4, credentials.getEmailCredentials());
         ps.setString(5, credentials.getPorduct());

         ps.executeUpdate();
         
         System.out.println("Credential was added successfully");

        } catch(SQLException e){e.printStackTrace();}
    }   
}
