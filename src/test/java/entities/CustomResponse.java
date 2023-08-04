package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class CustomResponse {
   //for category
   private int category_id;
   private String created;
   private Service service_type;
   private CustomResponse category;
   private int service_type_id;



   //for seller
   private int seller_id;
   @JsonProperty("company_name")
   private String companyName;
   private String email;
   private String seller_name;
   private String phone_number;
   private String address;
   private List<CustomResponse> responses;

   //for bank account
   private double balance;
   private String id;
   private String bank_account_name;
   private String responseBody;

   //for Janarbek
   private String success;
   private int status;
   private String message;

   //for business
   private CustomResponse business_area;
   //private CustomResponse company_name;
   private String role;
   private String ruTitle;
   private String enTitle;











}
