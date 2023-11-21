import java.util.Date;
import java.text.SimpleDateFormat;
class Donator
   {
      
      private String donarName;
      private String compaign;
      private String paymentType;
      private String description;
      private String email;
      private float amount;
      private Date date;
       Donator(String donarName,String compaign,String paymentType,String description,String email,float amount,Date date)
        {
        	
           this.donarName = donarName; 
           this.compaign=compaign;
           this.paymentType = paymentType;
           this.description= description;
           this.email = email;
           this.amount = amount;
           this.date = date;
        }
       
      SimpleDateFormat fdate = new SimpleDateFormat("dd-MMM-yyyy"); 
       String getName()
       {
            return donarName;
       }
       String getcompaign()
       {
            return compaign;
       }
       String getPayment()
       {
            return paymentType;
       }
       void setDescription(String s)
       {
             description = s;
       }
       String getDescription()
       {
            return description;
       }
       String getEmail()
       {
            return email;
       }
       float getAmount()
       {
            return amount;
       }
       Date getDate()
       {
            return  date;
       }
       
       public String toString()
       {
           return ""+("Donator Name  : " + getName())+("\nCampaign Name : " + getcompaign())+ ("\nPayment Type  : " + getPayment())+("\nDescription   : " + getDescription())+
                    ("\nEmail         : " + getEmail())+ ("\nAmount        : " + getAmount())+("\nDate          : " + fdate.format(getDate()));
       }
       
   }    
       
