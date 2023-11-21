import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

 class CampaignManager {
        
         int size;
         final int donar = 50;
         int campCount =0;
         Campaign [] camp;
        
         CampaignManager()
         {
             camp = new Campaign[donar];
             
         }
         Scanner scan = new Scanner(System.in);
         SimpleDateFormat fdate = new SimpleDateFormat("dd-MMM-yyyy");
         private String view(String str)
          {
                 if (str.equals(""))
                  {
                      System.out.println("Try again!");
                      return view(scan.nextLine());
                  }
                  return str;
          }
         private Date dateValidation(String dateFormate)
          {
              Date date =null;
                     try
                     {
                            date =fdate.parse(dateFormate);
                            return date;
                     }
                     catch(Exception e)
                     {
                             System.out.println("Invalid date format");
                             System.exit(0);
                     }
                     return date;
          }
          private String eventValidation(int eventNumber)
          {
               String eve="";
               switch (eventNumber)
                       {
                           case 1: return eve += "Cancer Treatment";
                           case 2: return eve += "Heart Treatment" ; 
                           case 3:  return eve += "Liver Treatment " ;
                           default : System.out.println("No event select");
                           System.exit(0);
                       }
                       return eve;
          }
         private int dateCompare(Date d1,Date d2)
         {
		  if(d1.compareTo(d2)<0)
		  {
		      return -1;
		  }
		  else if(d1.compareTo(d2)>0)
		  {
		     return 1;
		  }
		  return 0;
         }
         void addCamp()
         {
              if (campCount<donar)
             {
                     System.out.println ("Enter the campaign name :");
                     String campName= view(scan.nextLine());
                     System.out.println("Enter the Owner Name : ");
                     String name = view(scan.nextLine());
                     System.out.println ("launch date (DD-MON-YYYY) ");
                     String startDateFormate = view(scan.nextLine());
                     Date launchDate = dateValidation(startDateFormate);
                     if(launchDate.equals(null)) return;
                     System.out.println ("fundraiser Deadline date (DD-MON-YYYY) ");
                     String endDateFormate = view(scan.nextLine());
                     Date deadLineDate = dateValidation(endDateFormate);
                     if(deadLineDate.equals(null)) return;
                     if( dateCompare(launchDate,deadLineDate)==1)
                     {
                         System.out.println("start and end date not valid ");
                         return;
                     }
                     System.out.println ("Enter the goal amount : ");
                       float am = scan.nextFloat(); scan.nextLine();
                       System.out.println ("Enter the Descrition : ");
                       String des =view(scan.nextLine());
                       System.out.println("Enter the event : 1.Cancer  2.Heart  3.Liver"); 
                       int eventNumber = scan.nextInt();
                       String event =eventValidation(eventNumber);
                       if(event.equals("")) return;
                       Campaign campaign = new Campaign (name,campName,launchDate,deadLineDate,am,des,event,donar);
                       campaign.updateStatus();
                       camp[campCount]= campaign;
                       campCount++;
                       System.out.println("-------------------  Campaign Added  Successfully ----------------------");
                      
               } 
               else 
               {
                       System.out.println ("...............Reach this goal please try to next Time................");
               }
        }
         
         boolean  listOfCampaign()
         { 
                   
                     if (campCount==0)
                      {
                             System.out.println("No Campaign Available");
                             return false;
                      }
                      else 
                      {
                                  for (int i=0;i<campCount;i++)
                                  {
                                     System.out.println("Campaign Count      : " +(i+1));
                                     System.out.println(camp[i]);
                                     System.out.println("------------------------------------------------");
                                   }
                               return true;
                       }
            }
               

         void addDonar() throws ParseException 
         {
                     listOfCampaign();
                     System.out.println("------------------------------------------------");
                     System.out.println("Enter the number : ");
                     int num =scan.nextInt();
                        if (num<=0)
                        {
                            System.out.println("No Campaign");
                        }
                        else if (num<=campCount)
                        {
                             
                             addDonation(camp[num-1]);
                        }
                        else
                        {
                            System.out.println("Invalid campaign ");
                        }
          }
         
         void listOfDonator()
         {        
               if (listOfCampaign())
               {
                  System.out.println("------------------------------------------------");
                  System.out.println("Enter the number : ");
                  int num =scan.nextInt();
                       
                       if (num<=campCount)
                       {
                             camp[num-1].donator();
                       }
                       else
                       {
                            System.out.println("No Donators Available ");
                       } 
                }
           }
        
          void raisedAmount()
          {
                for (int i=0;i<campCount;i++)
                 {
                        System.out.println( "Total goal Amount : " + (i+1) + " - Campaign " + camp[i].getGoal() +"\nRaised amount  : "  +camp[i].getAmount());
                 }   
          }
        
       boolean campainNameValidation(String  camp,String camName)
        {
           if (camp.equals(camName))
                {
                  return true;
                }
                else 
                {
                 System.out.println(" Please enter the correct campaign name ");
                 return false;
                } 
        }
       private String paymentValidation(int paymentNumber)
        {
          String payment="";
          switch (paymentNumber)
                {
                   case 1: return payment+="UPI";
                   case 2: return payment+="Debit Card";
                   case 3: return payment+="Credit Card";
                   default : System.out.println("Please enter the correct payment option");
                }
                return payment;
        }
       
     
     void addDonation(Campaign cc) throws ParseException 
     {
          if (cc.isEmpty())
          {
                System.out.println("Enter the Donar Name :");
                String name = view(scan.nextLine());
                System.out.println ("Enter the email : ");
                String mail = view(scan.nextLine());
                System.out.println("Enter the Campaign Name :");
                String camp = view(scan.nextLine());
                if( !campainNameValidation(camp,cc.getCampaignName())) return;
                System.out.println("Enter the payment type");
                System.out.println("1.UPI" + " 2.Debit Card " + " 3.Credit Card" );
                int paymentNumber = scan.nextInt();scan.nextLine();
                String payment = paymentValidation(paymentNumber);
                if(payment.equals(""))return;
                System.out.println("Enter the Description : ");
                String des = view(scan.nextLine());
                System.out.println ("Enter the Amount : "); 
                float  amount = scan.nextFloat();
                scan.nextLine();
                cc.setAmount(amount);
                System.out.println("Enter the date (dd-MON-yyyy) :");
                String d = view(scan.nextLine());
                Date checkDate = fdate.parse(d);
                if( cc.dateValidation(checkDate)==false) 
                {System.out.println("End the campaign");
                return; }
                
                cc.addDon(name,camp,payment,des,mail,amount,checkDate) ;
               
                System.out.println(".............. Donator added successfully ...................");
          }
          else 
          {
               System.out.println("............... donator need is full please next time to add you .................");
          }
      }
       public static void main(String[]args)  throws Exception 
     {
          Scanner input = new Scanner(System.in);
          CampaignManager data = new CampaignManager();
          
          while (true)
          {
              System.out.println("************************************************");
              System.out.println("1.Add campaign");
              System.out.println("2.Add Donation" );
              System.out.println("3.List of Campign");
              System.out.println("4.List of Donator");
              System.out.println("5.Exit " );
              System.out.println("************************************************");
              System.out.print("Enter Your Choice : ");
              int choice = input.nextInt();
              switch (choice){
                   case  1: data.addCamp();break;
                   case  2: data.addDonar();break;
                   case  3: data.listOfCampaign(); break;
                   case  4: data.listOfDonator();break;
                   case  5: data.raisedAmount();System.exit(0);break;
                  default : System.out.println ("Please! enter the correct option");
              }
          }
         
     }
         
  }
 
