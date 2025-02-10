package finance;

/**
 * Encapsulates parameters of a certificate of deposit,
 * and can calculate value at maturity.
 * 
 * @author Will Provost
 */
public class CertificateOfDeposit {

	private double faceValue;
	private double rate;
	private int term;
	private int periodsPerYear = 1;

	/**
	 * No-argument constructor.
	 */
	public CertificateOfDeposit() {
	}
	
	/**
	 * Initializing constructor taking face value, rate, and term,
	 * and leaving the number of compounding periods at the default of 1.
	 */
	public CertificateOfDeposit(double faceValue, double rate, int term) {
		this.faceValue = faceValue;
		this.rate = rate;
		this.term = term;
	}

	/**
	 * Initializing constructor taking face value, rate, and term,
	 * and number of compounding periods.
	 */
	public CertificateOfDeposit(double faceValue, 
			double rate, int term, int periodsPerYear) {
		this(faceValue, rate, term);
		this.periodsPerYear = periodsPerYear;
	}

	/**
	 * Accessor for the face value.
	 */
	public double getFaceValue() {
		return faceValue;
	}

	/**
	 * Accessor for the rate.
	 */
	public double getRate() {
		return rate;
	}

	/**
	 * Accessor for the term, in years.
	 */
	public int getTerm() {
		return term;
	}

	/**
	 * Accessor for the number of compounding periods per year.
	 */
	public int getPeriodsPerYear() {
		return periodsPerYear;
	}

	/**
	 * Calculate the value at maturity by running the 
	 * compound interest calculations.
	 */
	public double getValueAtMaturity() {
		double value = faceValue;
		for (int year = 0; year < term * periodsPerYear; ++year) {
			value += value * rate / periodsPerYear;
		}
		
		return value;
	}
}
