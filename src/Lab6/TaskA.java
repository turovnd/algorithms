package Lab6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TaskA {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader("src/Lab6/search1.in"));
        PrintWriter out = new PrintWriter("src/Lab6/search1.out");

        char[] S = in.readLine().toCharArray();
        char[] T = in.readLine().toCharArray();

        int S_len = S.length;
        int T_len = T.length;

        int p = 55;
        long[] p_pow = new long[Math.max(S_len, T_len)];
        p_pow[0] = 1;

        for (int i = 1; i < p_pow.length; i++)
            p_pow[i] = p_pow[i - 1] * p;

        long[] h = new long[T_len];

        for (int i = 0; i < T_len; i++) {
            h[i] = (T[i] - 'a' + 1) * p_pow[i];
            if (i > 0)
                h[i] += h[i-1];
        }

        long h_s = 0;
        for (int i = 0; i < S_len; i++) {
            h_s += (S[i] - 'a' + 1) * p_pow[i];
        }

        ArrayList<Integer> res = new ArrayList<>();

        for (int i = 0; i + S_len-1 < T.length; i++) {
            long cur_h = h[i+S_len-1];
            if (i > 0)
                cur_h -= h[i-1];

            if (cur_h == h_s * p_pow[i]) {
                res.add(i+1);
            }
        }

        out.print(res.size() + "\n");
        for(int index : res) {
            out.print(index + " ");
        }

//        System.out.println(res.size());
//        for(int index : res) {
//            System.out.print(index + " ");
//        }

        out.close();
        in.close();

    }

}