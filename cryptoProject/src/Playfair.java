

import java.io.*;
import java.util.*;
public class Playfair {
    String key;
    String pText;
    String cText;
    char[][] pMatrix = new char[5][5];
    public String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public Playfair(String key, String plainText) {
        this.key = key.toLowerCase();

        this.pText = plainText.toLowerCase();
    }

    // function to remove duplicate characters from the key
    public void clearPKey() {
        String cKey = "";
        Set<Character> keySet = new LinkedHashSet<Character>();

        for (int i = 0; i < key.length(); i++) {
            keySet.add(key.charAt(i));
        }
        for (Character charcter : keySet) {
            cKey += charcter;
        }
        key = cKey;
    }

    // function to generate playfair cipher key table
    public void generatePMatrix() {
        //we will use set again since no duplicate in the table
        Set<Character> set = new HashSet<Character>();
        for (int i = 0; i < key.length(); i++) {
            if (key.charAt(i) == 'j') {
                continue;
            }
            set.add(key.charAt(i));
        }

        //making the playfair matrix as a string and removing the j char and key chars
        String temKey = key;
        for (int i = 0; i < alphabet.length(); i++) {
            char checkCh = alphabet.charAt(i);
            if (checkCh == 'j') {
                continue;
            }
            if (!set.contains(checkCh)) {
                temKey += checkCh;
            }
        }
        //creating table
        for (int i = 0, idx = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                pMatrix[i][j] = temKey.charAt(idx++);
            }
        }

    }

    //process plainText
    public String plainTextGen() {
        String plainMessage = "";
        //replace j with i
        for (int i = 0; i < pText.length(); i++) {
            if (pText.charAt(i) == 'j') {
                plainMessage += 'i';
            } else {
                plainMessage += pText.charAt(i);
            }
        }
        if (pText.length() % 2 != 0) {
            plainMessage += 'x';
        }
        //handling same 2 chars and odd no.of chars
        for (int i = 0; i < plainMessage.length()-1; i += 2) {
            if (plainMessage.charAt(i) == plainMessage.charAt(i + 1)) {
                plainMessage = plainMessage.substring(0, i + 1) + 'x'
                        + plainMessage.substring(i + 1);
            }
        }

        return plainMessage;
    }

    //collecting every two chars
    public String[] convertTwo(String plainText) {
        int size = plainText.length() / 2;
        String res[] = new String[size];
        for (int i = 0, cnt = 0; i < size; i++) {
            res[i] = plainText.substring(cnt, cnt += 2);
        }
        return res;
    }

    //finding pos in matrix
    public int[] getCharPosition(char ch) {
        int[] keyPos = new int[2];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {

                if (pMatrix[i][j] == ch) {
                    keyPos[0] = i;
                    keyPos[1] = j;
                    break;
                }
            }
        }
        return keyPos;
    }

    public String finalEncText() {
        clearPKey();
        generatePMatrix();
        String message = plainTextGen();
        String[] message2 = convertTwo(message);
        String encMessage = "";
        for (int i = 0; i < message2.length; i++) {
            char ch1 = message2[i].charAt(0);
            char ch2 = message2[i].charAt(1);
            int[] ch1Idx = getCharPosition(ch1);
            int[] ch2Idx = getCharPosition(ch2);
            // if both the characters are in the same row
            if (ch1Idx[0] == ch2Idx[0]) {
                ch1Idx[1] = (ch1Idx[1] + 1) % 5;
                ch2Idx[1] = (ch2Idx[1] + 1) % 5;
            } // if both the characters are in the same column
            else if (ch1Idx[1] == ch2Idx[1]) {
                ch1Idx[0] = (ch1Idx[0] + 1) % 5;
                ch2Idx[0] = (ch2Idx[0] + 1) % 5;
            } // if both the characters are in different rows
            // and columns
            else {
                int temp = ch1Idx[1];
                ch1Idx[1] = ch2Idx[1];
                ch2Idx[1] = temp;
            }
            encMessage = encMessage
                    + pMatrix[ch1Idx[0]][ch1Idx[1]]
                    + pMatrix[ch2Idx[0]][ch2Idx[1]];
        }
        cText = encMessage;
        return encMessage;
    }

    public String finalDecText(String cText) {
        clearPKey();
        generatePMatrix();
        String encMessage = cText;
        String decMessage="";
        String[] message2 = convertTwo(encMessage);
        for (int i = 0; i < message2.length; i++) {
            char ch1 = message2[i].charAt(0);
            char ch2 = message2[i].charAt(1);
            int[] ch1Idx = getCharPosition(ch1);
            int[] ch2Idx = getCharPosition(ch2);
            // if both the characters are in the same row
            if (ch1Idx[0] == ch2Idx[0]) {
                ch1Idx[1] = (ch1Idx[1] - 1) % 5;
                ch2Idx[1] = (ch2Idx[1] - 1) % 5;
                if(ch1Idx[1]<0 ){
                    ch1Idx[1]+=5;
                }
                if (ch2Idx[1]<0){
                    ch2Idx[1]+=5;
                }
            } // if both the characters are in the same column
            else if (ch1Idx[1] == ch2Idx[1]) {
                ch1Idx[0] = (ch1Idx[0] - 1) % 5;
                ch2Idx[0] = (ch2Idx[0] - 1) % 5;
                if (ch1Idx[0]<0){
                    ch1Idx[0]+=5;
                }
                if (ch2Idx[0]<0){
                    ch2Idx[0]+=5;
                }
            } // if both the characters are in different rows
            // and columns
            else {
                int temp = ch1Idx[1];
                ch1Idx[1] = ch2Idx[1];
                ch2Idx[1] = temp;
            }
            decMessage = decMessage
                    + pMatrix[ch1Idx[0]][ch1Idx[1]]
                    + pMatrix[ch2Idx[0]][ch2Idx[1]];
        }
        if(decMessage.charAt(decMessage.length()-1)=='x'){
            decMessage = decMessage.substring(0,decMessage.length()-1);
        }
        return decMessage;
    }
}
