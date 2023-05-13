

public class RailFence {
    public int key;
    public String pt;
    public String ct;

    public RailFence(String pt, int key){
        this.key =key;
        this.pt =pt;
    }

    public String Encrypt(String pt){

        String ct ="";
        pt = pt.toLowerCase();
        int ptlen = pt.length();
        if (ptlen%2!=0){
            pt+='x';
        }
        int count =0;
        int col = ptlen/key, row = key;
        char ctMatrix [][] = new char[row][col];
        for (int i = 0; i<col; i++){
            for (int j = 0; j<row; j++){
                ctMatrix[j][i] = pt.charAt(count);
                count++;
            }
        }
        for(int i=0;i< row;i++)
        {
            for(int j=0;j< col;j++)
            {
                ct+=ctMatrix[i][j];
            }
        }

        return ct;
    }
    public String Decrypt(String ct){
        String pt ="";
        ct = ct.toLowerCase();
        int ctlen = ct.length();
        int count =0;
        int col = ctlen/key, row = key;
        char ptMatrix [][] = new char[row][col];
        for (int i = 0; i<row; i++){
            for (int j = 0; j<col; j++){
                ptMatrix[i][j] = ct.charAt(count);
                count++;
            }
        }
        for(int i=0;i< col;i++)
        {
            for(int j=0;j< row;j++)
            {
                pt+=ptMatrix[j][i];
            }
        }
        return pt;
    }
}
