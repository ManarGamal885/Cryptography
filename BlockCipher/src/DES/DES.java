
import java.util.*;

public class DES {

    public static int[] PC_1 = {
        57, 49, 41, 33, 25, 17, 9,
        1, 58, 50, 42, 34, 26, 18,
        10, 2, 59, 51, 43, 35, 27,
        19, 11, 3, 60, 52, 44, 36,
        63, 55, 47, 39, 31, 23, 15,
        7, 62, 54, 46, 38, 30, 22,
        14, 6, 61, 53, 45, 37, 29,
        21, 13, 5, 28, 20, 12, 4};

    public static int[] PC_2 = {
        14, 17, 11, 24, 1, 5,
        3, 28, 15, 6, 21, 10,
        23, 19, 12, 4, 26, 8,
        16, 7, 27, 20, 13, 2,
        41, 52, 31, 37, 47, 55,
        30, 40, 51, 45, 33, 48,
        44, 49, 39, 56, 34, 53,
        46, 42, 50, 36, 29, 32};

    public static int[] Ip = {
        58, 50, 42, 34, 26, 18, 10, 2,
        60, 52, 44, 36, 28, 20, 12, 4,
        62, 54, 46, 38, 30, 22, 14, 6,
        64, 56, 48, 40, 32, 24, 16, 8,
        57, 49, 41, 33, 25, 17, 9, 1,
        59, 51, 43, 35, 27, 19, 11, 3,
        61, 53, 45, 37, 29, 21, 13, 5,
        63, 55, 47, 39, 31, 23, 15, 7};
    public static int IP_1[] = {
        40, 8, 48, 16, 56, 24, 64, 32,
        39, 7, 47, 15, 55, 23, 63, 31,
        38, 6, 46, 14, 54, 22, 62, 30,
        37, 5, 45, 13, 53, 21, 61, 29,
        36, 4, 44, 12, 52, 20, 60, 28,
        35, 3, 43, 11, 51, 19, 59, 27,
        34, 2, 42, 10, 50, 18, 58, 26,
        33, 1, 41, 9, 49, 17, 57, 25};
    public static int P[] = {
        16, 7, 20, 21,
        29, 12, 28, 17,
        1, 15, 23, 26,
        5, 18, 31, 10,
        2, 8, 24, 14,
        32, 27, 3, 9,
        19, 13, 30, 6,
        22, 11, 4, 25

    };
    public static int[] E = {
        32, 1, 2, 3, 4, 5,
        4, 5, 6, 7, 8, 9,
        8, 9, 10, 11, 12, 13,
        12, 13, 14, 15, 16, 17,
        16, 17, 18, 19, 20, 21,
        20, 21, 22, 23, 24, 25,
        24, 25, 26, 27, 28, 29,
        28, 29, 30, 31, 32, 1};
    public static int[][][] S_Boxes = {
        //        1        
        {
            {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
            {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
            {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
            {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
        },
        //        2   
        {
            {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
            {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
            {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
            {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}
        },
        //        3   
        {
            {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
            {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
            {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
            {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}
        },
        //        4   
        {
            {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
            {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
            {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
            {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}
        },
        //        5   
        {
            {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
            {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
            {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
            {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}
        },
        //        6   
        {
            {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
            {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
            {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
            {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}
        },
        //        7   
        {
            {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
            {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
            {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
            {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}
        },
        //        8   
        {
            {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
            {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
            {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
            {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}
        }

    };

    public static void main(String[] args) {
        String keyVal = "133457799BBCDFF1";
        String message = "0123456789ABCDEF";
        String CT = Encryption(keyVal, message);
        System.out.println(CT);
    }

//   -------------------------------first we convert key into ints binary rep-----------------------------
    public static int binaryValInt(int num) {
        int B_Number = 0;
        int cnt = 0;
        while (num != 0) {
            int rem = num % 2;
            double c = Math.pow(10, cnt);
            B_Number += rem * c;
            num /= 2;
            cnt++;
        }

        return B_Number;
    }

    public static String convertBinary(String s) {
        s = s.toUpperCase();
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String BinaryNumbers[] = {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001"};
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                int val = s.charAt(i) - '0';
                res += BinaryNumbers[val];
            } else {
                int idxOfletInAlp = alpha.indexOf(s.charAt(i)) + 10;
                int binaryVal = binaryValInt(idxOfletInAlp);
                String stringVal = Integer.toString(binaryVal);
                res += stringVal;
            }
        }
        return res;
    }

//------------------------------------------convert to pc_1 table -------------------------------------------------
    public static String convertPC_1(String key) {
        String keyDash = "";
        for (int i = 0; i < PC_1.length; i++) {
            keyDash += key.charAt(PC_1[i] - 1);
        }
        return keyDash;
    }

//--------------------------------------------split and generation portion ----------------------------------------
    public static String shiftString(String s, int shift) {
        char[] sVal = s.toCharArray();
        for (int i = 0; i < shift; i++) {
            int j;
            char first = sVal[0];
            for (j = 0; j < s.length() - 1; j++) {
                sVal[j] = sVal[j + 1];
            }
            sVal[j] = first;
        }
        String res = new String(sVal);
        return res;
    }

    public static ArrayList<String> keyVal(String keyDash) {
        ArrayList<String> C = new ArrayList<String>();
        ArrayList<String> D = new ArrayList<String>();
        ArrayList<String> KEY = new ArrayList<String>();
        int noOfLeftShift[] = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};
        String c0 = "";
        String d0 = "";
        int midLen = keyDash.length() / 2;
        for (int i = 0; i < midLen; i++) {
            c0 += keyDash.charAt(i);
        }
        for (int i = midLen; i < keyDash.length(); i++) {
            d0 += keyDash.charAt(i);
        }
        C.add(c0);
        D.add(d0);

        //generating c values 
        for (int i = 0; i < noOfLeftShift.length; i++) {
            c0 = shiftString(c0, noOfLeftShift[i]);
            C.add(c0);
        }
        for (int i = 0; i < noOfLeftShift.length; i++) {
            d0 = shiftString(d0, noOfLeftShift[i]);
            D.add(d0);
        }

        for (int i = 0; i < C.size(); i++) {
            String keyVal = C.get(i) + D.get(i);
            KEY.add(keyVal);
        }
        return KEY;
    }

//-----------------------------------------convert to PC_2-----------------------------------------------------------
    public static ArrayList<String> convertPC_2(ArrayList<String> KEY) {
        ArrayList<String> KEYDASH = new ArrayList<String>();
        for (int i = 0; i < KEY.size(); i++) {
            String keyVal = "";
            for (int j = 0; j < PC_2.length; j++) {
                keyVal += KEY.get(i).charAt(PC_2[j] - 1);
            }
            KEYDASH.add(keyVal);
        }
        return KEYDASH;
    }

//---------------------------------------------------------------------------------------------------------------------
//------------------------------------------------Data Portion---------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------
/*                                    Step 2:Encode each 64-bit block of data                                                   */
//-------------------------------------------Applaying First permutation (IP) -------------------------------------------   
    public static String convertIP(String pT) {
        String ptConverted = "";
        for (int i = 0; i < Ip.length; i++) {
            ptConverted += pT.charAt(Ip[i] - 1);
        }
        return ptConverted;
    }

    public static String convertP(String pT) {
        String ptConverted = "";
        for (int i = 0; i < P.length; i++) {
            ptConverted += pT.charAt(P[i] - 1);
        }
        return ptConverted;
    }
//-----------------------------------------Applaying Expansion permutation (E)-----------------------------------------------------------

    public static String convertE(String s) {

        String rE = "";

        for (int i = 0; i < E.length; i++) {
            rE += s.charAt(E[i] - 1);
        }
        return rE;
    }
//-----------------------------------------Applaying XOR on the 48E and 48K----------------------------------------------------------  

    public static String xorRes(String E, String key) {
        int size = E.length();
        String res = "";
        for (int i = 0; i < size; i++) {
            int val = ((int) E.charAt(i) ^ (int) key.charAt(i));
            char v = (char) (val + '0');
            res += v;
        }
        return res;
    }

    public static int convertTOdecimal(String s) {
        return Integer.parseInt(s, 2);
    }

    public static String convertTObinary(int n) {
        return Integer.toBinaryString(n);
    }

    public static String S_Boxes(String Xor) {
        String finalS_box = "";
        int j = 0, s_Box = 0;
        for (int i = 0; i < Xor.length(); i += 6) {
            String row = "", col = "";
            int rowVal, colVal;
            j = i + 6;
            String sub = Xor.subSequence(i, j).toString();
            row += sub.charAt(0);
            row += sub.charAt(5);
            col = sub.substring(1, 5);
            rowVal = convertTOdecimal(row);
            colVal = convertTOdecimal(col);
            int sBoxVal = S_Boxes[s_Box][rowVal][colVal];
            String binaryVal = "";
            if (sBoxVal <= 9) {
                binaryVal += convertBinary(Integer.toString(sBoxVal));
            } else {
                binaryVal += convertTObinary(sBoxVal);
            }
            finalS_box += binaryVal;
            s_Box++;
        }
        return finalS_box;

    }

    public static String convertIP_1(String s) {
        String ptConverted = "";
        for (int i = 0; i < IP_1.length; i++) {
            ptConverted += s.charAt(IP_1[i] - 1);
        }
        return ptConverted;
    }

    public static String Encryption(String key, String pt) {
        String keyVal = convertBinary(key);
        String message = convertBinary(pt);
        String pc1 = convertPC_1(keyVal);
        ArrayList<String> keys = keyVal(pc1);
        ArrayList<String> pc2 = convertPC_2(keys);
        String R = "";
        String L = "";
        for (int i = 0; i < message.length(); i++) {
            L += message.charAt(i);
        }
        for (int i = (message.length()) / 2; i <= message.length() - 1; i++) {
            R += message.charAt(i);
        }
        String messageIP = convertIP(message);
        String messageE = convertE(R);
        String xOr = xorRes(messageE, pc2.get(0));
        String s_box = S_Boxes(xOr);
        String messp = convertP(R);
        String Rnew = "", Lnew = R;
        for (int i = 0; i < s_box.length(); i++) {
            int val = ((int) L.charAt(i) ^ (int) s_box.charAt(i));
            char v = (char) (val + '0');
            Rnew += v;
        }
        String cipherTxt = convertIP_1(Lnew + Rnew);
        return cipherTxt;
    }
}
