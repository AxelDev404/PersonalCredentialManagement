package DB;

import Credentials.Credentials;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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


    public void viewCredentials(int idTemp)
    {
      try 
      {
       Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/credentials_management" , "root" , "root2003A03");
       String sql ="SELECT id_credential,pwd,username,email,product FROM credentials WHERE id_user_credentials = ?";

       PreparedStatement ps = connection.prepareStatement(sql);
       ps.setInt(1,idTemp);
       ResultSet resultSet = ps.executeQuery();
       

       while (resultSet.next()) 
       {
         System.out.println("|____________________"+resultSet.getString("product")+"____________________|");
         System.out.println("|");
         System.out.println("| ID user : "+idTemp);//ok
         System.out.println("|");
         System.out.println("| ID credential : "+resultSet.getInt("id_credential"));
         System.out.println("|");
         System.out.println("| Username : "+resultSet.getString("username"));
         System.out.println("|");
         System.out.println("| Email : "+resultSet.getString("email"));
         System.out.println("|");
         System.out.println("| Password : "+resultSet.getString("pwd"));
         System.out.println("|");
         System.out.println("| Service : "+resultSet.getString("product"));
         System.out.println();
       }

      } catch(SQLException e){e.printStackTrace();}
    }

    public void filterSerchByIdCredentials(int id_credential_insert , int idTemp)
    {
       try
       {
         Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/credentials_management" , "root" ,"root2003A03");
         String sqlPerIdCredential = "SELECT username , pwd , email , product FROM credentials WHERE id_user_credentials = ? AND id_credential = ?";
        
         PreparedStatement ps = connection.prepareStatement(sqlPerIdCredential);

         ps.setInt(1, idTemp);
         ps.setInt(2, id_credential_insert);

         ResultSet resultSet = ps.executeQuery(); 

            if(!resultSet.next()){
             System.out.println("No data information for this id credential");
            }
            else
            {
                do
                {
                 System.out.println("| Username : "+resultSet.getString("username"));
                 System.out.println("|");
                 System.out.println("| Email : "+resultSet.getString("email"));
                 System.out.println("|");
                 System.out.println("| Password : "+resultSet.getString("pwd"));
                 System.out.println("|");
                 System.out.println("| Product : "+resultSet.getString("product"));  

                }while(resultSet.next());
            }
       }catch(SQLException e){e.printStackTrace();}
    }

    public void filterSearchForUsername(String username_insert , int idTemp)
    {
        try 
        {
         Connection connection = DriverManager.getConnection("jdbc:mysql://loaclhost:3306/credentials_management" , "root" , "root2003A03");
         String sqlPerUsername ="SELECT username , pwd , email , product FROM credentials WHERE id_user_credentials = ? AND username = ?";

         PreparedStatement ps = connection.prepareStatement(sqlPerUsername);

         ps.setInt(1, idTemp);
         ps.setString(2, username_insert);

         ResultSet resultSet = ps.executeQuery();

           if(!resultSet.next()){
             System.out.println("No data information for this username");
           }
           else
           {
                do
                {
                 System.out.println("| Username : "+resultSet.getString("username"));
                 System.out.println("|");
                 System.out.println("| Email : "+resultSet.getString("email"));
                 System.out.println("|");
                 System.out.println("| Password : "+resultSet.getString("pwd"));
                 System.out.println("|");
                 System.out.println("| Product : "+resultSet.getString("product")); 
                }while(resultSet.next());
           }
        
        }catch(SQLException e){e.printStackTrace();}
    }

    public void searchForProduct(String product_insert , int idTemp)
    {
       try 
       {
         Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/credentials_management" , "root" , "root2003A03");
         String sqlPerProduct = "SELECT username , pwd , email , product FROM credentials WHERE id_user_credentials = ? AND product = ?";

         PreparedStatement ps = connection.prepareStatement(sqlPerProduct);
         ps.setInt(1, idTemp);
         ps.setString(2, product_insert);

         ResultSet resultSet = ps.executeQuery();

           if(!resultSet.next()){
             System.out.println("No data infromation for this product");
           }
           else
           {
                do
                {
                 System.out.println("| Product : "+resultSet.getString("product"));   
                 System.out.println("|");
                 System.out.println("| Username : "+resultSet.getString("username"));
                 System.out.println("|");
                 System.out.println("| Email : "+resultSet.getString("email"));
                 System.out.println("|");
                 System.out.println("| Password : "+resultSet.getString("pwd"));
                 System.out.println("|");
                 
                }while(resultSet.next());
           }
       } catch (SQLException e){e.printStackTrace();}     
    }

    public void searchForEmail(String email_insert , int idTemp)
    {
       try 
       {
         Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/credentials_management" , "root" , "root2003A03");
         String sqlPerEmail = "SELECT username , pwd , email , product FROM credentials WHERE id_user_credentials = ? AND email = ?";

         PreparedStatement ps = connection.prepareStatement(sqlPerEmail);
         ps.setInt(1, idTemp);
         ps.setString(2, email_insert);

         ResultSet resultSet = ps.executeQuery();

           if(!resultSet.next()){
             System.out.println("No data infromation for this product");
           }
           else
           {
                do
                {
                 System.out.println("| Email : "+resultSet.getString("email"));
                 System.out.println("|");
                 System.out.println("| Product : "+resultSet.getString("product"));   
                 System.out.println("|");
                 System.out.println("| Username : "+resultSet.getString("username"));
                 System.out.println("|");  
                 System.out.println("| Password : "+resultSet.getString("pwd"));
                 System.out.println("|");
                 
                }while(resultSet.next());
           }
       } catch (SQLException e){e.printStackTrace();}  
    }
}
