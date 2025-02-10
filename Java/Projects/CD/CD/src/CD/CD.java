package CD;

public class CD {
    double amount;
    double rate;
    int term;
    int Periods;

    /*public CD (double v, double r, int t, int p) {
        amount = v;
        rate = r;
        term = t;
        Periods = p;
    }*/

    private static double calc (double amount, double rate, int term, int Periods) {
        //value - the value amount invested
        //rate - interest rate
        //term / t - number of terms, expressed in years
        //numberOfPeriods / n - number of compound periods; default 1/year, but can be [2,4,12]
        //Result should be value*(1+rate/n)^(n*t)
        //System.out.printf("amount= %.2f, rate= %.2f, term= %d, periods= %d\n", amount, rate, term, 1);
        //System.out.printf("%.2f*(1+(%.2f/100)/%d) = %.2f\n", amount, rate, term, amount*(1+(rate/100)) );
        //System.out.printf("%.2f^(%.2f) = %.2f\n", amount, Math.pow ((1+rate/100/Periods),(Periods*term)), 1111.00 );
        //System.out.printf("%.2f*(%.2f^%d)=%0,10.2f\n", amount, (1+(rate/100)), Periods*term, (Math.pow ((1+rate/100/Periods),(Periods*term))));
        return amount*(Math.pow((1+rate/100/Periods),(Periods*term)));
    }

    public static void calcCD (double value, double rate, int term){
        //CD cd = new CD (value, rate, term, 1);
        System.out.printf("$ %,8.2f at %3.2f%% for %2d years -> $ %,8.2f\n", value, rate, term, calc(value, rate, term, 1));
    }

    public static void calcCD (double value, double rate, int term, int periods){
        System.out.printf("$ %,8.2f at %3.2f%% for %2d years compounding %d time(s) / year -> $ %,8.2f\n", value, rate, term, periods, calc(value, rate, term, periods));
    }
}
