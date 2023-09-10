package com.FXTrading.utility;

import java.text.DecimalFormat;

import org.springframework.stereotype.Component;

@Component
public class AmountFormater {
	
	public  String formatingUSDAmount(double amount)
	{
		DecimalFormat df = new DecimalFormat("#");
		df.setMaximumFractionDigits(2);
        String str=df.format(amount);
        DecimalFormat df1 = new DecimalFormat("##,###.00");
        String formatedAmount=df1.format(Double.parseDouble(str));
		return formatedAmount;
	}
	
	public  String converting_USD_To_INR(double amount)
    {

		double convertedAmount=amount*66;

		DecimalFormat df = new DecimalFormat("#");
		df.setMaximumFractionDigits(2);
        String str=df.format(convertedAmount)+".00";
        
        
        //inserting  ',' to get Indian format
        
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
        return formatedAmount+" INR";

    }

	
	


}
