package rocketBase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import rocketDomain.RateDomainModel;

public class Rate_Test {

	
	//TODO - RocketDAL rate_test
	//		Check to see if a known credit score returns a known interest rate
	@Test
	public void rate_test_1() {
		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
		RateDomainModel rate1 = new RateDomainModel();
		RateDomainModel rate2 = new RateDomainModel();
		RateDomainModel rate3 = new RateDomainModel();
		RateDomainModel rate4 = new RateDomainModel();
		RateDomainModel rate5 = new RateDomainModel();
		for (RateDomainModel rate : rates) {
			if (rate.getiMinCreditScore() == 600) {
				rate1 = rate;
			}
			else if (rate.getiMinCreditScore() == 650) {
				rate2 = rate;
			}
			else if (rate.getiMinCreditScore() == 700) {
				rate3 = rate;
			}
			else if (rate.getiMinCreditScore() == 750) {
				rate4 = rate;
			}
			else if (rate.getiMinCreditScore() == 800) {
				rate5 = rate;
			}
		}
		assertTrue(rate1.getdInterestRate() == 5);
		assertTrue(rate2.getdInterestRate() == 4.5);
		assertTrue(rate3.getdInterestRate() == 4);
		assertTrue(rate4.getdInterestRate() == 3.75);
		assertTrue(rate5.getdInterestRate() == 3.5);
	}
	
	//TODO - RocketDAL rate_test
	//		Check to see if a RateException is thrown if there are no rates for a given
	//		credit score
	@Test
	public void rate_test_2() {
		
	}
	
	@Test
	public void test() {
		
		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
		System.out.println ("Rates size: " + rates.size());
		assert(rates.size() > 0);
		
		assert(1==1);
	}

}
