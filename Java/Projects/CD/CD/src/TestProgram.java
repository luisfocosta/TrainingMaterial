import CD.*;
/**
 * Test program for the CertificateOfDeposit class.
 * 
 * @author Will Provost
 */
public class TestProgram {
	/**
	 * Quick test: runs four scenarios, each using a different instance.
	 */
	public static void main (String[] args) {
		
		double faceValue = 1000.0;
		double rate = 5;
		int term = 10;
		//TODO create a CD and get its value at maturity
		//CD cd = new CD(faceValue, rate, term, 1);
		CD.calcCD (faceValue, rate, term);
		
		faceValue = 800.0;
		rate = 4.5;
		term = 8;
		//TODO create a CD and get its value at maturity
		CD.calcCD (faceValue,rate,term);
		
		faceValue = 1000.0;
		rate = 5;
		term = 10;
		int numberOfPeriods = 2;
		//TODO create a CD and get its value at maturity
		CD.calcCD (faceValue,rate,term,numberOfPeriods);
		
		faceValue = 800.0;
		rate = 4.5;
		term = 8;
		numberOfPeriods = 12;
		//TODO create a CD and get its value at maturity
		CD.calcCD (faceValue,rate,term,numberOfPeriods);
	}
}
