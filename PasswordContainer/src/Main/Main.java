package Main;

//MYSQL LIBRARY

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// JAVA DEFAULT LIBRARIES

import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Result;

import Credentials.Credentials;
import DB.CredentialsManagement;
import DB.CredentialsManagement;

//MY LIBRARY

import DB.UserManagement;
import User.User;

@SuppressWarnings("unused")
public class Main 
{
    
  public static void main(String[] args) throws Exception 
  {
    Scanner sc = new Scanner(System.in);

    String menu = null , menuUser = null;
    String username_logIn = null , password_logIn = null;
    
    //VARIABILI UTENTE
    String username = null , password = null , nome = null , email = null , numeroTel = null;  

    //VARIABILI CREDENZIALI
    String password_credentials = null , username_credentials = null , email_credentials = null , product = null;
    int id_user_credentials = 0;

    try 
    {
      //SQL QUERY
      String sql = "SELECT pwd,username,id_user,email,nome,number_phone FROM user WHERE username=?";
        
      Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/credentials_management" , "root" , "root2003A03");
      PreparedStatement stmt = connection.prepareStatement(sql);
               
      do
      {
        System.out.println("+_____________________________________________+");
        System.out.println("|            CREDENTIAL DISPENSER             |");
        System.out.println("|                                             |");
        System.out.println("|               [1] [ LogIn ]                 |");
        System.out.println("|                                             |");
        System.out.println("|               [2] [ Register ]              |"); 
        System.out.println("|                                             |");
        System.out.println("|               [3] [ Quit ]                  |");
        System.out.println("|_____________________________________________|");
        System.out.println("+                                             +");
             
        System.out.println();
             
        System.out.print(">_");
        menu = sc.nextLine();

        System.out.println();

        switch (menu) 
        {
          case "1":
                     
            System.out.println();

            System.out.print("Username :");   
            username_logIn = sc.nextLine();

            System.out.println();

            System.out.print("Password : ");
            password_logIn = sc.nextLine();
                     
            stmt.setString(1, username_logIn);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) 
            {
                            
              if(password_logIn.equals(resultSet.getString("pwd")) && username_logIn.equals(resultSet.getString("username")))
              {
                int idTemp = 0; //ok // variabile temporenaea per la ricerca dell id utente
                //〈〉
                do
                {
                  System.out.println();
                  System.out.println();
                  System.out.println("+____________MENU ID :"+resultSet.getInt("id_user")+"____________+");
                  System.out.println("|");
                  System.out.println("|[1] [ Prfile ]");
                  System.out.println("|");
                  System.out.println("|[2] [ View all ]");
                  System.out.println("|");
                  System.out.println("|[3] [ Filter ]");
                  System.out.println("|");
                  System.out.println("|[4] [ Add credential ]");
                  System.out.println("|");
                  System.out.println("|[5] [ Quit ]");

                  //visualizza per dettaglio es product id_credential

                  System.out.println();

                  System.out.print(">_");
                  menuUser = sc.nextLine();

                  System.out.println();

                  switch (menuUser) 
                  {
                    case "1":

                      System.out.println();
                      System.out.println();
                      System.out.println("|");
                      System.out.println("| Name : "+resultSet.getString("nome"));
                      System.out.println("|");
                      System.out.println("| Phone : "+resultSet.getString("number_phone"));
                      System.out.println("|");
                      System.out.println("| Username : "+resultSet.getString("username"));  
                      System.out.println("|");
                      System.out.println("| Email : "+resultSet.getString("email"));
                      System.out.println();
                      System.out.println();

                    break;
                                    
                    case "2":  //PROVA AD OTTIMIZARE CON SOTTOCLASSI O CLASSI 

                     idTemp = resultSet.getInt("id_user"); //ok

                     CredentialsManagement cm = new CredentialsManagement();

                     cm.viewCredentials(idTemp);

                    break;
                    
                    case "3":
                      System.out.println("Here you can filter all your data by [ID USER , ID CREDENTIAL , USERNAME , EMAIL , SERVICE] ");
                      
                      String sqlPerID , sqlPerIdCredential , sqlPerUsername , sqlPerEmail , sqlPerProduct , menuCredentials = null;
                      
                      //QUERY

                      do
                      {
                        System.out.println("+____________MENU ID :"+resultSet.getInt("id_user")+"____________+");
                        System.out.println("|");
                        System.out.println("| [1] [ Search for id credential ]");
                        System.out.println("|");
                        System.out.println("| [2] [ Search for username ]");
                        System.out.println("|");
                        System.out.println("| [3] [ Search for email ]");
                        System.out.println("|");
                        System.out.println("| [4] [ Search for Service ]");
                        System.out.println("|");
                        System.out.println("| [5] [ Quit ]");

                        System.out.print(">_");
                        menuCredentials = sc.nextLine();

                        System.out.println();
                        
                        switch (menuCredentials) 
                        {
                          case "1":
                           CredentialsManagement cm2 = new CredentialsManagement();

                           int id_credential_insert;
                           idTemp = resultSet.getInt("id_user"); //ok
                                                     
                           System.out.print("Insert the credential id : ");
                           id_credential_insert = Integer.parseInt(sc.nextLine());

                           cm2.filterSerchByIdCredentials(id_credential_insert, idTemp);

                          break;  

                          case "2":
                           CredentialsManagement cm3 = new CredentialsManagement();

                           idTemp = resultSet.getInt("id_user"); //ok
                           String username_insert = null;
                            
                           System.out.print("Insert the username : ");
                           username_insert = sc.nextLine();

                            cm3.filterSearchForUsername(username_insert, idTemp);
                            
                          break;

                          case "3":
                           CredentialsManagement cm4 = new CredentialsManagement();

                           idTemp = resultSet.getInt("id_user"); //ok
                           String email_insert = null;                          
                           
                           System.out.print("Insert the email : ");
                           email_insert = sc.nextLine();

                           cm4.searchForEmail(email_insert, idTemp);

                          break;

                          case "4":
                           CredentialsManagement cm5 = new CredentialsManagement();

                           idTemp = resultSet.getInt("id_user"); //ok
                           String product_insert = null;

                           System.out.print("Insert the product : ");
                           product_insert = sc.nextLine();

                           cm5.searchForProduct(product_insert, idTemp);

                          break;

                          case "5": System.out.println("Goodbye!"); break;
                        
                          default: System.out.println("[ Ivalid option! ]");break;
                        }

                      }while(menuCredentials.isEmpty() || !menuCredentials.equals("5"));

                    break;

                    case "4":

                      System.out.println();
                      System.out.println("Username and email are optional values");
                      System.out.println();
                      //TRADUCI
                      do
                      {

                       System.out.println("Insert your id :");
                       id_user_credentials = Integer.parseInt(sc.nextLine());

                        if(id_user_credentials == resultSet.getInt("id_user")){
                         System.out.println("Id authentified!");
                        } 
                        else{System.out.println("Id is wrong");}

                      }while (id_user_credentials != resultSet.getInt("id_user"));
                          
                      System.out.print("Username : ");
                      username_credentials = sc.nextLine();

                      System.out.print("Password : ");
                      password_credentials = sc.nextLine();

                      System.out.print("Email : ");
                      email_credentials = sc.nextLine();

                      System.out.print("Product : ");
                      product = sc.nextLine();

                      Credentials credentials = new Credentials(id_user_credentials , password_credentials , username_credentials , email_credentials , product);
                      CredentialsManagement cma = new CredentialsManagement();

                      cma.addCredentials(credentials , id_user_credentials);  

                    break;

                    case "5": System.out.println("Goodbye!"); break;

                    default: System.out.println("Invalid option!");break;
                  }
                                
                }while(menuUser.isEmpty() || !menuUser.equals("5"));
                                          
              } 
              else{System.out.println("Denied access!");}
            }
                     
          break;
                     

          case "2":

            //crea un do while nel caso con parametro checkUsers magari con una domanda se vuole riprovare o no 
            //TRADUCI
            do
            {
              System.out.print("Username : ");
              username = sc.nextLine();
                         
              if(username.isEmpty()){
                System.out.println("[ The username can't be empty ]");
              }
            }while(username.isEmpty());
                    
                     
            System.out.println();
                     
            do
            {
              System.out.print("Password : ");
              password = sc.nextLine();
 
              if(password.isEmpty() || password.length() < 8){
                System.out.println("[ The password can't be empty and it must be 8 or more than 8 characters ]");
              }
            }while(password.isEmpty() || password.length() < 8);
                  
                  
            System.out.println();

            do
            { 
              System.out.print("Name : ");
              nome = sc.nextLine();
                         
              if(nome.isEmpty()){
                System.out.println("[ Name can't be empty ]");
              }
            }while(nome.isEmpty());


            System.out.println();
                     
            do
            {
              System.out.print("Email : ");
              email = sc.nextLine();
 
              if(email.isEmpty()){
                System.out.println("[ Email can't be empty ]");
              }
            }while(email.isEmpty());
                    

            System.out.println();
                     
            do
            {
              System.out.print("Phone : ");
              numeroTel = sc.nextLine();

              if(numeroTel.isEmpty()){
                System.out.println("[ Phone number can't be empty ]");
              }
            }while(numeroTel.isEmpty() || numeroTel.length() < 10);
                 
                     
            User user = new User(username , password, nome ,email, numeroTel);
            UserManagement userManagementAdding = new UserManagement();
                     
            if(userManagementAdding.checkUsers(username)==true){
              System.out.println("Repeat the registration");
            }
            else{userManagementAdding.userAdding(user);}
                      
          break;

          case"3":System.out.println("Goodbye"); break;
                
          default: System.out.println("Invalid option!"); break;
        }

      }while(menu.isEmpty() || !menu.equals("3"));

    }catch(SQLException e){e.printStackTrace();}
    
    sc.close();
  }
}
