import TranspositionCiphers.RailFence;
import TranspositionCiphers.RowTransposition;

import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        /*----------------------------------------------------------Caesar Cipher-------------------------------------------------------*/

//        Caesar caesar = new Caesar();
//        String ct = caesar.Encrypt("Manar",4).toString();
//        String pt = caesar.Decrypt(ct,4).toString();
//        System.out.println(ct);
//        System.out.println(pt);

        /*----------------------------------------------------------Monoalphabetic Cipher-----------------------------------------------*/

//        Monoalphabetic monoalphabetic = new Monoalphabetic();
//        String ct = monoalphabetic.Encryption("Manar", "DKVQFIBJWPESCXHTMYAUOLRGZN").toString();
//        String pt = monoalphabetic.Decryption(ct, "DKVQFIBJWPESCXHTMYAUOLRGZN").toString();
//        System.out.println(ct);
//        System.out.println(pt);

        /*----------------------------------------------------------Playfair Cipher-----------------------------------------------------*/

//        Playfair playfair = new Playfair("Problem", "Hello");
//        playfair.clearPKey();
//        playfair.generatePMatrix();
//        String ct = playfair.finalEncText();
//        String pt = playfair.finalDecText();
//        System.out.println(ct+"   "+pt);

        /*----------------------------------------------------------Vigenère Cipher-----------------------------------------------------*/

//        Vigenère vigenère = new Vigenère("wearediscoveredsaveyourself","deceptive");
//        vigenère.keyGenerator("deceptive");
//        String ct = vigenère.Encrypt("wearediscoveredsaveyourself");
//        String pt = vigenère.Decrypt("ZICVTWQNGRZGVTWAVZHCQYGLMGJ");
//        System.out.println(ct);
//        System.out.println(pt);

        /*----------------------------------------------------------Autokey Cipher-------------------------------------------------------*/

//        Autokey autokey = new Autokey("wearediscoveredsaveyourself","deceptive");
//        autokey.keyGenerator("deceptive");
//        String ct = autokey.Encrypt("wearediscoveredsaveyourself");
//        String pt = autokey.Decrypt(ct);
//        System.out.println(ct);
//        System.out.println(pt);

        /*----------------------------------------------------------Vernam Cipher-------------------------------------------------------*/

//        Vernam vernam = new Vernam("Manar", "MONEY");
//        String ct = vernam.Encrypt("Manar");
//        String pt = vernam.Decrypt(ct);
//        System.out.println(ct);
//        System.out.println(pt);

        /*----------------------------------------------------------One-Time Pad-------------------------------------------------------*/

//        One_TimePad oneTimePad = new One_TimePad();
//        String txt = "Manar";
//        String key = oneTimePad.RanadomKeyGenerator(txt.length());
//        String ct = oneTimePad.Encrypt(txt,key);
//        String pt = oneTimePad.Decrypt(ct,key);
//        System.out.println(ct+"  "+pt);

        /*----------------------------------------------------------Hill Cipher--------------------------------------------------------*/
//        Hill hill = new Hill("rrfvsvcct","paymoremoney");
//        System.out.println(hill.Encrypt("paymoremoney"));
//        System.out.println(hill.Decrypt("rrlmwbkaspdh"));

        /*-------------------------------------------------------Rail Fence cipher-------------------------------------------------------*/

//        RailFence railFence = new RailFence("meetmeafterthetogaparty",2);
//        System.out.println(railFence.Encrypt("meetmeafterthetogaparty"));
//        System.out.println(railFence.Decrypt("mematrhtgpretefeteoaat"));

        /*-------------------------------------------------------Row Transposition Ciphers-----------------------------------------------*/
//        RowTransposition rowTransposition = new RowTransposition("attackpostponeduntiltwoamxyz", "4312567");
//        rowTransposition.convertptTOmatrix();
//        System.out.println(rowTransposition.Encrypt("attackpostponeduntiltwoamxyz"));
//        rowTransposition.convertctTOmatrix("ttnaaptmtsuoaodwcoixknlypetz");
//        System.out.println(rowTransposition.Decrypt("ttnaaptmtsuoaodwcoixknlypetz"));
    }


}