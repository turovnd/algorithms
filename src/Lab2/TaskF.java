package Lab2;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Николай on 27.03.2017.
 */
public class TaskF {

    public enum Color { WHITE, GREY, BLACK }


    private static class Graph {
        Color[] color;
        int[] parent;
        int[] open;
        int[] close;
        boolean[] win;
        int ver;
        int rib;
        ArrayList<LinkedList<Integer>> adj;

        boolean isCycled;

        public Graph(int ver, int rib) {
            this.ver = ver;
            this.rib = rib;
            color = new Color[ver];
            parent = new int[ver];
            open = new int[ver];
            close = new int[ver];
            win = new boolean[ver];
            isCycled = false;
            adj = new ArrayList<>();
            for (int i = 0; i < ver; i++) {
                adj.add(new LinkedList());
            }
        }

        public void addRib(int from, int to) {
            adj.get(from).add(to);
        }

    }


    private static void DFS (Graph graph, int start) {
        for (int i = 0; i < graph.ver; i++) {
            graph.color[i] = Color.WHITE;
            graph.parent[i] = -1;
        }
        int time = 0;
        DFS_Visit(graph, start, time);
    }



    private static void DFS_Visit(Graph graph, int i, int time) {

        if (graph.isCycled) {
            return;
        }

        graph.color[i] = Color.GREY;
        time++;
        graph.open[i] = time;

        if (graph.adj.get(i).isEmpty()) {
            graph.win[i] = false;
        }

        for (Integer ver : graph.adj.get(i)) {
            if (graph.color[ver] == Color.WHITE) {
                graph.parent[ver] = i;
                DFS_Visit(graph, ver, time);
            }
        }

        graph.win[i] = false;

        for (Integer ver : graph.adj.get(i)) {
            graph.win[i] = graph.win[i] || !graph.win[ver];
        }

        graph.color[i] = Color.BLACK;
        graph.close[i] = time;

    }


    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("src/Lab2/game.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("src/Lab2/game.out"));

        String[] str = in.readLine().split("[ ]");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        int start = Integer.parseInt(str[2]);

        Graph graph = new Graph(n, m);

        int a,b;
        while (m-- > 0) {
            str = in.readLine().split(" ");
            a = Integer.parseInt(str[0]) - 1;
            b = Integer.parseInt(str[1]) - 1;
            graph.addRib(a, b);
        }

        DFS(graph, start-1);

        if (graph.win[start-1]) {
            out.write("First player wins");
        } else {
            out.write("Second player wins");
        }
        in.close();
        out.close();

    }

}
