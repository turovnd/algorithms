package Lab1;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Николай on 12.03.2017.
 */
public class taskF {

    private static int MAXN = 2147483647;
    private static int n, m;

    private static int[][] matrix;
    private static point start = null, tail = null;

    private static class point {
        int x;
        int y;

        public point(int i, int j) {
            x = i;
            y = j;
        }
    }


    /**
     * Breadth-first search
     * @param v
     */
    private static void bfs (point v) {

        Queue<point> queue = new LinkedList();
        queue.add(v);

        while (!queue.isEmpty())
        {
            v = queue.peek();
            queue.poll();

            // move Up
            if (v.x - 1 >= 0 && matrix[v.x - 1][v.y] == MAXN) {
                queue.add(new point(v.x - 1, v.y));
                matrix[v.x - 1][v.y] = matrix[v.x][v.y] + 1;
            }

            // move Down
            if (v.x + 1 < n && matrix[v.x + 1][v.y] == MAXN)  {
                queue.add(new point(v.x+1,v.y));
                matrix[v.x+1][v.y] = matrix[v.x][v.y] + 1;
            }

            // move Left
            if (v.y-1 >= 0 && matrix[v.x][v.y-1] == MAXN) {
                queue.add(new point(v.x,v.y-1));
                matrix[v.x][v.y-1] = matrix[v.x][v.y] + 1;
            }

            // move Right
            if (v.y + 1 < m && matrix[v.x][v.y+1] == MAXN) {
                queue.add(new point(v.x,v.y + 1));
                matrix[v.x][v.y+1] = matrix[v.x][v.y] + 1;
            }

        }
    }


    /**
     * display graph
     */
    private static void print_graph() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; ++j) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Main Function
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("src/Lab1/inputF.txt"));
        BufferedWriter out = new BufferedWriter(new FileWriter("src/Lab1/outputF.txt"));

        String[] str;

        str = in.readLine().split("[ ]");
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);

        matrix = new int[n][m];

        for (int i = 0; i < n; i++) {
            str = in.readLine().split("");
            for (int j = 0;  j < str.length; j++) {
                if ( str[j].equals(".") ) {
                    matrix[i][j] = MAXN;
                } else if ( str[j].equals("#") ) {
                    matrix[i][j] = -1;
                } else if ( str[j].equals("S") ) {
                    start = new point(i,j);
                    matrix[i][j] = 1;
                } else {
                    tail = new point(i,j);
                    matrix[i][j] = MAXN;
                }
            }
        }

        bfs(start);

        //print_graph();
        String command = "";

        if (matrix[tail.x][tail.y] == MAXN) {
            System.out.print(-1 + "\n");
            out.write(-1 + "\n");
        } else {
            System.out.print(matrix[tail.x][tail.y] - 1 + "\n");
            out.write(matrix[tail.x][tail.y] - 1 + "\n");

            int cur = matrix[tail.x][tail.y];
            point curV = tail;
            while (cur != 1) {
                cur--;
                if (curV.x - 1 >= 0 && matrix[curV.x - 1][curV.y] == cur) {
                    curV.x = curV.x - 1;
                    command = command + "D";
                } else if (curV.x + 1 < n && matrix[curV.x + 1][curV.y] == cur) {
                    curV.x = curV.x + 1;
                    command = command + "U";
                } else if (curV.y - 1 >= 0 && matrix[curV.x][curV.y - 1] == cur) {
                    curV.y = curV.y - 1;
                    command = command + "R";
                } else {
                    curV.y = curV.y + 1;
                    command = command + "L";
                }
            }

            System.out.print(new StringBuilder(command).reverse().toString() + "\n");
            out.write(new StringBuilder(command).reverse().toString() + "\n");
        }

        in.close();
        out.close();

    }
}
