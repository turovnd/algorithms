package Lab5;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Николай on 09.05.2017.
 **/

public class TaskB {

    static private int n1;
    static private int n2;
    static private int m;
    static private ArrayList<Integer> adj[];
    static private boolean used[];
    static int mtSize = 0;
    static private int mt[];


    private static boolean kuhn(int v) {
        if (used[v]) return false;
        used[v] = true;

        for (int i = 0; i < adj[v].size(); ++i) {
            int w = adj[v].get(i);
            if (mt[w] == -1 || kuhn(mt[w])) {
                mt[w] = v;
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("src/Lab5/matching.in"));
        PrintWriter out = new PrintWriter("src/Lab5/matching.out");

        String[] str = in.readLine().split("[ ]");
        int n1 = Integer.parseInt(str[0]);
        int n2 = Integer.parseInt(str[1]);
        int m = Integer.parseInt(str[2]);


        adj = new ArrayList[n1];
        for (int i = 0; i < n1; ++i) {
            adj[i] = new ArrayList<>();
        }

        int from, to;
        for (int i = 0; i < m; ++i) {
            str = in.readLine().split("[ ]");
            from = Integer.parseInt(str[0]) - 1;
            to = Integer.parseInt(str[1]) - 1;
            adj[from].add(to);
        }

        used = new boolean[n1];
        Arrays.fill(used, false);
        mt = new int[n2];
        Arrays.fill(mt, -1);

        for (int v = 0; v < n1; ++v) {
            Arrays.fill(used, false);
            if (kuhn(v)) {
                mtSize++;
            }
        }

        out.print(mtSize);
//        System.out.println(mtSize);
//        for (int i = 0; i < n2; ++i) {
//            if (mt[i] != -1) {
//                System.out.println((mt[i] + 1) + " " + (i + 1));
//            }
//        }

        in.close();
        out.close();
    }

}
