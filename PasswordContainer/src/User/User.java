package User;
public class User
{
 private String username;
 private String password;
 private String name;
 private String phone_number;
 private String email;

   public User (String username , String password , String name, String email , String phone_number)
   {
     setUsername(username);
     setPassword(password);
     setName(name);
     setPhone_number(phone_number); 
     setEmail(email);   
   }


   public String getUsername() {return username;}

   public String getPassword() {return password;}

   public String getName() {return name;}

   public String getPhone_number() {return phone_number;}

   public String getEmail(){return this.email;}


   public void setUsername(String username) {
      if(username.isEmpty() || username.length() < 4){
       System.out.println("Setter message : <Username non deve essere nullo e deve contenere 4 o piu caratteri!>");
      }
      else{
       this.username = username;
      }
        
   }

   public void setPassword(String password) {
      if(password.isEmpty() || password.length() < 8){
       System.out.println("Setter message : <La password deve essere minimo di 8 caratteri!>"); 
      }
      else{
       this.password = password;
      }
        
   }

   public void setName(String name) {
      if(name.isEmpty()){
       System.out.println("Setter message : <Il nome non puo essere nullo!>");
      }
      else{
       this.name = name;
      }
        
   }

   public void setPhone_number(String phone_number) {
      if(phone_number.isEmpty() || phone_number.length() < 10){
       System.out.println("Setter message : <Il numero di telefono non e valido!>");    
      }
      else{
       this.phone_number = phone_number;
      }
        
   }
    
   public void setEmail(String email){
      if(email.isEmpty()){
           System.out.println("Setter message : <Email non valida!>");
      }
      else{
           this.email = email;
      }
        
   }
   //email.contains("@gmail.com")

}
