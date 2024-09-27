package Credentials;

public class Credentials 
{
 private int id_user_credentials;
 private String password_credentials;
 private String username_credentials;
 private String email_credentials;
 private String product;

    public Credentials(int id_user_credentials , String password_credentials , String username_credentials , 
    String email_credentials , String product)
    {
     setPasswordCredentials(password_credentials);  
     setUsernameCredentials(username_credentials);
     setEmailContainers(email_credentials); 
     setProduct(product);   
    }

    public int getIdUserCredentials(){return this.id_user_credentials;}

    public String getPasswordCredentials(){return this.password_credentials;}

    public String getUsernameCredentials(){return this.username_credentials;}
    
    public String getEmailCredentials(){return this.email_credentials;}

    public String getPorduct(){return this.product;}


    public void setUsernameCredentials(int id_user_credentials){
        this.id_user_credentials = id_user_credentials;
    }


    public void setPasswordCredentials(String password_credentials){
        if(password_credentials.isEmpty()){
         System.out.println("Setter message : <The password can't be empty!>");
        }
        else{
         this.password_credentials = password_credentials;
        }
        
    }

    public void setUsernameCredentials(String username_credentials){
        this.username_credentials = username_credentials;
    }

    public void setEmailContainers(String email_credentials){
        if(email_credentials.isEmpty()){
         System.out.println("Setter message : <The email can't be empty!>");
        }
        else{
         this.email_credentials = email_credentials;    
        }
        
    }

    public void setProduct(String product){
        if(product.isEmpty()){
         System.out.println("Setter message : <The service can't be empty!>");
        }
        else{
         this.product = product;
        }
        
    }

}
