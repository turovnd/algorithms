package lab1;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Николай on 10.03.2017.
 */
public class taskB {

    private static boolean is_oriented(int[][] matrix, int n) {
        boolean checked = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j && matrix[i][j] != 0) {
                    checked = false;
                } else if (matrix[i][j] != matrix[j][i]){
                    checked = false;
                }
            }
        }
        return checked;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("src/lab1/input2.txt"));
        BufferedWriter out = new BufferedWriter(new FileWriter("src/lab1/output2.txt"));

        int n = Integer.parseInt(in.readLine());

        int[][] matrix = new int[n][n];

        String str;
        String[] strarr;

        for (int i = 0; i < n; i++) {
            str = in.readLine();
            strarr = str.split("[ ]");
            for (int j = 0;  j < str.split(" ").length; j++) {
                matrix[i][j] = Integer.parseInt(strarr[j]);
            }
        }

        if (is_oriented(matrix,n)) {
            out.write("YES");
        } else {
            out.write("NO");
        }

        in.close();
        out.close();

    }

}
