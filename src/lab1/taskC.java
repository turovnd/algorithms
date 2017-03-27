package Lab1;

import java.io.*;

/**
 * Created by Николай on 11.03.2017.
 */
public class taskC {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("src/Lab1/input3.txt"));
        BufferedWriter out = new BufferedWriter(new FileWriter("src/Lab1/output3.txt"));

        String[] str;
        int n, m, a, b;
        boolean flag = false;

        str = in.readLine().split("[ ]");
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);

        int[][] matrix = new int[n][n];

        for (int i = 0; i < m; i++) {
            str = in.readLine().split("[ ]");
            a = Integer.parseInt(str[0]) - 1 ;
            b = Integer.parseInt(str[1]) - 1;

            if ( matrix[a][b] == 1 || matrix[b][a] == 1) {
                flag = true;
                break;
            }

            matrix[a][b] = 1;
            matrix[b][a] = 1;
        }

        if (flag) {
            out.write("YES");
        } else {
            out.write("NO");
        }

        in.close();
        out.close();

    }

}
