package finance;

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
		
		int faceValue = 1000;
		double rate = 0.05;
		int term = 10;
		CertificateOfDeposit cd1 = new CertificateOfDeposit(faceValue, rate, term);
		System.out.format("$%,9.2f at %6.3f%% for %2d years -> $%,9.2f%n",
				cd1.getFaceValue(), cd1.getRate(), cd1.getTerm(),
				cd1.getValueAtMaturity());
		
		faceValue = 800;
		rate = 0.045;
		term = 8;
		CertificateOfDeposit cd2 = new CertificateOfDeposit(faceValue, rate, term);
		System.out.format("$%,9.2f at %6.3f%% for %2d years -> $%,9.2f%n",
				cd2.getFaceValue(), cd2.getRate(), cd2.getTerm(),
				cd2.getValueAtMaturity());
		
		faceValue = 1000;
		rate = 0.05;
		term = 10;
		int numberOfPeriods = 2;
		CertificateOfDeposit cd3 = new CertificateOfDeposit(faceValue, rate, term, numberOfPeriods);
		System.out.format("$%,9.2f at %6.3f%% compounding %2d time(s) per year for %2d years -> $%,9.2f%n",
				cd3.getFaceValue(), cd3.getRate(), 
				cd3.getPeriodsPerYear(), cd3.getTerm(),
				cd3.getValueAtMaturity());
		
		faceValue = 800;
		rate = 0.045;
		term = 8;
		numberOfPeriods = 12;
		CertificateOfDeposit cd4 = new CertificateOfDeposit(faceValue, rate, term, numberOfPeriods);
		System.out.format("$%,9.2f at %6.3f%% compounding %2d time(s) per year for %2d years -> $%,9.2f%n",
				cd4.getFaceValue(), cd4.getRate(), 
				cd4.getPeriodsPerYear(), cd4.getTerm(),
				cd4.getValueAtMaturity());
	}
}
