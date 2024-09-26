package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import User.User;

public class UserManagementAdding
{
  private boolean userExist = false;

  public void userAdding(User user){
    try 
    {
      Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/credentials_management" ,"Youreusername" , "Youreusername");

      String sql = "INSERT INTO user (username , pwd , nome , email , number_phone) VALUES(?, ?, ?, ?, ?)";
         
      PreparedStatement ps = connection.prepareStatement(sql);

      ps.setString(1, user.getUsername()); //se escludi le primary key fai una riconta escludendo l attributo a cui fa riferimento la primary key
      ps.setString(2, user.getPassword()); //stessa cosa per la foreign key
      ps.setString(3, user.getName());
      ps.setString(4, user.getEmail());
      ps.setString(5, user.getPhone_number());

      ps.executeUpdate();
          
      System.out.println("Registrazione completata");
            
    } catch (SQLException e){e.printStackTrace();}
  }

  public boolean checkUsers(String username){
    userExist = false;

    try 
    {
      Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/credentials_management", "Youreusername" , "Youreusername");
      String sql = "SELECT id_user FROM user WHERE username = ?";

      PreparedStatement ps = connection.prepareStatement(sql);
        
      ps.setString(1, username);

      //esegue la query
      ResultSet resultSet = ps.executeQuery();

      if(resultSet.next()){   //controlla le righe con rs.next per verificare se esistono altri utenti
        userExist = true; 
        System.out.println("Utente gia presente");
      } 
    
    } catch (SQLException e) {e.printStackTrace();}

    return userExist;
  }
}