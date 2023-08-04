package entities;
import lombok.Data;
@Data

public class RequestBody {
   //token
   private String email;
   private String password;

   //categories
   private String category_title;
   private String category_description;
   private boolean flag;

   //Seller
   private String company_name;
   private String seller_name;
   //not working use token one;
  // private String email;
   private String phone_number;
   private String address;

   //Janarbek
   private String title;
   private String category;
   private String description;

}
