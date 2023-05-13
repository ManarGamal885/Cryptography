import java.util.ArrayList;

public class Hill {
    public static int keyMatrix[][] = new int[3][3];
    public String key;
    public String pt;
    public String ct;
    public String alphabet = "abcdefghijklmnopqrstuvwxyz";
    public ArrayList<ArrayList<Integer>> ptMatrix = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> ctMatrix = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> ptMatrix1 = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> ctMatrix1 = new ArrayList<>();
    public static int adj[][] = new int[3][3];
    public static int inverseMat[][] = new int[3][3];

    public Hill(String key, String pt) {
        this.pt = pt;
        this.key = key;
    }

    //-----------------------------------------------Helper Methods--------------------------------------------------------------//
    static void getCofactor(int A[][], int temp[][], int p, int q, int n) {
        int i = 0, j = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {

                if (row != p && col != q) {
                    temp[i][j++] = A[row][col];

                    if (j == n - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

    static int determinant(int A[][], int n) {
        int D = 0; // Initialize result
        if (n == 1)
            return A[0][0];
        int[][] temp = new int[3][3];

        int sign = 1;

        for (int f = 0; f < n; f++) {
            getCofactor(A, temp, 0, f, n);
            D += sign * A[0][f] * determinant(temp, n - 1);
            sign = -sign;
        }
        D = D % 26;
        if (D < 0) {
            D += 26;
        }
        return D;
    }

    static void adjoint(int A[][]) {

        int sign = 1;
        int[][] temp = new int[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                getCofactor(A, temp, i, j, 3);

                sign = ((i + j) % 2 == 0) ? 1 : -1;
                int val = ((sign) * (determinant(temp, 3 - 1))) % 26;
                if (val < 0) {
                    val += 26;
                }
                adj[j][i] = val;
            }
        }
    }

    public static int getInverse(int num) {
        int res = 0;
        for (int i = 1; i <= 26; i++) {
            if ((num * i) % 26 == 1) {
                res = i;
                break;
            }
        }
        return res;
    }

    static boolean inverse(int A[][]) {
        int det = determinant(A, 3);
        int minIn = getInverse(det);
        adjoint(A);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int val = minIn * adj[i][j];
                if (val < 0) {
                    val += 26;
                }
                inverseMat[i][j] = val % 26;
            }
        }
        return true;
    }

//-----------------------------------------------Cipher Methods--------------------------------------------------------------//


    //function for convert pt and key to matrix
    public void keyMatrixtoNumbers(String key) {
        key = key.toLowerCase();
        int pointer = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                keyMatrix[i][j] = alphabet.indexOf(key.charAt(pointer));
                pointer++;
            }
        }
    }

    public void ptMatrixNumbers(String pt) {
        pt = pt.toLowerCase();
        for (int i = 0; i < pt.length(); i += 3) {
            ArrayList<Integer> letters3 = new ArrayList<>(3);
            for (int j = i; j < i + 3; j++) {
                int val = alphabet.indexOf(pt.charAt(j));
                letters3.add(val);
            }
            ptMatrix.add(letters3);
        }
    }

    public void ctMatrixNumbers(String ct) {
        ct = ct.toLowerCase();
        for (int i = 0; i < ct.length(); i += 3) {
            ArrayList<Integer> letters3 = new ArrayList<>(3);
            for (int j = i; j < i + 3; j++) {
                int val = alphabet.indexOf(ct.charAt(j));
                letters3.add(val);
            }
            ctMatrix1.add(letters3);
        }
    }

    public  String Encrypt(String pt) {
        String ct = "";
        ptMatrixNumbers(pt);
        keyMatrixtoNumbers(key);
        for (int i = 0; i < ptMatrix.size(); i++) {
            ArrayList<Integer> valMessage = ptMatrix.get(i);
            ArrayList<Integer> valCt = new ArrayList<>(3);
            for (int j = 0; j < 3; j++) {
                int val = 0;
                for (int l = 0; l < 3; l++) {
                    val += valMessage.get(l) * keyMatrix[l][j];
                }
                valCt.add(val % 26);
            }
            ctMatrix.add(valCt);
        }
        for (int i = 0; i < ctMatrix.size(); i++) {
            for (int j = 0; j < ctMatrix.get(i).size(); j++) {
                ct += alphabet.charAt(ctMatrix.get(i).get(j));
            }
        }
        return ct;
    }

    public String Decrypt(String ct) {
        String pt = "";
        ctMatrixNumbers(ct);
        inverse(keyMatrix);
        for (int i = 0; i < ctMatrix1.size(); i++) {
            ArrayList<Integer> valMessage = ctMatrix.get(i);
            ArrayList<Integer> valPt = new ArrayList<>(3);
            for (int j = 0; j < 3; j++) {
                int val = 0;
                for (int l = 0; l < 3; l++) {
                    val += valMessage.get(l) * inverseMat[l][j];
                }
                valPt.add(val % 26);
            }
            ptMatrix1.add(valPt);
        }

        for (int i = 0; i < ptMatrix1.size(); i++) {
            for (int j = 0; j < ptMatrix1.get(i).size(); j++) {
                pt += alphabet.charAt(ptMatrix1.get(i).get(j));
            }
        }
        return pt;
    }

}
