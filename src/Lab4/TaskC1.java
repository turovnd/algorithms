package Lab4;

import javafx.util.Pair;

import java.io.*;
import java.util.*;

/**
 * Created by Николай on 10.04.2017
 */
public class TaskC1 {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("pathbgep.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("pathbgep.out"));

        StreamTokenizer tokenizer = new StreamTokenizer(in);
        tokenizer.nextToken();
        int n = (int) tokenizer.nval;
        tokenizer.nextToken();
        int m = (int) tokenizer.nval;

        int start = 0;

        ArrayList<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            adj[i] = new ArrayList<Integer>();
        }

        ArrayList<Long>[] weight = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            weight[i] = new ArrayList<Long>();
        }

        int from, to;
        long w;

        for (int i = 0; i < m; i++) {
            tokenizer.nextToken();
            from = (int) tokenizer.nval - 1;
            tokenizer.nextToken();
            to = (int) tokenizer.nval - 1;
            tokenizer.nextToken();
            w = (int) tokenizer.nval;

            adj[from].add(to);
            weight[from].add(w);
        }

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);

        int[] prev = new int[n];
        Arrays.fill(prev, -1);

        boolean used[] = new boolean[n];

        dist[start] = 0;

        for (int iter = 0; iter < n; iter++) {
            int v = -1;
            long distV = Long.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                if (used[i]) {
                    continue;
                }
                if (distV < dist[i]) {
                    continue;
                }
                v = i;
                distV = dist[i];
            }

            for (int i = 0; i < adj[v].size(); i++) {
                int u = adj[v].get(i);
                long weightU = weight[v].get(i);

                if (dist[v] + weightU < dist[u]) {
                    dist[u] = dist[v] + weightU;
                    prev[u] = v;
                }
            }

            used[v] = true;
        }


        for (int i = 0; i < n; i++) {
            out.write(Long.toString(dist[i]) + " ");
        }


        in.close();
        out.close();

    }
}
