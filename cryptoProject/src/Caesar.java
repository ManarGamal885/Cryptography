

import java.util.*;
public class Caesar {

    public  StringBuffer Encrypt(String pt, int key) {
        StringBuffer ct = new StringBuffer();
        pt = pt.toUpperCase();
        for (int i = 0; i < pt.length(); i++) {
            char chElement = (char) (((int) pt.charAt(i) + key - 65) % 26 + 65);
            ct.append(chElement);
        }
        return ct;
    }

    public  StringBuffer Decrypt(String ct,int key) {
        StringBuffer pt = new StringBuffer();
        for (int i = 0; i < ct.length(); i++) {
            char chElement = (char) (((int) ct.charAt(i) - key - 65) % 26 + 65);
            pt.append(chElement);
        }
        return pt;
    }

}