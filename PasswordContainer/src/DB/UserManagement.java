package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import User.User;

public class UserManagement
{
  private boolean userExist = false;

  public void userAdding(User user){
    try 
    {
      Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/credentials_management" ,"username" , "pwd");

      String sql = "INSERT INTO user (username , pwd , nome , email , number_phone) VALUES(?, ?, ?, ?, ?)";
         
      PreparedStatement ps = connection.prepareStatement(sql);

      ps.setString(1, user.getUsername()); 
      ps.setString(2, user.getPassword());
      ps.setString(3, user.getName());
      ps.setString(4, user.getEmail());
      ps.setString(5, user.getPhone_number());

      ps.executeUpdate();
          
      System.out.println("Registration is complete");
            
    } catch (SQLException e){e.printStackTrace();}
  }

  public boolean checkUsers(String username){
    userExist = false;

    try 
    {
      Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/credentials_management", "username" , "pwd");
      String sql = "SELECT id_user FROM user WHERE username = ?";

      PreparedStatement ps = connection.prepareStatement(sql);
        
      ps.setString(1, username);

      //esegue la query
      ResultSet resultSet = ps.executeQuery();

      if(resultSet.next()){   //controll the lines with rs.next to verify if in my database I alredy had the same user
        userExist = true; 
        System.out.println("The user already exist");
      } 
    
    } catch (SQLException e) {e.printStackTrace();}

    return userExist;
  }
}