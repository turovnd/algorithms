package Lab5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Николай on 09.05.2017.
 **/

public class TaskA {

    private static int min_cap;
    private static int[][] residual_net;
    private static ArrayList<ArrayList<Integer>> graph;
    private static boolean[] visited;


    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("src/Lab5/maxflow.in"));
        PrintWriter out = new PrintWriter("src/Lab5/maxflow.out");

        String[] str = in.readLine().split("[ ]");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        residual_net = new int[n][n];

        int max_cap = 0;
        int from, to, cap;

        for (int i = 0; i < m; i++) {
            str    = in.readLine().split(" ");
            from   = Integer.parseInt(str[0]) - 1;
            to     = Integer.parseInt(str[1]) - 1;
            cap = Integer.parseInt(str[2]);

            graph.get(from).add(to);
            graph.get(to).add(from);
            residual_net[from][to] = cap;
            max_cap = Math.max(max_cap, cap);
        }

        min_cap = 1;

        while(min_cap * 2 <= max_cap)
            min_cap *= 2;

        long flow = 0;

        while(min_cap > 0) {
            // find flow paths
            int r;
            do {
                visited = new boolean[n];
                r = dfs(0, n - 1, 2000000000);
                flow += r;
            } while(r > 0);

            min_cap /= 2;
        };


        out.print(flow);
//        System.out.println(flow);

        out.close();
        in.close();

    }


    private static int dfs(int from, int to, int cur_flow) {
        if(from == to) return cur_flow;
        if(visited[from]) return 0;

        visited[from] = true;

        for(int v : graph.get(from)) {
            if(residual_net[from][v] >= min_cap) {
                int d = dfs(v, to, Math.min(cur_flow, residual_net[from][v]));
                if(d > 0) {
                    residual_net[from][v] -= d;
                    residual_net[v][from] += d;
                    return d;
                }
            }
        }
        return 0;
    }

}
