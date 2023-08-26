package Assisment;

import java.util.*;

public class ForeignExchange {
    private String user_email;
    private static String currencyPair="USDINR";
    protected static double rate=66.00;
    private static Map<String,String> verificationDetails=new Hashtable<>();
    private static Map<String, List<String> >Trades_of_user=new HashMap<>();
    private static Map<String, Long> amount_Multiplyers=new HashMap<>();
    private static Scanner sc=new Scanner(System.in);
    static {
        System.out.println("Welcome to Foreign Exchange !");
        amount_Multiplyers.put("MILLION",1000000L);
        amount_Multiplyers.put("BILLION",1000000000L);
        amount_Multiplyers.put("TRILLION",1000000000000L);
    }

    //for verification purpose
    public ForeignExchange()
    {
        System.out.println("please verify yourSelf..");
        account_Verification();

    }

    private void account_Verification()
    {

        System.out.println("Enter your email..");
        String email_id=sc.nextLine();
        System.out.println("Enter your password");
        String account_password=sc.nextLine();

        System.out.println("press 1 to SignUp\npress 2 to login");
        int val=sc.nextInt();
        if(val==1)
        {

            verificationDetails.put(email_id,account_password);
            System.out.println("Account Created..");

        }
        else if(val==2)
        {
            boolean errorMessageSelecter=false;
            boolean linebreaker=true;
            while(true)
            {

                if(errorMessageSelecter)
                {
                    System.out.println("you entered wrong details..\ntry again..");
                    System.out.println("Enter your email..");
                    if(linebreaker)
                    {
                        sc.nextLine();
                        linebreaker=false;
                    }
                    email_id=sc.nextLine();
                    System.out.println("Enter your password");
                    account_password=sc.nextLine();
                    System.out.println("Do you want to sign up..type(yes/no)");
                    String signup_decision=sc.nextLine();
                    if(signup_decision.equals("yes"))
                    {
                        verificationDetails.put(email_id,account_password);
                        System.out.println("Account Created..");
                        break;
                    }

                }
                if(verificationDetails.keySet().contains(email_id) && verificationDetails.get(email_id).equals(account_password))
                {
                    System.out.println("Login SuccessFull..let's begin your trade");
                    break;
                }
                else{
                    errorMessageSelecter=true;
                }
            }

        }
        this.user_email=email_id;

    }

    public void chooseOperation()
    {
       while(true)
       {   System.out.println("---------------------------------------------------------------");
           System.out.println("Enter 1 to Book trade\nEnter 2 to Print Trades\nEnter 3 to exit");
           int userChoice=sc.nextInt();
           sc.nextLine();
           if(userChoice==1)
           {
                toTrade();
           }
           else if(userChoice==2)
           {
               printUserTradeList();
           }
           else if(userChoice==3)
           {
                break;
           }
           else {
               System.out.println("please select the proper action.");
           }
       }

    }

    private void printUserTradeList()
    {
        if(ForeignExchange.Trades_of_user.containsKey(this.user_email)) {
            List<String> tradeData = ForeignExchange.Trades_of_user.get(this.user_email);
            System.out.println("TradeNo | CurrencyPair| CustomerName | Amount | Rate");
            for (int i = 0; i < tradeData.size(); i++) {
                String data = tradeData.get(i);
                String arr[] = data.split("[|]");
                System.out.println(i + 1 + " | " + ForeignExchange.currencyPair +" | "  + arr[1] + " | "  + arr[0] + "Rs | "  + arr[2]);

            }
        }
        else{
            System.out.println("Your trade list is Empty :(\n");
        }
    }

