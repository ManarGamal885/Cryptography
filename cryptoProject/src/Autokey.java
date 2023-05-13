

public class Autokey {
    public String key;
    public String pt;
    public String finalKey;
    public String alphabet = "abcdefghijklmnopqrstuvwxyz";
    public Autokey(String pt, String key) {
        this.key = key;
        this.pt = pt.toLowerCase();
    }
    public void keyGenerator(String key) {
        int neededLength = pt.length()-key.length();
        String finalNeededKey = key;
        for (int i = 0; i < neededLength; i++) {
            finalNeededKey += pt.charAt(i);
        }
        finalKey = finalNeededKey;
        System.out.println(finalKey);
    }

    public String Encrypt(String pt) {
        String ct = "";
        pt = pt.toUpperCase();
        for (int i = 0; i < pt.length(); i++) {
            char ch = finalKey.charAt(i);
            int idxAlpha = alphabet.indexOf(ch);
            char chElement = (char) (((int) pt.charAt(i) + idxAlpha - 65) % 26 + 65);
            ct += chElement;
        }
        return ct;
    }

    public String Decrypt(String ct) {
        String pt = "";
        for (int i = 0; i < ct.length(); i++) {
            char ch = finalKey.charAt(i);
            int idxAlpha = alphabet.indexOf(ch);
            char chElement = (char) (((int) ct.charAt(i) - idxAlpha + 65) % 26 + 65);
            pt += chElement;
        }
        return pt;

    }
}
