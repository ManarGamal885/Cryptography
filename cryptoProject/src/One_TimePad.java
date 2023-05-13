

import java.util.Random;

public class One_TimePad {
    public String pt;
    public String ct;
    public String alphabet = "abcdefghijklmnopqrstuvwxyz";


    public String RanadomKeyGenerator(int l) {
        String key = "";
        Random random = new Random();
        for (int i = 0; i < l; i++) {
            key += (char) (random.nextInt(26) + 'a');
        }
        return key;
    }

    public String Encrypt(String pt, String key) {
        String ct = "";
        pt = pt.toLowerCase();
        for (int i = 0; i < pt.length(); i++) {
            char valpt = pt.charAt(i);
            char valkey = key.charAt(i);
            int idx = alphabet.indexOf(valpt);
            int idxKey = alphabet.indexOf(valkey);
            int finalVal = (idx + idxKey) % 26;
            ct += alphabet.charAt(finalVal);
        }
        return ct;
    }

    public String Decrypt(String ct, String key) {
        String pt = "";
        for (int i = 0; i < ct.length(); i++) {
            char valct = ct.charAt(i);
            char valkey = key.charAt(i);
            int idx = alphabet.indexOf(valct);
            int idxKey = alphabet.indexOf(valkey);
            int finalVal = (idx - idxKey) % 26;
            if (finalVal < 0) {
                finalVal += 26;
            }
            pt += alphabet.charAt(finalVal);
        }
        return pt;
    }
}
