package Lab6;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Николай on 10.05.2017.
 **/

public class TaskC {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("src/Lab6/prefix.in"));
        PrintWriter out = new PrintWriter("src/Lab6/prefix.out");

        char[] P = in.readLine().toCharArray();

        int[] p = new int[P.length];
        p[0] = 0;
        int k;

        out.print(p[0] + " ");
//        System.out.print(p[0] + " ");

        for (int i = 1; i < P.length; i++) {
            k = p[i - 1];
            while (k > 0 && P[i] != P[k])
                k = p[k - 1];
            if (P[i] == P[k])
                k++;
            p[i] = k;

            out.print(p[i] + " ");
//            System.out.print(p[i] + " ");
        }

        out.close();
        in.close();

    }

}
