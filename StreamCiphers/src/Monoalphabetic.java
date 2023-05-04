import java.util.*;

public class Monoalphabetic {
    public static String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public  StringBuffer Encryption(String pt, String key) {
        pt = pt.toLowerCase();
        StringBuffer ct = new StringBuffer();
        for (int i = 0; i < pt.length(); i++) {
            for (int j = 0; j < 26; j++) {
                if (pt.charAt(i) == alphabet.charAt(j)) {
                    ct.append(key.charAt(j));
                    break;
                }
                if (!Character.isLetter(pt.charAt(i))) {
                    ct.append(pt.charAt(i));
                    break;
                }
            }
        }
        return ct;
    }

    public  StringBuffer Decryption(String ct, String key) {
        StringBuffer pt = new StringBuffer();

        for (int i = 0; i < ct.length(); i++) {
            for (int j = 0; j < 26; j++) {
                if (ct.charAt(i) == key.charAt(j)) {
                    pt.append(alphabet.charAt(j));
                    break;
                }
                if (!Character.isLetter(ct.charAt(i))) {
                    pt.append(ct.charAt(i));
                    break;
                }
            }
        }
        return pt;
    }
}
