package Lab2;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Николай on 27.03.2017.
 */
public class TaskD1 {

    private static class Graph {
        boolean[] visited;
        int ver;
        int rib;
        ArrayList<LinkedList<Integer>> adj;
        ArrayList<LinkedList<Integer>> adj__T;

        LinkedList<Integer> order;
        LinkedList<Integer> comps;
        int compNum;

        public Graph(int ver, int rib) {
            this.ver = ver;
            this.rib = rib;
            this.compNum = 0;
            visited = new boolean[ver];
            order = new LinkedList();
            comps = new LinkedList();
            adj = new ArrayList<>();
            adj__T = new ArrayList<>();
            for (int i = 0; i < ver; i++) {
                adj.add(new LinkedList());
                adj__T.add(new LinkedList());
            }
        }

        public void addRib(int from, int to, int q) {
            if (q==1) adj.get(from).add(to);
            if (q==2) adj__T.get(from).add(to);
        }

        public void clearVisited () {
            for (int i = 0; i < ver; i++) {
                this.visited[i] = false;
            }
        }

    }

    private static void dfs (Graph graph, int v) {
        graph.visited[v] = true;
        for (int x : graph.adj.get(v)) {
            if (!graph.visited[x]) {
                dfs(graph, x);
            }
        }
        graph.order.add(v);
    }

    private static void dfs__T (Graph graph, int v) {
        graph.visited[v] = true;
        graph.comps.add(v);
        for (int x : graph.adj__T.get(v)) {
            if (!graph.visited[x]) {
                dfs__T(graph, x);
            }
        }
    }


    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("src/Lab2/cond.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("src/Lab2/cond.out"));

        String[] str = in.readLine().split("[ ]");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        Graph graph = new Graph(n, m);

        int a,b;
        for (int i = 0; i < m; i++)  {
            str = in.readLine().split(" ");
            a = Integer.parseInt(str[0]) - 1;
            b = Integer.parseInt(str[1]) - 1;
            graph.addRib(a, b, 1);
            graph.addRib(b, a, 2);
        }

        for (int v = 0; v < n; v++) {
            if (!graph.visited[v]) {
                dfs(graph, v);
            }
        }

        graph.clearVisited();

        graph.compNum++;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            if (!graph.visited[graph.order.get(n - i - 1)]) {
                dfs__T(graph, graph.order.get(n - i - 1));
                for (int j = 0; j < graph.comps.size(); j++) {
                    result[graph.comps.get(j)] = graph.compNum;
                }
                graph.compNum++;
                graph.comps.clear();
            }
        }
        graph.compNum-=1;
        out.write(graph.compNum + "\n");
        for (int v = 0; v < n; v++) {
            out.write(result[v] + " ");
        }

        in.close();
        out.close();

    }

}
