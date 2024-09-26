package Main;

//MYSQL LIBRARY

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// JAVA DEFAULT LIBRARIES

import java.util.Scanner;

import Credentials.Credentials;
import DB.CredentialsManagementAdding;
import DB.CredentialsManagementAdding;

//MY LIBRARY

import DB.UserManagementAdding;
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
        
      Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/credentials_management" , "Youreusername" , "Yourepassword");
      PreparedStatement stmt = connection.prepareStatement(sql);
               
      do
      {
        System.out.println("+______________________________+");
        System.out.println("|            LogIn             |");
        System.out.println("|                              |");
        System.out.println("|        [1] |Accedi|          |");
        System.out.println("|                              |");
        System.out.println("|        [2] |Crea un account| |"); 
        System.out.println("|                              |");
        System.out.println("|        [3] |Esci|            |");
        System.out.println("|______________________________|");
        System.out.println("+                              +");
             
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
                  System.out.println("|[1] < Profilo >");
                  System.out.println("|");
                  System.out.println("|[2] < Visualizza tutte le credenziali >");
                  System.out.println("|");
                  System.out.println("|[3] < Aggiungi credenziali >");
                  System.out.println("|");
                  System.out.println("|[4] < Esci >");

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
                      System.out.println("| Nome : "+resultSet.getString("nome"));
                      System.out.println("|");
                      System.out.println("| Telefono : "+resultSet.getString("number_phone"));
                      System.out.println("|");
                      System.out.println("| Username : "+resultSet.getString("username"));  
                      System.out.println("|");
                      System.out.println("| Email : "+resultSet.getString("email"));
                      System.out.println();
                      System.out.println();

                    break;
                                    
                    case "2":
                                    
                      try 
                      {
                        idTemp = resultSet.getInt("id_user"); //ok

                        String sql2 = "SELECT id_credential,pwd,username,email,product FROM credentials WHERE id_user_credentials = ?"; //ok
        
                        PreparedStatement stmt2 = connection.prepareStatement(sql2); //ok
                        stmt2.setInt(1, idTemp); //ok
                        ResultSet resultSet2 = stmt2.executeQuery(); //ok

                        while (resultSet2.next()) {//ok
                          
                        
                          System.out.println("|____________________"+resultSet2.getString("product")+"____________________|");
                          System.out.println("|");
                          System.out.println("| ID user : "+resultSet.getInt("id_user"));//ok
                          System.out.println("|");
                          System.out.println("| ID credential : "+resultSet2.getInt("id_credential"));
                          System.out.println("|");
                          System.out.println("| Username : "+resultSet2.getString("username"));
                          System.out.println("|");
                          System.out.println("| Email : "+resultSet2.getString("email"));
                          System.out.println("|");
                          System.out.println("| Password : "+resultSet2.getString("pwd"));
                          System.out.println("|");
                          System.out.println("| Product : "+resultSet2.getString("product"));
                          System.out.println();
                          
                          
                        }
                       
                      } catch (SQLException e) {e.printStackTrace();}
                      
                    break;

                    case "3":

                      System.out.println();
                      System.out.println("Sono opzionali come inserimenti email e username , si consiglia di aggiungere almeno uno dei due qual ora ce ne sia"+"\n"+
                      "bisogno");
                      System.out.println();

                      do
                      {

                        System.out.println("Insersci il tuo id :");
                        id_user_credentials = Integer.parseInt(sc.nextLine());

                        if(id_user_credentials == resultSet.getInt("id_user")){
                          System.out.println("Id autentificato!");
                        } 
                        else{System.out.println("Id errato");}

                      }while (id_user_credentials != resultSet.getInt("id_user"));
                          
                      System.out.print("Username : ");
                      username_credentials = sc.nextLine();

                      System.out.print("Password : ");
                      password_credentials = sc.nextLine();

                      System.out.print("Email : ");
                      email_credentials = sc.nextLine();

                      System.out.println("Product : ");
                      product = sc.nextLine();

                      Credentials credentials = new Credentials(id_user_credentials , username_credentials , password_credentials , email_credentials , product);
                      CredentialsManagementAdding cma = new CredentialsManagementAdding();

                      cma.addCredentials(credentials , id_user_credentials);  

                    break;

                    case "4": System.out.println("Arrivederci"); break;

                    default: System.out.println("Opzione non valida");break;
                  }
                                
                }while(menuUser.isEmpty() || !menuUser.equals("4"));
                                          
              } 
              else{System.out.println("Accesso fallito");}
            }
                     
          break;
                     

          case "2":

            //crea un do while nel caso con parametro checkUsers magari con una domanda se vuole riprovare o no 

            do
            {
              System.out.print("Username : ");
              username = sc.nextLine();
                         
              if(username.isEmpty()){
                System.out.println("[ Il username non puo essere vuoto ]");
              }
            }while(username.isEmpty());
                    
                     
            System.out.println();
                     
            do
            {
              System.out.print("Password : ");
              password = sc.nextLine();
 
              if(password.isEmpty() || password.length() < 8){
                System.out.println("[ La password non puo essere vuota e deve contenere minimo 8 caratteri ]");
              }
            }while(password.isEmpty() || password.length() < 8);
                  
                  
            System.out.println();

            do
            { 
              System.out.print("Nome : ");
              nome = sc.nextLine();
                         
              if(nome.isEmpty()){
                System.out.println("[ Il nome non puo essere vuoto ]");
              }
            }while(nome.isEmpty());


            System.out.println();
                     
            do
            {
              System.out.print("Email : ");
              email = sc.nextLine();
 
              if(email.isEmpty()){
                System.out.println("[ La email non puo essere vuota ]");
              }
            }while(email.isEmpty());
                    

            System.out.println();
                     
            do
            {
              System.out.print("Numero di telefono : ");
              numeroTel = sc.nextLine();

              if(numeroTel.isEmpty()){
                System.out.println("[ Il numero di telefono non puo essere vuoto ]");
              }
            }while(numeroTel.isEmpty() || numeroTel.length() < 10);
                 
                     
            User user = new User(username , password, nome ,email, numeroTel);
            UserManagementAdding userManagementAdding = new UserManagementAdding();
                     
            if(userManagementAdding.checkUsers(username)==true){
              System.out.println("Ripetere l inserimento");
            }
            else{userManagementAdding.userAdding(user);}
                      
          break;

          case"3":System.out.println("Arrivederci!"); break;
                
          default: System.out.println("Opzione non esistente oppure non valida"); break;
        }

      }while(menu.isEmpty() || !menu.equals("3"));

    }catch(SQLException e){e.printStackTrace();}
    
    sc.close();
  }
}
