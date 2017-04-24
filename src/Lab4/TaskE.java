package Lab4;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Николай on 10.04.2017
 */
public class TaskE {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("src/Lab4/negcycle.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("src/Lab4/negcycle.out"));

        StreamTokenizer tokenizer = new StreamTokenizer(in);
        tokenizer.nextToken();
        int n = (int) tokenizer.nval;

        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tokenizer.nextToken();
                matrix[i][j] = (int) tokenizer.nval;
            }
        }

        int [] dist = new int[n];
        int [] parent = new int[n];
        Arrays.fill(dist, 1000000000);
        Arrays.fill(parent, -1);

        dist[0] = 0;
        int start = -1;

        for (int k = 0; k < n; k++) {
            start = -1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[j] > dist[i] + matrix[i][j]) {
                        parent[j] = i;
                        dist[j] = dist[i] + matrix[i][j];
                        start = j;
                    }
                }
            }
        }


        if (start == -1) {
            out.write("NO");
        } else {
            int finish = start;

            for (int i = 0; i < n; i++) {
                finish = parent[finish];
            }

            ArrayList<Integer> path = new ArrayList<>();

            for (int j = finish; ; j = parent[j]) {
                path.add(j);
                if (j == finish && path.size() > 1) break;
            }

            out.write("YES\n");
            out.write(path.size() + "\n");
            for (int i = path.size() - 1; i >= 0; i--) {
                out.write((path.get(i) + 1) + " ");
            }
        }


        in.close();
        out.close();

    }
}
