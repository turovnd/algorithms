package Lab2;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Николай on 27.03.2017.
 */
public class TaskC {

    public enum Color {
        WHITE, BLACK1, BLACK2;

        public static Color change(Color color) {
            if (color == BLACK1) {
                return BLACK2;
            } else {
                return BLACK1;
            }
        }

    }


    private static class Graph {
        Color[] color;
        int[] parent;
        int[] open;
        int[] close;
        int ver;
        int rib;
        ArrayList<LinkedList<Integer>> adj;

        boolean isBipartite;

        public Graph(int ver, int rib) {
            this.ver = ver;
            this.rib = rib;
            color = new Color[ver];
            parent = new int[ver];
            open = new int[ver];
            close = new int[ver];
            isBipartite = true;
            adj = new ArrayList<>();
            for (int i = 0; i < ver; i++) {
                adj.add(new LinkedList());
            }
        }

        public void addRib(int from, int to) {
            adj.get(from).add(to);
        }
    }


    private static void DFS (Graph graph) {
        for (int i = 0; i < graph.ver; i++) {
            graph.color[i] = Color.WHITE;
            graph.parent[i] = -1;
        }
        int time = 0;
        Color color = Color.BLACK1;
        for (int i = 0; i < graph.ver; i++) {
            if (graph.color[i] == Color.WHITE) {
                DFS_Visit(graph, i, time, color);
                color = Color.change(color);
            }
        }
    }


    private static void DFS_Visit(Graph graph, int i, int time, Color color) {

        if (!graph.isBipartite) {
            return;
        }

        graph.color[i] = color;
        time++;
        graph.open[i] = time;

        for (Integer ver : graph.adj.get(i)) {
            if (graph.color[ver] == Color.WHITE) {
                graph.parent[ver] = i;
                DFS_Visit(graph, ver, time, Color.change(color));
                if (!graph.isBipartite) {
                    return;
                }
            } else if (graph.color[ver] == color) {
                graph.isBipartite = false;
                return;
            }
        }
        graph.close[i] = time;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("src/Lab2/bipartite.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("src/Lab2/bipartite.out"));

        String[] str = in.readLine().split("[ ]");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        Graph graph = new Graph(n, m);

        int a,b;
        while (m-- > 0) {
            str = in.readLine().split(" ");
            a = Integer.parseInt(str[0]) - 1;
            b = Integer.parseInt(str[1]) - 1;
            graph.addRib(a, b);
            graph.addRib(b, a);
        }

        DFS(graph);

        if (graph.isBipartite) {
            out.write("YES");
        } else {
            out.write("NO");
        }

        in.close();
        out.close();

    }

}
