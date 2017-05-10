package Lab5;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;

import static java.util.Objects.hash;

/**
 * Created by Николай on 09.05.2017.
 **/

public class TaskC {

    private static int min_cap;
    private static int[][] residual_net, residual_net1;
    private static ArrayList<ArrayList<Integer>> graph;
    private static boolean[] visited;
    private static ArrayList<ArrayList<Integer>> flows;


    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("src/Lab5/maxflow.in"));
        PrintWriter out = new PrintWriter("src/Lab5/decomposition.out");

        String[] str = in.readLine().split("[ ]");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        Hashtable<Integer, Integer> edges = new Hashtable<>(m);

        graph = new ArrayList<>(n);
        flows = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        residual_net = new int[n][n];
        residual_net1 = new int[n][n];

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
            residual_net1[from][to] = cap;
            max_cap = Math.max(max_cap, cap);

            edges.put(hash(from,to,cap), i+1);
        }

//        System.out.println(edges.get(hash(0,1,1)));

        min_cap = 1;

        while(min_cap * 2 <= max_cap)
            min_cap *= 2;

        long flow = 0;

        while(min_cap > 0) {
            // find flow paths
            int r;
            do {
                flows.add(new ArrayList<>());
                visited = new boolean[n];
                r = dfs(0, n - 1, 2000000000);
                flow += r;
                ind++;
            } while(r > 0);

            min_cap /= 2;
        };


//        out.print(flow);
        System.out.println(flow);
        for (ArrayList<Integer> flow1 : flows) {
            for (int i = 0; i < flow1.size(); i++) {
                System.out.print(edges.get(flow1.get(i)));
            }
            System.out.println();
        }

        out.close();
        in.close();

    }
private static int ind = 0;

    private static int dfs(int from, int to, int cur_flow) {
        if(from == to) return cur_flow;
        if(visited[from]) return 0;

        visited[from] = true;

        for(int v : graph.get(from)) {
            if(residual_net[from][v] >= min_cap) {
                int d = dfs(v, to, Math.min(cur_flow, residual_net[from][v]));
                if(d > 0) {
                    flows.get(ind).add(hash(from,to,residual_net1[from][v]));
                    residual_net[from][v] -= d;
                    residual_net[v][from] += d;
                    return d;
                }
            }
        }
        return 0;
    }

}
