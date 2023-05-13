import java.util.ArrayList;

public class Vernam {
    //    plaintext and key should have the same size
    public String key;
    public String pt;
    public String ct;
    public String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public Vernam(String pt, String key) {
        this.pt = pt.toLowerCase();
        this.key = key;
    }


    public String Encrypt(String pt) {
        pt = pt.toLowerCase();
        key = key.toLowerCase();
        String ct ="";
        for (int i = 0; i < pt.length(); i++) {
            int v1 = (pt.charAt(i) - 'a');
            int v2 = (key.charAt(i) - 'a');
            int val = (v1 + v2)%26;
            char val2 = alphabet.charAt(val);
            ct += val2;
        }
        return ct;
    }

    public String Decrypt(String ct) {

        String pt ="";
        for (int i = 0; i < ct.length(); i++) {
            int v1 = (ct.charAt(i) - 'a');
            int v2 = (key.charAt(i) - 'a');
            int val = (v1 - v2);
            if (val<0){
                val+=26;
            }
            char val2 = alphabet.charAt(val);
            pt += val2;
        }
        return pt;
    }
}
