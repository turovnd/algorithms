package Lab4;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by Николай on 10.04.2017
 */
public class TaskA {

    private static class Edge {
        int start;
        int end;
        int weight;

        Edge(int s, int e, int w) {
            this.start  = s;
            this.end    = e;
            this.weight = w;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("src/Lab4/pathmgep.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("src/Lab4/pathmgep.out"));

        StreamTokenizer tokenizer = new StreamTokenizer(in);
        tokenizer.nextToken();
        int n = (int) tokenizer.nval;
        tokenizer.nextToken();
        int start = (int) tokenizer.nval - 1;
        tokenizer.nextToken();
        int end = (int) tokenizer.nval - 1;

        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tokenizer.nextToken();
                matrix[i][j] = (int) tokenizer.nval;
            }
        }

        long[] d = new long[n];
        Arrays.fill(d, Long.MAX_VALUE);
        boolean[] visited = new boolean[n];

        int index = -1, k = 0, i;
        d[start] = 0;

        while (k < n) {
            k++;
            long min = Long.MAX_VALUE;
            for (i=0; i<n; i++) {
                if (!visited[i] && d[i]<=min) {
                    min = d[i];
                    index = i;
                }
            }

            visited[index]=true;

            for (i = 0; i < n; i++) {
                if (!visited[i] && matrix[index][i] != -1 && d[index] != Long.MAX_VALUE && d[index] + matrix[index][i] <= d[i]) {
                    d[i] = d[index] + matrix[index][i];
                }
            }

        }

        if (d[end] != Long.MAX_VALUE) {
            out.write(Long.toString(d[end]));
        } else {
            out.write("-1");
        }

        in.close();
        out.close();

    }
}