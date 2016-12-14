package rocketBase;

import java.util.ArrayList;

import org.apache.poi.ss.formula.functions.*;

import exceptions.RateException;
import rocketDomain.RateDomainModel;

public class RateBLL {

	private static RateDAL _RateDAL = new RateDAL();
	
	public static double getRate(int GivenCreditScore) throws RateException
	{
		//TODO - RocketBLL RateBLL.getRate - make sure you throw any exception
		if (GivenCreditScore < 600) {
			RateDomainModel tempRate = new RateDomainModel();
			tempRate.setdInterestRate(GivenCreditScore);
			throw new RateException(tempRate);
		}
		else {
			ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
			for (int i = 1; i < rates.size(); i++) {
				if (GivenCreditScore < rates.get(i).getiMinCreditScore()) {
					return rates.get(i - 1).getdInterestRate();				
				}
			}
			return 3.5;
		}
		//		Call RateDAL.getAllRates... this returns an array of rates
		//		write the code that will search the rates to determine the 
		//		interest rate for the given credit score
		//		hints:  you have to sort the rates...  you can do this by using
		//			a comparator... or by using an OrderBy statement in the HQL
		
	}
	
	
	//TODO - RocketBLL RateBLL.getPayment 
	//		how to use:
	//		https://poi.apache.org/apidocs/org/apache/poi/ss/formula/functions/FinanceLib.html
	
	public static double getPayment(double r, double n, double p, double f, boolean t)
	{
		return (r / 1200) * p / (1 - Math.pow((1 + r / 1200), (-n)));
	}
}
