package Lab4;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Николай on 10.04.2017
 */
public class TaskB {

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

        BufferedReader in = new BufferedReader(new FileReader("src/Lab4/pathsg.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("src/Lab4/pathsg.out"));

        StreamTokenizer tokenizer = new StreamTokenizer(in);
        tokenizer.nextToken();
        int n = (int) tokenizer.nval;
        tokenizer.nextToken();
        int m = (int) tokenizer.nval;

        ArrayList<Edge> edges = new ArrayList<>();

        int s, e, w;
        for (int i = 0; i < m; i++) {
            tokenizer.nextToken();
            s = (int) tokenizer.nval - 1;
            tokenizer.nextToken();
            e = (int) tokenizer.nval - 1;
            tokenizer.nextToken();
            w = (int) tokenizer.nval;

            edges.add(new Edge(s, e, w));
        }

        for(int i = 0; i < n; i++) {
            int [] d = new int[n];
            Arrays.fill(d, Integer.MAX_VALUE);

            d[i] = 0;
            boolean flag = true;
            while (flag) {
                flag = false;
                for (Edge edge : edges) {
                    if (d[edge.start] < Integer.MAX_VALUE) {
                        if (d[edge.end] > d[edge.start] + edge.weight) {
                            d[edge.end] = d[edge.start] + edge.weight;
                            flag = true;
                        }
                    }
                }
            }
            for(int q = 0; q < n; q++)
            {
                if (d[q] != Integer.MAX_VALUE)
                    out.write(Integer.toString(d[q]) + " ");
                else
                    out.write("-1 ");
            }
            out.write("\n");
        }

        in.close();
        out.close();

    }
}
