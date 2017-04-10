package Lab3;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Николай on 10.04.2017
 */
public class TaskB {

    private static class Graph {
        int ver;
        int rib;
        ArrayList<LinkedList<Integer>> adj;

        Graph(int ver, int rib) {
            this.ver = ver;
            this.rib = rib;
            adj = new ArrayList<>();
            for (int i = 0; i < ver; i++) {
                adj.add(new LinkedList());
            }
        }

        void addRib(int from, int to) {
            adj.get(from).add(to);
        }
    }
    

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("src/Lab3/spantree.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("src/Lab3/spantree.out"));

        String[] str = in.readLine().split("[ ]");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        Graph graph = new Graph(n, m);

        int a,b;
        for (int i = 0; i < m; i++) {
            str = in.readLine().split(" ");
            a = Integer.parseInt(str[0]) - 1;
            b = Integer.parseInt(str[1]) - 1;
            graph.addRib(a, b);
            graph.addRib(b, a);
        }

        for (int i = 0; i < graph.ver; i++) {
            out.write( Integer.toString(graph.adj.get(i).size()) + " ");
        }

        in.close();
        out.close();

    }
}
