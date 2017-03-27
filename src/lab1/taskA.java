package Lab1;

import java.io.*;

/**
 * Created by Николай on 10.03.2017.
 */
public class taskA {

    public static void main(String [ ] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("src/Lab1/input.txt"));
        BufferedWriter out = new BufferedWriter(new FileWriter("src/Lab1/output.txt"));

        StreamTokenizer tokenizer = new StreamTokenizer(in);

        tokenizer.nextToken();
        int n = (int) tokenizer.nval;
        tokenizer.nextToken();
        int m = (int) tokenizer.nval;

        int[][] adjacency_matrix = new int[n][n];


        int i, j;

        for(int q = 0; q < m; q++) {
            tokenizer.nextToken();
            i = (int) tokenizer.nval - 1;
            tokenizer.nextToken();
            j = (int) tokenizer.nval - 1;
            adjacency_matrix[i][j] = 1;
        }

        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                out.write(Integer.toString(adjacency_matrix[i][j]) + " ");
            }
            out.write('\n');
        }

        in.close();
        out.close();

    }

}
