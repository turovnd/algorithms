package Lab3;

import java.io.*;

import static java.lang.Math.sqrt;

/**
 * Created by Николай on 10.04.2017
 */
public class TaskB {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("src/Lab3/spantree.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("src/Lab3/spantree.out"));

        String[] str;
        Double res = 0.0;
        int MAXN = 2147483647;

        int n = Integer.parseInt(in.readLine());

        int[][] array_in = new int[n][2];
        int[][] graph = new int[n][n];
        boolean[] used = new boolean[n];
        int[] min_for = new int[n];
        int[] min_lead = new int[n];



        for (int i = 0; i < n; i++) {
            str = in.readLine().split(" ");
            array_in[i][0] = Integer.parseInt(str[0]);
            array_in[i][1] = Integer.parseInt(str[1]);

            for(int j = 0; j < i; j++) {
                graph[i][j] = (array_in[j][0]-array_in[i][0])*(array_in[j][0]-array_in[i][0]) + (array_in[j][1]-array_in[i][1])*(array_in[j][1]-array_in[i][1]);
                graph[j][i] = graph[i][j];
            }
            used[i] = false;
            min_for[i] = MAXN;
            min_lead[i] = -1;
        }

        min_for[0] = 0;
        
        for (int i = 0; i < n; i++) {

            int ver = -1;

            for (int j = 0; j < n; j++) {
                if ( (!used[j]) && ((ver == -1) || (min_for[j] < min_for[ver])) ) {
                    ver = j;
                }
            }

            used[ver] = true;

            if (min_lead[ver] != -1) {
                res += sqrt((double)(graph[ver][min_lead[ver]]));
            }

            for (int j = 0; j < n; j++) {
                if (graph[ver][j] < min_for[j]) {
                    min_for[j] = graph[ver][j];
                    min_lead[j] = ver;
                }
            }

        }

        //System.out.println(String.format("%.15f",res));
        out.write(String.format("%.15f",res));

        in.close();
        out.close();

    }
}
