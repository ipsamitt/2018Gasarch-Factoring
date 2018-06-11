import java.math.BigInteger;
import java.util.ArrayList;

public class Factoring {
    private BigInteger test;

    public Factoring(BigInteger a) {
        test = a;
    }

    public ArrayList<Integer> naive() {
        long startTime = System.nanoTime();
        int count = 0;
        ArrayList<BigInteger> nums = new ArrayList<BigInteger>();
        nums.add(BigInteger.TWO);
        for (BigInteger i = BigInteger.valueOf(3); i.compareTo(BigInteger.valueOf(1000000)) < 0; i = i.add(BigInteger.TWO)) {
            nums.add(i);
        }

        for (BigInteger i = BigInteger.ZERO; i.compareTo(BigInteger.valueOf(nums.size())) < 0; i = i.add(BigInteger.ONE)) {
            for (BigInteger j = i.add(BigInteger.ONE); j.compareTo(BigInteger.valueOf(nums.size())) < 0; j = j.add(BigInteger.ONE)) {
                if (nums.get(j.intValueExact()).mod(nums.get(i.intValueExact())).equals(BigInteger.ZERO)) {
                    nums.remove(j.intValueExact());
                    count++;
                }
            }
            System.out.println(i);
        }
        System.out.println(nums);
        long timeElapsed = System.nanoTime() - startTime;
        System.out.println("Mod was performed " + count + " times.");
        System.out.println("Took " + timeElapsed + " nanoseconds.");
        //1,000,000 mod-421502 and nanoseconds-497973274070

        ArrayList<Integer> factors = new ArrayList<Integer>();
        for (BigInteger i = BigInteger.ZERO; nums.get(i.intValueExact()).compareTo(BigInteger.valueOf((int) Math.sqrt(test.doubleValue()))) < 0; i = i.add(BigInteger.ONE)) {
            if (test.mod(nums.get(i.intValueExact())).equals(BigInteger.ZERO)) {
                factors.add(nums.get(i.intValueExact()).intValueExact());
            }
        }
        return factors;
    }

    public ArrayList<BigInteger> gcdtesting() {
        long startTime = System.nanoTime();
        boolean cont = true;
        BigInteger x = BigInteger.valueOf((int) (test.doubleValue() * Math.random()));
        ArrayList<BigInteger> xvalues = new ArrayList<BigInteger>();
        xvalues.add(x);
        while (cont && xvalues.size() < 1000) {
            x = ((x.multiply(x)).add(BigInteger.ONE)).mod(test);
            xvalues.add(x);
            for (int i = 0; i < xvalues.size(); i++) {
                for (int j = i + 1; j < xvalues.size(); j++) {
                    if (xvalues.get(i).equals(xvalues.get(j))) {
                        cont = false;
                    }
                }
            }
        }
        System.out.println(xvalues);

        ArrayList<BigInteger> gcdt = new ArrayList<BigInteger>();
        int index = -1;
        int index1 = 1;
        int index2 = 2;
        do {
            BigInteger testnum = xvalues.get(index2 - 1).subtract(xvalues.get(index1 - 1)).abs();
            BigInteger temp = test.gcd(testnum);
            gcdt.add(temp);
            index1++;
            index2 = index1 * 2;
            index++;
        } while (index2 < xvalues.size() && gcdt.get(index).equals(BigInteger.ONE));
        System.out.println(gcdt);
        long timeElapsed = System.nanoTime() - startTime;
        System.out.println("Took " + timeElapsed + " nanoseconds.");
        return gcdt;
    }
}


//Runner
import java.util.Scanner;

public class PrimeFactors {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        Factoring test1 = new Factoring(scan.nextBigInteger());
        test1.gcdtesting();

        scan.close();
    }
}