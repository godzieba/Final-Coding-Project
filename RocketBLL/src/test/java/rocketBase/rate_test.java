package rocketBase;

import static org.junit.Assert.*;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.junit.Test;

import exceptions.RateException;

public class rate_test {

	//TODO - RocketBLL rate_test
	//		Check to see if a known credit score returns a known interest rate
	@Test
	public void rate_test_1() {
		try {
			assertTrue(RateBLL.getRate(600) == 5);
			assertTrue(RateBLL.getRate(650) == 4.5);
			assertTrue(RateBLL.getRate(700) == 4);
			assertTrue(RateBLL.getRate(750) == 3.75);
			assertTrue(RateBLL.getRate(800) == 3.5);
		} catch (RateException e) {
			e.printStackTrace();
		}
	}
	
	//TODO - RocketBLL rate_test
	//		Check to see if a RateException is thrown if there are no rates for a given
	//		credit score
	@Test
	public void rate_test_2() {
		try {
			RateBLL.getRate(599);
		} catch (RateException e) {
			assertTrue(1==1);
		}
	}
	
	@Test
	public void payment_test() {
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.CEILING);
		assertTrue(1432.25 == Double.parseDouble(df.format(RateBLL.getPayment(4, 360, 300000, 0, true))));
	}
	
	@Test
	public void test() {
		assert(1==1);
	}

}