    private void toTrade()
    {

        System.out.println("Please enter Customer Name.");
        String customerName=sc.nextLine();

        System.out.println("Enter currency-Pair");
        boolean checker=false;
        while(true)
        {
            if(checker)
            {
                System.out.println("you entered wrong currency-pair please enter correct one");
            }
            String currencyPair=sc.nextLine();
            if(currencyPair.equals(ForeignExchange.currencyPair))
            {
                break;
            }
            else {
                checker=true;
            }
        }

        System.out.println("Enter the amount in USD.");
        String input_amount=sc.nextLine();
        double amount=validate_USD_Amount(input_amount);
        String parsedAmount= parseAmountToUserFormat(amount*ForeignExchange.rate);


        System.out.println("Do you want to get Rate");
        String user_ans=sc.nextLine().toLowerCase();
        if(user_ans.equals("yes"))
        {
            System.out.println("You are transferring INR "+parsedAmount+" to "+customerName+".");
        }


        System.out.println("Are you sure you want to book this trade ? type(yes/no)");
        String user_conformation=sc.nextLine().toLowerCase();
        if(user_conformation.equals("yes"))
        {
            System.out.println("Trade for "+ForeignExchange.currencyPair+" has been booked with rate "+ForeignExchange.rate+" ," +
                    " The amount of Rs "+parsedAmount+
                    " will  be transferred in 2 working days to "+customerName+"..");
            if(ForeignExchange.Trades_of_user.containsKey(this.user_email))
            {
                List<String> currentUserTradeList=ForeignExchange.Trades_of_user.get(this.user_email);
                String data=parsedAmount+"|"+customerName+"|"+ForeignExchange.rate;
                currentUserTradeList.add(data);
            }
            else{
                List<String> currentUserTradeList=new ArrayList<>();
                String data=parsedAmount+"|"+customerName+"|"+ForeignExchange.rate;
                currentUserTradeList.add(data);
                ForeignExchange.Trades_of_user.put(this.user_email,currentUserTradeList);
            }
        }
        else if(user_conformation.equals("no"))
        {
            System.out.println("Trade is Canceled.");
        }


    }
    private String parseAmountToUserFormat(double amount)
    {

        String str=Double.toString(amount);
        str=removing_E_format(str);
        String formatedAmount="";
        boolean setter=true;
        boolean thousandSetter=false;
        boolean otherSetter=false;
        int counter=0;
        for(int i=str.length()-1;i>=0;i--)
        {
            if(str.charAt(i)=='.')
            {
                formatedAmount=str.charAt(i)+formatedAmount;
                setter=false;
                thousandSetter=true;
                counter=0;
            }
            else if(setter)
            {
                formatedAmount=str.charAt(i)+formatedAmount;

            }
            else if(thousandSetter && counter==4)
            {
                formatedAmount=str.charAt(i)+","+formatedAmount;
                thousandSetter=false;
                otherSetter=true;
                counter=0;

            }
            else if(otherSetter && counter==2)
            {
                formatedAmount=str.charAt(i)+","+formatedAmount;
                counter=0;
            }
            else{
                formatedAmount=str.charAt(i)+formatedAmount;
            }
            counter++;
        }
        return formatedAmount;

    }

    private String removing_E_format(String amount)
    {
        String formated_amount="";

        if(amount.contains("E")){


            int index=amount.indexOf("E");

            String str=amount.substring(index+1,amount.length());

            if(amount.substring(0,amount.indexOf('E')).length()-1<Integer.parseInt(str))
            {
                int dotValue=amount.indexOf('E')-(amount.indexOf('.')+1);
                amount=amount.substring(0,amount.indexOf('E'));


                for(int i=0;i<Integer.parseInt(str)-dotValue;i++)
                {
                    amount+='0';
                }
                return amount.substring(0,amount.indexOf('.'))+amount.substring(amount.indexOf('.')+1)+".00";
            }
            int val=Integer.parseInt(str)+amount.indexOf('.');
            for(int i=0;i<index;i++)
            {
                if(amount.charAt(i)=='.')
                {
                    continue;
                }
                if(i==val+1)
                {
                    formated_amount=formated_amount+".";
                }
                formated_amount=formated_amount+amount.charAt(i);
            }
        }
        else{
            formated_amount=amount;
        }
        return formated_amount ;
    }
    private double validate_USD_Amount(String input_amount )
    {
        String amount="";
        double usd_amount=0;
        for(int i=0;i<input_amount.length();i++)
        {
            if(input_amount.charAt(i)>='0' && input_amount.charAt(i)<='9')
            {
                amount+=input_amount.charAt(i);
            }
            else if(input_amount.charAt(i)==',')
            {
                continue;
            }
            else if(input_amount.charAt(i)=='.')
            {
                amount+=input_amount.charAt(i);
            }
            else{
                String val=input_amount.substring(i,input_amount.length()).trim().toUpperCase();
                String arr[] = val.split(" ");
                if(!val.isEmpty()) {
                    if (amount_Multiplyers.containsKey(arr[0]))
                    {
                        usd_amount = Double.parseDouble(amount) * (double) amount_Multiplyers.get(arr[0]);
                    }
                    else if(arr[0].equals("K")){
                        usd_amount = Double.parseDouble(amount)*1000;
                    }
                    else{
                        System.out.println("you entered wrong amount");
                        System.out.println("Enter the amount in USD.");
                        input_amount=sc.nextLine();
                        i=-1;
                        amount="";
                        continue;
                    }
                }
                break;
            }
        }
        if(usd_amount==0)
        {
            usd_amount = Double.parseDouble(amount);
        }


        return usd_amount;
    }



    public static void main(String[] args) {
        ForeignExchange foreignExchange_1=new ForeignExchange();
        foreignExchange_1.chooseOperation();
    }


}
