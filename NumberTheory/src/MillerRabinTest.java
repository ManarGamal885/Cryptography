

import java.util.*;
import java.math.BigInteger;
import java.math.BigDecimal;

public class MillerRabinTest {
    public static BigInteger Two = BigInteger.TWO;
    public static BigInteger One = BigInteger.ONE;

    public static BigInteger Zero = BigInteger.ZERO;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        BigInteger n = BigInteger.valueOf(5987);
        System.out.println(isPrime(n));
    }

    public static boolean isPrime(BigInteger n) {
        if (n.mod(Two).equals(Zero)) {
            return false;
        }
        if (n.compareTo(One) <= 0) {
            return false;
        }
        if (n.compareTo(Two) == 0) {
            return true;
        }

        BigInteger num = n.subtract(BigInteger.ONE);
        BigInteger val = num.divide(BigInteger.valueOf(2));
        int k = 0;
        int i = 2;
        BigInteger Two = BigInteger.TWO;
        BigInteger Zero = BigInteger.ZERO;
        if (n.mod(Two) == Zero && !n.equals(2)) {
            return false;
        }

        while (val.mod(Two) == Zero) {
            BigInteger valPower = Two.pow(i);
            val = num.divide(valPower);
            i++;
        }
        k = i - 1;
        for (int j = 0; j < 100; j++) {
            BigInteger a = randomBase(n);
            BigInteger T = a.modPow(num, n);
//            using fermat's little theorm a^p-1 cong 1 mod p
//                                         a^p-1 cong p-1 mod p
            if (T.equals(BigInteger.ONE)|| T.equals(num)) {
                return true;
            }
            for (int l = 1; l < k; l++) {
                T = T.modPow(Two, n);
                if (T.compareTo(One) == 0 || T.equals(BigInteger.valueOf(-1))) {
                    return false;
                }
                if (T.compareTo(BigInteger.valueOf(-1)) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private static BigInteger randomBase(BigInteger n) {
        Random rnd = new Random();
        BigInteger result;
        for (; ; ) {
            result = new BigInteger(n.bitLength(), rnd);
            if (result.compareTo(n) < 0 && result.compareTo(BigInteger.ONE) > 0) {
                break;
            }
        }
        return result;
    }
}
