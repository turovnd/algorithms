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
        boolean[] visited2;
        int ver;
        int rib;
        ArrayList<LinkedList<Integer>> adj;
        ArrayList<LinkedList<Integer>> adj2;

        LinkedList<Integer> order;
        LinkedList<Integer> comps;
        int compNum;

        public Graph(int ver, int rib) {
            this.ver = ver;
            this.rib = rib;
            this.compNum = 0;
            visited = new boolean[ver];
            visited2 = new boolean[ver];
            order = new LinkedList();
            comps = new LinkedList();

            adj = new ArrayList<>();
            adj2 = new ArrayList<>();
            for (int i = 0; i < ver; i++) {
                adj.add(new LinkedList());
                adj2.add(new LinkedList());
            }
        }

        public void addRib(int from, int to, int q) {
            if (q==1) adj.get(from).add(to);
            else adj2.get(from).add(to);
        }

    }

    private static void dfs (Graph graph, int v) {
        graph.visited[v] = true;
        for (int x : graph.adj.get(v)) {
            if (!graph.visited[x]) {
                dfs(graph, x);
            }
        }
        graph.order.addFirst(v);
    }

    private static void dfs2 (Graph graph, int v, int compNum) {
        graph.visited2[v] = true;
        graph.comps.add(compNum);
        for (int x : graph.adj2.get(v)) {
            if (!graph.visited2[x]) {
                dfs2(graph, x, compNum);
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

        for (int v : graph.order) {
            if (!graph.visited2[v]) {
                graph.compNum++;
                dfs2(graph, v, graph.compNum);
            }
        }

        out.write(graph.compNum + "\n");
        for (int v = 0; v < graph.comps.size(); v++) {
            out.write(graph.comps.get(v) + " ");
        }


        in.close();
        out.close();

    }

}
