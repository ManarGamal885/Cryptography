package TranspositionCiphers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RowTransposition {
    public String key;
    public String pt;
    public String ct;
    public char ctMatrix[][];
    public char ptMatrix[][];

    public RowTransposition(String pt, String key) {
        this.pt = pt;
        this.key = key;
    }

    public void convertptTOmatrix() {
        int col = key.length();
        int row = pt.length() / col;
        ctMatrix = new char[row][col];
        int counter = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                ctMatrix[i][j] = pt.charAt(counter);
                counter++;
            }
        }
    }

    public void convertctTOmatrix(String ct) {
        int col = key.length();
        int row = pt.length() / col;

        String pt = "";
        ptMatrix = new char[row][col];
        int counter = 0;
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                ptMatrix[j][i] = ct.charAt(counter);
                counter++;
            }
        }
    }

    public String Encrypt(String pt) {
        int col = key.length();
        int row = pt.length() / col;
        String ct = "";
        String ctFinal[] = new String[key.length()];
        for (int i = 0; i < key.length(); i++) {
            String valPush = "";
            for (int j = 0; j < row; j++) {
                valPush += ctMatrix[j][i];
            }
            ctFinal[i] = valPush;
        }
        Map map = new HashMap<>(key.length());
        for (int i = 0; i < key.length(); i++) {
            int colPrint = Character.getNumericValue(key.charAt(i)) - 1;
            map.put(colPrint, i);
        }
        for (int i = 0; i < map.size(); i++) {
            int val = Integer.valueOf((int) map.get(i));
            ct += ctFinal[val];
        }
        return ct;
    }

    public String Decrypt(String ct) {
        String pt ="";
        int col = key.length();
        int row = ct.length() / col;
        int row1 = col;
        int col1 = row;
        String ptFinal[] = new String[key.length()];
        for (int i = 0; i < key.length(); i++) {
            String valPush = "";
            for (int j = 0; j < row; j++) {
                valPush += ctMatrix[j][i];
            }
            ptFinal[i] = valPush;
        }
        char ptFinal2[][] = new char[row1][col1];
        for (int i = 0; i < col1; i++) {
            for (int j = 0; j < row1; j++) {
                ptFinal2[j][i] = ptFinal[j].charAt(i);
            }
        }
        for (int i = 0; i < col1; i++) {
            for (int j = 0; j < row1; j++) {
               pt += ptFinal2[j][i];
            }
        }
        return pt;
    }
}
